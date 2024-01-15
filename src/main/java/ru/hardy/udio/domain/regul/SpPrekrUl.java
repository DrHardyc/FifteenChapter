package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.20 */
@Getter
@Setter
@Table(schema = "regul", name = "sp_prekr_ul")
@XmlRootElement
public class SpPrekrUl {
    @Id
    private Long id;

    private String codeSpPrekrUL;
    private String nameSpPrekrUL;

    @XmlElement(name = "КодСпПрекрЮЛ")
    public void setCodeSpPrekrUL(String codeSpPrekrUL) {
        this.codeSpPrekrUL = codeSpPrekrUL;
    }
    @XmlElement(name = "НаимСпПрекрЮЛ")
    public void setNameSpPrekrUL(String nameSpPrekrUL) {
        this.nameSpPrekrUL = nameSpPrekrUL;
    }
}
