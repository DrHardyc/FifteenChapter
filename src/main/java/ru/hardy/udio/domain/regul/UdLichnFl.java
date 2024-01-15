package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "ud_lichn")
@XmlRootElement(name = "СвУдЛичнФЛ")
public class UdLichnFl {
    @Id
    private Long id;
    @MappedCollection(idColumn = "ud_lichn_id")
    private UdLichnTypeP udLichnTypeP;

    @XmlElement(name = "УдЛичнТипР")
    public void setUdLichnTypeP(UdLichnTypeP udLichnTypeP) {
        this.udLichnTypeP = udLichnTypeP;
    }
}
