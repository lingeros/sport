package ling.sport.mapper;

import ling.sport.entity.Currentbd;

import org.springframework.stereotype.Repository;

@Repository("CurrentbdMapper")
public interface CurrentbdMapper {
    Currentbd getCurrentbdById(String id);
    Currentbd getCurrentbdByUserId(String user_id);
    Currentbd getCurrentbdByUserName(String user_name);
    Currentbd getCurrentbdByEquipmentId(String equipment_id);
    void addCurrentbd(Currentbd currentbd);
}
