package ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.List;

@Repository
public interface IndividualHistoryInformingRequestRecordRepo extends JpaRepository<IndividualHistoryInformingRequestRecord, Long> {


}
