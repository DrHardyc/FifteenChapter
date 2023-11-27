package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.25 */
@Getter
@Setter
@Table(schema = "regul", name = "regfss")
public class RegFSS {
    @Id
    private Long id;

    private String regNomFSS;
    private String datePriNom;
    private String dateReg;
    @MappedCollection(idColumn = "regfss_id")
    private OrgFSS orgFSS;

    /**
     * @param ГРНДата {@link GRNDate#regFSS}
     * @param ГРНДатаИспр {@link GRNDate#regFSSIspr}
     */
}
