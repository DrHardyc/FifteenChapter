package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.56 */
@Getter
@Setter
@Table(schema = "regul", name = "konv_zaim")
@XmlRootElement(name = "СвКонвЗайм")
public class KonvZaim {
    @Id
    private Long id;

    private String number;
    private String date;
    @MappedCollection(idColumn = "konvzaim_id")
    private FIOType fioType;
    @MappedCollection(idColumn = "konvzaim_id")
    private ZaimDavFL zaimDavFL;
    @MappedCollection(idColumn = "konvzaim_id")
    private ZaimDavUL zaimDavUL;
    @MappedCollection(idColumn = "konvzaim_id")
    private DoliaUstKapEGRULType doliaUstKap;

    @XmlAttribute(name = "ГРНДатаПерв")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "Номер")
    public void setDate(String date) {
        this.date = date;
    }
    @XmlElement(name = "ФИОНотариус")
    public void setFioType(FIOType fioType) {
        this.fioType = fioType;
    }
    @XmlElement(name = "ЗаймДавФЛ")
    public void setZaimDavFL(ZaimDavFL zaimDavFL) {
        this.zaimDavFL = zaimDavFL;
    }
    @XmlElement(name = "ЗаймДавЮЛ")
    public void setZaimDavUL(ZaimDavUL zaimDavUL) {
        this.zaimDavUL = zaimDavUL;
    }
    @XmlElement(name = "ДоляУстКап")
    public void setDoliaUstKap(DoliaUstKapEGRULType doliaUstKap) {
        this.doliaUstKap = doliaUstKap;
    }
}
