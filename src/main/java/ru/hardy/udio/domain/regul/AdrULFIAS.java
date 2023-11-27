package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.89 */
@Getter
@Setter
@Table(schema = "regul", name = "adr_ul_fias")
public class AdrULFIAS {
    @Id
    private Long id;

    private String idNom;
    /** Параметры перенесены для удобства связи БД
     @param МуниципРайон {@link VidNameCodeType#adrULFIASMR}
     @param ГородСелПоселен {@link VidNameCodeType#adrULFIASGSP}
     @param НаселенПункт {@link VidNamePType#adrULFIAS}
     @param ЭлПланСтруктур {@link TypeNamePType#adrULFIASStruct}
     @param ЭлУлДорСети {@link TypeNamePType#adrULFIASSeti}
     @param Здание {@link NumberPType#adrULFIASPomeshZdanie}
     @param ПомещЗдания {@link NumberPType#adrULFIASPomeshZdanie}
     @param ПомещКвартиры {@link NumberPType#adrULFIASKvartira}
     @param ГРНДата {@link GRNDate#adrULFIAS}
     @param ГРНДатаИспр {@link GRNDate#adrULFIASIspr}

     */
}
