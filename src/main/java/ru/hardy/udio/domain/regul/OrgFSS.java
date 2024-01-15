package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.26*/
@Getter
@Setter
@Table(schema = "regul", name = "org_fss")
@XmlRootElement
public class OrgFSS {
    @Id
    private Long id;

    private String code;
    private String name;

    public OrgFSS() {
    }

    public OrgFSS(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "КодФСС")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "НаимФСС")
    public void setName(String name) {
        this.name = name;
    }
}
