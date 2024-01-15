package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "ud_lichn_type_p")
@XmlRootElement(name = "УдЛичнТипР")
public class UdLichnTypeP {
    @Id
    private Long id;
    private String codeVidDoc;
    private String nameDoc;
    private String serNumDoc;
    private String dateDoc;
    private String vidachDoc;
    private String codeVidachDoc;

    @XmlAttribute(name = "КодВидДок")
    public void setCodeVidDoc(String codeVidDoc) {
        this.codeVidDoc = codeVidDoc;
    }
    @XmlAttribute(name = "НаимДок")
    public void setNameDoc(String nameDoc) {
        this.nameDoc = nameDoc;
    }
    @XmlAttribute(name = "СерНомДок")
    public void setSerNumDoc(String serNumDoc) {
        this.serNumDoc = serNumDoc;
    }
    @XmlAttribute(name = "ДатаДок")
    public void setDateDoc(String dateDoc) {
        this.dateDoc = dateDoc;
    }
    @XmlAttribute(name = "ВыдДок")
    public void setVidachDoc(String vidachDoc) {
        this.vidachDoc = vidachDoc;
    }
    @XmlAttribute(name = "КодВыдДок")
    public void setCodeVidachDoc(String codeVidachDoc) {
        this.codeVidachDoc = codeVidachDoc;
    }
}
