package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.99 */
@Getter
@Setter
@Table(schema = "regul", name = "drob_type")
@XmlRootElement(name = "ДробьТип")
public class DrobType {
    @Id
    private Long id;

    private String numerator;
    private String denominator;

    @XmlAttribute(name = "Числит")
    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }
    @XmlAttribute(name = "Знаменат")
    public void setDenominator(String denominator) {
        this.denominator = denominator;
    }
}
