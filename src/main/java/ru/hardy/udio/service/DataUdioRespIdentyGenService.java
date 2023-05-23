package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DataUdioRespIdentyGen;
import ru.hardy.udio.repo.DataUdioRespIdentyGenRepo;

@Service
public class DataUdioRespIdentyGenService {

    @Autowired
    private DataUdioRespIdentyGenRepo dataUdioRespIdentyGenRepo;

    public DataUdioRespIdentyGen add(DataUdioRespIdentyGen dataUdioRespIdentyGen){
        return dataUdioRespIdentyGenRepo.save(dataUdioRespIdentyGen);
    }
}
