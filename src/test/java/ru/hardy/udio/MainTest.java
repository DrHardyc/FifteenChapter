package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeats;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MainTest {

    @Autowired
    private NumberAvailableSeatsRepo numberAvailableSeatsRepo;

    @Test
    public void test() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        NumberAvailableSeats numberAvailableSeats = numberAvailableSeatsRepo.
                findAllByCodeDepAndCodeMOAft9(78, -1);

//                .stream().filter(numberAvailableSeats1 ->
//                {
//                    try {
//                        return dateFormat.parse(dateFormat.format(numberAvailableSeats1.getDateBeg())).equals(
//                                dateFormat.parse(dateFormat.format(Date.from(Instant.now()))));
//                    } catch (ParseException e) {
//                        throw new RuntimeException(e);
//                    }
//                });

        //System.out.println(numberAvailableSeats.size());

    }
}
