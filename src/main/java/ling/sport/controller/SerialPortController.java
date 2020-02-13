package ling.sport.controller;

import gnu.io.SerialPort;
import ling.sport.utils.SerialListener;
import ling.sport.utils.SerialTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RequestMapping("/SerialPort")
@Controller
public class SerialPortController {
    private SerialPort serialPort;
    private InputStream inputStream;
    private OutputStream outputStream;

    @ResponseBody
    @RequestMapping("/showAllSerialPort")
    public String showAllSerialPort(){
        ArrayList<String> allSerialPortList = SerialTool.findPort();
        StringBuilder strTemp = new StringBuilder();
        for (String temp:allSerialPortList
             ) {
            strTemp.append(temp);
        }
        return strTemp.toString();
    }

    @ResponseBody
    @RequestMapping("/startSerialPort/{port_num}")
    public String startSerialPort(@PathVariable String port_num){
        serialPort = SerialTool.openPort(port_num,460800);
        try {
            if(serialPort != null){
                inputStream = new BufferedInputStream(serialPort.getInputStream(),20*1024);
                outputStream = serialPort.getOutputStream();
            }else{
                return "串口无法打开";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(serialPort != null){
            SerialListener myListener = new SerialListener();
            myListener.setInputStream(inputStream);
            myListener.setSerialPort(serialPort);
            SerialTool.addListener(serialPort,myListener);

            return "已打开："+port_num+"串口";
        }else{
            return "串口无法打开";
        }
    }
    @RequestMapping("/closeSerialPort/{port_num}")
    @ResponseBody
    public String closeSerialPort(@PathVariable String port_num){
        if(serialPort != null){
            SerialTool.closePort(serialPort);
            return "已关闭："+port_num+"串口";
        }else{
            return "没有打开的串口";
        }

    }

    @ResponseBody
    @RequestMapping("/getSerialData/showAll")
    public Map<String,String> getSerialData(){
        Map<String,String> maps = new HashMap<>();
        maps.put("n","n0h0B");
        maps.put("m","m0h0B");
        return maps;
        //return SerialTool.serialData;
    }

    @RequestMapping("/getSerialData")
    public String root_index(){
        return "SerialData";
    }

}
