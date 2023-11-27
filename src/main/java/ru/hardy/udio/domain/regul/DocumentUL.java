package ru.hardy.udio.domain.regul;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.atmosphere.config.service.Get;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Getter
@Setter
@Table(schema = "regul", name = "document_ul")
public class DocumentUL {
    @Id
    private Long id;

    @MappedCollection(idColumn = "documentul_id")
    private PersonUL personUL;
}
