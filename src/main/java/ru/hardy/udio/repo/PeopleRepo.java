package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Repository
public interface PeopleRepo extends JpaRepository<People, Long> {

    @Query("SELECT p FROM People p WHERE p.fam = :fam and p.im = :im and p.ot = :ot and p.dr = :dr and p.enp = :enp")
    People findPeopleByFamAndImAndOtAndDrAndEnp(@Param("fam") String fam, @Param("im") String im,
                                                @Param("ot") String ot, @Param("dr") Date dr,
                                                @Param("enp") String enp);

    @Query("SELECT p FROM People p WHERE p.enp = :enp")
    People findPeopleByEnp(@Param("enp") String enp);

//    @Modifying
//    @Query("update EARAttachment ear set ear.status = ?1 where ear.id = ?2")
//    int update(Integer status, Long id);
}
