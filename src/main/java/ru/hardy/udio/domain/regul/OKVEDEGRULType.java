package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.119 */

@Getter
@Setter
@Table(schema = "regul", name = "okved")
public class OKVEDEGRULType {
    @Id
    private Long id;
    /**
     *
     * @param СвОКВЭДОсн {@link OKVEDType#okvedegrulTypeOsn}
     * @param СвОКВЭДДоп {@link OKVEDType#okvedegrulTypeDop}
     */
}
