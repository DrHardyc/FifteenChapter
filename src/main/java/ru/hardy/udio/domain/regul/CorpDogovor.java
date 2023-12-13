package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/** @version таблица 4.38 */

@Getter
@Setter
@Table(schema = "regul", name = "corp_dogovor")
@XmlRootElement(name = "СвКорпДог")
public class CorpDogovor {
    @Id
    private Long id;
    @MappedCollection(idColumn = "corpdogovor_id")
    private String vidSvedKorpDog;

    @XmlAttribute(name = "ВидСведКорпДог")
    public void setVidSvedKorpDog(String vidSvedKorpDog) {
        this.vidSvedKorpDog = vidSvedKorpDog;
    }
}
