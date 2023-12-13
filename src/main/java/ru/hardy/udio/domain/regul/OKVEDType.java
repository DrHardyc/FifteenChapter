package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.120 */
@Getter
@Setter
@Table(schema = "regul", name = "okved_type")
@XmlRootElement(name = "СвОКВЭДТип")
public class OKVEDType {
    @Id
    private Long id;

    private String code;
    private String name;
    private String version;
    @MappedCollection(idColumn = "okvedtype_id")
    private DoliaOKVED doliaOKVED;

    @XmlAttribute(name = "КодОКВЭД")
    public void setCode(String code) {
        this.code = code;
    }
    @XmlAttribute(name = "НаимОКВЭД")
    public void setName(String name) {
        this.name = name;
    }
    @XmlAttribute(name = "ПрВерсОКВЭД")
    public void setVersion(String version) {
        this.version = version;
    }
    @XmlElement(name = "СвДоляОКВЭД")
    public void setDoliaOKVED(DoliaOKVED doliaOKVED) {
        this.doliaOKVED = doliaOKVED;
    }
}
