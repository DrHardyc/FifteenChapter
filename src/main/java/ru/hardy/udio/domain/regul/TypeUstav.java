package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.21 */
@Getter
@Setter
@Table(schema = "regul", name = "")
public class TypeUstav {
    @Id
    private Long id;
    private String nomTypeUstav;

    /**
     * @param ГРНДата {@link GRNDate#typeUstav}
     * @param ГРНДатаИспр {@link GRNDate#typeUstavIspr}
     */
}
