package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.Token;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Token findByLpu(String lpu);
}
