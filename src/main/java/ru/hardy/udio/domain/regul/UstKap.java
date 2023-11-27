package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.27 */
@Getter
@Setter
@Table(schema = "regul", name = "ust_kap")
public class UstKap {
    @Id
    private Long id;

    private String nameVidKap;
    private String sumKap;
    @MappedCollection(idColumn = "ustkap_id")
    private DolyRub dolyRub;
    @MappedCollection(idColumn = "ustkap_id")
    private SvedUmUK svedUmUK;

    /**
     * @param ГРНДата {@link GRNDate#ustKap}
     * @param ГРНДатаИспр {@link GRNDate#ustKapIspr}
     */
}
