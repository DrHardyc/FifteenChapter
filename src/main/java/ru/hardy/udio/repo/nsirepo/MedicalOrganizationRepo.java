package ru.hardy.udio.repo.nsirepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

@Repository
public interface MedicalOrganizationRepo extends JpaRepository<MedicalOrganization, Long> {
    MedicalOrganization findByCodeMO(int codeMO);
}
