package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DNGet;

import java.time.Month;
import java.util.Date;
import java.util.List;

@Repository
public interface DNGetRepo extends JpaRepository<DNGet, Long> {

    //@Query("select t from DNGet t where t.people = :id")
    List<DNGet> findByPeopleId(Long id);
    DNGet findByNhistory(String nhistory);


    @Query("select t from DNGet t " +
            "where t.date_1 between :dateBeg and :dateEnd")
    List<DNGet> findAllWithInterval(@Param("dateBeg") Date dateBeg, @Param("dateEnd") Date dateEnd);

    @Query("select count(t) from DNGet t " +
            "where t.date_2 between :dateBeg and :dateEnd")
    int findCount_m_16_60();

    @Query("select t from DNGet t where t.specialization = 76")
    List<DNGet> findAllByTherapist();

    @Query("select t from DNGet t where substring(t.diag, 1, 1) = 'C' or substring(t.diag, 1, 2) = 'D0'")
    List<DNGet> findAllByONKO();

    @Query("select t from DNGet t where substring(t.diag, 1, 1) = 'I'")
    List<DNGet> findAllByKARDIO();
}
