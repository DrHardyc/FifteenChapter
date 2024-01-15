package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** @version таблица 4.44 */
@Getter
@Setter
@Table(schema = "regul", name = "uchr_fl")
@XmlRootElement
public class UchrFL {
    @Id
    private Long id;

    private String vidAdrKlassif;
    @Column("ogrn_ip")
    private String ogrnip;
    @MappedCollection(idColumn = "uchrfl_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "uchrfl_id")
    private FLEGRULType flegrulType;
    @MappedCollection(idColumn = "uchrfl_id")
    private FIOZAGSType fiozagsType;
    @MappedCollection(idColumn = "uchrfl_id")
    private Set<NedDanUchrType> nedDanUchrType;
    @MappedCollection(idColumn = "uchrfl_id")
    private PolFLType polFLType;
    @MappedCollection(idColumn = "uchrfl_id")
    private RojdEGRULType rojdEGRULType;
    @MappedCollection(idColumn = "uchrfl_id")
    private ZapRojdType zapRojdType;
    @MappedCollection(idColumn = "uchrfl_id")
    private GrajdType grajdType;
    @MappedCollection(idColumn = "uchrfl_id")
    private UdLichEGRULType udLichEGRULType;
    @MappedCollection(idColumn = "uchrfl_id")
    private AdrMJRFEGRULType adrMJRFEGRULType;
    @MappedCollection(idColumn = "uchrfl_id")
    private AdrMJFIASEGRULType adrMJFIASEGRULType;
    @MappedCollection(idColumn = "uchrfl_id")
    private DoliaUstKapEGRULType doliaUstKapEGRULType;
    @MappedCollection(idColumn = "uchrfl_id")
    private ObemPravType obemPravType;
    @MappedCollection(idColumn = "uchrfl_id")
    private Set<ObremType> obremType;
    @MappedCollection(idColumn = "uchrfl_id")
    private Set<UpravZalType> upravZalType;
    @MappedCollection(idColumn = "uchrfl_id")
    private Set<DovUprULType> dovUprULType;
    @MappedCollection(idColumn = "uchrfl_id")
    private Set<DovUprFLType> dovUprFLType;
    @MappedCollection(idColumn = "uchrfl_id")
    private Set<LicoUprNasl> licoUprNasl;

    @XmlAttribute(name = "ВидАдрКлассиф")
    public void setVidAdrKlassif(String vidAdrKlassif) {
        this.vidAdrKlassif = vidAdrKlassif;
    }
    @XmlAttribute(name = "ОГРНИП")
    public void setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
    }
    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "СвФЛ")
    public void setFlegrulType(FLEGRULType flegrulType) {
        this.flegrulType = flegrulType;
    }
    @XmlElement(name = "СвФИОЗАГС")
    public void setFiozagsType(FIOZAGSType fiozagsType) {
        this.fiozagsType = fiozagsType;
    }
    @XmlElement(name = "СвНедДанУчр")
    public void setNedDanUchrType(Set<NedDanUchrType> nedDanUchrType) {
        this.nedDanUchrType = nedDanUchrType;
    }
    @XmlElement(name = "СвПолФЛ")
    public void setPolFLType(PolFLType polFLType) {
        this.polFLType = polFLType;
    }
    @XmlElement(name = "СвРождФЛ")
    public void setRojdEGRULType(RojdEGRULType rojdEGRULType) {
        this.rojdEGRULType = rojdEGRULType;
    }
    @XmlElement(name = "СвЗапРожд")
    public void setZapRojdType(ZapRojdType zapRojdType) {
        this.zapRojdType = zapRojdType;
    }
    @XmlElement(name = "СвГраждФЛ")
    public void setGrajdType(GrajdType grajdType) {
        this.grajdType = grajdType;
    }
    @XmlElement(name = "УдЛичнФЛ")
    public void setUdLichEGRULType(UdLichEGRULType udLichEGRULType) {
        this.udLichEGRULType = udLichEGRULType;
    }
    @XmlElement(name = "АдресМЖРФ")
    public void setAdrMJRFEGRULType(AdrMJRFEGRULType adrMJRFEGRULType) {
        this.adrMJRFEGRULType = adrMJRFEGRULType;
    }
    @XmlElement(name = "АдрМЖФИАС")
    public void setAdrMJFIASEGRULType(AdrMJFIASEGRULType adrMJFIASEGRULType) {
        this.adrMJFIASEGRULType = adrMJFIASEGRULType;
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
    @XmlElement(name = "ЛицоУпрНасл")
    public void setLicoUprNasl(Set<LicoUprNasl> licoUprNasl) {
        this.licoUprNasl = licoUprNasl;
    }
}
