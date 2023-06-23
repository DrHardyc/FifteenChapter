package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.Sex;
import ru.hardy.udio.repo.DNGetRepo;
import ru.hardy.udio.repo.DNOutRepo;
import ru.hardy.udio.repo.PeopleRepo;
import ru.hardy.udio.repo.SexRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MainTest {

    @Autowired
    private DNGetRepo dnGetRepo;

    @Autowired
    private DNOutRepo dnOutRepo;

    @Autowired
    private PeopleRepo peopleRepo;

    @Autowired
    private SexRepo sexRepo;

    @Test
    public void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


        DNGet dnGet = dnGetRepo.getReferenceById(412598L);


        Sex sex = sexRepo.searchById(1L);
        System.out.println(sex.getId());


//        System.out.println(Period.between(dateFormat.parse("25.01.2011").toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
//                LocalDate.now()).getYears());
    }
}
