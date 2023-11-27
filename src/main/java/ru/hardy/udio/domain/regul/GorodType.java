package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.95 */
@Getter
@Setter
@Table(schema = "regui", name = "gorod_type")
public class GorodType {
    @Id
    private Long id;

    private String type;
    private String name;

    @MappedCollection(idColumn = "gorodtype_id")
    private AdrRF adrRF;
    @MappedCollection(idColumn = "gorodtype_id")
    private ReshIzmMN reshIzmMN;
}

