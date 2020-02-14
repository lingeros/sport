package ling.sport.originalSources;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.InputStream;
import java.io.OutputStream;

public class SerialPortListener implements SerialPortEventListener {
    private final String TAG = "SerialPortListener";
    //串口传进来的
    private InputStream inputStream;
    private OutputStream outputStream;

    public SerialPortListener() {
    }

    public SerialPortListener(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
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
                try{
                    if(inputStream.available()>0){
                        receiveData();
                    }
                }catch (Exception e){
                    DebugPrint.DPrint(TAG ,e.toString());
                }
                break;
        }
    }

    private void receiveData(){
        DebugPrint.DPrint("接收数据");
    }

}
