package ru.hardy.udio.repo.apirepo.volumemedicalcarerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCare;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Repository
public interface VolumeMedicalCareRepo extends JpaRepository<VolumeMedicalCare, Long> {


    //VolumeMedicalCare findByCodeMOAndRequestRecord_CodeDep(int codeMO, int codeDep);

    @Query("select t from VolumeMedicalCare t join t.requestRecord rr join rr.request r where rr.codeDep = :codeDep and r.medicalOrganization.codeMO = :codeMO and " +
            "function('to_timestamp', function('to_char', t.date_beg, 'yyyy-mm-dd hh24'), 'yyyy-mm-dd hh24') " +
            " between function('to_timestamp', function('to_char', :dateBeginInterval, 'yyyy-mm-dd') || ' 09', 'yyyy-mm-dd hh24') and" +
            " function('to_timestamp', function('to_char', now(), 'yyyy-mm-dd') || ' 09', 'yyyy-mm-dd hh24')")
    VolumeMedicalCare findByAllCodeDepAndCodeMOBef9(int codeDep, int codeMO, Instant dateBeginInterval);

    @Query("select t from VolumeMedicalCare t join t.requestRecord rr join rr.request r where rr.codeDep = :codeDep and r.medicalOrganization.codeMO = :codeMO and " +
            "function('to_timestamp', function('to_char', t.date_beg, 'yyyy-mm-dd hh24'), 'yyyy-mm-dd hh24') " +
            " between function('to_timestamp', function('to_char', now(), 'yyyy-mm-dd') || ' 09', 'yyyy-mm-dd hh24') and" +
            " function('to_timestamp', function('to_char', :dateEndInterval, 'yyyy-mm-dd') || ' 09', 'yyyy-mm-dd hh24')")
    VolumeMedicalCare findAllByCodeDepAndCodeMOAft9(int codeDep, int codeMO, Instant dateEndInterval);
}
