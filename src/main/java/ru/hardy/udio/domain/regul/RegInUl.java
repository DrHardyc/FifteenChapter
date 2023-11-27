package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.14 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_in_ul")
public class RegInUl {
    @Id
    private Long id;

    private String inn;
    private String nameRu;
    private String nameEn;
    private String oksm;
    private String nameCountry;
    private String dateReg;
    private String regNum;
    private String codeIOStrReg;
}
