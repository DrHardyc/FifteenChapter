package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.40 */
@Getter
@Setter
@Table(schema = "regul", name = "zapret_rasp_doliami")
@XmlRootElement(name = "СвЗапретРаспДолями")
public class ZapretRaspDoliami {
    @Id
    private Long id;

    private String textZapretRaspDoliami;

    @XmlAttribute(name = "ТексЗапретРаспДолями")
    public void setTextZapretRaspDoliami(String textZapretRaspDoliami) {
        this.textZapretRaspDoliami = textZapretRaspDoliami;
    }
}
