package ru.hardy.udio.domain.regul;


import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Date;

/** @version таблица 4.4 */
@Getter
@Setter
@Table(schema = "regul", name = "person_ip")
@XmlRootElement
public class PersonIP {
    @Id
    private Long id;
    private String dateVip;
    private String ogrnIp;
    private String dateOgrnIp;
    private String innFl;
    private String codeVidIp;
    private String nameVidIp;
    private String regNFoms;
    private Date dateBeg;
    private Date dateEdit;
    @MappedCollection(idColumn = "personip_id")
    private FL fl;
    @MappedCollection(idColumn = "personip_id")
    private FIOZAGSType fioZagsType;
    @MappedCollection(idColumn = "personip_id")
    private Rojd rojd;
    @MappedCollection(idColumn = "personip_id")
    private Grajd grajd;
    @MappedCollection(idColumn = "personip_id")
    private UdLichnFl udLichnFl;
    @MappedCollection(idColumn = "personip_id")
    private PravJitRF pravJitRF;
    @MappedCollection(idColumn = "personip_id")
    private AdrMJ adrMJ;
    @MappedCollection(idColumn = "personip_id")
    private AdrMail adrMail;
    @MappedCollection(idColumn = "personip_id")
    private RegIP regIP;
    @MappedCollection(idColumn = "personip_id")
    private GlKFH kfh;
    @MappedCollection(idColumn = "personip_id")
    private RegOrg regOrg;
    @MappedCollection(idColumn = "personip_id")
    private StatusIP status;
    @MappedCollection(idColumn = "personip_id")
    private Prekrash prekrash;
    @MappedCollection(idColumn = "personip_id")
    private UchetNO uchetNO;
    @MappedCollection(idColumn = "personip_id")
    private RegPFIP regPF;
    @MappedCollection(idColumn = "personip_id")
    private RegFSSIP regFSSIP;
    @MappedCollection(idColumn = "personip_id")
    private OKVEDIP okvedip;
    @MappedCollection(idColumn = "personip_id")
    private LicenseIP licenseIP;
    @MappedCollection(idColumn = "personip_id")
    private ZapEGRIP zapEGRIP;

    @XmlAttribute(name = "ДатаВып")
    public void setDateVip(String dateVip) {
        this.dateVip = dateVip;
    }
    @XmlAttribute(name = "ОГРНИП")
    public void setOgrnIp(String ogrnIp) {
        this.ogrnIp = ogrnIp;
    }
    @XmlAttribute(name = "ДатаОГРНИП")
    public void setDateOgrnIp(String dateOgrnIp) {
        this.dateOgrnIp = dateOgrnIp;
    }
    @XmlAttribute(name = "ИННФЛ")
    public void setInnFl(String innFl) {
        this.innFl = innFl;
    }
    @XmlAttribute(name = "КодВидИП")
    public void setCodeVidIp(String codeVidIp) {
        this.codeVidIp = codeVidIp;
    }
    @XmlAttribute(name = "НаимВидИП")
    public void setNameVidIp(String nameVidIp) {
        this.nameVidIp = nameVidIp;
    }
    @XmlElement(name = "СвФЛ")
    public void setFl(FL fl) {
        this.fl = fl;
    }
    @XmlElement(name = "СвФИОЗАГСТип")
    public void setFiozagsType(FIOZAGSType fiozagsType) {
        this.fioZagsType = fiozagsType;
    }
    @XmlElement(name = "СвРожд")
    public void setRojd(Rojd rojd) {
        this.rojd = rojd;
    }
    @XmlElement(name = "СвГражд")
    public void setGrajd(Grajd grajd) {
        this.grajd = grajd;
    }
    @XmlElement(name = "СвУдЛичнФЛ")
    public void setUdLichnFl(UdLichnFl udLichnFl) {
        this.udLichnFl = udLichnFl;
    }
    @XmlElement(name = "СвПравЖитРФ")
    public void setPravJitRF(PravJitRF pravJitRF) {
        this.pravJitRF = pravJitRF;
    }
    @XmlElement(name = "СвАдрМЖ")
    public void setAdrMJ(AdrMJ adrMJ) {
        this.adrMJ = adrMJ;
    }
    @XmlElement(name = "СвАдрЭлПочты")
    public void setAdrMail(AdrMail adrMail) {
        this.adrMail = adrMail;
    }
    @XmlElement(name = "СвРегИП")
    public void setRegIP(RegIP regIP) {
        this.regIP = regIP;
    }
    @XmlElement(name = "СвГлКФХ")
    public void setKfh(GlKFH kfh) {
        this.kfh = kfh;
    }
    @XmlElement(name = "СвРегОрг")
    public void setRegOrg(RegOrg regOrg) {
        this.regOrg = regOrg;
    }
    @XmlElement(name = "СвСтатус")
    public void setStatus(StatusIP status) {
        this.status = status;
    }
    @XmlElement(name = "СвПрекращ")
    public void setPrekrash(Prekrash prekrash) {
        this.prekrash = prekrash;
    }
    @XmlElement(name = "СвУчетНО")
    public void setUchetNO(UchetNO uchetNO) {
        this.uchetNO = uchetNO;
    }
    @XmlElement(name = "СвРегПФ")
    public void setRegPF(RegPFIP regPF) {
        this.regPF = regPF;
    }
    @XmlElement(name = "СвРегФСС")
    public void setRegFSSIP(RegFSSIP regFSSIP) {
        this.regFSSIP = regFSSIP;
    }
    @XmlElement(name = "СвОКВЭДЕГРИПТип")
    public void setOkvedip(OKVEDIP okvedip) {
        this.okvedip = okvedip;
    }
    @XmlElement(name = "СвЛицензия")
    public void setLicenseIP(LicenseIP licenseIP) {
        this.licenseIP = licenseIP;
    }
    @XmlElement(name = "СвЗапЕГРИП")
    public void setZapEGRIP(ZapEGRIP zapEGRIP) {
        this.zapEGRIP = zapEGRIP;
    }

    public PersonIP() {
        this.dateBeg = Date.from(Instant.now());
        this.dateEdit = Date.from(Instant.now());
    }
}
