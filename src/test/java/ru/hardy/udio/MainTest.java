package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.repo.DNGetRepo;
import ru.hardy.udio.service.ExcelService;
import ru.hardy.udio.service.PeopleService;

import java.util.Arrays;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MainTest {

    @Autowired
    private DNGetRepo dnGetRepo;

    @Test
    public void test(){
        System.out.println("test".toUpperCase());
    }
}
