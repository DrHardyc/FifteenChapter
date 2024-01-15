package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** @version таблица 4.43 */
@Getter
@Setter
@Table(schema = "regul", name = "uchr_ul_in")
@XmlRootElement
public class UchrULIn {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uchrulin_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "uchrulin_id")
    private ULEGRULType nameINNUL;
    @MappedCollection(idColumn = "uchrulin_id")
    private NamePolnType namePolnType;
    @MappedCollection(idColumn = "uchrulin_id")
    private RegInULEGRULType regInULEGRULType;
    @MappedCollection(idColumn = "uchrulin_id")
    private Set<NedDanUchrType> nedDanUchrType;
    @MappedCollection(idColumn = "uchrulin_id")
    private DoliaUstKapEGRULType doliaUstKapEGRULType;
    @MappedCollection(idColumn = "uchrulin_id")
    private Set<ObemPravType> obemPravType;
    @MappedCollection(idColumn = "uchrulin_id")
    private Set<ObremType> obremType;
    @MappedCollection(idColumn = "uchrulin_id")
    private Set<UpravZalType> upravZalType;
    @MappedCollection(idColumn = "uchrulin_id")
    private Set<DovUprULType> dovUprULType;
    @MappedCollection(idColumn = "uchrulin_id")
    private Set<DovUprFLType> dovUprFLType;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "НаимИННЮЛ")
    public void setNameINNUL(ULEGRULType nameINNUL) {
        this.nameINNUL = nameINNUL;
    }
    @XmlElement(name = "СвНаимЮЛПолнИн")
    public void setNamePolnType(NamePolnType namePolnType) {
        this.namePolnType = namePolnType;
    }
    @XmlElement(name = "СвРегИн")
    public void setRegInULEGRULType(RegInULEGRULType regInULEGRULType) {
        this.regInULEGRULType = regInULEGRULType;
    }
    @XmlElement(name = "СвНедДанУчр")
    public void setNedDanUchrType(Set<NedDanUchrType> nedDanUchrType) {
        this.nedDanUchrType = nedDanUchrType;
    }
    @XmlElement(name = "ДоляУстКап")
    public void setDoliaUstKapEGRULType(DoliaUstKapEGRULType doliaUstKapEGRULType) {
        this.doliaUstKapEGRULType = doliaUstKapEGRULType;
    }
    @XmlElement(name = "СвОбъемПрав")
    public void setObemPravType(Set<ObemPravType> obemPravType) {
        this.obemPravType = obemPravType;
    }
    @XmlElement(name = "СвОбрем")
    public void setObremType(Set<ObremType> obremType) {
        this.obremType = obremType;
    }
    @XmlElement(name = "СвУправЗал")
    public void setUpravZalType(Set<UpravZalType> upravZalType) {
        this.upravZalType = upravZalType;
    }
    @XmlElement(name = "СвДовУпрЮЛ")
    public void setDovUprULType(Set<DovUprULType> dovUprULType) {
        this.dovUprULType = dovUprULType;
    }
    @XmlElement(name = "СвДовУпрФЛ")
    public void setDovUprFLType(Set<DovUprFLType> dovUprFLType) {
        this.dovUprFLType = dovUprFLType;
    }
}
