package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DNOut;

@Repository
public interface DNOutRepo extends JpaRepository<DNOut, Long> {
}
