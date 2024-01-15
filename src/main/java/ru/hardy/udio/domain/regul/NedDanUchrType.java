package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.112 */
@Getter
@Setter
@Table(schema = "regul", name = "ned_dan_uchr_type")
@XmlRootElement
public class NedDanUchrType {
    @Id
    private Long id;
    private String priznNedDanUchr;
    private String textNedDanUchr;
    @MappedCollection(idColumn = "neddanuchrtype_id")
    private ReshSudType reshSudType;

    @XmlAttribute(name = "ПризнНедДанУчр")
    public void setPriznNedDanUchr(String priznNedDanUchr) {
        this.priznNedDanUchr = priznNedDanUchr;
    }
    @XmlAttribute(name = "ТекстНедДанУчр")
    public void setTextNedDanUchr(String textNedDanUchr) {
        this.textNedDanUchr = textNedDanUchr;
    }
    @XmlElement(name = "РешСудНедДанУчр")
    public void setReshSudType(ReshSudType reshSudType) {
        this.reshSudType = reshSudType;
    }
}
