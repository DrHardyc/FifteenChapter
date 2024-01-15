package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.122 */
@Getter
@Setter
@Table(schema = "regul", name = "name_pred_ul_type")
@XmlRootElement
public class NamePredULType {
    @Id
    private Long id;
    private String name;

    @XmlAttribute(name = "НаимПредЮЛ")
    public void setName(String name) {
        this.name = name;
    }
}
