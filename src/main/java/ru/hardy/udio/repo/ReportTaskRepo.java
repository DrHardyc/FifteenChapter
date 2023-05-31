package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.task.ReportTask;

import java.util.List;

@Repository
public interface ReportTaskRepo extends JpaRepository<ReportTask, Long> {

    @Query("select t from ReportTask t order by t.dateBeg desc")
    List<ReportTask> findAllOrderByDateBeg();
}
