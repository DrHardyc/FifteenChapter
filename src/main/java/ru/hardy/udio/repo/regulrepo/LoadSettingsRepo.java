package ru.hardy.udio.repo.regulrepo;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.regul.LoadSettings;

import java.util.List;
import java.util.function.LongFunction;

@Repository
public interface LoadSettingsRepo extends CrudRepository<LoadSettings, Long> {
    @Modifying
    @Query("update regul.user_dir_file set path_in = :path_in, path_out = :path_out where id = :id")
    void update(String path_in, String path_out, long id);

    List<LoadSettings> findLoadSettingsByUserId(Long userId);
}
