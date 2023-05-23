package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DataUdioRespIdentyGen;

@Repository
public interface DataUdioRespIdentyGenRepo extends JpaRepository<DataUdioRespIdentyGen, Long> {

}
