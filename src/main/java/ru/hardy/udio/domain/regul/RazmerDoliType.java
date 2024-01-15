package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.98 */
@Getter
@Setter
@Table(schema = "regul", name = "razmer_doli_type")
@XmlRootElement
public class RazmerDoliType {
    @Id
    private Long id;

    private String percent;
    private String drobDesyat;
    @MappedCollection(idColumn = "razmerdolitype_id")
    private DrobType drobProst;

    @XmlAttribute(name = "Процент")
    public void setPercent(String percent) {
        this.percent = percent;
    }
    @XmlAttribute(name = "ДробДесят")
    public void setDrobDesyat(String drobDesyat) {
        this.drobDesyat = drobDesyat;
    }
    @XmlElement(name = "ДробПрост")
    public void setDrobProst(DrobType drobProst) {
        this.drobProst = drobProst;
    }
}
