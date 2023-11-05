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
import ru.hardy.udio.repo.apirepo.volumemedicalcarerepo.VolumeMedicalCareRequestRecordRepo;
import ru.hardy.udio.repo.nsirepo.MedicalOrganizationRepo;
import ru.hardy.udio.service.nsiservice.MedicalOrganizationService;

import java.text.ParseException;
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

    @Test
    public void test() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

//        calendar.setTime();
        List<People> peopleList = peopleRepo.findAll();

//        peopleList.forEach(people -> {
//            if (Integer.parseInt(new SimpleDateFormat("HH").format(people.getDateBeg())) < 9
//                    && Integer.parseInt(new SimpleDateFormat("HH").format(newDate)) > 9){
//                System.out.println(people.getFIO());
//            }
//        });
        List<VolumeMedicalCareRequestRecord> volumeMedicalCareRequestRecords =
                volumeMedicalCareRequestRecordRepo.findAllByMOLast7Days(medicalOrganizationRepo.findByCodeMO(1),
                        DateTo900Format(7), DateTo900Format(0));

//        List<VolumeMedicalCareRequestRecord> volumeMedicalCareRequestRecords =
//                volumeMedicalCareRequestRecordRepo.findAll();

        System.out.println(volumeMedicalCareRequestRecords);


    }


}
