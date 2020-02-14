package ling.sport.originalSources;

import com.sun.jmx.remote.internal.ArrayQueue;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


public class SerialPorts {
    //串口队列 用来保存所有的串口，每次使用一个就弹出一个
    private static ArrayDeque<String> serialPortQueuel = new ArrayDeque<String>();

    //获得当前所有可用串口
    private static Enumeration<CommPortIdentifier> portList;
    //用来放置所有打开的串口
    private static Map<String,SerialPort> portsMap = new HashMap<>();
    private static ArrayDeque<SerialPort> ports = new ArrayDeque<>();
    /**
     * 返回值作为是否有串口的判断
     * @return false：表示没有串口可用  true:表示有串口
     */
    private static boolean init(){
        portList = CommPortIdentifier.getPortIdentifiers();
        String temp = "";
        while (portList.hasMoreElements()){
            temp = portList.nextElement().getName();
            serialPortQueuel.push(temp);
        }
        //
        return serialPortQueuel.size() != 0;
    }

    private static SerialPort openSerialPort(String portName,int baudrate){
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            CommPort commTemp = portIdentifier.open(portName,baudrate);
            if(commTemp instanceof SerialPort){
                SerialPort serialPort = (SerialPort) commTemp;
                serialPort.setSerialPortParams(baudrate,SerialPort.DATABITS_8,SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                ports.push(serialPort);
                portsMap.put(serialPort.getName(),serialPort);
                return serialPort;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void closeSerialPort(){
        if(ports.size() > 0){
            for (int i = 0; i < ports.size(); i++) {
                //从队列中弹出，并关闭
                SerialPort temp = ports.pop();
                temp.close();
                //从集合中删除，因为关闭了
                portsMap.remove(temp.getName());

            }
        }
    }


    public static void main(String[] args) {
        boolean result = init();
        if(result){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String portName = serialPortQueuel.pop();
                        SerialPort serialPort = openSerialPort(portName,115200);
                        String sendMsg = portName+":message";
                        if(serialPort != null){
                            try {
                                InputStream inputStream = new BufferedInputStream(serialPort.getInputStream(),1024);
                                OutputStream outputStream = serialPort.getOutputStream();
                                serialPort.addEventListener(new SerialPortListener(inputStream,outputStream));
                                serialPort.notifyOnDataAvailable(true);
                                serialPort.notifyOnBreakInterrupt(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            sendData(serialPort,sendMsg.getBytes());
                        }else{
                            System.out.println("没有打开串口");
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();




        }


    }


    private static void sendData(SerialPort serialPort, byte[] order)  {

        OutputStream outputStream = null;

        try {

            outputStream = serialPort.getOutputStream();
            outputStream.write(order);
            outputStream.flush();

        } catch (IOException e) {
            DebugPrint.DPrint(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                    outputStream = null;
                }
            } catch (IOException e) {
                DebugPrint.DPrint(e);
            }
        }

    }


}
