package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.10 */
@Getter
@Setter
@Table(schema = "regul", name = "resh_izm_mn")
public class ReshIzmMN {
    @Id
    private Long id;

    private String textReshIzmMN;

    /**
     * @param Регион {@link RegionType#reshIzmMN}
     * @param Район {@link RaionType#reshIzmMN}
     * @param Город {@link GorodType#reshIzmMN}
     * @param НаселПункт {@link NaselPunktType#reshIzmMN}
     * @param ГРНДата {@link GRNDate#reshIzmMN}
     * @param ГРНДатаИспр {@link GRNDate#reshIzmMNIspr}
     */
}
