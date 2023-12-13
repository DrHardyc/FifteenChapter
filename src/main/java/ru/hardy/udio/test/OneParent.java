package ru.hardy.udio.test;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;


@Getter
@Setter
@XmlRootElement(name = "Файл")
//@XmlType(propOrder = { "id", "name", "date" })
@Table(schema = "udio_datacontrol", name = "one_parent")
public class OneParent {
    private Long id;

    private String name;
    private String author;
    //private Date date;

    @XmlAttribute(name = "ид")
    public void setId(Long id) {
        this.id = id;
    }
    @XmlElement(name = "title")
    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public void setAuthor(String author) {
        this.author = author;
    }

    // constructor, getters and setters
}
