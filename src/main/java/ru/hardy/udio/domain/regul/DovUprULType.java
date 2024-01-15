package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.108 */
@Getter
@Setter
@Table(schema = "regul", name = "dov_up_ul_type")
@XmlRootElement
public class DovUprULType {
    @Id
    private Long id;

    private String dateOtkrNasl;
    @MappedCollection(idColumn = "dovuprultype_id")
    private NameINNDovUpr nameINNDovUpr;
    @MappedCollection(idColumn = "dovuprultype_id")
    private NamePolnType namePolnType;
    @MappedCollection(idColumn = "dovuprultype_id")
    private RegInULEGRULType regInULEGRULType;

    @XmlAttribute(name = "ДатаОткрНасл")
    public void setDateOtkrNasl(String dateOtkrNasl) {
        this.dateOtkrNasl = dateOtkrNasl;
    }
    @XmlElement(name = "НаимИННДовУпр")
    public void setNameINNDovUpr(NameINNDovUpr nameINNDovUpr) {
        this.nameINNDovUpr = nameINNDovUpr;
    }
    @XmlElement(name = "СвНаимЮЛПолнИн")
    public void setNamePolnType(NamePolnType namePolnType) {
        this.namePolnType = namePolnType;
    }
    @XmlElement(name = "СвРегИн")
    public void setRegInULEGRULType(RegInULEGRULType regInULEGRULType) {
        this.regInULEGRULType = regInULEGRULType;
    }
}
