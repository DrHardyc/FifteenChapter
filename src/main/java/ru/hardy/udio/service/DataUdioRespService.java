package ru.hardy.udio.service;

import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DataUdioResp;
import ru.hardy.udio.domain.struct.People;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataUdioRespService {
    private static final String SEARCHERRSRZ = "Запись не найдена в ЕРЗЛ";
    private static final String SEARCHACCESS = "Запись успешно обработана";
    public List<DataUdioResp> getListDataUdioFromPeoples(List<People> peoples) {
        List<DataUdioResp> dataUdioResps = new ArrayList<>();
        for (People people : peoples){
            if (people.getIdsrz() == null)
                dataUdioResps.add(new DataUdioResp(people, SEARCHERRSRZ));
            else dataUdioResps.add(new DataUdioResp(people, SEARCHACCESS));
        }
        return dataUdioResps;
    }
}
