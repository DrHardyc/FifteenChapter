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
@Table(schema = "regul", name = "reg_fss_ip")
@XmlRootElement(name = "СвРегФСС")
public class RegFSSIP {
    @Id
    private Long id;

    private String regNomFSS;
    private String datePrisvNom;
    private String dateReg;
    private String dateSnReg;
    @MappedCollection(idColumn = "regfssip_id")
    private OrgFSSIP orgFSSIP;

    @XmlAttribute(name = "РегНомФСС")
    public void setRegNomFSS(String regNomFSS) {
        this.regNomFSS = regNomFSS;
    }
    @XmlAttribute(name = "ДатаПрисвНом")
    public void setDatePrisvNom(String datePrisvNom) {
        this.datePrisvNom = datePrisvNom;
    }
    @XmlAttribute(name = "ДатаРег")
    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }
    @XmlAttribute(name = "ДатаСнРег")
    public void setDateSnReg(String dateSnReg) {
        this.dateSnReg = dateSnReg;
    }
    @XmlElement(name = "СвОргФСС")
    public void setOrgFSSIP(OrgFSSIP orgFSSIP) {
        this.orgFSSIP = orgFSSIP;
    }


    public RegFSSIP() {
    }

    public RegFSSIP(OrgFSSIP orgFSSIP) {
        this.orgFSSIP = orgFSSIP;
    }
}
