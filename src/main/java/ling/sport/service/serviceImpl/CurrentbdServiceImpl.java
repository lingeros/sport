package ling.sport.service.serviceImpl;

import ling.sport.entity.Currentbd;

import ling.sport.mapper.CurrentbdMapper;
import ling.sport.service.CurrentbdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("CurrentbdService")
public class CurrentbdServiceImpl implements CurrentbdService {

    @Resource(name = "CurrentbdMapper")
    private CurrentbdMapper currentbdMapper;


    @Override
    public Currentbd getCurrentbdById(String id) {
        return currentbdMapper.getCurrentbdById(id);
    }

    @Override
    public Currentbd getCurrentbdByUserId(String user_id) {
        return currentbdMapper.getCurrentbdByUserId(user_id);
    }

    @Override
    public Currentbd getCurrentbdByUserName(String user_name) {
        return currentbdMapper.getCurrentbdByUserName(user_name);
    }

    @Override
    public Currentbd getCurrentbdByEquipmentId(String equipment_id) {
        return currentbdMapper.getCurrentbdByEquipmentId(equipment_id);
    }

    @Override
    public void addCurrentbd(Currentbd currentbd) {
        currentbdMapper.addCurrentbd(currentbd);
    }
}
