package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "okved_ip")
@XmlRootElement(name = "СвОКВЭДЕГРИПТип")
public class OKVEDIP {
    @Id
    private Long id;

    private String osn;
    private String dop;

    @XmlAttribute(name = "СвОКВЭДОсн")
    public void setOsn(String osn) {
        this.osn = osn;
    }
    @XmlAttribute(name = "СвОКВЭДДоп")
    public void setDop(String dop) {
        this.dop = dop;
    }
}
