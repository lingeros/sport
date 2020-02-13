package ling.sport.service.serviceImpl;

import ling.sport.entity.Admin;
import ling.sport.mapper.AdminMapper;
import ling.sport.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Resource(name = "AdminMapper")
    AdminMapper adminMapper;

    @Override
    public Admin getAdmin(String name){
        return adminMapper.getAdmin(name);
    }

    @Override
    public boolean changeAdminPassword(String newPassword) {
        adminMapper.changeAdminPassword(newPassword);
        return getAdmin("admin").getAdmin_key().equals(newPassword);
    }

    @Override
    public void addAdmin(Admin admin) {
        adminMapper.addAdmin(admin);
    }


}
