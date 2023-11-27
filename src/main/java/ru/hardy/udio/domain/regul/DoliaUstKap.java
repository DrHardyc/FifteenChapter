package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.59 */

@Getter
@Setter
@Table(schema = "regul", name = "dolia_ust_kap")
public class DoliaUstKap {
    @Id
    private Long id;

    private String NominStoim;
    @MappedCollection(idColumn = "doliaustkap_id")
    private DiliaRublia diliaRublia;
    @MappedCollection(idColumn = "doliaustkap_id")
    private RazmerDoli razmerDoli;
    @MappedCollection(idColumn = "doliaustkap_id")
    private Obrem obrem;
    @MappedCollection(idColumn = "doliaustkap_id")
    private UpravZal upravZal;

    /**
     * @param ОгрДосСв {@link OgrDoc#doliaUstKap}
     * @param ГРНДата {@link GRNDate#doliaUstKap}
     * @param ГРНДатаИспр {@link GRNDate#doliaUstKapIspr}
     */


}
