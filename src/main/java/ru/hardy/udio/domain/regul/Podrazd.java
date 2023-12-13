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

/** @version таблица 4.63 */
@Getter
@Setter
@Table(schema = "regul", name = "podrazd")
@XmlRootElement(name = "СвПодразд")
public class Podrazd {
    @Id
    private Long id;

    @MappedCollection(idColumn = "podrazd_id")
    private Set<Filial> filial;
    @MappedCollection(idColumn = "podrazd_id")
    private Set<Predst> predst;

    @XmlElement(name = "СвФилиал")
    public void setFilial(Set<Filial> filial) {
        this.filial = filial;
    }
    @XmlElement(name = "СвПредстав")
    public void setPredst(Set<Predst> predst) {
        this.predst = predst;
    }
}
