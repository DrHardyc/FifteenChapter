package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.62 */
@Getter
@Setter
@Table(schema = "regul", name = "priost_lic")
@XmlRootElement
public class PriostLic {
    @Id
    private Long id;

    private String date;
    private String licOrg;
    @XmlAttribute(name = "ДатаПриостЛиц")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlAttribute(name = "ЛицОргПриостЛиц")
    public void setLicOrg(String licOrg) {
        this.licOrg = licOrg;
    }
}
