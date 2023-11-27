package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.72 */
@Getter
@Setter
@Table(schema = "regul", name = "preem")
public class Preem {
    @Id
    private Long id;

    private String ogrn;
    private String inn;
    private String nameLong;
}
