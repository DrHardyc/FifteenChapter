package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;


/** @version  таблице 4.5 */

@Getter
@Setter
@Table(schema = "regul", name = "name_ul")
public class NameUL {
    @Id
    private Long id;
    private String fullName;
    @MappedCollection(idColumn = "nameul_id")
    private ShortNameUl shortName;
    @MappedCollection(idColumn = "nameul_id")
    private NameUlKodOKIN nameUlKodOKIN;
    @MappedCollection(idColumn = "nameul_id")
    private FullNameUlIn fullNameUlIn;
    @MappedCollection(idColumn = "nameul_id")
    private ShortNameUlIn shortNameUlIn;

    /**
     @param ГРНДата {@link GRNDate#nameUL}
     @param ГРНДатаИспр {@link GRNDate#nameULIspr}
     */
}
