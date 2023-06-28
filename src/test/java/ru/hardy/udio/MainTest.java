package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.repo.DNGetRepo;
import ru.hardy.udio.repo.DNOutRepo;
import ru.hardy.udio.repo.PeopleRepo;
import ru.hardy.udio.repo.SexRepo;


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
    public void test() {

    }
}
