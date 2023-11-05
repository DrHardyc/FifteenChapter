package ru.hardy.udio.repo.apirepo.volumemedicalcarerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCare;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.util.Date;
import java.util.List;

@Repository
public interface VolumeMedicalCareRequestRecordRepo extends JpaRepository<VolumeMedicalCareRequestRecord, Long> {
    @Query("select t from VolumeMedicalCareRequestRecord t " +
            "join t.department d " +
            "join t.request r where r.medicalOrganization = :medicalOrganization " +
            "and d.date_beg between :dateBeg and :dateEnd")
    List<VolumeMedicalCareRequestRecord> findAllByMOLast7Days(MedicalOrganization medicalOrganization,
                                                              Date dateBeg, Date dateEnd);
}
