package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;


/** @version  таблица 4.11 */
@Getter
@Setter
@Table(schema = "regul", name = "email_ul")
@XmlRootElement(name = "СвАдрЭлПочты")
public class EmailUl {
    @Id
    private Long id;
    private String email;

    @XmlAttribute(name = "E-mail")
    public void setEmail(String email) {
        this.email = email;
    }
}
