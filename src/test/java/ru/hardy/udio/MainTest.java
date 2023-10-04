package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MainTest {

    @Autowired
    private NumberAvailableSeatsRepo numberAvailableSeatsRepo;

    @Test
    public void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        NumberAvailableSeats numberAvailableSeats = numberAvailableSeatsRepo.
//                findByAllCodeDepAndCodeMOBef9(78, -1, Instant.now().minus(Duration.ofDays(1)));
//
//        System.out.println(numberAvailableSeats);
        System.out.println(Integer.parseInt(new SimpleDateFormat("HH").format(Date.from(Instant.now()))));

    }
}
