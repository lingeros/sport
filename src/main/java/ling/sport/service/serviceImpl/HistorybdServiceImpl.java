package ling.sport.service.serviceImpl;

import ling.sport.entity.Historybd;
import ling.sport.mapper.HistorybdMapper;
import ling.sport.service.HistorybdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("HistorybdService")
public class HistorybdServiceImpl implements HistorybdService {

    @Resource(name = "HistorybdMapper")
    HistorybdMapper historybdMapper;

    @Override
    public Historybd getHistorybdById(String id) {
        return historybdMapper.getHistorybdById(id);

    }

    @Override
    public Historybd getHistorybdByUserId(String user_id) {
        return historybdMapper.getHistorybdByUserId(user_id);
    }

    @Override
    public Historybd getHistorybdByUserName(String user_name) {
        return historybdMapper.getHistorybdByUserName(user_name);
    }

    @Override
    public Historybd getHistorybdByEquipmentId(String equipment_id) {
        return historybdMapper.getHistorybdByEquipmentId(equipment_id);
    }

    @Override
    public void addHistorybd(Historybd newHistorybd) {
        historybdMapper.addHistorybd(newHistorybd);
    }
}
