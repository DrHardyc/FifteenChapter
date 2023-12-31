package ru.hardy.udio.service.taskservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.domain.task.ReportTask;
import ru.hardy.udio.domain.task.TaskStatus;
import ru.hardy.udio.repo.ReportTaskRepo;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class ReportTaskService {

    @Autowired
    private ReportTaskRepo reportTaskRepo;

    public List<ReportTask> getAll(){
        return reportTaskRepo.findAllOrderByDateBeg();
    }

    @Transactional
    public void updateStatus(TaskStatus taskStatus, String result, String filname, Long id){
        ReportTask reportTask = reportTaskRepo.getReferenceById(id);
        reportTask.setDateEdit(Date.from(Instant.now()));
        reportTask.setFile_name(filname);
        reportTask.setStatus(taskStatus);
        reportTask.setResult(result);
        reportTaskRepo.save(reportTask);
    }

    public Long add(ReportTask reportTask){
        return reportTaskRepo.save(reportTask).getId();
    }
}
