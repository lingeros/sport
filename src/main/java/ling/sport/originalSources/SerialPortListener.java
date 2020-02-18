package ling.sport.originalSources;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import ling.sport.entity.SerialPortData;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class SerialPortListener implements SerialPortEventListener {
    private final String TAG = "SerialPortListener";
    //串口传进来的
    private InputStream inputStream;
    private OutputStream outputStream;
    //保存当前的串口对象
    private SerialPort currentSerialPort;
    public SerialPortListener() {
    }

    public SerialPortListener(InputStream inputStream, OutputStream outputStream,SerialPort serialPort) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
        this.currentSerialPort = serialPort;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.BI:
                DebugPrint.DPrint("串口通讯中断");
                break;
            case SerialPortEvent.OE:
                DebugPrint.DPrint("溢出错误");

            case SerialPortEvent.FE:
                DebugPrint.DPrint("帧错误");

            case SerialPortEvent.PE:
                DebugPrint.DPrint("奇偶校验错误");

            case SerialPortEvent.CD:
                DebugPrint.DPrint("载波检测");

            case SerialPortEvent.CTS:
                DebugPrint.DPrint("清除待发送数据");

            case SerialPortEvent.DSR:
                DebugPrint.DPrint("待发送数据准备好了");

            case SerialPortEvent.RI:
                DebugPrint.DPrint("振铃指示");

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                DebugPrint.DPrint("输出缓冲区已清空");
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                DebugPrint.DPrint("串口存在可用数据");
                //接收数据
                receiveData();
                break;
        }
    }

    private void receiveData(){
        byte[] readBuffer = new byte[35];
        try{
            int read_length = -1;
            while(inputStream.available()>0){
                read_length = inputStream.read(readBuffer);
                if(read_length == -1){
                    break;
                }
            }
            DebugPrint.DPrint(currentSerialPort.getName() +":一次读取数据结束！");
            DebugPrint.DPrint(new String(readBuffer));
            SerialPortData serialPortData = new SerialPortData(new String(readBuffer));
            DebugPrint.DPrint("转换成串口数据："+serialPortData.toString());
        }catch (Exception e){
            DebugPrint.DPrint(TAG ,e.toString());
        }
    }

}
