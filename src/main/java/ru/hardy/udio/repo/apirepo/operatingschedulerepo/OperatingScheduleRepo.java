package ru.hardy.udio.repo.apirepo.operatingschedulerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.operatingschedule.OperatingSchedule;

@Repository
public interface OperatingScheduleRepo extends JpaRepository<OperatingSchedule, Long> {

    OperatingSchedule findByCodeMOAndAndDepartmentRequest_CodeDepAndDepartmentRequest_HolidaysDep(int codeMO, int codeDep,
                                                                                                  String holidaysDep);
}
