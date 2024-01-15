package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "kfh")
@XmlRootElement(name = "СвКФХ")
public class KFH {
    @Id
    private Long id;

    private String ogrn;
    private String inn;
    private String nameUlPoln;
}
