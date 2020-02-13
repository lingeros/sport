package ling.sport.mapper;

import ling.sport.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository("AdminMapper")
public interface AdminMapper {
    Admin getAdmin(String name);
    boolean changeAdminPassword(String newPassword);
    void addAdmin(Admin admin);
}
