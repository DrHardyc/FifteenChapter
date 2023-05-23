package ru.hardy.udio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.struct.DataUdioRespIdenty;

@Repository
public interface DataUdioRespIdentyRepo extends JpaRepository<DataUdioRespIdenty, Long> {
    @Query("SELECT d FROM DataUdioRespIdenty d WHERE d.id = :id")
    DataUdioRespIdenty getOneById(Long id);

    @Query("SELECT d FROM DataUdioRespIdenty d WHERE d.identy = :identy")
    DataUdioRespIdenty findByIdenty(Long identy);
}
