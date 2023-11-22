package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.PeopleRepo;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRepo;
import ru.hardy.udio.repo.apirepo.recommendationspatientrepo.RecommendationsPatientRepo;
import ru.hardy.udio.repo.apirepo.volumemedicalcarerepo.VolumeMedicalCareRequestRecordRepo;
import ru.hardy.udio.repo.nsirepo.MedicalOrganizationRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.nsiservice.MedicalOrganizationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static ru.hardy.udio.service.UtilService.DateTo900Format;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MainTest {

    @Autowired
    private NumberAvailableSeatsRepo numberAvailableSeatsRepo;

    @Autowired
    private PeopleRepo peopleRepo;

    @Autowired
    private VolumeMedicalCareRequestRecordRepo volumeMedicalCareRequestRecordRepo;

    @Autowired
    private MedicalOrganizationRepo medicalOrganizationRepo;
    @Autowired
    private RecommendationsPatientRepo recommendationsPatientRepo;

    @Test
    public void test() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }
}
