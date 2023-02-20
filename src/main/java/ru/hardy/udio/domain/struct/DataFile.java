package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "datafile_id", nullable = false)
    private List<DataFilePatient> dataFilePatient;

    public DataFile(String name, Date date, String lpu, List<DataFilePatient> dataFilePatient){
        this.name = name;
        this.date = date;
        this.lpu = lpu;
        this.getDataFilePatient().clear();
        this.getDataFilePatient().addAll(dataFilePatient);
    }

    public DataFile() {

    }
}
