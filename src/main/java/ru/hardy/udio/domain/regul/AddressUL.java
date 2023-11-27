package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.7 */
@Getter
@Setter
@Table(schema = "regul", name = "address_ul")
public class AddressUL {
    @Id
    private Long id;

    private String VidAdrKlassif;
    @MappedCollection(idColumn = "addressul_id")
    private MNUL mnul;
    @MappedCollection(idColumn = "addressul_id")
    private AdrULFIAS adrULFIAS;
    @MappedCollection(idColumn = "addressul_id")
    private AdrRF adrRF;
    @MappedCollection(idColumn = "addressul_id")
    private NedAdrUl nedAdrUl;
    @MappedCollection(idColumn = "addressul_id")
    private ReshIzmMN reshIzmMN;
}
