package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.3 */
@Getter
@Setter
@Table(schema = "regul", name = "document_ul")
@XmlRootElement(name = "Документ")
public class DocumentUL {
    @Id
    private Long id;

    private String idDoc;
    @MappedCollection(idColumn = "documentul_id")
    private PersonUL personUL;

    @XmlAttribute(name = "ИдДок")
    public void setIdDoc(String idDoc) {
        this.idDoc = idDoc;
    }
    @XmlElement(name = "СвЮЛ")
    public void setPersonUL(PersonUL personUL) {
        this.personUL = personUL;
    }
}
