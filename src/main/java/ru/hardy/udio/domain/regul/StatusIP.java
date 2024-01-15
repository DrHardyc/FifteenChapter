package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "status_ip")
@XmlRootElement(name = "СвРешИсклИП")
public class StatusIP {
    @Id
    private Long id;
    @MappedCollection(idColumn = "statusip_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "statusip_id")
    private Status status;
    @MappedCollection(idColumn = "statusip_id")
    private ReshIsklIP reshIsklIP;

    @XmlElement(name = "ОгрДосСв")
    public void setOgrDoc(OgrDoc ogrDoc) {
        this.ogrDoc = ogrDoc;
    }
    @XmlElement(name = "СвСтатус")
    public void setStatus(Status status) {
        this.status = status;
    }
    @XmlElement(name = "СвРешИсклЮЛ")
    public void setReshIsklIP(ReshIsklIP reshIsklIP) {
        this.reshIsklIP = reshIsklIP;
    }

    public StatusIP(){}

    public StatusIP(Status status){
        this.status = status;
    }
}

