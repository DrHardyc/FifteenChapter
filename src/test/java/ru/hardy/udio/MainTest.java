package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.DNGetRepo;
import ru.hardy.udio.repo.DNOutRepo;
import ru.hardy.udio.repo.PeopleRepo;

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

    @Test
    public void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


        System.out.println(dnGetRepo.findAllByDate_1Between(dateFormat.parse("26.02.2022"), dateFormat.parse("25.02.2023")));
    }
}
