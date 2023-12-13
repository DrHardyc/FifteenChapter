package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.32 */
@Getter
@Setter
@Table(schema = "regul", name = "ned_dan_upr_org")
@XmlRootElement(name = "СвНедДанУпрОрг")
public class NedDanUprOrg {
    @Id
    private Long id;

    private String priznNedDanUprOrg;
    private String textNedDanUprOrg;
    @MappedCollection(idColumn = "neddanuprorg_id")
    private ReshSudType reshSudType;

    @XmlAttribute(name = "ПризнНедДанУпрОрг")
    public void setPriznNedDanUprOrg(String priznNedDanUprOrg) {
        this.priznNedDanUprOrg = priznNedDanUprOrg;
    }
    @XmlAttribute(name = "ТекстНедДанУпрОрг")
    public void setTextNedDanUprOrg(String textNedDanUprOrg) {
        this.textNedDanUprOrg = textNedDanUprOrg;
    }
    @XmlElement(name = "РешСудНедДанУпрОрг")
    public void setReshSudType(ReshSudType reshSudType) {
        this.reshSudType = reshSudType;
    }
}
