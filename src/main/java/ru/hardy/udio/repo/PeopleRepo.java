package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;
import java.util.List;

@Repository
public interface PeopleRepo extends JpaRepository<People, Long> {

    //@Query("SELECT p FROM People p WHERE p.fam = :fam and p.im = :im and p.ot = :ot and p.dr = :dr and p.enp = :enp")
    People findPeopleByFamIgnoreCaseAndImIgnoreCaseAndOtIgnoreCaseAndDrAndEnp(String fam, String im, String ot, Date dr, String enp);

    @Query("SELECT p FROM People p WHERE p.enp = :enp")
    People findPeopleByEnp(@Param("enp") String enp);

    @Query("SELECT p FROM People p WHERE p.id = :id")
    People findById1(Long id);

    @Query("SELECT p from People p where p.ds is null")
    List<People> findAlivePeople();

    @Query("select t from People t inner join t.dngets")
    List<People> findAllByDNGets();
}
