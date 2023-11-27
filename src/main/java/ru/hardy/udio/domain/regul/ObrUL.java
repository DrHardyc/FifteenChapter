package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.12 */
@Getter
@Setter
@Table(schema = "regul", name = "obr_ul")
public class ObrUL {
    @Id
    private Long id;

    private String statusMKF;
    private String ogrn;
    private String dateOgrn;
    private String regNom;
    private String dateReg;
    private String nameRO;
    private String KodUl;
    @MappedCollection(idColumn = "obrul_id")
    private SpObrUl obrUl;
    @MappedCollection(idColumn = "obrul_id")
    private RegInUl regInUl;

    /**
     * @param ГРНДата {@link GRNDate#obrUL}
     * @param ГРНДатаИспр {@link GRNDate#obrULIspr}
     */
}
