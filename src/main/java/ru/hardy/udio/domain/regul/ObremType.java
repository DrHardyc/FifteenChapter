package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.116 */
@Getter
@Setter
@Table(schema = "regul", name = "obrem_type")
@XmlRootElement
public class ObremType {
    @Id
    private Long id;
    private String vid;
    @MappedCollection(idColumn = "obremtype_id")
    private ReshSudType reshSudType;
    @MappedCollection(idColumn = "obremtype_id")
    private ZalogDerjFL zalogDerjFL;
    @MappedCollection(idColumn = "obremtype_id")
    private ZalogDerjUL zalogDerjUL;

    @XmlAttribute(name = "ВидОбрем")
    public void setVid(String vid) {
        this.vid = vid;
    }
    @XmlElement(name = "РешСуд")
    public void setReshSudType(ReshSudType reshSudType) {
        this.reshSudType = reshSudType;
    }
    @XmlElement(name = "СвЗалогДержФЛ")
    public void setZalogDerjFL(ZalogDerjFL zalogDerjFL) {
        this.zalogDerjFL = zalogDerjFL;
    }
    @XmlElement(name = "СвЗалогДержЮЛ")
    public void setZalogDerjUL(ZalogDerjUL zalogDerjUL) {
        this.zalogDerjUL = zalogDerjUL;
    }
}
