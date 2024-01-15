package ru.hardy.udio.domain.regul;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version  таблица 4.16 */
@Getter
@Setter
@Table(schema = "regul", name = "status_ul")
@XmlRootElement
public class StatusUl {
    @Id
    private Long id;
    @MappedCollection(idColumn = "statusul_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "statusul_id")
    private Status status;
    @MappedCollection(idColumn = "statusul_id")
    private ReshIsklUl reshIsklUl;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "СвСтатус")
    public void setStatus(Status status) {
        this.status = status;
    }
    @XmlElement(name = "СвРешИсклЮЛ")
    public void setReshIsklUl(ReshIsklUl reshIsklUl) {
        this.reshIsklUl = reshIsklUl;
    }

    public StatusUl(){}

    public StatusUl(Status status){
        this.status = status;
    }
}
