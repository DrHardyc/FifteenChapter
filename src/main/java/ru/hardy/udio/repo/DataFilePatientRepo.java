package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DataFilePatient;

import java.util.List;

@Repository
public interface DataFilePatientRepo extends JpaRepository<DataFilePatient, Long> {


    @Query("select t from DataFilePatient t where t.srz_status = 'не найден в srz'")
    List<DataFilePatient> getNoSearchFromSRZ();

    @Query("select t from DataFilePatient t where t.srz_status_code = :status_code")
    List<DataFilePatient> findBySrz_status_code(int status_code);

//    @Query("select t from DataFilePatient t where concat(t.fam, ' ', t.im, ' ', t.ot, t.enp) not in (:fios)")
//    List<DataFilePatient> getNoAdd(@Param("fios") String fios);
}
