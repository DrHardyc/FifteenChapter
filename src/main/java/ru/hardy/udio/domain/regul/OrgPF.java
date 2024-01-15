package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.24 */
@Getter
@Setter
@Table(schema = "regul", name = "org_pf")
@XmlRootElement
public class OrgPF {
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

    public OrgPF(){}
    public OrgPF(String name){
        this.name = name;
    }
}
