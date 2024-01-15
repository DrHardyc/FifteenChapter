package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.19 */
@Getter
@Setter
@Table(schema = "regul", name = "prek_ul")
@XmlRootElement
public class PrekrUl {
    @Id
    private Long id;

    private String datePrekrUl;
    @MappedCollection(idColumn = "prekrul_id")
    private SpPrekrUl prekrUl;
    @MappedCollection(idColumn = "prekrul_id")
    private RegOrgPrekr regOrg;

    @XmlAttribute(name = "ДатаПрекрЮЛ")
    public void setDatePrekrUl(String datePrekrUl) {
        this.datePrekrUl = datePrekrUl;
    }
    @XmlElement(name = "СпПрекрЮЛ")
    public void setPrekrUl(SpPrekrUl prekrUl) {
        this.prekrUl = prekrUl;
    }
    @XmlElement(name = "СвРегОрг")
    public void setRegOrg(RegOrgPrekr regOrg) {
        this.regOrg = regOrg;
    }
}
