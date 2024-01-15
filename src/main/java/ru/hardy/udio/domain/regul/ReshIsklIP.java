package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "resh_iskl_ip")
@XmlRootElement(name = "СвРешИсклИП")
public class ReshIsklIP {
    @Id
    private Long id;

    private String dateResh;
    private String number;
    private String datePublication;
    private String numberJurnal;

    @XmlAttribute(name = "ДатаРеш")
    public void setDateResh(String dateResh) {
        this.dateResh = dateResh;
    }
    @XmlAttribute(name = "НомерРеш")
    public void setNumber(String number) {
        this.number = number;
    }
    @XmlAttribute(name = "ДатаПубликации")
    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }
    @XmlAttribute(name = "НомерЖурнала")
    public void setNumberJurnal(String numberJurnal) {
        this.numberJurnal = numberJurnal;
    }
}
