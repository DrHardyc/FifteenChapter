package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблице 4.6 */
@Getter
@Setter
@Table(schema = "regul", name = "name_ul_kod_in")
public class NameUlKodOKIN {
    @Id
    private Long id;

    private String kodOkin;
    private String NameOkin;
}
