package ru.hardy.udio.domain.regul;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.22 */

@Getter
@Setter
@Table(schema = "regul", name = "uchet_ul")
public class UchetNO {
    @Id
    private Long id;

    private String inn;
    private String kpp;
    private String datePostUch;
    @MappedCollection(idColumn = "uchetno_id")
    private NO no;

    /**
     * @param ГРНДата {@link GRNDate#uchetNO}
     * @param ГРНДатаИспр {@link GRNDate#uchetNOIspr}
     */
}
