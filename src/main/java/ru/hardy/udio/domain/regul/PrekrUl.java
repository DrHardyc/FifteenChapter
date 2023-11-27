package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.19 */
@Getter
@Setter
@Table(schema = "regul", name = "prek_ul")
public class PrekrUl {
    @Id
    private Long id;

    private String datePrekrUl;
    @MappedCollection(idColumn = "prekrul_id")
    private CpPrekrUl prekrUl;
    @MappedCollection(idColumn = "prekrul_id")
    private RegOrgPrekr regOrg;

    /**
     * @param ГРНДата {@link GRNDate#prekrUl}
     */
}
