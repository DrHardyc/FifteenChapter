package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.56 */
@Getter
@Setter
@Table(schema = "regul", name = "konvzaim")
public class KonvZaim {
    @Id
    private Long id;

    private String number;
    private String date;
    @MappedCollection(idColumn = "konvzaim_id")
    private FIONotarius fioNotarius;
    @MappedCollection(idColumn = "konvzaim_id")
    private ZaimDavFL zaimDavFL;
    @MappedCollection(idColumn = "konvzaim_id")
    private ZaimDavUL zaimDavUL;
    @MappedCollection(idColumn = "konvzaim_id")
    private DoliaUstKapEGRULType doliaUstKap;

    /**
     * @param ГРНДатаПерв {@link GRNDate#konvZaimPerv}
     * @param ГРНДата {@link GRNDate#konvZaim}
     * @param ГРНДатаIspr {@link GRNDate#konvZaimIspr}
     */
}
