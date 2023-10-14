package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;
import java.util.List;

@Repository
public interface PeopleRepo extends JpaRepository<People, Long> {

    People findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(
            String surname, String name, String patronymic, Date date_birth, String enp);

//    @Query("select p from People p where (:surname is null or p.surname = :name) and ()")
//    List<People> findAllBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(
//             String surname, String name, String patronymic, Date date_birth, String enp);

    @Query("SELECT p FROM People p WHERE p.enp = :enp")
    People findPeopleByEnp(@Param("enp") String enp);

    @Query("SELECT p FROM People p WHERE p.id = :id")
    People findById1(Long id);

    @Query("SELECT p from People p where p.ds is null")
    List<People> findAlivePeople();

    @Query("select t from People t inner join t.dngets")
    List<People> findAllByDNGets();

    @Query("select distinct p from People p inner join p.dngets d where date_part('year', age(now(), p.dateBirth)) between :ageBeg and :ageEnd " +
            "and d.diag in :diag and p.sex = :sex and d.specialization = :spec")
    List<People> findDNTherapistReport(int ageBeg, int ageEnd, List<String> diag, int sex, String spec);

    @Query("select distinct p from People p inner join p.dngets d where d.diag in :diag and d.specialization = :spec")
    List<People> findDNTherapistReport(List<String> diag, String spec);
    @Query("select distinct p from People p inner join p.dngets d where date_part('year', age(now(), p.dateBirth)) = :age " +
            "and d.diag in :diag and p.sex >= :sex and d.specialization = :spec")
    List<People> findDNTherapistReport(int age, List<String> diag, int sex, String spec);

    //с интервалами
    @Query("select distinct p from People p inner join p.dngets d where date_part('year', age(now(), p.dateBirth)) between :ageBeg and :ageEnd " +
            "and d.diag in :diag and p.sex = :sex and d.specialization = :spec and d.date_1 between :dateBeg and :dateEnd")
    List<People> findDNTherapistReport(int ageBeg, int ageEnd, List<String> diag, int sex, String spec, Date dateBeg, Date dateEnd);

    @Query("select distinct p from People p inner join p.dngets d where d.diag in :diag and d.specialization = :spec " +
            "and d.date_1 between :dateBeg and :dateEnd")
    List<People> findDNTherapistReport(List<String> diag, String spec, Date dateBeg, Date dateEnd);

    @Query("select distinct p from People p inner join p.dngets d where date_part('year', age(now(), p.dateBirth)) = :age " +
            "and d.diag in :diag and p.sex >= :sex and d.specialization = :spec and d.date_1 between :dateBeg and :dateEnd")
    List<People> findDNTherapistReport(int age, List<String> diag, int sex, String spec, Date dateBeg, Date dateEnd);

    //============

    @Query("select distinct p from People p inner join p.dngets d where date_part('year', age(now(), p.dateBirth)) between :ageBeg and :ageEnd " +
            "and d.diag in :diag and p.sex = :sex and d.date_call is not null and d.specialization = :spec")
    List<People> findDNTherapistReportCall(int ageBeg, int ageEnd, List<String> diag, int sex, String spec);

    @Query("select distinct p from People p inner join p.dngets d where d.diag in :diag and d.date_call is not null" +
            " and d.specialization = :spec")
    List<People> findDNTherapistReportCall(List<String> diag, String spec);

    @Query("select distinct p from People p inner join p.dngets d where date_part('year', age(now(), p.dateBirth)) = :age " +
            "and d.diag in :diag and p.sex >= :sex and d.date_call is not null and d.specialization = :spec")
    List<People> findDNTherapistReportCall(int age, List<String> diag, int sex, String spec);

    @Query("select distinct p from People p inner join p.dngets d where date_part('year', age(now(), p.dateBirth)) " +
            "between :ageBeg and :ageEnd and d.diag in :diag and p.sex = :sex and p.inv is not null and d.specialization = :spec")
    List<People> findDNTherapistReportInv(int ageBeg, int ageEnd, List<String> diag, int sex, String spec);

    @Query("select distinct p from People p inner join p.dngets d where d.diag in :diag and p.inv is not null" +
            " and d.specialization = :spec")
    List<People> findDNTherapistReportInv(List<String> diag, String spec);

    @Query("select distinct p from People p inner join p.dngets d where date_part('year', age(now(), p.dateBirth)) = :age " +
            "and substring(d.diag, 1, 3) in :diag and p.sex = :sex and p.inv is not null and d.specialization = :spec")
    List<People> findDNTherapistReportInv(int age, List<String> diag, int sex, String spec);

    @Query("select distinct p from People p inner join p.dngets d where substring(d.diag, 1, 1) = 'I'")
    List<People> findDNKardioReport();

    @Query("select distinct p from People p inner join p.dngets d where substring(d.diag, 1, 1) = 'C' or substring(d.diag, 1, 2) = 'D0'")
    List<People> findDNOnkoReport();

    @Query("select distinct p from People p inner join p.dnouts d")
    List<People> findDistinctDie();

    People findPeopleById(Long id);
}



