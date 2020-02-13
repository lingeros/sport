package ling.sport.mapper;

import ling.sport.entity.UserData;
import org.springframework.stereotype.Repository;

@Repository("UserDataMapper")
public interface UserDataMapper {

    UserData getUserByName(String user_name);
    UserData getUserById(String user_id);
}
