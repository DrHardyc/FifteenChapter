package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.30 */
@Getter
@Setter
@Table(schema = "regul", name = "upr_org")
public class UprOrg {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uprorg_id")
    private NameINNUL nameINNUL;
    @MappedCollection(idColumn = "uprorg_id")
    private RegIn regIn;
    @MappedCollection(idColumn = "uprorg_id")
    private NedDanUprOrg nedDanUprOrg;
    @MappedCollection(idColumn = "uprorg_id")
    private PredUL predUL;
    @MappedCollection(idColumn = "uprorg_id")
    private SvAkRAFP svAkRAFP;

    /**
     * @param ОгрДосСв {@link OgrDoc#uprOrg}
     * @parma ГРНДатаПерв {@link GRNDate#uprOrgPerv}
     */
}
