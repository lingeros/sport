package ling.sport.controller;

import ling.sport.entity.Admin;
import ling.sport.entity.UserForm;
import ling.sport.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
public class IndexController {


    @RequestMapping("/")
    public String root_index(){
        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("newAdmin",new Admin());
        return "admin/index";
    }

    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String add(@ModelAttribute UserForm user){
        String username = user.getUsername();
        String password = user.getPassword();
        UserForm userForm = new UserForm(username,password);
        System.out.println("----------"+userForm.toString());
        return username+"__"+password;
    }

    @ResponseBody
    @RequestMapping(value = "/add_new_admin",method = RequestMethod.POST)
    public String add_new_admin(@ModelAttribute Admin temp_admin){
        Admin newAdmin = new Admin();
        newAdmin.setAdmin_key(temp_admin.getAdmin_key());
        newAdmin.setAdmin_name(temp_admin.getAdmin_name());
        return newAdmin.toString();
    }
}
