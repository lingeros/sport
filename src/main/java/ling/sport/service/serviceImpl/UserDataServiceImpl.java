package ling.sport.service.serviceImpl;

import ling.sport.entity.UserData;
import ling.sport.mapper.UserDataMapper;
import ling.sport.service.UserDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("UserDataService")
public class UserDataServiceImpl implements UserDataService {

    @Resource
    private UserDataMapper userDataMapper;

    @Override
    public UserData getUserByName(String user_name) {
        return userDataMapper.getUserByName(user_name);
    }

    @Override
    public UserData getUserById(String user_id) {
        return userDataMapper.getUserById(user_id);
    }
}
