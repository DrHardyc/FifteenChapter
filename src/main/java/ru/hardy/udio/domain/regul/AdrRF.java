package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.88 */
@Getter
@Setter
@Table(schema = "regul", name = "ser_rf")
public class AdrRF {
    @Id
    private Long id;

    private String index;
    private String kodRegion;
    private String kodAdrKladr;
    private String dom;
    private String korpus;
    private String kvart;

    /**
     * @param Регион {@link RegionType#adrRF}
     * @param Район {@link RaionType#adrRF}
     * @param Город {@link GorodType#adrRF}
     * @param НаселПункт {@link NaselPunktType#adrRF}
     * @param Улица {@link UlicaType#adrRF}
     * @param ГРНДата {@link GRNDate#adrRF}
     * @param ГРНДатаИспр {@link GRNDate#adrRFIspr}
     */
}
