package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.94 */

@Getter
@Setter
@Table(schema = "regul", name = "number_p_type")
public class NumberPType {
    @Id
    private Long id;

    private String type;
    private String number;

    @MappedCollection(idColumn = "number_p_type")
    private AdrULFIAS adrULFIASZdanie;
    @MappedCollection(idColumn = "number_p_type")
    private AdrULFIAS adrULFIASPomeshZdanie;
    @MappedCollection(idColumn = "number_p_type")
    private AdrULFIAS adrULFIASKvartira;

}
