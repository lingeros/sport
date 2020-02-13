package ling.sport.controller;


import ling.sport.entity.Currentbd;
import ling.sport.service.CurrentbdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping("/currentbd")
@Controller
public class CurrentbdController {
    @Resource
    private CurrentbdService currentbdService;

    @ResponseBody
    @RequestMapping("/getCurrentbdById/{id}")
    public String getCurrentbdById(@PathVariable String id){
        return currentbdService.getCurrentbdById(id).toString();
    }

    @ResponseBody
    @RequestMapping("/getCurrentbdByUserId/{user_id}")
    public String getCurrentbdByUserId(@PathVariable String user_id){
        return currentbdService.getCurrentbdByUserId(user_id).toString();
    }

    @ResponseBody
    @RequestMapping("/getCurrentbdByUserName/{user_name}")
    public String getCurrentbdByUserName(@PathVariable String user_name){
        return currentbdService.getCurrentbdByUserName(user_name).toString();
    }

    @ResponseBody
    @RequestMapping("/getCurrentbdByEquipmentId/{equipment_id}")
    public String getCurrentbdByEquipmentId(@PathVariable String equipment_id){
        return currentbdService.getCurrentbdByEquipmentId(equipment_id).toString();
    }

    @RequestMapping("/addCurrentbd")
    @ResponseBody
    public String addCurrentbd(){
        Currentbd currentbd = new Currentbd();
        currentbd.setId("112");
        currentbd.setUser_id("112");
        currentbd.setUser_name("112");
        currentbd.setEquipment_id("112");
        currentbd.setUser_condition("112");
        currentbd.setCycle_num("112");
        currentbd.setHearbeat("112");
        currentbd.setWatch_power("112");
        currentbd.setUser_long("112");
        currentbd.setLat("112");
        currentbd.setTotalTime("12");
        currentbd.setRun("1");
        currentbdService.addCurrentbd(currentbd);
        //重新读数据库，看是否插入成功
        String temp = currentbdService.getCurrentbdById(currentbd.getId()).toString();
        if("".equals(temp)){
            return "插入失败";

        }else{
            return temp;
        }
    }
}
