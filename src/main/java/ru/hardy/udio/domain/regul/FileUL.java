package ru.hardy.udio.domain.regul;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import ru.hardy.udio.domain.regul.enumul.InfoType;

import java.util.Set;

@Getter
@Setter
@Table(schema = "regul")
public class FileUL {
    @Id
    private Long id;
    private String id_file;
    private String formatVersion;
    private InfoType infoType;
    private String transmissionProgramVersion;
    private String quantityDoc;

    @MappedCollection(idColumn = "fileul_id")
    private SenderPeople senderPeople;

    @MappedCollection(idColumn = "fileul_id")
    private Set<DocumentUL> documentUL;
}
