package ru.hardy.udio.test.testrepo;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.test.OneParent;

import java.util.List;

@Repository
public interface OneParentRepo extends CrudRepository<OneParent, Long> {
    OneParent findByName(String name);

    @NotNull List<OneParent> findAll();
}
