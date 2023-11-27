package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.15 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_org")
public class RegOrg {
    @Id
    private Long id;

    private String kodNO;
    private String nameNO;
    private String adrRO;

    /**
     * @param ГРНДата {@link GRNDate#regOrg}
     */

}
