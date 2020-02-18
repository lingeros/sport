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

    //没有串口错误
    private static final String NOT_PORT_ERROR = "not port error";

    //获得当前所有可用串口
    private static Enumeration<CommPortIdentifier> portList;
    //用来放置所有打开的串口
    private static Map<String, SerialPort> portsMap = new HashMap<>();
    private static ArrayDeque<SerialPort> ports = new ArrayDeque<>();

    /**
     * 返回值作为是否有串口的判断
     *
     * @return false：表示没有串口可用  true:表示有串口
     */
    private static boolean init() {
        portList = CommPortIdentifier.getPortIdentifiers();
        String temp = "";
        while (portList.hasMoreElements()) {
            temp = portList.nextElement().getName();
            DebugPrint.DPrint("find a serial port :" + temp);
            serialPortQueuel.push(temp);
        }
        //
        return serialPortQueuel.size() != 0;
    }

    private static SerialPort openSerialPort(String portName, int baudrate) {
        try {
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            CommPort commTemp = portIdentifier.open(portName, baudrate);
            if (commTemp instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commTemp;
                serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                ports.push(serialPort);
                portsMap.put(serialPort.getName(), serialPort);
                return serialPort;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void closeSerialPort() {
        if (ports.size() > 0) {
            for (int i = 0; i < ports.size(); i++) {
                //从队列中弹出，并关闭
                SerialPort temp = ports.pop();
                temp.close();
                //从集合中删除，因为关闭了
                portsMap.remove(temp.getName());

            }
        }
    }

    /**
     * 给外界提供一个方法用来启动4 条线程
     */
    public static void startThread(){
        boolean result = init();
        if (result) {
            try {
                SerialPortThread serialPortThread = new SerialPortThread();
                SerialPortThread.serialPortThreadQueuel = serialPortQueuel;
                Thread portThread1 = new Thread(serialPortThread);
                portThread1.setName("串口线程1");
                Thread portThread2 = new Thread(serialPortThread);
                portThread2.setName("串口线程2");
                Thread portThread3 = new Thread(serialPortThread);
                portThread3.setName("串口线程3");
                Thread portThread4 = new Thread(serialPortThread);
                portThread4.setName("串口线程4");
                portThread1.start();
                Thread.sleep(100);//令当前线程暂停100毫秒，以避开线程冲突
                portThread2.start();
                Thread.sleep(100);//令当前线程暂停100毫秒，以避开线程冲突
                portThread3.start();
                Thread.sleep(100);//令当前线程暂停100毫秒，以避开线程冲突
                portThread4.start();
                Thread.sleep(100);//令当前线程暂停100毫秒，以避开线程冲突
            } catch (Exception e) {
                DebugPrint.DPrint(e);
            }
        } else {
            DebugPrint.DPrint(NOT_PORT_ERROR);
        }
    }



    private static void sendData(SerialPort serialPort, byte[] order) {
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

    static class SerialPortThread implements Runnable {
        private static ArrayDeque<String> serialPortThreadQueuel = new ArrayDeque<String>();

        @Override
        public void run() {
            DebugPrint.DPrint("new Thread start:" + Thread.currentThread().getId());
            String portName = SerialPortThread.serialPortThreadQueuel.pop();
            SerialPort serialPort = openSerialPort(portName, 115200);
            String sendMsg = portName + ":message";
            if (serialPort != null) {
                try {
                    DebugPrint.DPrint("open serial port is :" + portName);
                    InputStream inputStream = new BufferedInputStream(serialPort.getInputStream(), 1024);
                    OutputStream outputStream = serialPort.getOutputStream();
                    serialPort.addEventListener(new SerialPortListener(inputStream, outputStream, serialPort));
                    serialPort.notifyOnDataAvailable(true);
                    serialPort.notifyOnBreakInterrupt(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sendData(serialPort, sendMsg.getBytes());
            } else {
                System.out.println("没有打开串口");
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
