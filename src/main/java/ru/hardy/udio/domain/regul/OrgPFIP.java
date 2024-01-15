package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "org_pf_ip")
@XmlRootElement(name = "СвОргПФ")
public class OrgPFIP {

    @Id
    private Long id;

    private String code;
    private String name;

    @XmlAttribute(name = "КодПФ")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "НаимПФ")
    public void setName(String name) {
        this.name = name;
    }

    public OrgPFIP() {
    }

    public OrgPFIP(String code, String name) {
        this.name = name;
    }
}
