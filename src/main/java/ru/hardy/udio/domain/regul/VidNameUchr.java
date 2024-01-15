package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.47 */
@Getter
@Setter
@Table(schema = "regul", name = "vid_name_uchr")
@XmlRootElement
public class VidNameUchr {
    @Id
    private Long id;

    private String code;
    private String name;
    private String codeRegion;
    private String nameRegion;

    @XmlAttribute(name = "КодУчрРФСубМО")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "НаимМО")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "КодРегион")
    public void setCodeRegion(String codeRegion) {
        this.codeRegion = codeRegion;
    }
    @XmlAttribute(name = "НаимРегион")
    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }
}
