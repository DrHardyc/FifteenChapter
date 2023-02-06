package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DNCase;

@Repository
public interface DNCaseRepo extends JpaRepository<DNCase, Long> {
}
