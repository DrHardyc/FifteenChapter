package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;


/** @version  таблице 4.5 */

@Getter
@Setter
@Table(schema = "regul", name = "name_ul")
@XmlRootElement
public class NameUL {
    @Id
    private Long id;
    private String fullName;
    @MappedCollection(idColumn = "nameul_id")
    private ShortNameUlType shortName;
    @MappedCollection(idColumn = "nameul_id")
    private Set<NameUlKodOKIN> nameUlKodOKIN;
    @MappedCollection(idColumn = "nameul_id")
    private NamePolnType namePoLnType;
    @MappedCollection(idColumn = "nameul_id")
    private NameSokrType nameSokrType;

    @XmlAttribute(name = "НаимЮЛПолн")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @XmlElement(name = "СвНаимЮЛСокр")
    public void setShortName(ShortNameUlType shortName) {
        this.shortName = shortName;
    }
    @XmlElement(name = "СвНаимЮЛКодОКИН")
    public void setNameUlKodOKIN(Set<NameUlKodOKIN> nameUlKodOKIN) {
        this.nameUlKodOKIN = nameUlKodOKIN;
    }
    @XmlElement(name = "СвНаимЮЛПолнИн")
    public void setNamePoLnType(NamePolnType namePoLnType) {
        this.namePoLnType = namePoLnType;
    }
    @XmlElement(name = "СвНаимЮЛСокрИн")
    public void setNameSokrType(NameSokrType nameSokrType) {
        this.nameSokrType = nameSokrType;
    }


    public NameUL(){}

    public NameUL(String fullName, ShortNameUlType shortName){
        this.fullName = fullName;
        this.shortName = shortName;
    }
}
