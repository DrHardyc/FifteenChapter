package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.53 */
@Getter
@Setter
@Table(schema = "regul", name = "uchr_dog_inv_tov")
@XmlRootElement(name = "УчрДогИнвТов")
public class UchrDogInvTov {
    @Id
    private Long id;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private InPrDogInvTov inPrDogInvTov;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private ULEGRULType upTovUL;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private UpTovInUL upTovInUL;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private NedDanUchrType nedDanUchrType;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private DoliaUstKapEGRULType doliaUstKapEGRULType;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private ObemPravType obemPravType;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private ObremType obremType;
    @MappedCollection(idColumn = "uchrdoginvtov_id")
    private UpravZalType upravZalType;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "ИнПрДогИнвТов")
    public void setInPrDogInvTov(InPrDogInvTov inPrDogInvTov) {
        this.inPrDogInvTov = inPrDogInvTov;
    }
    @XmlElement(name = "СвУпТовЮЛ")
    public void setUpTovUL(ULEGRULType upTovUL) {
        this.upTovUL = upTovUL;
    }
    @XmlElement(name = "СвУпТовИнЮЛ")
    public void setUpTovInUL(UpTovInUL upTovInUL) {
        this.upTovInUL = upTovInUL;
    }
    @XmlElement(name = "СвНедДанУчр")
    public void setNedDanUchrType(NedDanUchrType nedDanUchrType) {
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
    @XmlElement(name = "СвОбрем")
    public void setObremType(ObremType obremType) {
        this.obremType = obremType;
    }
    @XmlElement(name = "СвУправЗал")
    public void setUpravZalType(UpravZalType upravZalType) {
        this.upravZalType = upravZalType;
    }
}
