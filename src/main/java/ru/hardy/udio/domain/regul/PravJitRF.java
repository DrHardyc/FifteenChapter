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
@Table(schema = "regul", name = "prav_jit_rf")
@XmlRootElement(name = "СвПравЖитРФ")
public class PravJitRF {
    @Id
    private Long id;
    private String srokDeistvDoc;
    @MappedCollection(idColumn = "pravjitrf_id")
    private UdLichnTypeP docPravJitRf;

    @XmlAttribute(name = "СрокДействДок")
    public void setSrokDeistvDoc(String srokDeistvDoc) {
        this.srokDeistvDoc = srokDeistvDoc;
    }
    @XmlElement(name = "ДокПравЖитРФ")
    public void setDocPravJitRf(UdLichnTypeP docPravJitRf) {
        this.docPravJitRf = docPravJitRf;
    }
}
