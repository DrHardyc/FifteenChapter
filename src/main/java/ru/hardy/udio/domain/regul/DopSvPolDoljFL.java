package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.36 */
@Getter
@Setter
@Table(schema = "regul", name = "dop_sv_pol_dolj_fl")
@XmlRootElement
public class DopSvPolDoljFL {
    @Id
    private Long id;

    private String priznak;
    private String text;

    @XmlAttribute(name = "ПризДопСвПолДолжнФЛ")
    public void setPriznak(String priznak) {
        this.priznak = priznak;
    }
    @XmlAttribute(name = "ТекстДопСвПолДолжнФЛ")
    public void setText(String text) {
        this.text = text;
    }
}
