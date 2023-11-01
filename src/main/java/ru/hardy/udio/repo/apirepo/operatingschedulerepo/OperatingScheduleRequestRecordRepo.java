package ru.hardy.udio.repo.apirepo.operatingschedulerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequestRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.util.List;

@Repository
public interface OperatingScheduleRequestRecordRepo extends JpaRepository<OperatingScheduleRequestRecord, Long> {


    @Query("select t from OperatingScheduleRequestRecord t " +
            "join t.department d " +
            "join t.request r " +
            "where r.medicalOrganization = :medicalOrganization")
    List<OperatingScheduleRequestRecord> findAllActualByMO(MedicalOrganization medicalOrganization);
}
