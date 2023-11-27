package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.101 */
@Getter
@Setter
@Table(schema = "regul", name = "nasel_punkt_type")
public class NaselPunktType {
    @Id
    private Long id;

    private String type;
    private String name;

    @MappedCollection(idColumn = "naselpunkttype_id")
    private AdrRF adrRF;
    @MappedCollection(idColumn = "naselpunkttype_id")
    private ReshIzmMN reshIzmMN;
}
