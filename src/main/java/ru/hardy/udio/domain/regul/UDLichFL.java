package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.83 */
@Getter
@Setter
@Table(schema = "regul", name = "ud_lich_fl")
@XmlRootElement(name = "УдЛичнФЛ")
public class UDLichFL {
    @Id
    private Long id;

    private String code;
    private String name;
    private String serNomDoc;
    private String dateDoc;
    private String vidDoc;
    private String codeVidDoc;

    @XmlAttribute(name = "КодВидДок")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "НаимДок")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "СерНомДок")
    public void setSerNomDoc(String serNomDoc) {
        this.serNomDoc = serNomDoc;
    }
    @XmlAttribute(name = "ДатаДок")
    public void setDateDoc(String dateDoc) {
        this.dateDoc = dateDoc;
    }
    @XmlAttribute(name = "ВыдДок")
    public void setVidDoc(String vidDoc) {
        this.vidDoc = vidDoc;
    }
    @XmlAttribute(name = "КодВыдДок")
    public void setCodeVIdDoc(String codeVIdDoc) {
        this.codeVidDoc = codeVIdDoc;
    }
}
