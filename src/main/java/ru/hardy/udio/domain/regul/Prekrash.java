package ru.hardy.udio.domain.regul;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(schema = "regul", name = "prekrash")
@XmlRootElement(name = "СвПрекращ")
public class Prekrash {
    @Id
    private Long id;
    @MappedCollection(idColumn = "prekrash_id")
    private StatusPrekr status;
    @MappedCollection(idColumn = "prekrash_id")
    private NovUl novUl;

    @XmlElement(name = "СвСтатус")
    public void setStatus(StatusPrekr status) {
        this.status = status;
    }
    @XmlElement(name = "СвНовЮЛ")
    public void setNovUl(NovUl novUl) {
        this.novUl = novUl;
    }
}
