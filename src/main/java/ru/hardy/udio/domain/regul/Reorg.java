package ru.hardy.udio.domain.regul;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

/** @version таблица 4.66 */
@Getter
@Setter
@Table(schema = "regui", name = "reorg")
public class Reorg {
    @Id
    private Long id;

    @MappedCollection(idColumn = "reorg_id")
    private StatusReorg statusReorg;
    @MappedCollection(idColumn = "reorg_id")
    private ReorgUL reorgUL;
}
