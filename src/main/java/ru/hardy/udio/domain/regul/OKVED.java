package ru.hardy.udio.domain.regul;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

/** @version таблица 4.120 */

@Getter
@Setter
@Table(schema = "regul", name = "okved")
public class OKVED {
    @Id
    private Long id;

    private String kod;
    private String name;
    private String version;
    @MappedCollection(idColumn = "okved_id")
    private DoliaOKVED doliaOKVED;

    @MappedCollection(idColumn = "okved_id")
    private OKVEDEGRULType okvedOsn;
    @MappedCollection(idColumn = "okved_id")
    private OKVEDEGRULType okvedDop;
}
