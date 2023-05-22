package ru.hardy.udio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.hardy.udio.domain.report.AgeLimit;
import ru.hardy.udio.repo.DNGetRepo;
import ru.hardy.udio.service.ExcelService;
import ru.hardy.udio.service.report.ONKOReport;

import java.util.UUID;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MainTest {

    @Autowired
    private DNGetRepo dnGetRepo;

    @Test
    public void test(){

        ExcelService excelService = new ExcelService();
        excelService.getONKOReport(dnGetRepo.findAllByONKO(), "3", "2023", UUID.randomUUID() + "_ОНКО.xlsx");
//        ONKOReport onkoReport = new ONKOReport(dnGetRepo.findAllByONKO());
//        System.out.println(onkoReport.getCountOnkoSpec(AgeLimit.all, "1", "2023", "9,41,49,102,19,39", "1,2,3"));
//        System.out.println(onkoReport.getCountOnkoSpec(AgeLimit.older_18, "1", "2023", "9,41,39", "1,2"));
//        System.out.println(onkoReport.getCountOnkoSpec(AgeLimit.older_18, "1", "2023", "9,41,39", "3"));
//        System.out.println(onkoReport.getCountOnkoSpec(AgeLimit.younger_18, "1", "2023", "49,102,19,39", "1,2"));
//        System.out.println(onkoReport.getCountOnkoSpec(AgeLimit.younger_18, "1", "2023", "49,102,19,39", "3"));
//        System.out.println();
//            System.out.println("hisadfgsdf".contains("sad"));
    }
}
