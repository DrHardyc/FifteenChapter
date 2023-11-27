package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.104 */

@Getter
@Setter
@Table(schema = "regul", name = "region_type")
public class RegionType {
    @Id
    private Long id;

    private String typeRegion;
    private String nameRegion;

    @MappedCollection(idColumn = "regiontype_id")
    private AdrRF adrRF;
    @MappedCollection(idColumn = "regiontype_id")
    private ReshIzmMN reshIzmMN;
}
