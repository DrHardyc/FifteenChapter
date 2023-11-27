package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.38 */

@Getter
@Setter
@Table(schema = "regul", name = "corp_dogovor")
public class CorpDogovor {
    @Id
    private Long id;
    @MappedCollection(idColumn = "corpdogovor_id")
    private VidSvedKorpDog vidSvedKorpDog;

    /**
     * @param ГРНДата {@link GRNDate#corpDogovor}
     * @param ГРНДатаИспр {@link GRNDate#corpDogovorIspr}
     */
}
