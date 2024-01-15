package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "org_fss_ip")
@XmlRootElement(name = "СвОргФСС")
public class OrgFSSIP {
    @Id
    private Long id;

    private String code;
    private String name;


    @XmlAttribute(name = "КодФСС")
    public void setCode(String code) {
        this.code = code;
    }

    @XmlAttribute(name = "НаимФСС")
    public void setName(String name) {
        this.name = name;
    }

    public OrgFSSIP() {
    }

    public OrgFSSIP(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
