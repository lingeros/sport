package ling.sport.controller;


import ling.sport.entity.Historybd;
import ling.sport.service.HistorybdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@RequestMapping("/historybd")
@Controller
public class HistorybdController {

    @Resource
    private HistorybdService historybdService;

    @RequestMapping("/getHistorybd/ById/{id}")
    @ResponseBody
    public String getHistorybdById(@PathVariable String id){
        Historybd historybd = historybdService.getHistorybdById(id);
        if (historybd !=null){
            return historybd.toString();
        }else{
            return "没有查找到！";
        }

    }

    @RequestMapping("/getHistorybd/ByUserId/{user_id}")
    @ResponseBody
    public String getHistorybdByUserId(@PathVariable String user_id){
        Historybd historybd = historybdService.getHistorybdByUserId(user_id);
        if (historybd !=null){
            return historybd.toString();
        }else{
            return "没有查找到！";
        }

    }

    @RequestMapping("/getHistorybd/ByUserName/{user_name}")
    @ResponseBody
    public String getHistorybdByUserName(@PathVariable String user_name){
        Historybd historybd = historybdService.getHistorybdByUserName(user_name);
        if (historybd !=null){
            return historybd.toString();
        }else{
            return "没有查找到！";
        }

    }

    @RequestMapping("/getHistorybd/ByEquipmentId/{equipment_id}")
    @ResponseBody
    public String getHistorybdByEquipmentId(@PathVariable String equipment_id){
        Historybd historybd = historybdService.getHistorybdById(equipment_id);
        if (historybd !=null){
            return historybd.toString();
        }else{
            return "没有查找到！";
        }

    }

    @RequestMapping("/addNewHistorybd")
    @ResponseBody
    public String addHistorybd(){
        Historybd historybd = new Historybd();
        historybd.setId("112");
        historybd.setUser_id("112");
        historybd.setUser_name("112");
        historybd.setEquipment_id("112");
        historybd.setUser_condition("112");
        historybd.setCycle_num("112");
        historybd.setHearbeat("112");
        historybd.setWatch_power("112");
        historybd.setUser_long("112");
        historybd.setLat("112");
        historybd.setSet_time(new Timestamp(new Date().getTime()));
        historybdService.addHistorybd(historybd);
        //重新读数据库，看是否插入成功
        String temp = historybdService.getHistorybdById(historybd.getId()).toString();
        if("".equals(temp)){
            return "插入失败";

        }else{
            return temp;
        }
    }

}
