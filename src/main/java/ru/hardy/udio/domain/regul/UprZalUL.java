package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.130 */
@Getter
@Setter
@Table(schema = "regul", name = "upr_zal_ul")
@XmlRootElement(name = "СвУпрЗалЮЛ")
public class UprZalUL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uprzalul_id")
    private ULEGRULType ulegrulType;
    @MappedCollection(idColumn = "uprzalul_id")
    private NamePolnType namePoLnType;
    @MappedCollection(idColumn = "uprzalul_id")
    private RegInULEGRULType regInULEGRULType;

    @XmlElement(name = "НаимИННЮЛ")
    public void setUlegrulType(ULEGRULType ulegrulType) {
        this.ulegrulType = ulegrulType;
    }
    @XmlElement(name = "СвНаимЮЛПолнИн")
    public void setNamePoLnType(NamePolnType namePoLnType) {
        this.namePoLnType = namePoLnType;
    }
    @XmlElement(name = "СвРегИн")
    public void setRegInULEGRULType(RegInULEGRULType regInULEGRULType) {
        this.regInULEGRULType = regInULEGRULType;
    }
}
