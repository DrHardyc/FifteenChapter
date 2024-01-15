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

@Getter
@Setter
@Table(schema = "regul", name = "adr_fias_egrip_type")
@XmlRootElement(name = "АдрМЖФИАС")
public class AdrFIASEGRIPType {
    @Id
    private Long id;
    private String idNum;
    private String index;
    private String region;
    private String nameRegion;
    @MappedCollection(idColumn = "adrfiasegriptype_id")
    private VidNameCodeType municRaion;
    @MappedCollection(idColumn = "adrfiasegriptype_id")
    private VidNameCodeType poselen;
    @MappedCollection(idColumn = "adrfiasegriptype_id")
    private VidNamePType naselPunkt;
    @MappedCollection(idColumn = "adrfiasegriptype_id")
    private TypeNamePType struct;
    @MappedCollection(idColumn = "adrfiasegriptype_id")
    private TypeNamePType seti;
    @MappedCollection(idColumn = "adrfiasegriptype_id")
    private Set<NumberPType> zdanie;
    @MappedCollection(idColumn = "adrfiasegriptype_id")
    private NumberPType pomesh;
    @MappedCollection(idColumn = "adrfiasegriptype_id")
    private NumberPType kvart;

    @XmlAttribute(name = "ИдНом")
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }
    @XmlAttribute(name = "Индекс")
    public void setIndex(String index) {
        this.index = index;
    }
    @XmlAttribute(name = "Регион")
    public void setRegion(String region) {
        this.region = region;
    }
    @XmlAttribute(name = "НаимРегион")
    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }
    @XmlElement(name = "МуниципРайон")
    public void setMunicRaion(VidNameCodeType municRaion) {
        this.municRaion = municRaion;
    }
    @XmlElement(name = "ГородСелПоселен")
    public void setPoselen(VidNameCodeType poselen) {
        this.poselen = poselen;
    }
    @XmlElement(name = "НаселенПункт")
    public void setNaselPunkt(VidNamePType naselPunkt) {
        this.naselPunkt = naselPunkt;
    }
    @XmlElement(name = "ЭлПланСтруктур")
    public void setStruct(TypeNamePType struct) {
        this.struct = struct;
    }
    @XmlElement(name = "ЭлУлДорСети")
    public void setSeti(TypeNamePType seti) {
        this.seti = seti;
    }
    @XmlElement(name = "Здание")
    public void setZdanie(Set<NumberPType> zdanie) {
        this.zdanie = zdanie;
    }
    @XmlElement(name = "ПомещЗдания")
    public void setPomesh(NumberPType pomesh) {
        this.pomesh = pomesh;
    }
    @XmlElement(name = "ПомещКвартиры")
    public void setKvart(NumberPType kvart) {
        this.kvart = kvart;
    }
}
