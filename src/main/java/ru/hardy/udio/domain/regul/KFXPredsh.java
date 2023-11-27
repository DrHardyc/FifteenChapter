package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.71 */
@Getter
@Setter
@Table(schema = "regul", name = "kfx_prdsh")
public class KFXPredsh {
    @Id
    private Long id;

    private String ogrnip;
}
