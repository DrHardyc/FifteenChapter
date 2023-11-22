package ru.hardy.udio.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Getter
@Setter
@Table(schema = "udio_tfoms", name = "oneparent")
public class OneParent {
    @Id
    private Long id;
    private String name;
    private Date date;
    private int number;

}
