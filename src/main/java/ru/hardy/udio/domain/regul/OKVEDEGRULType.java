package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.119 */

@Getter
@Setter
@Table(schema = "regul", name = "okved_egrul_type")
@XmlRootElement
public class OKVEDEGRULType {
    @Id
    private Long id;
    @MappedCollection(idColumn = "okvedegrultype_id")
    private OKVEDType okvedTypeOsn;
    @MappedCollection(idColumn = "okvedegrultype_id")
    private OKVEDType okvedTypeDop;

    @XmlElement(name = "СвОКВЭДОсн")
    public void setOkvedTypeOsn(OKVEDType okvedTypeOsn) {
        this.okvedTypeOsn = okvedTypeOsn;
    }
    @XmlElement(name = "СвОКВЭДДоп")
    public void setOkvedTypeDop(OKVEDType okvedTypeDop) {
        this.okvedTypeDop = okvedTypeDop;
    }
}
