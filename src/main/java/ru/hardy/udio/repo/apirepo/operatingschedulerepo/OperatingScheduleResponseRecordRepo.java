package ru.hardy.udio.repo.apirepo.operatingschedulerepo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponseRecord;

@Repository
public interface OperatingScheduleResponseRecordRepo extends JpaRepository<OperatingScheduleResponseRecord, Long> {
}
