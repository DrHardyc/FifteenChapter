package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.132 */
@Getter
@Setter
@Table(schema = "regul", name = "fl_egrul_type")
@XmlRootElement(name = "СвФЛЕГРЮЛТип")
public class FLEGRULType {
    @Id
    private Long id;

    private String surname;
    private String name;
    private String patronymic;
    private String innfl;

    @XmlAttribute(name = "Фамилия")
    public void setSurname(String surname) {
        this.surname = surname;
    }
    @XmlAttribute(name = "Имя")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "Отчество")
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    @XmlAttribute(name = "ИННФЛ")
    public void setInnfl(String innfl) {
        this.innfl = innfl;
    }
}
