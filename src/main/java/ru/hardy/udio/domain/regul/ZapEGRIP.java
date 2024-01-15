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
@Table(schema = "regul", name = "zap_egrip")
@XmlRootElement(name = "СвЗапЕГРИП")
public class ZapEGRIP {
    @Id
    private Long id;

    private String idZap;
    private String grnip;
    private String dateZap;
    private String vidZap;
    private String regOrg;
    private String svedPredDoc;
    private String svid;

    @XmlAttribute(name = "ИдЗап")
    public void setIdZap(String idZap) {
        this.idZap = idZap;
    }
    @XmlAttribute(name = "ГРНИП")
    public void setGrnip(String grnip) {
        this.grnip = grnip;
    }
    @XmlAttribute(name = "ДатаЗап")
    public void setDateZap(String dateZap) {
        this.dateZap = dateZap;
    }
    @XmlAttribute(name = "ВидЗап")
    public void setVidZap(String vidZap) {
        this.vidZap = vidZap;
    }
    @XmlAttribute(name = "СвРегОрг")
    public void setRegOrg(String regOrg) {
        this.regOrg = regOrg;
    }
    @XmlAttribute(name = "СведПредДок")
    public void setSvedPredDoc(String svedPredDoc) {
        this.svedPredDoc = svedPredDoc;
    }
    @XmlAttribute(name = "СвСвид")
    public void setSvid(String svid) {
        this.svid = svid;
    }
}
