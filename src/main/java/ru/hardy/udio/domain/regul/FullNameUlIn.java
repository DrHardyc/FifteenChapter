package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.110 */
@Getter
@Setter
@Table(schema = "regul", name = "full_name_ul_in")
public class FullNameUlIn {
    @Id
    private Long id;

    private String nameLong;
}
