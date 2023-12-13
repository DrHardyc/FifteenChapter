package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.67 */
@Getter
@Setter
@Table(schema = "regul", name = "status_reorg")
@XmlRootElement(name = "СвСтатус")
public class StatusReorg {
    @Id
    private Long id;

    private String code;
    private String name;

    @XmlAttribute(name = "КодСтатусЮЛ")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "НаимСтатусЮЛ")
    public void setName(String name) {
        this.name = name;
    }
}
