package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.89 */
@Getter
@Setter
@Table(schema = "regul", name = "adr_mj_fias_egrul_type")
@XmlRootElement
public class AdrMJFIASEGRULType {
    @Id
    private Long id;

    private String idNom;
    private String index;
    private String region;
    private String nameRegion;
    @MappedCollection(idColumn = "adrmjfiasegrultype_id")
    private VidNameCodeType vidNameCodeTypeRaion;
    @MappedCollection(idColumn = "adrmjfiasegrultype_id")
    private VidNameCodeType vidNameCodeTypePos;
    @MappedCollection(idColumn = "adrmjfiasegrultype_id")
    private VidNamePType vidNamePType;
    @MappedCollection(idColumn = "adrmjfiasegrultype_id")
    private TypeNamePType typeNamePTypeStruct;
    @MappedCollection(idColumn = "adrmjfiasegrultype_id")
    private TypeNamePType typeNamePTypeSeti;
    @MappedCollection(idColumn = "adrmjfiasegrultype_id")
    private NumberPType numberPTypeZdanie;
    @MappedCollection(idColumn = "adrmjfiasegrultype_id")
    private NumberPType numberPTypePomesh;
    @MappedCollection(idColumn = "adrmjfiasegrultype_id")
    private NumberPType numberPTypeKv;

    @XmlAttribute(name = "ИдНом")
    public void setIdNom(String idNom) {
        this.idNom = idNom;
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
    public void setVidNameCodeTypeRaion(VidNameCodeType vidNameCodeTypeRaion) {
        this.vidNameCodeTypeRaion = vidNameCodeTypeRaion;
    }
    @XmlElement(name = "ГородСелПоселен")
    public void setVidNameCodeTypePos(VidNameCodeType vidNameCodeTypePos) {
        this.vidNameCodeTypePos = vidNameCodeTypePos;
    }
    @XmlElement(name = "НаселенПункт")
    public void setVidNamePType(VidNamePType vidNamePType) {
        this.vidNamePType = vidNamePType;
    }
    @XmlElement(name = "ЭлПланСтруктур")
    public void setTypeNamePTypeStruct(TypeNamePType typeNamePTypeStruct) {
        this.typeNamePTypeStruct = typeNamePTypeStruct;
    }
    @XmlElement(name = "ЭлУлДорСети")
    public void setTypeNamePTypeSeti(TypeNamePType typeNamePTypeSeti) {
        this.typeNamePTypeSeti = typeNamePTypeSeti;
    }
    @XmlElement(name = "Здание")
    public void setNumberPTypeZdanie(NumberPType numberPTypeZdanie) {
        this.numberPTypeZdanie = numberPTypeZdanie;
    }
    @XmlElement(name = "ПомещЗдания")
    public void setNumberPTypePomesh(NumberPType numberPTypePomesh) {
        this.numberPTypePomesh = numberPTypePomesh;
    }
    @XmlElement(name = "ПомещКвартиры")
    public void setNumberPTypeKv(NumberPType numberPTypeKv) {
        this.numberPTypeKv = numberPTypeKv;
    }
}
