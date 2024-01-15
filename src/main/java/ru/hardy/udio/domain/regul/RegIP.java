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
@Table(schema = "regul", name = "reg_ip")
@XmlRootElement(name = "СвРегИП")
public class RegIP {
    @Id
    private Long id;
    private String ogrnIp;
    private String dateOgrnIp;
    private String regNum;
    private String dateReg;
    private String nameRo;
    @MappedCollection(idColumn = "regip_id")
    private KFH kfh;

    @XmlAttribute(name = "ОГРНИП")
    public void setOgrnIp(String ogrnIp) {
        this.ogrnIp = ogrnIp;
    }
    @XmlAttribute(name = "ДатаОГРНИП")
    public void setDateOgrnIp(String dateOgrnIp) {
        this.dateOgrnIp = dateOgrnIp;
    }
    @XmlAttribute(name = "РегНом")
    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }
    @XmlAttribute(name = "ДатаРег")
    public void setDateReg(String dateReg) {
        this.dateReg = dateReg;
    }
    @XmlAttribute(name = "НаимРО")
    public void setNameRo(String nameRo) {
        this.nameRo = nameRo;
    }
    @XmlElement(name = "СвКФХ")
    public void setKfh(KFH kfh) {
        this.kfh = kfh;
    }
}
