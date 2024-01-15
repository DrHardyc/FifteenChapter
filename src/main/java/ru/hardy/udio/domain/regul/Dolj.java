package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.34 */
@Getter
@Setter
@Table(schema = "regul", name = "dolj")
@XmlRootElement
public class Dolj {
    @Id
    private Long id;

    private String ogrnIp;
    private String vid;
    private String nameVid;
    private String name;

    @XmlAttribute(name = "ОГРНИП")
    public void setOgrnIp(String ogrnIp) {
        this.ogrnIp = ogrnIp;
    }
    @XmlAttribute(name = "ВидДолжн")
    public void setVid(String vid) {
        this.vid = vid;
    }
    @XmlAttribute(name = "НаимВидДолжн")
    public void setNameVid(String nameVid) {
        this.nameVid = nameVid;
    }
    @XmlAttribute(name = "НаимДолжн")
    public void setName(String name) {
        this.name = name;
    }
}
