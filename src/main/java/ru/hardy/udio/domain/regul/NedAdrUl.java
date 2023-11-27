package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.9 */

@Getter
@Setter
@Table(schema = "regul", name = "ned_adr_ul")
public class NedAdrUl {
    @Id
    private Long id;

    private String priznNedAdresUL;
    private String textNedAdresUL;
    private ReshSudNedAdr reshSudNedAdr;
    /**
     @param ГРНДата {@link GRNDate#nedAdrUl}
     @param ГРНДатаИспр {@link GRNDate#nedAdrUlIspr}
     */


}
