package ru.hardy.udio.domain.regul;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.MappedCollection;

/** @version таблица 4.96 */
@Getter
@Setter
@Table(schema = "regul", name = "grndate")
public class GRNDate {
    @Id
    private Long id;

    private String grn;
    private String dateWrite;

    @MappedCollection(idColumn = "grndate_id")
    private NameUL nameUL;
    @MappedCollection(idColumn = "grndate_id")
    private NameUL nameULIspr;
    @MappedCollection(idColumn = "grndate_id")
    private EmailUl emailUl;
    @MappedCollection(idColumn = "grndate_id")
    private EmailUl emailUlIspr;
    @MappedCollection(idColumn = "grndate_id")
    private ObrUL obrUL;
    @MappedCollection(idColumn = "grndate_id")
    private ObrUL obrULIspr;
    @MappedCollection(idColumn = "grndate_id")
    private RegOrg regOrg;
    @MappedCollection(idColumn = "grndate_id")
    private StatusUl statusUl;
    @MappedCollection(idColumn = "grndate_id")
    private StatusUl statusUlIspr;
    @MappedCollection(idColumn = "grndate_id")
    private PrekrUl prekrUl;
    @MappedCollection(idColumn = "grndate_id")
    private TypeUstav typeUstav;
    @MappedCollection(idColumn = "grndate_id")
    private TypeUstav typeUstavIspr;
    @MappedCollection(idColumn = "grndate_id")
    private UchetNO uchetNO;
    @MappedCollection(idColumn = "grndate_id")
    private UchetNO uchetNOIspr;
    @MappedCollection(idColumn = "grndate_id")
    private RegPF regPF;
    @MappedCollection(idColumn = "grndate_id")
    private RegPF regPFIspr;
    @MappedCollection(idColumn = "grndate_id")
    private RegFSS regFSS;
    @MappedCollection(idColumn = "grndate_id")
    private RegFSS regFSSIspr;
    @MappedCollection(idColumn = "grndate_id")
    private UstKap ustKap;
    @MappedCollection(idColumn = "grndate_id")
    private UstKap ustKapIspr;
    @MappedCollection(idColumn = "grndate_id")
    private Polnomochniy polnomochniy;
    @MappedCollection(idColumn = "grndate_id")
    private Polnomochniy polnomochniyIspr;
    @MappedCollection(idColumn = "grndate_id")
    private UprOrg uprOrgPerv;
    @MappedCollection(idColumn = "grndate_id")
    private DoljFL doljFLPerv;
    @MappedCollection(idColumn = "grndate_id")
    private CorpDogovor corpDogovor;
    @MappedCollection(idColumn = "grndate_id")
    private CorpDogovor corpDogovorIspr;
    @MappedCollection(idColumn = "grndate_id")
    private KonvZaim konvZaimPerv;
    @MappedCollection(idColumn = "grndate_id")
    private KonvZaim konvZaim;
    @MappedCollection(idColumn = "grndate_id")
    private KonvZaim konvZaimIspr;
    @MappedCollection(idColumn = "grndate_id")
    private DoliaUstKap doliaUstKap;
    @MappedCollection(idColumn = "grndate_id")
    private DoliaUstKap doliaUstKapIspr;
    @MappedCollection(idColumn = "grndate_id")
    private DerjReestAO derjReestAOPerv;
    @MappedCollection(idColumn = "grndate_id")
    private OKVED okved;
    @MappedCollection(idColumn = "grndate_id")
    private OKVED okvedIspr;
    @MappedCollection(idColumn = "grndate_id")
    private License license;
    @MappedCollection(idColumn = "grndate_id")
    private License licenseIspr;
    @MappedCollection(idColumn = "grndate_id")
    private OgrDoc ogrDoc;
    @MappedCollection(idColumn = "grndate_id")
    private OgrDoc ogrDocIspr;
    @MappedCollection(idColumn = "grndate_id")
    private Reorg reorg;
    @MappedCollection(idColumn = "grndate_id")
    private Reorg reorgIzmSostUL;
    @MappedCollection(idColumn = "grndate_id")
    private Predsh predsh;
    @MappedCollection(idColumn = "grndate_id")
    private Predsh predshIspr;
    @MappedCollection(idColumn = "grndate_id")
    private FL fl;
    @MappedCollection(idColumn = "grndate_id")
    private FL flIspr;
    @MappedCollection(idColumn = "grndate_id")
    private Preem preem;
    @MappedCollection(idColumn = "grndate_id")
    private Preem preemIspr;
    @MappedCollection(idColumn = "grndate_id")
    private ShortNameUl shortNameUl;
    @MappedCollection(idColumn = "grndate_id")
    private ShortNameUl shortNameUlIspr;
    @MappedCollection(idColumn = "grndate_id")
    private NameUlKodOKIN nameUlKodOKIN;
    @MappedCollection(idColumn = "grndate_id")
    private NameUlKodOKIN nameUlKodOKINIspr;
    @MappedCollection(idColumn = "grndate_id")
    private FullNameUlIn fullNameUlIn;
    @MappedCollection(idColumn = "grndate_id")
    private FullNameUlIn fullNameUlInIspr;
    @MappedCollection(idColumn = "grndate_id")
    private ShortNameUlIn shortNameUlIn;
    @MappedCollection(idColumn = "grndate_id")
    private ShortNameUlIn shortNameUlInIspr;
    @MappedCollection(idColumn = "grndate_id")
    private MNUL mnul;
    @MappedCollection(idColumn = "grndate_id")
    private MNUL mnulIspr;
    @MappedCollection(idColumn = "grndate_id")
    private AdrULFIAS adrULFIAS;
    @MappedCollection(idColumn = "grndate_id")
    private AdrULFIAS adrULFIASIspr;
    @MappedCollection(idColumn = "grndate_id")
    private AdrRF adrRF;
    @MappedCollection(idColumn = "grndate_id")
    private AdrRF adrRFIspr;
    @MappedCollection(idColumn = "grndate_id")
    private NedAdrUl nedAdrUl;
    @MappedCollection(idColumn = "grndate_id")
    private NedAdrUl nedAdrUlIspr;
    @MappedCollection(idColumn = "grndate_id")
    private ReshIzmMN reshIzmMN;
    @MappedCollection(idColumn = "grndate_id")
    private ReshIzmMN reshIzmMNIspr;
    @MappedCollection(idColumn = "grndate_id")
    private OKVEDType okvedType;
    @MappedCollection(idColumn = "grndate_id")
    private OKVEDType okvedTypeIspr;
}

