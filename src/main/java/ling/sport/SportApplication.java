package ling.sport;

import ling.sport.entity.SerialPortData;
import ling.sport.originalSources.LoginPanel;
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

        /*try {
            Desktop.getDesktop().browse(new URL("http://localhost:8080/").toURI());
        } catch (Exception e1) {
            e1.printStackTrace();
        }*/
        /*LoginPanel LoginPanelSingleInstance = LoginPanel.getInstance();
        LoginPanelSingleInstance.login();*/
        SerialPortData serialPortData = new SerialPortData("A12E11321.7995N23.9.2798H0B");
        System.out.println(serialPortData.initData());
        //SpringApplication.run(SportApplication.class, args);



    }

}
