package ling.sport.mapper;

import ling.sport.entity.Historybd;
import org.springframework.stereotype.Repository;

@Repository("HistorybdMapper")
public interface HistorybdMapper {
    Historybd getHistorybdById(String id);
    Historybd getHistorybdByUserId(String user_id);
    Historybd getHistorybdByUserName(String user_name);
    Historybd getHistorybdByEquipmentId(String equipment_id);

    void addHistorybd(Historybd newHistorybd);

}
