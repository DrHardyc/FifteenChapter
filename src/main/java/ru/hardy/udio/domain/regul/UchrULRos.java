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

/** @version таблица 4.41 */
@Getter
@Setter
@Table(schema = "regul", name = "uchr_ul_ros")
@XmlRootElement(name = "УчрЮЛРос")
public class UchrULRos {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uchrulros_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "uchrulros_id")
    private ULEGRULType nameINNUL;
    @MappedCollection(idColumn = "uchrulros_id")
    private RegStarie regStarie;
    @MappedCollection(idColumn = "uchrulros_id")
    private Set<NedDanUchrType> nedDanUchrType;
    @MappedCollection(idColumn = "uchrulros_id")
    private DoliaUstKapEGRULType doliaUstKapEGRULType;
    @MappedCollection(idColumn = "uchrulros_id")
    private ObemPravType obemPravType;
    @MappedCollection(idColumn = "uchrulros_id")
    private Set<ObremType> obremType;
    @MappedCollection(idColumn = "uchrulros_id")
    private Set<UpravZalType> upravZalType;
    @MappedCollection(idColumn = "uchrulros_id")
    private Set<DovUprULType> dovUprULType;
    @MappedCollection(idColumn = "uchrulros_id")
    private Set<DovUprFLType> dovUprFLType;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "НаимИННЮЛ")
    public void setNameINNUL(ULEGRULType nameINNUL) {
        this.nameINNUL = nameINNUL;
    }
    @XmlElement(name = "СвРегСтарые")
    public void setRegStarie(RegStarie regStarie) {
        this.regStarie = regStarie;
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
    public void setObemPravType(ObemPravType obemPravType) {
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
