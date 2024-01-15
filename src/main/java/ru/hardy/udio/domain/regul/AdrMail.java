package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "adr_mail")
@XmlRootElement(name = "СвАдрЭлПочты")
public class AdrMail {
    @Id
    private Long id;
    private String email;

    @XmlAttribute(name = "E-mail")
    public void setEmail(String email) {
        this.email = email;
    }
}
