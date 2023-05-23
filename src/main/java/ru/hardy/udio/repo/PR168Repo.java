package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.PR168;

@Repository
public interface PR168Repo extends JpaRepository<PR168, Long> {

    PR168 findByDiagContainingAndSpec(String s, int kod_spec);
}
