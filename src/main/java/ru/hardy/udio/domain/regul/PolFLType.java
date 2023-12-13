package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.123 */
@Getter
@Setter
@Table(schema = "regul", name = "pol_fl_type")
@XmlRootElement(name = "СвПолФЛТип")
public class PolFLType {
    @Id
    private Long id;

    private String pol;
    @XmlAttribute(name = "Пол")
    public void setPol(String pol) {
        this.pol = pol;
    }
}
