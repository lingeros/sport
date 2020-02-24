package ling.sport;

import ling.sport.controller.AdminController;
import ling.sport.entity.Admin;
import ling.sport.originalSources.DebugPrint;
import ling.sport.originalSources.LoginPanel;
import ling.sport.service.AdminService;
import ling.sport.utils.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


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
        //LoginPanel LoginPanelSingleInstance = LoginPanel.getInstance();
        //LoginPanelSingleInstance.login();

        SpringApplication.run(SportApplication.class, args);

        //普通类能够直接获取数据库数据
        /*ApplicationContext context = SpringUtil.getApplicationContext();
        AdminService adminService = context.getBean(AdminService.class);
        Admin admin = adminService.getAdmin("admin");
        if(admin != null){
            DebugPrint.DPrint(admin.toString());
        }*/

    }

}
