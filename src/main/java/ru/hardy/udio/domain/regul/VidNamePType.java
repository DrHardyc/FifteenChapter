package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.92 */
@Getter
@Setter
@Table(schema = "regul", name = "naselen_punkt")
public class VidNamePType {
    @Id
    private Long id;

    private String vid;
    private String name;

    @MappedCollection(idColumn = "vidnameptype_id")
    private MNUL mnulNasPunkt;

    @MappedCollection(idColumn = "vidnameptype_id")
    private AdrULFIAS adrULFIAS;
}
