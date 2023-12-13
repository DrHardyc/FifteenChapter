package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.100 */
@Getter
@Setter
@Table(schema = "regul", name = "id_grn_date_type")
@XmlRootElement(name = "ИдГРНДатаТип")
public class IdGRNDateType {
    @Id
    private Long id;

    private String idZap;
    private String grn;
    private String dateZap;

    @XmlAttribute(name = "ИдЗап")
    public void setIdZap(String idZap) {
        this.idZap = idZap;
    }
    @XmlAttribute(name = "ГРН")
    public void setGrn(String grn) {
        this.grn = grn;
    }
    @XmlAttribute(name = "ДатаЗап")
    public void setDateZap(String dateZap) {
        this.dateZap = dateZap;
    }
}
