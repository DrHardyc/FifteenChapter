package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeats;

import java.util.Date;
import java.util.List;

@Repository
public interface NumberAvailableSeatsRepo extends JpaRepository<NumberAvailableSeats, Long> {

    @Query("select t from NumberAvailableSeats t where t.codeDep = :codeDep and t.codeMO = :codeMO and " +
            "function('to_timestamp', function('to_char', t.dateBeg, 'yyyy-mm-dd hh24'), 'yyyy-mm-dd hh24') " +
            " < function('to_timestamp', function('to_char', now(), 'yyyy-mm-dd') || ' 09', 'yyyy-mm-dd hh24')")
    NumberAvailableSeats findByAllCodeDepAndCodeMOBef9(int codeDep, int codeMO);

    @Query("select t from NumberAvailableSeats t where t.codeDep = :codeDep and t.codeMO = :codeMO and " +
            "function('to_timestamp', function('to_char', t.dateBeg, 'yyyy-mm-dd hh24'), 'yyyy-mm-dd hh24') " +
            " > function('to_timestamp', function('to_char', now(), 'yyyy-mm-dd') || ' 09', 'yyyy-mm-dd hh24')")
    NumberAvailableSeats findAllByCodeDepAndCodeMOAft9(int codeDep, int codeMO);

}
