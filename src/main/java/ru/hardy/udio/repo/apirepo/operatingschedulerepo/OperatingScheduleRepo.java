package ru.hardy.udio.repo.apirepo.operatingschedulerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.operatingschedule.OperatingSchedule;

@Repository
public interface OperatingScheduleRepo extends JpaRepository<OperatingSchedule, Long> {

    @Query("select o from OperatingSchedule o " +
            "join o.requestRecord rr " +
            "join rr.request r join r.medicalOrganization mo where mo.codeMO = :codeMO " +
            "and rr.codeDep = :codeDep and rr.holidaysDep = :holidaysDep")
    OperatingSchedule findByCodeMOAndCodeDepAndHolidaysDep(int codeMO, int codeDep, String holidaysDep);
}
