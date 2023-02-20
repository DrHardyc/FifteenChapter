package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DataFile;

@Repository
public interface DataFileRepo extends JpaRepository<DataFile, Long> {

}
