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

/** @version таблица 4.33 */

@Getter
@Setter
@Table(schema = "regul", name = "dolj_fl")
@XmlRootElement
public class DoljFL {
    @Id
    private Long id;

    private String vidAdrClassif;
    @MappedCollection(idColumn = "doljfl_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "doljfl_id")
    private FLEGRULType flegrulType;
    @MappedCollection(idColumn = "doljfl_id")
    private FIOZAGSType fiozagsType;
    @MappedCollection(idColumn = "doljfl_id")
    private Dolj dolj;
    @MappedCollection(idColumn = "doljfl_id")
    private Set<NedDanDoljFL> nedDanDoljFL;
    @MappedCollection(idColumn = "doljfl_id")
    private Set<DopSvPolDoljFL> dopSvPolDoljFL;
    @MappedCollection(idColumn = "doljfl_id")
    private PolFLType polFLType;
    @MappedCollection(idColumn = "doljfl_id")
    private RojdEGRULType rojdEGRULType;
    @MappedCollection(idColumn = "doljfl_id")
    private ZapRojdType zapRojdType;
    @MappedCollection(idColumn = "doljfl_id")
    private GrajdType grajdType;
    @MappedCollection(idColumn = "doljfl_id")
    private UdLichEGRULType udLichEGRULType;
    @MappedCollection(idColumn = "doljfl_id")
    private AdrMJRFEGRULType adrMJRFEGRULType;
    @MappedCollection(idColumn = "doljfl_id")
    private AdrMJFIASEGRULType adrMJFIASEGRULType;
    @MappedCollection(idColumn = "doljfl_id")
    private Set<Diskv> diskv;

    @XmlAttribute(name = "ВидАдрКлассиф")
    public void setVidAdrClassif(String vidAdrClassif) {
        this.vidAdrClassif = vidAdrClassif;
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
    @XmlElement(name = "СвДолжн")
    public void setDolj(Dolj dolj) {
        this.dolj = dolj;
    }
    @XmlElement(name = "СвНедДанДолжнФЛ")
    public void setNedDanDoljFL(Set<NedDanDoljFL> nedDanDoljFL) {
        this.nedDanDoljFL = nedDanDoljFL;
    }
    @XmlElement(name = "ДопСвПолДолжФЛ")
    public void setDopSvPolDoljFL(Set<DopSvPolDoljFL> dopSvPolDoljFL) {
        this.dopSvPolDoljFL = dopSvPolDoljFL;
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
    @XmlElement(name = "СвДискв")
    public void setDiskv(Set<Diskv> diskv) {
        this.diskv = diskv;
    }
}
