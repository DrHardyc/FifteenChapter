package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DataFilePatient;

import java.util.List;

@Repository
public interface DataFilePatientRepo extends JpaRepository<DataFilePatient, Long> {


    @Query("select t from DataFilePatient t where t.srz_status = 'не найден в srz'")
    List<DataFilePatient> getNoSearchFromSRZ();
}
