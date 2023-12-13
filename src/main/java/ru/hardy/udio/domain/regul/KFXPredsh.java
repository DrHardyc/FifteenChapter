package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.71 */
@Getter
@Setter
@Table(schema = "regul", name = "kfx_prdsh")
@XmlRootElement(name = "СвКФХПредш")
public class KFXPredsh {
    @Id
    private Long id;
    private String ogrnip;
    @MappedCollection(idColumn = "kfxpredsh_id")
    private FLEGRULType flegrulType;

    @XmlAttribute(name = "ОГРНИП")
    public void setOgrnip(String ogrnip) {
        this.ogrnip = ogrnip;
    }
    @XmlElement(name = "СвФЛ")
    public void setFlegrulType(FLEGRULType flegrulType) {
        this.flegrulType = flegrulType;
    }
}
