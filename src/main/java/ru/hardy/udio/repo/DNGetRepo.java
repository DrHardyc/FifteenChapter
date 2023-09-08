package ru.hardy.udio.repo;

import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;
import java.util.List;

@Repository
public interface DNGetRepo extends JpaRepository<DNGet, Long> {

    //@Query("select t from DNGet t where t.people = :id")
    List<DNGet> findByPeopleId(Long id);
    DNGet findByDiagAndPeople(String diag, People people);

    @Query("select t from DNGet t where exists (select p from People p where t.people = p and p.surname = :fam and p.name = :im and p.patronymic = :ot)")
    List<DNGet> findByFamAndImAndOtOrderByFIO(String fam, String im, String ot);

    @Query("select t from DNGet t " +
            "where t.date_1 between :dateBeg and :dateEnd")
    List<DNGet> findAllWithInterval(Date dateBeg, Date dateEnd);

    @Query("select count(t) from DNGet t " +
            "where t.date_2 between :dateBeg and :dateEnd")
    int findCount_m_16_60();

    @Query("select t from DNGet t where t.specialization = 76")
    List<DNGet> findAllByTherapist();

    @Query("select t from DNGet t where substring(t.diag, 1, 1) = 'C' or substring(t.diag, 1, 2) = 'D0'")
    List<DNGet> findAllByONKO();

    @Query("select t from DNGet t " +
            "where t.date_1 between :dateFrom and :dateTo")
    List<DNGet> findAllByDate_1Between(Date dateFrom, Date dateTo);


    @Query("select t from People t inner join t.dngets")
    List<DNGet> findAll1();

    @Query("select t from DNGet t where t.specialization = 41")
    List<DNGet> findAllByOnkologist();
}
