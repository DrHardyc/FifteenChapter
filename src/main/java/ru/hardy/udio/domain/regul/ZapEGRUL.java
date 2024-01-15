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
/** @version таблица 4.75 */
@Getter
@Setter
@Table(schema = "regul", name = "zap_egrul")
@XmlRootElement
public class ZapEGRUL {
    @Id
    private Long id;

    private String idZap;
    private String grn;
    private String dateZap;
    @MappedCollection(idColumn = "zapegrul_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "zapegrul_id")
    private VidZap vidZap;
    @MappedCollection(idColumn = "zapegrul_id")
    private RegOrgType regOrgType;
    @MappedCollection(idColumn = "zapegrul_id")
    private Set<ZayvFL> zayvFL;
    @MappedCollection(idColumn = "zapegrul_id")
    private Set<PredDoc> predDoc;
    @MappedCollection(idColumn = "zapegrul_id")
    private Set<Svid> svid;
    @MappedCollection(idColumn = "zapegrul_id")
    private StatusZap statusZap;

    @XmlAttribute(name = "ИдЗап")
    public void setIdZap(String idZap) {
        this.idZap = idZap;
    }
    @XmlAttribute(name = "ГРН")
    public void setGrn(String grn) {
        this.grn = grn;
    }
    @XmlAttribute(name = "ДатаЗап")
    public void setDateZap(String dateZap) {
        this.dateZap = dateZap;
    }
    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "ВидЗап")
    public void setVidZap(VidZap vidZap) {
        this.vidZap = vidZap;
    }
    @XmlElement(name = "СвРегОрг")
    public void setRegOrgType(RegOrgType regOrgType) {
        this.regOrgType = regOrgType;
    }
    @XmlElement(name = "СвЗаявФЛ")
    public void setZayvFL(Set<ZayvFL> zayvFL) {
        this.zayvFL = zayvFL;
    }
    @XmlElement(name = "СведПредДок")
    public void setPredDoc(Set<PredDoc> predDoc) {
        this.predDoc = predDoc;
    }
    @XmlElement(name = "СвСвид")
    public void setSvid(Set<Svid> svid) {
        this.svid = svid;
    }
    @XmlElement(name = "ГРНДатаИспрПред")
    public void setStatusZap(StatusZap statusZap) {
        this.statusZap = statusZap;
    }

    public ZapEGRUL(){}

    public ZapEGRUL(String dateZap){
        this.dateZap = dateZap;
    }
}
