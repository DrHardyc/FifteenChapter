package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.61 */

@Getter
@Setter
@Table(schema = "regul", name = "license")
public class License {
    @Id
    private Long id;

    private String nomerERUL;
    private String munber;
    private String date;
    private String dateNachLic;
    private String dateOkonchLic;
    private String naimLicVidDeyat;
    private String licOrgVidDeyat;
    @MappedCollection(idColumn = "license_id")
    private PriostLic priostLic;

    /**
     * @param ОгрДосСв {@link OgrDoc#license}
     * @param ГРНДата {@link }
     * @param ГРНДатаИспр
     */
}
