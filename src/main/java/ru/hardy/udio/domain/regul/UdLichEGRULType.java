package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.136 */
@Getter
@Setter
@Table(schema = "regul", name = "ud_lichn_egrul_type")
@XmlRootElement
public class UdLichEGRULType {
    @Id
    private Long id;

    private String codeVidDoc;
    private String name;
    private String serNomDoc;
    private String dateDoc;
    private String vidDoc;
    private String codePodrazdDoc;

    @XmlAttribute(name = "КодВидДок")
    public void setCodeVidDoc(String codeVidDoc) {
        this.codeVidDoc = codeVidDoc;
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
    public void setCodePodrazdDoc(String codePodrazdDoc) {
        this.codePodrazdDoc = codePodrazdDoc;
    }
}
