package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "zap_egrul")
public class ZapEGRUL {
    @Id
    private Long id;

    private String idZap;
    private String grn;
    @MappedCollection(idColumn = "zapegrul_id")
    private VidZap vidZap;
    @MappedCollection(idColumn = "zapegrul_id")
    private Set<ZayvFL> zayvFL;
    @MappedCollection(idColumn = "zapegrul_id")
    private Set<PredDoc> predDoc;
    @MappedCollection(idColumn = "zapegrul_id")
    private Set<Svid> svid;
    @MappedCollection(idColumn = "zapegrul_id")
    private GRNDateIsprPred grnDateIsprPred;
    @MappedCollection(idColumn = "zapegrul_id")
    private GRNDateNedPred grnDateNedPred;
    @MappedCollection(idColumn = "zapegrul_id")
    private StatusZap statusZap;

}
