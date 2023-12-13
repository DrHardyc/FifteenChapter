package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.58 */
@Getter
@Setter
@Table(schema = "regul", name = "zaim_dav_ul")
@XmlRootElement(name = "ЗаймДавЮЛ")
public class ZaimDavUL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "zaimdavul_id")
    private ULEGRULType ulegrulType;
    @MappedCollection(idColumn = "zaimdavul_id")
    private NamePolnType namePolnType;
    @MappedCollection(idColumn = "zaimdavul_id")
    private RegInULEGRULType regInULEGRULType;

    @XmlElement(name = "НаимИННЮЛ")
    public void setUlegrulType(ULEGRULType ulegrulType) {
        this.ulegrulType = ulegrulType;
    }
    @XmlElement(name = "СвНаимЮЛПолнИн")
    public void setNamePolnType(NamePolnType namePolnType) {
        this.namePolnType = namePolnType;
    }
    @XmlElement(name = "СвРегИн")
    public void setRegInULEGRULType(RegInULEGRULType regInULEGRULType) {
        this.regInULEGRULType = regInULEGRULType;
    }
}
