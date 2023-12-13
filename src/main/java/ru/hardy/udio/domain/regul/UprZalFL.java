package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.129 */
@Getter
@Setter
@Table(schema = "regul", name = "upr_zal_fl")
@XmlRootElement(name = "СвУпрЗалФЛ")
public class UprZalFL {
    @Id
    private Long id;

    private String ogrn;
    @MappedCollection(idColumn = "uprzalfl_id")
    private FLEGRULType flegrulType;

    @XmlAttribute(name = "ОГРНИП")
    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }
    @XmlElement(name = "СвФЛ")
    public void setFlegrulType(FLEGRULType flegrulType) {
        this.flegrulType = flegrulType;
    }
}
