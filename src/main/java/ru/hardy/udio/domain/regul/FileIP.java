package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "file_ip")
@XmlRootElement(name = "Файл")
public class FileIP {

    private Long id;
    private String idFile;
    private String versForm;
    private String typeInf;
    private String versProg;
    private String kolDoc;
    private SenderPeople idOtpr;
    private DocumentUL documentIP;
}
