package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.deamon.Deamon;

@Repository
public interface DeamonRepo extends JpaRepository<Deamon, Long> {

}
