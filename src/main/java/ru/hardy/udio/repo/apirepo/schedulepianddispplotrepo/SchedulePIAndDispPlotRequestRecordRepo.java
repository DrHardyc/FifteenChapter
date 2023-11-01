package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequestRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchedulePIAndDispPlotRequestRecordRepo extends JpaRepository<SchedulePIAndDispPlotRequestRecord, Long> {
    @Query("select t from SchedulePIAndDispPlotRequestRecord t " +
            "join t.department d " +
            "join t.request r " +
            "where r.medicalOrganization = :medicalOrganization")
    List<SchedulePIAndDispPlotRequestRecord> findAllActualByMO(MedicalOrganization medicalOrganization);

}
