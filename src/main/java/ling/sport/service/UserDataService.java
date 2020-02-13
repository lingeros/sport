package ling.sport.service;

import ling.sport.entity.UserData;

public interface UserDataService {
    UserData getUserByName(String user_name);
    UserData getUserById(String user_id);
}
