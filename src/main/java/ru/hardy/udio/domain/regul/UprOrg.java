package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.30 */
@Getter
@Setter
@Table(schema = "regul", name = "upr_org")
@XmlRootElement
public class UprOrg {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uprorg_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "uprorg_id")
    private NameINNUL nameINNUL;
    @MappedCollection(idColumn = "uprorg_id")
    private RegInULEGRULType regInULEGRULType;
    @MappedCollection(idColumn = "uprorg_id")
    private NedDanUprOrg nedDanUprOrg;
    @MappedCollection(idColumn = "uprorg_id")
    private NamePredULType namePredULType;
    @MappedCollection(idColumn = "uprorg_id")
    private AkRAFPType akRAFPType;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "НаимИННЮЛ")
    public void setNameINNUL(NameINNUL nameINNUL) {
        this.nameINNUL = nameINNUL;
    }
    @XmlElement(name = "СвРегИн")
    public void setRegInULEGRULType(RegInULEGRULType regInULEGRULType) {
        this.regInULEGRULType = regInULEGRULType;
    }
    @XmlElement(name = "СвНедДанУпрОрг")
    public void setNedDanUprOrg(NedDanUprOrg nedDanUprOrg) {
        this.nedDanUprOrg = nedDanUprOrg;
    }
    @XmlElement(name = "СвПредЮЛ")
    public void setNamePredULType(NamePredULType namePredULType) {
        this.namePredULType = namePredULType;
    }
    @XmlElement(name = "СвАкРАФП")
    public void setAkRAFPType(AkRAFPType akRAFPType) {
        this.akRAFPType = akRAFPType;
    }
}
