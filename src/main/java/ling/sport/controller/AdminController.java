package ling.sport.controller;

import ling.sport.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test_get_admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @RequestMapping("/getAdmin/{name}")
    @ResponseBody
    public String getAdmin(@PathVariable String name){
        return adminService.getAdmin(name).toString();
    }

    @RequestMapping("/changeAdminPassword/{newPassword}")
    @ResponseBody
    public boolean changeAdminPassword(@PathVariable String newPassword){
        return adminService.changeAdminPassword(newPassword);
    }
}




