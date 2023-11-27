package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.103 */
@Getter
@Setter
@Table(schema = "regul", name = "raion_type")
public class RaionType {
    @Id
    private Long id;

    private String type;
    private String name;

    @MappedCollection(idColumn = "raiontype_id")
    private AdrRF adrRF;
    @MappedCollection(idColumn = "raiontype_id")
    private ReshIzmMN reshIzmMN;
}
