package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.97 */
@Getter
@Setter
@Table(schema = "regul", name = "dolia_ust_kap_egrul_type")
@XmlRootElement(name = "ДоляУстКапЕГРЮЛТип")
public class DoliaUstKapEGRULType {
    @Id
    private Long id;

    private String name;
    @MappedCollection(idColumn = "doliaustkapegrultype_id")
    private DrobType drobType;
    @MappedCollection(idColumn = "doliaustkapegrultype_id")
    private RazmerDoliType razmerDoliType;

    @XmlAttribute(name = "НоминСтоим")
    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "ДоляРубля")
    public void setDrobType(DrobType drobType) {
        this.drobType = drobType;
    }
    @XmlElement(name = "РазмерДоли")
    public void setRazmerDoliType(RazmerDoliType razmerDoliType) {
        this.razmerDoliType = razmerDoliType;
    }
}
