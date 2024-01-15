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

/** @version таблица 4.61 */

@Getter
@Setter
@Table(schema = "regul", name = "license")
@XmlRootElement
public class License {
    @Id
    private Long id;

    private String nomerERUL;
    private String number;
    private String date;
    private String dateNachLic;
    private String dateOkonchLic;
    private Set<String> naimLicVidDeyat;
    private String licOrgVidDeyat;
    @MappedCollection(idColumn = "license_id")
    private PriostLic priostLic;
    @MappedCollection(idColumn = "license_id")
    private OgrDoc ogrDoc;

    @XmlAttribute(name = "НомерЕРУЛ")
    public void setNomerERUL(String nomerERUL) {
        this.nomerERUL = nomerERUL;
    }
    @XmlAttribute(name = "НомЛиц")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "ДатаЛиц")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlAttribute(name = "ДатаНачЛиц")
    public void setDateNachLic(String dateNachLic) {
        this.dateNachLic = dateNachLic;
    }
    @XmlAttribute(name = "ДатаОкончЛиц")
    public void setDateOkonchLic(String dateOkonchLic) {
        this.dateOkonchLic = dateOkonchLic;
    }
    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlAttribute(name = "НаимЛицВидДеят")
    public void setNaimLicVidDeyat(Set<String> naimLicVidDeyat) {
        this.naimLicVidDeyat = naimLicVidDeyat;
    }
    @XmlAttribute(name = "ЛицОргВыдЛиц")
    public void setLicOrgVidDeyat(String licOrgVidDeyat) {
        this.licOrgVidDeyat = licOrgVidDeyat;
    }
    @XmlElement(name = "СвПриостЛиц")
    public void setPriostLic(PriostLic priostLic) {
        this.priostLic = priostLic;
    }
}
