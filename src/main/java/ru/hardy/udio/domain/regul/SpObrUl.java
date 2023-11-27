package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.13 */
@Getter
@Setter
@Table(schema = "regul", name = "sp_obr_ul")
public class SpObrUl {
    @Id
    private Long id;

    private String code;
    private String name;
}
