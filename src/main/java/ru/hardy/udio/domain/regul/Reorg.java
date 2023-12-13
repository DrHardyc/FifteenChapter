package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.66 */
@Getter
@Setter
@Table(schema = "regul", name = "reorg")
@XmlRootElement(name = "СвРеорг")
public class Reorg {
    @Id
    private Long id;

    @MappedCollection(idColumn = "reorg_id")
    private StatusReorg statusReorg;
    @MappedCollection(idColumn = "reorg_id")
    private ReorgUL reorgUL;
    @MappedCollection(idColumn = "reorg_id")
    private OgrDoc ogrDoc;

    @XmlElement(name = "ОгрДосСв")
    public void setStatusReorg(StatusReorg statusReorg) {
        this.statusReorg = statusReorg;
    }
    @XmlElement(name = "СвСтатус")
    public void setReorgUL(ReorgUL reorgUL) {
        this.reorgUL = reorgUL;
    }
    @XmlElement(name = "ГРНДата")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
}
