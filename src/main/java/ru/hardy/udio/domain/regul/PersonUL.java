package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Table(schema = "regul", name = "person_ul")
public class PersonUL {
    @Id
    private Long id;

    private String ogrn;
    @Column("date_ogrn")
    private Date dateogrn;

    private String inn;
    private String kpp;
    private String kopf;
    private String kodOpf;
    private String fullNameOpf;
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
    @MappedCollection(idColumn = "personal_id")
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
    private DoliaUstKap doliaUstKap;
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




}
