package ling.sport.originalSources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.TooManyListenersException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class SerialPortThread extends Thread implements SerialPortEventListener { // SerialPortEventListener

    static CommPortIdentifier portId; //
    static Enumeration<?> portList; //
    InputStream inputStream; //
    static OutputStream outputStream;//
    static SerialPort serialPort; //

    private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();

    @Override
    /**
     * SerialPort EventListener
     */
    public void serialEvent(SerialPortEvent event) {//
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[20];
                try {
                    int numBytes = -1;
                    while (inputStream.available() > 0) {
                        numBytes = inputStream.read(readBuffer);//读取的字节放在缓冲数组readBuffer中，返回实际读取的字节数
                        if (numBytes > 0) {
                            readBuffer = new byte[20];//清空
                        } else {
                            msgQueue.add("��------û�ж�������");
                        }
                    }
                } catch (IOException e) {
                    DebugPrint.DPrint(e);
                }
                break;
        }
    }
    public int startComPort() {
        portList = CommPortIdentifier.getPortIdentifiers();  //获取当前可用的串口
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            DebugPrint.DPrint("portId port type --->" + portId.getPortType());
            DebugPrint.DPrint("portId port name ---->" + portId.getName());
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals("COM4")) {
                    try {
                        serialPort = (SerialPort) portId.open("COM4", 2000);
                    } catch (PortInUseException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    try {
                        inputStream = serialPort.getInputStream();
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    try {
                        serialPort.addEventListener(this);
                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    serialPort.notifyOnDataAvailable(true);
                    try {
                        serialPort.setSerialPortParams(256000, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();
                        return 0;
                    }
                    return 1;
                }
            }
        }
        return 0;
    }

    public void sendCom() {
        try {
            String st = "123";
            DebugPrint.DPrint("send data length:" + st.getBytes().length);
            outputStream.write(st.getBytes(), 0, st.getBytes().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
	        

