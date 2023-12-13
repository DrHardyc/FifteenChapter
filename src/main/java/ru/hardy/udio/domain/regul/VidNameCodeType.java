package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.91 */
@Getter
@Setter
@Table(schema = "regul", name = "vid_name_code_type")
@XmlRootElement(name = "ВидНаимКодТип")
public class VidNameCodeType {
    @Id
    private Long id;

    private String vidKod;
    private String name;

    @XmlAttribute(name = "ВидКод")
    public void setVidKod(String vidKod) {
        this.vidKod = vidKod;
    }
    @XmlAttribute(name = "Наим")
    public void setName(String name) {
        this.name = name;
    }
}
