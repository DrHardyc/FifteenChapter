package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.task.ReportTask;

@Repository
public interface ReportTaskRepo extends JpaRepository<ReportTask, Long> {

}
