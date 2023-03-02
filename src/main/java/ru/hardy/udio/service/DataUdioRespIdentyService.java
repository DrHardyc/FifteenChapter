package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.ResponseAnswerUdio;
import ru.hardy.udio.domain.struct.DataUdioResp;
import ru.hardy.udio.domain.struct.DataUdioRespIdenty;
import ru.hardy.udio.repo.DataUdioRespIdentyRepo;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class DataUdioRespIdentyService {

    @Autowired
    private DataUdioRespIdentyRepo dataUdioRespIdentyRepo;

    public DataUdioRespIdenty add(DataUdioRespIdenty dataUdioRespIdenty){
        return dataUdioRespIdentyRepo.save(dataUdioRespIdenty);
    }

    @Transactional
    public void updateProcessEnd(List<DataUdioResp> dataUdioResps, Long id) {
        DataUdioRespIdenty dataUdioRespIdenty = dataUdioRespIdentyRepo.getOneById(id);
        dataUdioRespIdenty.getDataUdioResps().clear();
        dataUdioRespIdenty.getDataUdioResps().addAll(dataUdioResps);
        dataUdioRespIdenty.setStatus(ResponseAnswerUdio.PROCESSING_END);
        dataUdioRespIdenty.setDate_edit(Date.from(Instant.now()));
        dataUdioRespIdentyRepo.save(dataUdioRespIdenty);
    }

    public DataUdioRespIdenty getByIdenty(Long identy) {
        return dataUdioRespIdentyRepo.findByIdenty(identy);
    }
}