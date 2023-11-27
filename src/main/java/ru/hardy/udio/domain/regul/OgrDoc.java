package ru.hardy.udio.domain.regul;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

/** @version таблица 4.102 */
@Getter
@Setter
@Table(schema = "regul", name = "org_doc")
public class OgrDoc {
    @Id
    private Long id;
    private String priznak;

    @MappedCollection(idColumn = "orgdoc_id")
    private Reorg reorg;
    @MappedCollection(idColumn = "orgdoc_id")
    private StatusUl statusUl;
    @MappedCollection(idColumn = "orgdoc_id")
    private Predsh predsh;
    @MappedCollection(idColumn = "orgdoc_id")
    private Preem preem;
    @MappedCollection(idColumn = "orgdoc_id")
    private ZapEGRUL zapEGRUL;
    @MappedCollection(idColumn = "orgdoc_id")
    private UprOrg uprOrg;
    @MappedCollection(idColumn = "orgdoc_id")
    private DoljFL doljFL;
    @MappedCollection(idColumn = "orgdoc_id")
    private DoliaUstKap doliaUstKap;
    @MappedCollection(idColumn = "orgdoc_id")
    private DerjReestAO derjReestAO;
    @MappedCollection(idColumn = "orgdoc_id")
    private License license;

}
