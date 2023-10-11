package ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;
import ru.hardy.udio.domain.struct.People;

@Repository
public interface IndividualHistoryInformingRepo extends JpaRepository<IndividualHistoryInforming, Long> {
    IndividualHistoryInforming findByPeople(People people);

}
