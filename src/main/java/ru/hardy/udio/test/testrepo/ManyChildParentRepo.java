package ru.hardy.udio.test.testrepo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.test.ManyChildParent;

@Repository
public interface ManyChildParentRepo extends PagingAndSortingRepository<ManyChildParent, Long> {
}
