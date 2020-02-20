package ling.sport;

import gnu.io.SerialPort;
import ling.sport.entity.SerialPortData;
import ling.sport.originalSources.DebugPrint;
import ling.sport.originalSources.LoginPanel;
import ling.sport.originalSources.SerialPorts;
import ling.sport.utils.SerialPortDataList;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@MapperScan("ling.sport.mapper")
@SpringBootApplication
public class SportApplication {

    public static void main(String[] args) {
        DebugPrint.DPrint(""+Thread.currentThread().getId());

        /*try {
            Desktop.getDesktop().browse(new URL("http://localhost:8080/").toURI());
        } catch (Exception e1) {
            e1.printStackTrace();
        }*/
        /*LoginPanel LoginPanelSingleInstance = LoginPanel.getInstance();
        LoginPanelSingleInstance.login();*/

        SerialPorts.startThreads();

        while(true){
            SerialPortData[] serialPortData = SerialPortDataList.getData();
            for (int i = 0; i < serialPortData.length; i++) {
                DebugPrint.DPrint(serialPortData[i].toString());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
