package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.23 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_pph")
public class RegPF {
    @Id
    private Long id;

    private String regNomPF;
    private String DatePrisvNom;
    private String DateReg;
    @MappedCollection(idColumn = "regpf_id")
    private OrgPF orgPF;

    /**
     * @param ГРНДата {@link GRNDate#regPF}
     * @param ГРНДатаИспр {@link GRNDate#regPFIspr}
     */
}
