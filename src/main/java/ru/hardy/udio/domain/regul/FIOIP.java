package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "fio_ip")
@XmlRootElement
public class FIOIP {
    @Id
    private Long id;
    private String surname;
    private String name;
    private String patronymic;

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

    public FIOIP(){

    }

    public FIOIP(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }
}
