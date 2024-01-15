package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "reg_pf_ip")
@XmlRootElement(name = "СвРегПФ")
public class RegPFIP {
    @Id
    private Long id;

    private String regNomPF;
    private String datePrisvNom;
    private String dateReg;
    @MappedCollection(idColumn = "regpfip_id")
    private OrgPFIP orgPFIP;


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
    public void setOrgPFIP(OrgPFIP orgPFIP) {
        this.orgPFIP = orgPFIP;
    }

    public RegPFIP() {
    }

    public RegPFIP(OrgPFIP orgPFIP) {
        this.orgPFIP = orgPFIP;
    }
}
