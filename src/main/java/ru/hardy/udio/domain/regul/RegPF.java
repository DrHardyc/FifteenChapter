package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.23 */
@Getter
@Setter
@Table(schema = "regul", name = "reg_pf")
@XmlRootElement
public class RegPF {
    @Id
    private Long id;

    private String regNomPF;
    private String datePrisvNom;
    private String dateReg;
    @MappedCollection(idColumn = "regpf_id")
    private OrgPF orgPF;

    @XmlAttribute(name = "РегНомПФ")
    public void setRegNomPF(String regNomPF) {
        this.regNomPF = regNomPF;
    }
    @XmlAttribute(name = "ДатаПрисвНом")
    public void setDatePrisvNom(String datePrisvNom) {
        this.datePrisvNom = datePrisvNom;
    }
    @XmlAttribute(name = "ДатаРег")
    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }
    @XmlElement(name = "СвОргПФ")
    public void setOrgPF(OrgPF orgPF) {
        this.orgPF = orgPF;
    }

    public RegPF(){}

    public RegPF(String regNomPF, OrgPF orgPF){
        this.regNomPF = regNomPF;
        this.orgPF = orgPF;
    }
}
