package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class Sex {
    @Id
    private Long id;

    private String name;

    private Date date_beg;
    private Date date_edit;
}
