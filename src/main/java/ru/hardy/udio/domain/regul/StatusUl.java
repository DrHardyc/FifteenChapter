package ru.hardy.udio.domain.regul;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.16 */
@Getter
@Setter
@Table(schema = "regul", name = "status_ul")
public class StatusUl {
    @Id
    private Long id;

    @MappedCollection(idColumn = "statusul_id")
    private Status status;
    @MappedCollection(idColumn = "statusul_id")
    private ReshIsklUl reshIsklUl;

    /**
     * @param ОгрДосСв {@link OgrDoc#statusUl}
     * @param ГРНДата {@link GRNDate#statusUl}
     * @param ГРНДатаИспр {@link GRNDate#statusUlIspr}
     */

}
