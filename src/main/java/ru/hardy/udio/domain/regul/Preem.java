package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.72 */
@Getter
@Setter
@Table(schema = "regul", name = "preem")
@XmlRootElement
public class Preem {
    @Id
    private Long id;

    private String ogrn;
    private String inn;
    private String nameLong;
    @MappedCollection(idColumn = "preem")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "preem")
    private ULSlojReorg ulSlojReorg;

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
    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "СвЮЛсложнРеорг")
    public void setUlSlojReorg(ULSlojReorg ulSlojReorg) {
        this.ulSlojReorg = ulSlojReorg;
    }
}
