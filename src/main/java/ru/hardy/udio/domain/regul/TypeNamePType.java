package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.93 */
@Getter
@Setter
@Table(schema = "regul", name = "type_name_p_type")
public class TypeNamePType {
    @Id
    private Long id;

    private String typeElement;
    private String nameElement;

    @MappedCollection(idColumn = "typenameptype_id")
    private AdrULFIAS adrULFIASStruct;
    @MappedCollection(idColumn = "typenameptype_id")
    private AdrULFIAS adrULFIASSeti;

}
