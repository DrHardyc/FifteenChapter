package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.60 */

@Getter
@Setter
@Table(schema = "regul")
public class DerjReestAO {
    @Id
    private Long id;

    @MappedCollection(idColumn = "derjreestao_id")
    private OgrDoc ogrDos;
    @MappedCollection(idColumn = "derjreestao_id")
    private NameDerjReestrAO nameDerjReestrAO;

    /**
     * @param ОгрДосСв {@link OgrDoc#derjReestAO}
     * @param ГРНДатаПерв {@link GRNDate#derjReestAOPerv}
     */

}
