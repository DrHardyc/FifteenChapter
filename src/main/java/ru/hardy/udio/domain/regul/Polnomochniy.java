package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.29 */
@Getter
@Setter
@Table(schema = "regul", name = "polnomochniy")
public class Polnomochniy {
    @Id
    private Long id;
    private String vidPol;

    /**
     * @param ГРНДата {@link GRNDate#polnomochniy}
     * @param ГРНДатаИспр {@link GRNDate#polnomochniyIspr}
     */
}
