package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


/** @version  таблица 4.11 */
@Getter
@Setter
@Table(schema = "regul", name = "email_ul")
public class EmailUl {
    @Id
    private Long id;
    private String email;

    /**
     * @param ГРНДата {@link GRNDate#emailUl}
     * @param ГРНДатаИспр {@link GRNDate#emailUlIspr}
     */
}
