package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class DataFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date date;
    private String lpu;

    private String resultCode;
    private String resultDesc;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "datafile_id", nullable = false)
    private List<DataFilePatient> dataFilePatient;

    private Date date_beg;
    private Date date_edit;

    public DataFile() {

    }
}
