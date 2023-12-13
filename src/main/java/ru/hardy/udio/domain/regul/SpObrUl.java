package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.13 */
@Getter
@Setter
@Table(schema = "regul", name = "sp_obr_ul")
@XmlRootElement(name = "СпОбрЮЛ")
public class SpObrUl {
    @Id
    private Long id;

    private String code;
    private String name;

    @XmlAttribute(name = "КодСпОбрЮЛ")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "НаимСпОбрЮЛ")
    public void setName(String name) {
        this.name = name;
    }
}
