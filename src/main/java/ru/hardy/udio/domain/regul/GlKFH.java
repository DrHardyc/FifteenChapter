package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "gl_kfh")
@XmlRootElement(name = "СвГлКФХ")
public class GlKFH {
    @Id
    private Long id;
    private String textGlKFH;

    @XmlAttribute(name = "ТекстГлКФХ")
    public void setTextGlKFH(String textGlKFH) {
        this.textGlKFH = textGlKFH;
    }
}
