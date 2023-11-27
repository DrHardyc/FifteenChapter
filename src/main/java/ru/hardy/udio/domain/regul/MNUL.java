package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.8 */

@Getter
@Setter
@Table(schema = "regul", name = "mnul")
public class MNUL {
    @Id
    private Long id;

    private String idNom;
    private String region;
    private String nameRegion;

    /** Параметры перенесены для удобства связи БД
     @param МуниципРайон {@link VidNameCodeType#mnul}
     @param ГородСелПоселен {@link VidNameCodeType#mnulPosel}
     @param НаселенПункт {@link VidNamePType#mnulNasPunkt}
     @param ГРНДата {@link GRNDate#mnul}
     @param ГРНДатаИспр {@link GRNDate#mnulIspr}
    */

}
