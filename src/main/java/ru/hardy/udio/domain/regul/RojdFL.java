package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.82 */
@Getter
@Setter
@Table(schema = "regul", name = "rojd_fl")
@XmlRootElement(name = "СвРожд")
public class RojdFL {
    @Id
    private Long id;

    private String date;
    private String mesto;
    private String prDate;

    @XmlAttribute(name = "ДатаРожд")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlAttribute(name = "МестоРожд")
    public void setMesto(String mesto) {
        this.mesto = mesto;
    }
    @XmlAttribute(name = "ПрДатаРожд")
    public void setPrDate(String prDate) {
        this.prDate = prDate;
    }
}
