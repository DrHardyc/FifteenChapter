package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMORequest;

@Repository
public interface NumberAvailableSeatsSMORequestRepo extends JpaRepository<NumberAvailableSeatsSMORequest, Long> {
}
