package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.60 */

@Getter
@Setter
@Table(schema = "regul", name = "derj_reestr_ao")
@XmlRootElement
public class DerjReestAO {
    @Id
    private Long id;

    @MappedCollection(idColumn = "derjreestrao_id")
    private OgrDoc ogrDos;
    @MappedCollection(idColumn = "derjreestrao_id")
    private ULEGRULType nameDerjReestrAO;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDos(OgrDoc ogrDos) {
        this.ogrDos = ogrDos;
    }
    @XmlElement(name = "ДержРеестрАО")
    public void setNameDerjReestrAO(ULEGRULType nameDerjReestrAO) {
        this.nameDerjReestrAO = nameDerjReestrAO;
    }
}
