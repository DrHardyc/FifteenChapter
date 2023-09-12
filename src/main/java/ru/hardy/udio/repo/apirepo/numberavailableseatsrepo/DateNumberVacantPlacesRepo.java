package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.DateNumberVacantPlaces;

import java.util.List;

@Repository
public interface DateNumberVacantPlacesRepo extends JpaRepository<DateNumberVacantPlaces, Long> {
}
