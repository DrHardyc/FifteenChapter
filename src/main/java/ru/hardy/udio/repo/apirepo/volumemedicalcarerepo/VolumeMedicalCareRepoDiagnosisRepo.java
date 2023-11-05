package ru.hardy.udio.repo.apirepo.volumemedicalcarerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareDiagnosis;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.util.Date;
import java.util.List;

@Repository
public interface VolumeMedicalCareRepoDiagnosisRepo extends JpaRepository<VolumeMedicalCareDiagnosis, Long> {

    @Query("select t from VolumeMedicalCareDiagnosis t join t.requestRecord rr join rr.request r join rr.department d" +
            " where r.medicalOrganization = :medicalOrganization and d.date_beg between :dateBeg and :dateEnd")
    List<VolumeMedicalCareDiagnosis> findAllByMOAndDateInterval(MedicalOrganization medicalOrganization, Date dateBeg, Date dateEnd);

    @Query("select t from VolumeMedicalCareDiagnosis t join t.requestRecord rr join rr.request r join rr.department d" +
            " where rr.nameDep = :nameDep and r.medicalOrganization = :medicalOrganization and d.date_beg between :dateBeg and :dateEnd")
    List<VolumeMedicalCareDiagnosis> findAllByMOAndCodeDepDateInterval(MedicalOrganization medicalOrganization, String nameDep, Date dateBeg, Date dateEnd);

    @Query("select t from VolumeMedicalCareDiagnosis t join t.requestRecord rr join rr.request r join rr.department d" +
            " where rr.nameDep = :nameDep and t.codeDiagnosis = :codeDiag and r.medicalOrganization = :medicalOrganization " +
            "and d.date_beg between :dateBeg and :dateEnd")
    VolumeMedicalCareDiagnosis findAllByMOAndCodeDepAndCodeDiagDateInterval(MedicalOrganization medicalOrganization,
                                                                                  String codeDiag, String nameDep,
                                                                                  Date dateBeg, Date dateEnd);

}
