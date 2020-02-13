package ling.sport.service;

import ling.sport.entity.Historybd;

public interface HistorybdService {
    Historybd getHistorybdById(String id);
    Historybd getHistorybdByUserId(String user_id);
    Historybd getHistorybdByUserName(String user_name);
    Historybd getHistorybdByEquipmentId(String equipment_id);
    void addHistorybd(Historybd newHistorybd);

}
