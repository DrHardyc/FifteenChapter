package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.120 */
@Getter
@Setter
@Table(schema = "regul", name = "okved_type")
public class OKVEDType {
    @Id
    private Long id;

    private String okved;
    private String name;
    private String version;
    private DoliaOKVED doliaOKVED;

    @MappedCollection(idColumn = "okvedtype_id")
    private OKVEDEGRULType okvedegrulTypeOsn;
    @MappedCollection(idColumn = "okvedtype_id")
    private OKVEDEGRULType okvedegrulTypeDop;


    /**
     * @param ГРНДата {@link GRNDate#okvedType}
     * @param ГРНДатаИспр {@link GRNDate#okvedTypeIspr}
     */


}
