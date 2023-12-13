package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.annotation.Id;
/** @version таблица 4.2 */
@Getter
@Setter
@Table(schema = "regul", name = "sender_people")
@XmlRootElement(name = "")
public class SenderPeople {
    @Id
    private Long id;

    private String position;
    private String phone;
    private String email;
    @MappedCollection(idColumn = "senderpeople_id")
    private FIOType snpSenderPeople;

    @XmlAttribute(name = "ДолжОтв")
    public void setPosition(String position) {
        this.position = position;
    }
    @XmlAttribute(name = "Тлф")
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @XmlAttribute(name = "E-mail")
    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement(name = "ФИООтв")
    public void setSnpSenderPeople(FIOType snpSenderPeople) {
        this.snpSenderPeople = snpSenderPeople;
    }
}
