package ru.hardy.udio.repo.apirepo.hospitalizationrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMORequest;

@Repository
public interface HospitalizationSMORequestRepo extends JpaRepository<HospitalizationSMORequest, Long> {
}
