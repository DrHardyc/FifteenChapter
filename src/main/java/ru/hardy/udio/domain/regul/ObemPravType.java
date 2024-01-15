package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.115 */
@Getter
@Setter
@Table(schema = "regul", name = "obem_prav_type")
@XmlRootElement
public class ObemPravType {
    @Id
    private Long id;

    private String obemPrav;

    @XmlAttribute(name = "ОбъемПрав")
    public void setObemPrav(String obemPrav) {
        this.obemPrav = obemPrav;
    }
}
