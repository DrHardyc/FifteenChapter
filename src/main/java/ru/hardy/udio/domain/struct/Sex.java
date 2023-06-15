package ru.hardy.udio.domain.struct;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(schema = "udio_tfoms")
public class Sex {
    @Id
    private Long id;

    private String name;

    private Date date_beg;
    private Date date_edit;


}
