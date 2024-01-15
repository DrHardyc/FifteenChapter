package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.68 */
@Getter
@Setter
@Table(schema = "regul", name = "reorg_ul")
@XmlRootElement
public class ReorgUL {
    @Id
    private Long id;

    private String ogrn;
    private String inn;
    private String nameLong;
    private String sostULPosle;

    @XmlAttribute(name = "ОГРН")
    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }
    @XmlAttribute(name = "ИНН")
    public void setInn(String inn) {
        this.inn = inn;
    }
    @XmlAttribute(name = "НаимЮЛПолн")
    public void setNameLong(String nameLong) {
        this.nameLong = nameLong;
    }
    @XmlAttribute(name = "СостЮЛпосле")
    public void setSostULPosle(String sostULPosle) {
        this.sostULPosle = sostULPosle;
    }
}
