package ru.hardy.udio.domain.regul;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

/** @version таблица 4.69 */

@Getter
@Setter
@Table(schema = "regul", name = "predsh")
public class Predsh {
    @Id
    private Long id;

    private String ogrn;
    private String inn;
    private String nameULLong;
}
