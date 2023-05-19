package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.repo.DNGetRepo;
import ru.hardy.udio.service.ExcelService;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MainTest {

    @Autowired
    private DNGetRepo dnGetRepo;

    @Test
    public void test(){

        ExcelService excelService = new ExcelService();
        excelService.getDNTherapistReportSample(dnGetRepo.findAllByTherapist(), "1", "2023", "");
        //excelService.getOtherDNGets(dnGetRepo.findAllByTherapist());

        //        DNTherapistReportService dnTherapistReportService = new DNTherapistReportService();
//        System.out.println(dnTherapistReportService.countInv(dnGetRepo.findAllByTherapist(), WorkingAgeSex.W_older_55,
//                "I69.0, I69.1, I69.2, I69.3, I69.4, I67.8"));

    }


}
