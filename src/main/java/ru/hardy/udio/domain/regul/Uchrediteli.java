package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

/** @version таблица 4.39 */
@Getter
@Setter
@Table(schema = "regul", name = "uchrediteli")
@XmlRootElement
public class Uchrediteli {
    @Id
    private Long id;

    @MappedCollection(idColumn = "uchrediteli_id")
    private ZapretRaspDoliami zapretRaspDoliami;
    @MappedCollection(idColumn = "uchrediteli_id")
    private Set<UchrULRos> uchrULRos;
    @MappedCollection(idColumn = "uchrediteli_id")
    private Set<UchrULIn> uchrULIn;
    @MappedCollection(idColumn = "uchrediteli_id")
    private Set<UchrFL> uchrFL;
    @MappedCollection(idColumn = "uchrediteli_id")
    private Set<UchrRFSubMO> uchrRFSubMO;
    @MappedCollection(idColumn = "uchrediteli_id")
    private Set<UchrPIF> uchrPIF;
    @MappedCollection(idColumn = "uchrediteli_id")
    private Set<UchrDogInvTov> uchrDogInvTov;

    @XmlElement(name = "СвЗапретРаспДолями")
    public void setZapretRaspDoliami(ZapretRaspDoliami zapretRaspDoliami) {
        this.zapretRaspDoliami = zapretRaspDoliami;
    }
    @XmlElement(name = "УчрЮЛРос")
    public void setUchrULRos(Set<UchrULRos> uchrULRos) {
        this.uchrULRos = uchrULRos;
    }
    @XmlElement(name = "УчрЮЛИн")
    public void setUchrULIn(Set<UchrULIn> uchrULIn) {
        this.uchrULIn = uchrULIn;
    }
    @XmlElement(name = "УчрФЛ")
    public void setUchrFL(Set<UchrFL> uchrFL) {
        this.uchrFL = uchrFL;
    }
    @XmlElement(name = "УчрРФСубМО")
    public void setUchrRFSubMO(Set<UchrRFSubMO> uchrRFSubMO) {
        this.uchrRFSubMO = uchrRFSubMO;
    }
    @XmlElement(name = "УчрПИФ")
    public void setUchrPIF(Set<UchrPIF> uchrPIF) {
        this.uchrPIF = uchrPIF;
    }
    @XmlElement(name = "УчрДогИнвТов")
    public void setUchrDogInvTov(Set<UchrDogInvTov> uchrDogInvTov) {
        this.uchrDogInvTov = uchrDogInvTov;
    }
}
