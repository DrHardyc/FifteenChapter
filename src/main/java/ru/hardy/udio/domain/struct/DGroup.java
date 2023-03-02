package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class DGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ds;
    private String period;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    private Integer code_spec;

    private Date date_beg;
    private Date date_edit;
}
