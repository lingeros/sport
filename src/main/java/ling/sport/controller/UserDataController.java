package ling.sport.controller;

import ling.sport.entity.UserData;
import ling.sport.service.UserDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping("/userData")
@Controller
public class UserDataController {

    @Resource
    private UserDataService userDataService;

    @ResponseBody
    @RequestMapping("/getUserByName/{user_name}")
    public String getUserByName(@PathVariable String user_name){
       return userDataService.getUserByName(user_name).toString();
    }

    @ResponseBody
    @RequestMapping("/getUserById/{user_id}")
    public String getUserById(@PathVariable String user_id){
        return userDataService.getUserById(user_id).toString();
    }

}
