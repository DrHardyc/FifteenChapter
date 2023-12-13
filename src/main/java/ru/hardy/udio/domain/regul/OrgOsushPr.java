package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.48 */
@Getter
@Setter
@Table(schema = "regul", name = "org_osush_pr")
@XmlRootElement(name = "СвОргОсущПр")
public class OrgOsushPr {
    @Id
    private Long id;

    @MappedCollection(idColumn = "orgosushpr_id")
    private ULEGRULType ulegrulType;

    @XmlElement(name = "НаимИННЮЛ")
    public void setUlegrulType(ULEGRULType ulegrulType) {
        this.ulegrulType = ulegrulType;
    }
}
