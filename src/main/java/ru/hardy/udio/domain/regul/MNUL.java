package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.8 */

@Getter
@Setter
@Table(schema = "regul", name = "mnul")
@XmlRootElement
public class MNUL {
    @Id
    private Long id;

    private String idNom;
    private String region;
    private String nameRegion;
    @MappedCollection(idColumn = "mnul_id")
    private VidNameCodeType vidNameCodeTypeRaion;
    @MappedCollection(idColumn = "mnul_id")
    private VidNameCodeType vidNameCodeTypePos;
    @MappedCollection(idColumn = "mnul_id")
    private VidNamePType vidNamePType;

    @XmlAttribute(name = "ИдНом")
    public void setIdNom(String idNom) {
        this.idNom = idNom;
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
}
