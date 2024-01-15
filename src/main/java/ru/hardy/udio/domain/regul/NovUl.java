package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "nov_ul")
@XmlRootElement(name = "СвНовЮЛ")
public class NovUl {
    @Id
    private Long id;

    private String ogrn;
    private String inn;
    private String nameFull;

    @XmlAttribute(name = "ОГРН")
    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }
    @XmlAttribute(name = "ИНН")
    public void setInn(String inn) {
        this.inn = inn;
    }
    @XmlAttribute(name = "НаимЮЛПолн")
    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }
}

