package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.104 */

@Getter
@Setter
@Table(schema = "regul", name = "region_type")
@XmlRootElement(name = "РегионТип")
public class RegionType {
    @Id
    private Long id;

    private String typeRegion;
    private String nameRegion;

    @XmlAttribute(name = "ТипРегион")
    public void setTypeRegion(String typeRegion) {
        this.typeRegion = typeRegion;
    }
    @XmlAttribute(name = "НаимРегион")
    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }
}
