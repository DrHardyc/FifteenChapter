package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.49 */
@Getter
@Setter
@Table(schema = "regul", name = "fl_osush_pr")
@XmlRootElement(name = "СвФЛОсущПр")
public class FLOcushPr {
    @Id
    private Long id;
    @MappedCollection(idColumn = "flosushpr_id")
    private FLEGRULType flegrulType;
    @MappedCollection(idColumn = "flosushpr_id")
    private RojdEGRULType rojdEGRULType;
    @MappedCollection(idColumn = "flosushpr_id")
    private UdLichEGRULType udLichEGRULType;

    @XmlElement(name = "ГРНДатаПерв")
    public void setFlegrulType(FLEGRULType flegrulType) {
        this.flegrulType = flegrulType;
    }
    @XmlElement(name = "СвФЛ")
    public void setRojdEGRULType(RojdEGRULType rojdEGRULType) {
        this.rojdEGRULType = rojdEGRULType;
    }
    @XmlElement(name = "СвРождФЛ")
    public void setUdLichEGRULType(UdLichEGRULType udLichEGRULType) {
        this.udLichEGRULType = udLichEGRULType;
    }
}
