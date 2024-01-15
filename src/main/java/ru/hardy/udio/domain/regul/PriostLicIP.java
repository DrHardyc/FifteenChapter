package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "priost_lic_ip")
@XmlRootElement(name = "СвПриостЛиц")
public class PriostLicIP {
    @Id
    private Long id;

    private String datePriostLic;
    private String licOrgPriostLic;

    @XmlAttribute(name = "ДатаПриостЛиц")
    public void setDatePriostLic(String datePriostLic) {
        this.datePriostLic = datePriostLic;
    }
    @XmlAttribute(name = "ЛицОргПриостЛиц")
    public void setLicOrgPriostLic(String licOrgPriostLic) {
        this.licOrgPriostLic = licOrgPriostLic;
    }
}
