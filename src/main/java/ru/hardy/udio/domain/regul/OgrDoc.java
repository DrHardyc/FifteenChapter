package ru.hardy.udio.domain.regul;

import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

/** @version таблица 4.102 */
@Getter
@Setter
@Table(schema = "regul", name = "org_doc")
@XmlRootElement(name = "ОгрДосСвТип")
public class OgrDoc {
    @Id
    private Long id;
    private String ogrDocSv;

    @XmlAttribute(name = "ОгрДосСв")
    public void setOgrDocSv(String ogrDocSv) {
        this.ogrDocSv = ogrDocSv;
    }
}
