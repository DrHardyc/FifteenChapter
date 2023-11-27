package ru.hardy.udio.domain.regul;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

/** @version таблица 4.63 */
@Getter
@Setter
@Table(schema = "regul", name = "podrazd")
public class Podrazd {
    @Id
    private Long id;

    @MappedCollection(idColumn = "podrazd_id")
    private Filial filial;
    @MappedCollection(idColumn = "podrazd_id")
    private Predst predst;
}
