package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** @version таблица 4.86 */
@Getter
@Setter
@Table(schema = "regul", name = "status_zap")
@XmlRootElement(name = "СвСтатусЗап")
public class StatusZap {
    @Id
    private Long id;

    @MappedCollection(idColumn = "statuszap_id")
    private IdGRNDateType idGRNDateTypeNed;
    @MappedCollection(idColumn = "statuszap_id")
    private Set<IdGRNDateType> idGRNDateTypeIspr;

    @XmlElement(name = "ГРНДатаНед")
    public void setIdGRNDateTypeNed(IdGRNDateType idGRNDateTypeNed) {
        this.idGRNDateTypeNed = idGRNDateTypeNed;
    }
    @XmlElement(name = "ГРНДатаИспр")
    public void setIdGRNDateTypeIspr(Set<IdGRNDateType> idGRNDateTypeIspr) {
        this.idGRNDateTypeIspr = idGRNDateTypeIspr;
    }
}
