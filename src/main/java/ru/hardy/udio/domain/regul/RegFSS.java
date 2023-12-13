package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.25 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_fss")
@XmlRootElement(name = "СвРегФСС")
public class RegFSS {
    @Id
    private Long id;

    private String regNomFSS;
    private String datePriNom;
    private String dateReg;
    @MappedCollection(idColumn = "regfss_id")
    private OrgFSS orgFSS;

    @XmlAttribute(name = "РегНомФСС")
    public void setRegNomFSS(String regNomFSS) {
        this.regNomFSS = regNomFSS;
    }
    @XmlAttribute(name = "ДатаПрисвНом")
    public void setDatePriNom(String datePriNom) {
        this.datePriNom = datePriNom;
    }
    @XmlAttribute(name = "ДатаРег")
    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }
    @XmlElement(name = "СвОргФСС")
    public void setOrgFSS(OrgFSS orgFSS) {
        this.orgFSS = orgFSS;
    }
}
