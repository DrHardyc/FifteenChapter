package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.64 */
@Getter
@Setter
@Table(schema = "regul", name = "filial")
@XmlRootElement
public class Filial {
    @Id
    private Long id;

    private String vidAdrClassif;

    @MappedCollection(idColumn = "filial_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "filial_id")
    private NamePolnType namePolnType;
    @MappedCollection(idColumn = "filial_id")
    private AdrRFEGRULType adrRFEGRULType;
    @MappedCollection(idColumn = "filial_id")
    private AdrFIASEGRULType adrFIASEGRULType;
    @MappedCollection(idColumn = "filial_id")
    private AdrInEGRULType adrInEGRULType;
    @MappedCollection(idColumn = "filial_id")
    private AkrFilPredType akrFilPredFil;
    @MappedCollection(idColumn = "filial_id")
    private UchetNOPodrazdType uchetNOPodrazdType;

    @XmlAttribute(name = "ВидАдрКлассиф")
    public void setVidAdrClassif(String vidAdrClassif) {
        this.vidAdrClassif = vidAdrClassif;
    }
    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "СвНаим")
    public void setNamePolnType(NamePolnType namePolnType) {
        this.namePolnType = namePolnType;
    }
    @XmlElement(name = "АдрМНРФ")
    public void setAdrRFEGRULType(AdrRFEGRULType adrRFEGRULType) {
        this.adrRFEGRULType = adrRFEGRULType;
    }
    @XmlElement(name = "АдрМНФИАС")
    public void setAdrFIASEGRULType(AdrFIASEGRULType adrFIASEGRULType) {
        this.adrFIASEGRULType = adrFIASEGRULType;
    }
    @XmlElement(name = "АдрМНИн")
    public void setAdrInEGRULType(AdrInEGRULType adrInEGRULType) {
        this.adrInEGRULType = adrInEGRULType;
    }
    @XmlElement(name = "СвАкрФилПредст")
    public void setAkrFilPredFil(AkrFilPredType akrFilPredFil) {
        this.akrFilPredFil = akrFilPredFil;
    }
    @XmlElement(name = "СвУчетНОФилиал")
    public void setUchetNOPodrazdType(UchetNOPodrazdType uchetNOPodrazdType) {
        this.uchetNOPodrazdType = uchetNOPodrazdType;
    }
}
