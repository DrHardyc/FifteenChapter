package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.128 */
@Getter
@Setter
@Table(schema = "regul", name = "uprav_zal_type")
@XmlRootElement(name = "СвУправЗалТип")
public class UpravZalType {
    @Id
    private Long id;

    @MappedCollection(idColumn = "upravzaltype_id")
    private NotUdDogZalType uprNotFL;
    @MappedCollection(idColumn = "upravzaltype_id")
    private NotUdDogZalType uprDogFL;
    @MappedCollection(idColumn = "upravzaltype_id")
    private UprZalFL uprZalFL;
    @MappedCollection(idColumn = "upravzaltype_id")
    private UprZalUL uprZalUL;

    @XmlElement(name = "СвНотУдДогЗал")
    public void setUprNotFL(NotUdDogZalType uprNotFL) {
        this.uprNotFL = uprNotFL;
    }
    @XmlElement(name = "СвДогУправЗал")
    public void setUprDogFL(NotUdDogZalType uprDogFL) {
        this.uprDogFL = uprDogFL;
    }
    @XmlElement(name = "СвУпрЗалФЛ")
    public void setUprZalFL(UprZalFL uprZalFL) {
        this.uprZalFL = uprZalFL;
    }
    @XmlElement(name = "СвУпрЗалЮЛ")
    public void setUprZalUL(UprZalUL uprZalUL) {
        this.uprZalUL = uprZalUL;
    }
}
