package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** @version таблица 4.50 */
@Getter
@Setter
@Table(schema = "regul", name = "uchr_pif")
@XmlRootElement(name = "УчрПИФ")
public class UchrPIF {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uchrpif_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "uchrpif_id")
    private NamePIF namePIF;
    @MappedCollection(idColumn = "uchrpif_id")
    private Set<NedDanUchrType> nedDanUchrType;
    @MappedCollection(idColumn = "uchrpif_id")
    private UprKompPIF uprKompPIF;
    @MappedCollection(idColumn = "uchrpif_id")
    private DoliaUstKapEGRULType doliaUstKapEGRULType;
    @MappedCollection(idColumn = "uchrpif_id")
    private ObemPravType obemPravType;
    @MappedCollection(idColumn = "uchrpif_id")
    private Set<ObremType> obremType;
    @MappedCollection(idColumn = "uchrpif_id")
    private Set<UpravZalType> upravZalType;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "СвНаимПИФ")
    public void setNamePIF(NamePIF namePIF) {
        this.namePIF = namePIF;
    }
    @XmlElement(name = "СвНедДанУчр")
    public void setUprKompPIF(UprKompPIF uprKompPIF) {
        this.uprKompPIF = uprKompPIF;
    }
    @XmlElement(name = "СвУпрКомпПИФ")
    public void setNedDanUchrType(Set<NedDanUchrType> nedDanUchrType) {
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
    public void setObremType(Set<ObremType> obremType) {
        this.obremType = obremType;
    }
    @XmlElement(name = "СвУправЗал")
    public void setUpravZalType(Set<UpravZalType> upravZalType) {
        this.upravZalType = upravZalType;
    }
}
