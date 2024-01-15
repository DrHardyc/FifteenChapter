package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.76 */
@Getter
@Setter
@Table(schema = "regul", name = "zayv_fl")
@XmlRootElement
public class ZayvFL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "zayvfl_id")
    private VidZaiav vidZaiav;
    @MappedCollection(idColumn = "zayvfl_id")
    private ULZaiavFL ulZaiavFL;
    @MappedCollection(idColumn = "zayvfl_id")
    private UprOrgZaiavFL uprOrgZaiavFL;
    @MappedCollection(idColumn = "zayvfl_id")
    private FLZaiavFL flZaiavFL;

    @XmlElement(name = "ВидЗаяв")
    public void setVidZaiav(VidZaiav vidZaiav) {
        this.vidZaiav = vidZaiav;
    }
    @XmlElement(name = "СвЮЛ")
    public void setUlZaiavFL(ULZaiavFL ulZaiavFL) {
        this.ulZaiavFL = ulZaiavFL;
    }
    @XmlElement(name = "СвУпрОрг")
    public void setUprOrgZaiavFL(UprOrgZaiavFL uprOrgZaiavFL) {
        this.uprOrgZaiavFL = uprOrgZaiavFL;
    }
    @XmlElement(name = "СвФЛ")
    public void setFlZaiavFL(FLZaiavFL flZaiavFL) {
        this.flZaiavFL = flZaiavFL;
    }
}
