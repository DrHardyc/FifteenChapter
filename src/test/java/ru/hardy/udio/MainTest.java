package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.PeopleRepo;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRepo;
import ru.hardy.udio.service.PeopleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MainTest {

    @Autowired
    private NumberAvailableSeatsRepo numberAvailableSeatsRepo;

    @Autowired
    private PeopleRepo peopleRepo;

    @Test
    public void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        NumberAvailableSeats numberAvailableSeats = numberAvailableSeatsRepo.
//                findByAllCodeDepAndCodeMOBef9(78, -1, Instant.now().minus(Duration.ofDays(1)));
//
//        System.out.println(numberAvailableSeats);
        System.out.println(peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(
                "Премудрая", "Василиса", "Ивановна", dateFormat.parse("1111-02-12"), "1235486925412365") != null);

        System.out.println(peopleRepo.findAllBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndEnp(
                "Durak", "Ivan", "Vasilevich", "1235486925412367").size());

        System.out.println(peopleRepo.findAllBySurname("Durak").size());

//        JdbcTemplate jdbcTemplate = new JdbcTemplate();
//        jdbcTemplate.setDataSource(new DBJDBCConfig().getUDIODataSource());
//        People people = jdbcTemplate.queryForObject("select * from udio_tfoms.people p where p.surname = 'Премудрая'", People.class);
//        System.out.println(Objects.requireNonNull(people).getFIO());

        People people = new PeopleService().getPeopleWithJDBC().get(0);
        people.setSex(1);
        peopleRepo.save(people);
        System.out.println(people.getFIO());

   }
}
