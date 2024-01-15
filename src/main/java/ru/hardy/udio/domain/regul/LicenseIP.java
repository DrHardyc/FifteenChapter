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
@Table(schema = "regul", name = "license_ip")
@XmlRootElement(name = "СвЛицензия")
public class LicenseIP {
    @Id
    private Long id;

    private String numberERUL;
    private String nomLic;
    private String dateLic;
    private String dateNachLic;
    private String dateOkonchLic;
    private String nameVidLic;
    private String licOrgVidLic;
    @MappedCollection(idColumn = "licenseip_id")
    private PriostLicIP priostLicIP;

    @XmlAttribute(name = "НомерЕРУЛ")
    public void setNumberERUL(String numberERUL) {
        this.numberERUL = numberERUL;
    }
    @XmlAttribute(name = "НомЛиц")
    public void setNomLic(String nomLic) {
        this.nomLic = nomLic;
    }
    @XmlAttribute(name = "ДатаЛиц")
    public void setDateLic(String dateLic) {
        this.dateLic = dateLic;
    }
    @XmlAttribute(name = "ДатаНачЛиц")
    public void setDateNachLic(String dateNachLic) {
        this.dateNachLic = dateNachLic;
    }
    @XmlAttribute(name = "ДатаОкончЛиц")
    public void setDateOkonchLic(String dateOkonchLic) {
        this.dateOkonchLic = dateOkonchLic;
    }
    @XmlAttribute(name = "НаимЛицВидДеят")
    public void setNameVidLic(String nameVidLic) {
        this.nameVidLic = nameVidLic;
    }
    @XmlAttribute(name = "ЛицОргВыдЛиц")
    public void setLicOrgVidLic(String licOrgVidLic) {
        this.licOrgVidLic = licOrgVidLic;
    }
    @XmlElement(name = "СвПриостЛиц")
    public void setPriostLicIP(PriostLicIP priostLicIP) {
        this.priostLicIP = priostLicIP;
    }
}
