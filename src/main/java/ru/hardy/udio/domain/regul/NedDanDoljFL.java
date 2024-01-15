package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.35 */
@Getter
@Setter
@Table(schema = "regul", name = "ned_dan_dolj_fl")
@XmlRootElement
public class NedDanDoljFL {
    @Id
    private Long id;

    private String priznakNedDanDoljFL;
    private String textNedDanDoljFL;
    @MappedCollection(idColumn = "neddandoljfl_id")
    private ReshSudType reshSudNedDanDoljFL;

    @XmlAttribute(name = "ПризнНедДанДолжнФЛ")
    public void setPriznakNedDanDoljFL(String priznakNedDanDoljFL) {
        this.priznakNedDanDoljFL = priznakNedDanDoljFL;
    }
    @XmlAttribute(name = "ТекстНедДанДолжнФЛ")
    public void setTextNedDanDoljFL(String textNedDanDoljFL) {
        this.textNedDanDoljFL = textNedDanDoljFL;
    }
    @XmlElement(name = "РешСудНедДанДолжнФЛ")
    public void setReshSudNedDanDoljFL(ReshSudType reshSudNedDanDoljFL) {
        this.reshSudNedDanDoljFL = reshSudNedDanDoljFL;
    }
}
