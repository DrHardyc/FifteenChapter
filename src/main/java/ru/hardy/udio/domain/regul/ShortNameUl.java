package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблице 4.111 */
@Getter
@Setter
@Table(schema = "regul", name = "short_name_ul")
public class ShortNameUl {
    @Id
    private Long id;
    private String nameSokr;
}
