package ru.hardy.udio.domain.struct;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class DNGet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "file_id", nullable = false)
    private DataFile file_id;
    private Date dbeg;
    private Long iddokt;

    @OneToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People idpeople;

    private String lpu;
    private String nhistory;
    private Date date_1;
    private Long iddiag;
}
