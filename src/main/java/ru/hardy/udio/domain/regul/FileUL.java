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
/** @version таблица 4.1 */
@Getter
@Setter
@Table(schema = "regul", name = "file_ul")
@XmlRootElement(name = "Файл")
public class FileUL {
    @Id
    private Long id;
    private String idFile;
    private String formatVersion;
    private String infoType;
    private String transmissionProgramVersion;
    private String quantityDoc;
    @MappedCollection(idColumn = "fileul_id")
    private SenderPeople senderPeople;
    @MappedCollection(idColumn = "fileul_id")
    private Set<DocumentUL> documentUL;


    @XmlAttribute(name = "ИдФайл")
    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }
    @XmlAttribute(name = "ВерсФорм")
    public void setFormatVersion(String formatVersion) {
        this.formatVersion = formatVersion;
    }
    @XmlAttribute(name = "ТипИнф")
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
    @XmlAttribute(name = "ВерсПрог")
    public void setTransmissionProgramVersion(String transmissionProgramVersion) {
        this.transmissionProgramVersion = transmissionProgramVersion;
    }
    @XmlAttribute(name = "КолДок")
    public void setQuantityDoc(String quantityDoc) {
        this.quantityDoc = quantityDoc;
    }
    @XmlElement(name = "ИдОтпр")
    public void setSenderPeople(SenderPeople senderPeople) {
        this.senderPeople = senderPeople;
    }
    @XmlElement(name = "Документ")
    public void setDocumentUL(Set<DocumentUL> documentUL) {
        this.documentUL = documentUL;
    }
}
