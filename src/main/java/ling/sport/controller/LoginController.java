package ling.sport.controller;

import ling.sport.entity.Admin;
import ling.sport.entity.UserData;
import ling.sport.service.AdminService;
import ling.sport.service.UserDataService;
import ling.sport.utils.ResultInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    @Resource
    private AdminService adminService;


    @RequestMapping("/login")
    public String login(Model model) {
        Admin login_admin = new Admin();
        model.addAttribute("login_admin", login_admin);
        return "logins/signin";
    }

    @ResponseBody
    @PostMapping("/check_login_admin")
    public Object check_login_admin(@RequestBody Admin admin,HttpSession session) {
        String admin_username = admin.getAdmin_name();
        String admin_password = admin.getAdmin_key();
        Admin get_admin_from_sql = adminService.getAdmin(admin_username);
        ResultInfo result = new ResultInfo();
        if (get_admin_from_sql != null) {
            if (get_admin_from_sql.getAdmin_key().equals(admin_password)) {
                result.setSuccess(true);
            } else {
                result.setMsg("密码错误");
                result.setSuccess(false);
            }

        } else {
            result.setMsg("没有获取到该用户名的信息");
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping("/register")
    public String register() {
        return "logins/signup";
    }

    @ResponseBody
    @PostMapping("/add_admin")
    public Object add_admin(@RequestBody Admin admin,HttpSession session){
        String admin_name = admin.getAdmin_name();
        String admin_password = admin.getAdmin_key();
        ResultInfo result = new ResultInfo();
        if(adminService.getAdmin(admin_name)!=null){//用户名重复
            result.setSuccess(false);
            result.setMsg("该用户名已经存在！");
        }else{//用户名没有重复
            Admin add_new_admin = new Admin(admin_name,admin_password);
            System.out.println(add_new_admin.toString());
            adminService.addAdmin(add_new_admin);
            result.setSuccess(true);
            result.setMsg("添加成功！");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/test/{admin_name}/{admin_password}")
    public String test(@PathVariable String admin_name,@PathVariable String admin_password) {
        Admin add_admin = new Admin();
        add_admin.setAdmin_name(admin_name);
        add_admin.setAdmin_key(admin_password);
        if(adminService.getAdmin(add_admin.getAdmin_name())!=null){
            return "已经存在用户名";
        }else{
            adminService.addAdmin(add_admin);
            return "添加成功";
        }

    }


}
