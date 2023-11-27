package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.73 */
@Getter
@Setter
@Table(schema = "regul", name = "ul_sloj_reorg")
public class ULSlojReorg {
    @Id
    private Long id;

    private String ogrn;
    private String inn;
    private String nameLong;

    @MappedCollection(idColumn = "ulslojreorg_id")
    private Preem preem;
    @MappedCollection(idColumn = "ulslojreorg_id")
    private Predsh predsh;
}
