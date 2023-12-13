package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.55 */
@Getter
@Setter
@Table(schema = "regul", name = "up_tov_in_ul")
@XmlRootElement(name = "СвУпТовИнЮЛ")
public class UpTovInUL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uptovinul_id")
    private ULEGRULType nameINNUL;
    @MappedCollection(idColumn = "uptovinul_id")
    private RegInULEGRULType regInULEGRULType;
    @MappedCollection(idColumn = "uptovinul_id")
    private NamePredULType namePredULType;
    @MappedCollection(idColumn = "uptovinul_id")
    private AkRAFPType akRAFPType;

    @XmlElement(name = "НаимИННЮЛ")
    public void setNameINNUL(ULEGRULType nameINNUL) {
        this.nameINNUL = nameINNUL;
    }
    @XmlElement(name = "СвРегИн")
    public void setRegInULEGRULType(RegInULEGRULType regInULEGRULType) {
        this.regInULEGRULType = regInULEGRULType;
    }
    @XmlElement(name = "СвПредЮЛ")
    public void setNamePredULType(NamePredULType namePredULType) {
        this.namePredULType = namePredULType;
    }
    @XmlElement(name = "СвАкРАФП")
    public void setAkRAFPType(AkRAFPType akRAFPType) {
        this.akRAFPType = akRAFPType;
    }
}
