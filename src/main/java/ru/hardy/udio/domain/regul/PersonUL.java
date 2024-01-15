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
import java.util.Set;
/** @version таблица 4.4 */
@Getter
@Setter
@Table(schema = "regul", name = "person_ul")
@XmlRootElement
public class
PersonUL {
    @Id
    private Long id;
    private String dateVipl;
    private String ogrn;
    private String dateOgrn;
    private String inn;
    private String kpp;
    private String opf;
    private String kodOpf;
    private String fullNameOpf;
    private String regNFoms;
    @MappedCollection(idColumn = "personul_id")
    private NameUL nameUl;
    @MappedCollection(idColumn = "personul_id")
    private AddressUL addressUl;
    @MappedCollection(idColumn = "personul_id")
    private EmailUl emailUl;
    @MappedCollection(idColumn = "personul_id")
    private ObrUL obrUL;
    @MappedCollection(idColumn = "personul_id")
    private RegOrg regOrg;
    @MappedCollection(idColumn = "personul_id")
    private Set<StatusUl> statusUl;
    @MappedCollection(idColumn = "personul_id")
    private PrekrUl prekrUl;
    @MappedCollection(idColumn = "personul_id")
    private TypeUstav typeUstav;
    @MappedCollection(idColumn = "personul_id")
    private UchetNO uchetNO;
    @MappedCollection(idColumn = "personul_id")
    private RegPF regPF;
    @MappedCollection(idColumn = "personul_id")
    private RegFSS regFSS;
    @MappedCollection(idColumn = "personul_id")
    private UstKap ustKap;
    @MappedCollection(idColumn = "personul_id")
    private Polnomochniy polnomochniy;
    @MappedCollection(idColumn = "personul_id")
    private Set<UprOrg> uprOrg;
    @MappedCollection(idColumn = "personul_id")
    private Set<DoljFL> doljFL;
    @MappedCollection(idColumn = "personul_id")
    private Set<CorpDogovor> corpDogovor;
    @MappedCollection(idColumn = "personul_id")
    private Uchrediteli uchrediteli;
    @MappedCollection(idColumn = "personul_id")
    private Set<KonvZaim> konvZaim;
    @MappedCollection(idColumn = "personul_id")
    private DoliaOOO doliaOOO;
    @MappedCollection(idColumn = "personul_id")
    private DerjReestAO derjreestAO;
    @MappedCollection(idColumn = "personul_id")
    private OKVEDEGRULType OKVEDEGRULType;
    @MappedCollection(idColumn = "personul_id")
    private Set<License> license;
    @MappedCollection(idColumn = "personul_id")
    private Podrazd podrazd;
    @MappedCollection(idColumn = "personul_id")
    private Set<Reorg> reorg;
    @MappedCollection(idColumn = "personul_id")
    private Set<Predsh> predsh;
    @MappedCollection(idColumn = "personul_id")
    private Set<KFXPredsh> kfxPredsh;
    @MappedCollection(idColumn = "personul_id")
    private Set<Preem> preem;
    @MappedCollection(idColumn = "personul_id")
    private KFXPreem kfxPreem;
    @MappedCollection(idColumn = "personul_id")
    private Set<ZapEGRUL> zapEGRUL;

    private Date dateBeg;
    private Date dateEdit;

    @XmlAttribute(name = "ДатаВып")
    public void setDateVipl(String dateVipl) {
        this.dateVipl = dateVipl;
    }
    @XmlAttribute(name = "ОГРН")
    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }
    @XmlAttribute(name = "ДатаОГРН")
    public void setDateOgrn(String dateOgrn) {
        this.dateOgrn = dateOgrn;
    }
    @XmlAttribute(name = "ИНН")
    public void setInn(String inn) {
        this.inn = inn;
    }
    @XmlAttribute(name = "КПП")
    public void setKpp(String kpp) {
        this.kpp = kpp;
    }
    @XmlAttribute(name = "СпрОПФ")
    public void setOpf(String opf) {
        this.opf = opf;
    }
    @XmlAttribute(name = "КодОПФ")
    public void setKodOpf(String kodOpf) {
        this.kodOpf = kodOpf;
    }
    @XmlAttribute(name = "ПолнНаимОПФ")
    public void setFullNameOpf(String fullNameOpf) {
        this.fullNameOpf = fullNameOpf;
    }
    @XmlElement(name = "СвНаимЮЛ")
    public void setNameUl(NameUL nameUl) {
        this.nameUl = nameUl;
    }
    @XmlElement(name = "СвАдресЮЛ")
    public void setAddressUL(AddressUL addressUl) {
        this.addressUl = addressUl;
    }
    @XmlElement(name = "СвАдрЭлПочты")
    public void setEmailUl(EmailUl emailUl) {
        this.emailUl = emailUl;
    }
    @XmlElement(name = "СвОбрЮЛ")
    public void setObrUL(ObrUL obrUL) {
        this.obrUL = obrUL;
    }
    @XmlElement(name = "СвРегОрг")
    public void setRegOrg(RegOrg regOrg) {
        this.regOrg = regOrg;
    }
    @XmlElement(name = "СвСтатус")
    public void setStatusUl(Set<StatusUl> statusUl) {
        this.statusUl = statusUl;
    }
    @XmlElement(name = "СвПрекрЮЛ")
    public void setPrekrUl(PrekrUl prekrUl) {
        this.prekrUl = prekrUl;
    }
    @XmlElement(name = "СвТипУстав")
    public void setTypeUstav(TypeUstav typeUstav) {
        this.typeUstav = typeUstav;
    }
    @XmlElement(name = "СвУчетНО")
    public void setUchetNO(UchetNO uchetNO) {
        this.uchetNO = uchetNO;
    }
    @XmlElement(name = "СвРегПФ")
    public void setRegPF(RegPF regPF) {
        this.regPF = regPF;
    }
    @XmlElement(name = "СвРегФСС")
    public void setRegFSS(RegFSS regFSS) {
        this.regFSS = regFSS;
    }
    @XmlElement(name = "СвУстКап")
    public void setUstKap(UstKap ustKap) {
        this.ustKap = ustKap;
    }
    @XmlElement(name = "СвПолном")
    public void setPolnomochniy(Polnomochniy polnomochniy) {
        this.polnomochniy = polnomochniy;
    }
    @XmlElement(name = "СвУпрОрг")
    public void setUprOrg(Set<UprOrg> uprOrg) {
        this.uprOrg = uprOrg;
    }
    @XmlElement(name = "СведДолжнФЛ")
    public void setDoljFL(Set<DoljFL> doljFL) {
        this.doljFL = doljFL;
    }
    @XmlElement(name = "СвКорпДог")
    public void setCorpDogovor(Set<CorpDogovor> corpDogovor) {
        this.corpDogovor = corpDogovor;
    }
    @XmlElement(name = "СвУчредит")
    public void setUchrediteli(Uchrediteli uchrediteli) {
        this.uchrediteli = uchrediteli;
    }
    @XmlElement(name = "СвКонвЗайм")
    public void setKonvZaim(Set<KonvZaim> konvZaim) {
        this.konvZaim = konvZaim;
    }
    @XmlElement(name = "СвДоляООО")
    public void setDoliaOOO(DoliaOOO doliaOOO) {
        this.doliaOOO = doliaOOO;
    }
    @XmlElement(name = "СвДержРеестрАО")
    public void setDerjreestAO(DerjReestAO derjreestAO) {
        this.derjreestAO = derjreestAO;
    }
    @XmlElement(name = "СвОКВЭД")
    public void setOKVEDEGRULType(OKVEDEGRULType OKVEDEGRULType) {
        this.OKVEDEGRULType = OKVEDEGRULType;
    }
    @XmlElement(name = "СвЛицензия")
    public void setLicense(Set<License> license) {
        this.license = license;
    }
    @XmlElement(name = "СвПодразд")
    public void setPodrazd(Podrazd podrazd) {
        this.podrazd = podrazd;
    }
    @XmlElement(name = "СвРеорг")
    public void setReorg(Set<Reorg> reorg) {
        this.reorg = reorg;
    }
    @XmlElement(name = "СвПредш")
    public void setPredsh(Set<Predsh> predsh) {
        this.predsh = predsh;
    }
    @XmlElement(name = "СвКФХПредш")
    public void setKfxPredsh(Set<KFXPredsh> kfxPredsh) {
        this.kfxPredsh = kfxPredsh;
    }
    @XmlElement(name = "СвПреем")
    public void setPreem(Set<Preem> preem) {
        this.preem = preem;
    }
    @XmlElement(name = "СвКФХПреем")
    public void setKfxPreem(KFXPreem kfxPreem) {
        this.kfxPreem = kfxPreem;
    }
    @XmlElement(name = "СвЗапЕГРЮЛ")
    public void setZapEGRUL(Set<ZapEGRUL> zapEGRUL) {
        this.zapEGRUL = zapEGRUL;
    }

    public PersonUL(){
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }
}
