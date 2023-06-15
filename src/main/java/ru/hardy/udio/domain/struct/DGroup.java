package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(schema = "udio_tfoms")
public class DGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dgroup_seq")
    @SequenceGenerator(name = "dgroup_seq", allocationSize = 1)
    private Long id;

    private String ds;
    private String period;

    private Integer code_spec;

    private Date date_beg;
    private Date date_edit;

    public DGroup(){
        this.date_beg = java.util.Date.from(Instant.now());
        this.date_edit = java.util.Date.from(Instant.now());
    }

}
