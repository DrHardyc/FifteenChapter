package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.118 */
@Getter
@Setter
@Table(schema = "regul", name = "zalog_derj_ul")
@XmlRootElement(name = "СвЗалогДержЮЛ")
public class ZalogDerjUL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "zalogderjul_id")
    private ULEGRULType nameINNUL;
    @MappedCollection(idColumn = "zalogderjul_id")
    private NamePolnType namePolnType;
    @MappedCollection(idColumn = "zalogderjul_id")
    private RegInULEGRULType regInULEGRULType;
    @MappedCollection(idColumn = "zalogderjul_id")
    private NotUdDogZalType notUdDogZalType;

    @XmlElement(name = "НаимИННЮЛ")
    public void setNameINNUL(ULEGRULType nameINNUL) {
        this.nameINNUL = nameINNUL;
    }
    @XmlElement(name = "СвНаимЮЛПолнИн")
    public void setNamePolnType(NamePolnType namePolnType) {
        this.namePolnType = namePolnType;
    }
    @XmlElement(name = "СвРегИн")
    public void setRegInULEGRULType(RegInULEGRULType regInULEGRULType) {
        this.regInULEGRULType = regInULEGRULType;
    }
    @XmlElement(name = "СвНотУдДогЗал")
    public void setNotUdDogZalType(NotUdDogZalType notUdDogZalType) {
        this.notUdDogZalType = notUdDogZalType;
    }
}
