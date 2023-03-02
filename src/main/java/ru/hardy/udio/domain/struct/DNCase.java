package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class DNCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;

    @OneToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People pid;
    private Integer idsl;

    private Date date_beg;
    private Date date_edit;

}
