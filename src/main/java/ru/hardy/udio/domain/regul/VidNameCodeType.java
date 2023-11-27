package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.91 */
@Getter
@Setter
@Table(schema = "regul", name = "municipal_raion")
public class VidNameCodeType {
    @Id
    private Long id;

    private String vidKod;
    private String name;

    @MappedCollection(idColumn = "municipalraion_id")
    private MNUL mnulNasPunkt;
    @MappedCollection(idColumn = "municipalraion_id")
    private MNUL mnulPosel;
    @MappedCollection(idColumn = "municipalraion_id")
    private AdrULFIAS adrULFIASMR;
    @MappedCollection(idColumn = "municipalraion_id")
    private AdrULFIAS adrULFIASGSP;

}
