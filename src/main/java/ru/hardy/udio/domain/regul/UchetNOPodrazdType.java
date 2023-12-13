package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.131 */
@Getter
@Setter
@Table(schema = "regul", name = "uchet_no_podrazd_type")
@XmlRootElement(name = "СвУчетНОПодраздТип")
public class UchetNOPodrazdType {
    @Id
    private Long id;

    private String kpp;
    private String datePostUch;
    @MappedCollection(idColumn = "uchetnopodrazdtype_id")
    private NOType noType;

    @XmlAttribute(name = "КПП")
    public void setKpp(String kpp) {
        this.kpp = kpp;
    }
    @XmlAttribute(name = "ДатаПостУч")
    public void setDatePostUch(String datePostUch) {
        this.datePostUch = datePostUch;
    }
    @XmlElement(name = "СвНО")
    public void setNoType(NOType noType) {
        this.noType = noType;
    }
}
