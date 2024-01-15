package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "rojd")
@XmlRootElement(name = "СвРожд")
public class Rojd {
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
