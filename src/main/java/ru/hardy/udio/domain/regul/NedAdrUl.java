package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.9 */

@Getter
@Setter
@Table(schema = "regul", name = "ned_adr_ul")
@XmlRootElement(name = "СвНедАдресЮЛ")
public class NedAdrUl {
    @Id
    private Long id;

    private String priznNedAdresUL;
    private String textNedAdresUL;
    @MappedCollection(idColumn = "nedadrul_id")
    private ReshSudType reshSudType;

    @XmlAttribute(name = "ПризнНедАдресЮЛ")
    public void setPriznNedAdresUL(String priznNedAdresUL) {
        this.priznNedAdresUL = priznNedAdresUL;
    }
    @XmlAttribute(name = "ТекстНедАдресЮЛ")
    public void setTextNedAdresUL(String textNedAdresUL) {
        this.textNedAdresUL = textNedAdresUL;
    }
    @XmlElement(name = "РешСудНедАдр")
    public void setReshSudType(ReshSudType reshSudType) {
        this.reshSudType = reshSudType;
    }
}
