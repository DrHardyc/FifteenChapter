package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** @version таблица 4.46 */
@Getter
@Setter
@Table(schema = "regul", name = "uchr_rf_sub_mo")
@XmlRootElement
public class UchrRFSubMO {
    @Id
    private Long id;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private VidNameUchr vidNameUchr;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private Set<NedDanUchrType> nedDanUchrType;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private DoliaUstKapEGRULType doliaUstKapEGRULType;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private ObemPravType obemPravType;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private Set<OrgOsushPr> orgOsushPr;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private Set<FLOcushPr> flOcushPr;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private Set<ObremType> obremType;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private Set<UpravZalType> upravZalType;
    @MappedCollection(idColumn = "uchrrfsubmo_id")
    private Set<ULEGRULType> ulegrulType;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "ВидНаимУчр")
    public void setVidNameUchr(VidNameUchr vidNameUchr) {
        this.vidNameUchr = vidNameUchr;
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
    @XmlElement(name = "СвОргОсущПр")
    public void setOrgOsushPr(Set<OrgOsushPr> orgOsushPr) {
        this.orgOsushPr = orgOsushPr;
    }
    @XmlElement(name = "СвФЛОсущПр")
    public void setFlOcushPr(Set<FLOcushPr> flOcushPr) {
        this.flOcushPr = flOcushPr;
    }
    @XmlElement(name = "СвОбрем")
    public void setObremType(Set<ObremType> obremType) {
        this.obremType = obremType;
    }
    @XmlElement(name = "СвУправЗал")
    public void setUpravZalType(Set<UpravZalType> upravZalType) {
        this.upravZalType = upravZalType;
    }
    @XmlElement(name = "СвДовУпрЮЛРФ")
    public void setUlegrulType(Set<ULEGRULType> ulegrulType) {
        this.ulegrulType = ulegrulType;
    }
}
