package ling.sport.service;


import ling.sport.entity.Currentbd;


public interface CurrentbdService {
    Currentbd getCurrentbdById(String id);
    Currentbd getCurrentbdByUserId(String user_id);
    Currentbd getCurrentbdByUserName(String user_name);
    Currentbd getCurrentbdByEquipmentId(String equipment_id);
    void addCurrentbd(Currentbd currentbd);
}
