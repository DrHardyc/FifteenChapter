package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.132 */
@Getter
@Setter
@Table(schema = "regul", name = "fl")
public class FL {
    @Id
    private Long id;

    private String surname;
    private String name;
    private String patronymic;
    private String inn;

    @MappedCollection(idColumn = "fl_id")
    private KFXPreem kfxPreem;
    @MappedCollection(idColumn = "fl_id")
    private KFXPredsh kfxPredsh;
    @MappedCollection(idColumn = "fl_id")
    private DoljFL doljFL;

}
