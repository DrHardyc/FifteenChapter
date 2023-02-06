package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.Sex;

@Repository
public interface SexRepo extends JpaRepository<Sex, Long> {

}
