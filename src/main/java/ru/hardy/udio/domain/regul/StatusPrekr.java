package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "status_pr")
@XmlRootElement(name = "СвСтатус")
public class StatusPrekr {

    @Id
    private Long id;

    private String code;
    private String name;
    private String datePrekr;

    @XmlAttribute(name = "КодСтатус")
    public void setCode(String code) {
        this.code = code;
    }

    @XmlAttribute(name = "НаимСтатус")
    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "ДатаПрекращ")
    public void setDatePrekr(String datePrekr) {
        this.datePrekr = datePrekr;
    }
}
