package ru.hardy.udio.repo.regulrepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.regul.FileUL;

@Repository
public interface FileUlRepo extends CrudRepository<FileUL, Long> {
}
