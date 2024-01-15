package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.31 */
@Getter
@Setter
@Table(schema = "regul", name = "name_inn_ul")
@XmlRootElement
public class NameINNUL {
    @Id
    private Long id;

    private String ogrn;
    private String inn;
    private String nameLong;
    private String dopSv;

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
    @XmlAttribute(name = "ДопСв")
    public void setDopSv(String dopSv) {
        this.dopSv = dopSv;
    }
}
