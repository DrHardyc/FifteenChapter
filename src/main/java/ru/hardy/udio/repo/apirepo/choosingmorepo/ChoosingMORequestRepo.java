package ru.hardy.udio.repo.apirepo.choosingmorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;

@Repository
public interface ChoosingMORequestRepo extends JpaRepository<ChoosingMORequest, Long> {
}
