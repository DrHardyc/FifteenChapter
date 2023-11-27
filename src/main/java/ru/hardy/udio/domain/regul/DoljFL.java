package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.33 */

@Getter
@Setter
@Table(schema = "regul", name = "dolj_fl")
public class DoljFL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "doljfl_id")
    private VidAdrClassif vidAdrClassif;
    @MappedCollection(idColumn = "doljfl_id")
    private FIOZAGS fiozags;
    @MappedCollection(idColumn = "doljfl_id")
    private Dolj dolj;
    @MappedCollection(idColumn = "doljfl_id")
    private NedDanDoljFL nedDanDoljFL;
    @MappedCollection(idColumn = "doljfl_id")
    private DopSvPolDoljFL dopSvPolDoljFL;
    @MappedCollection(idColumn = "doljfl_id")
    private PolFL polFL;
    @MappedCollection(idColumn = "doljfl_id")
    private RojdFL rojdFL;
    @MappedCollection(idColumn = "doljfl_id")
    private ZapRojd zapRojd;
    @MappedCollection(idColumn = "doljfl_id")
    private GrajdFL grajdFL;
    @MappedCollection(idColumn = "doljfl_id")
    private LichnFL lichnFL;
    @MappedCollection(idColumn = "doljfl_id")
    private AdrMJRF adrMJRF;
    @MappedCollection(idColumn = "doljfl_id")
    private AdrMJFIAS adrMJFIAS;
    @MappedCollection(idColumn = "doljfl_id")
    private Diskv diskv;


    /**
     * @param ОгрДосСв {@link OgrDoc#doljFL}
     * @param ГРНДатаПерв {@link GRNDate#doljFLPerv}
     * @param СвФЛ {@link FL#doljFL}
     */


}
