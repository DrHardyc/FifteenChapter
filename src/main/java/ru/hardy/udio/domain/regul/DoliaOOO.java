package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.59 */

@Getter
@Setter
@Table(schema = "regul", name = "dolia_ooo")
@XmlRootElement
public class DoliaOOO {
    @Id
    private Long id;

    private String nominStoim;
    @MappedCollection(idColumn = "doliaooo_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "doliaooo_id")
    private DrobType doliaRublia;
    @MappedCollection(idColumn = "doliaooo_id")
    private RazmerDoliType razmerDoliType;
    @MappedCollection(idColumn = "doliaooo_id")
    private ObremType obremType;
    @MappedCollection(idColumn = "doliaooo_id")
    private UpravZalType upravZalType;


    @XmlAttribute(name = "НоминСтоим")
    public void setNominStoim(String nominStoim) {
        this.nominStoim = nominStoim;
    }
    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "ДоляРубля")
    public void setDoliaRublia(DrobType doliaRublia) {
        this.doliaRublia = doliaRublia;
    }
    @XmlElement(name = "РазмерДоли")
    public void setRazmerDoliType(RazmerDoliType razmerDoliType) {
        this.razmerDoliType = razmerDoliType;
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
