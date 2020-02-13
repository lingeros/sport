package ling.sport.service;

import ling.sport.entity.Admin;



public interface AdminService {

    Admin getAdmin(String name);
    boolean changeAdminPassword(String newPassword);
    void addAdmin(Admin admin);
}
