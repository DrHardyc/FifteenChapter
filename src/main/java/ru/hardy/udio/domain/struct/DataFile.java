package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "data_file", schema = "udio_tfoms")
public class DataFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "data_file_seq")
    @SequenceGenerator(name = "data_file_seq", allocationSize = 1)
    private Long id;
    private String name;
    private Date date;
    private Integer mo;

    private Long import_id;

    private String resultCode;
    private String resultDesc;

    @OneToMany(mappedBy = "datafile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DataFilePatient> dataFilePatient;
    private Date date_beg;
    private Date date_edit;

    public DataFile(String name, Date date, Integer mo, Long import_id){
        this.name = name;
        this.date = date;
        this.mo = mo;
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
        this.import_id = import_id;
    }

    public DataFile() {
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }



}
