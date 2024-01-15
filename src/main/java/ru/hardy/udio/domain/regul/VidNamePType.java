package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.92 */
@Getter
@Setter
@Table(schema = "regul", name = "vid_name_p_type")
@XmlRootElement
public class VidNamePType {
    @Id
    private Long id;

    private String vid;
    private String name;

    @XmlAttribute(name = "Вид")
    public void setVid(String vid) {
        this.vid = vid;
    }
    @XmlAttribute(name = "Наим")
    public void setName(String name) {
        this.name = name;
    }
}
