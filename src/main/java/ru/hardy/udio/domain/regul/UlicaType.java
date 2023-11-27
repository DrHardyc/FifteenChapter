package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.137 */

@Getter
@Setter
@Table(schema = "regul", name = "ulica_type")
public class UlicaType {
    @Id
    private Long id;

    private String type;
    private String name;

    @MappedCollection(idColumn = "ulicatype_id")
    private AdrRF adrRF;
}
