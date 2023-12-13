package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.52 */
@Getter
@Setter
@Table(schema = "regul", name = "upr_komp_pif")
@XmlRootElement(name = "СвУпрКомпПИФ")
public class UprKompPIF {
    @Id
    private Long id;
    @MappedCollection(idColumn = "uprkomppif_id")
    private ULEGRULType uprKompPIF;

    @XmlElement(name = "УпрКомпПиф")
    public void setUprKompPIF(ULEGRULType uprKompPIF) {
        this.uprKompPIF = uprKompPIF;
    }
}
