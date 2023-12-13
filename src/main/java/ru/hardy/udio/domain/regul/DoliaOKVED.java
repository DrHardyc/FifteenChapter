package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.121 */
@Getter
@Setter
@Table(schema = "regul", name = "dolia_okved")
public class DoliaOKVED {
    @Id
    private Long id;

    private String dopSvedOKVED;
    private String percent;
}
