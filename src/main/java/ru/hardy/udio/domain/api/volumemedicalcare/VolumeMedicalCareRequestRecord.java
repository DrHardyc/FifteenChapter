package ru.hardy.udio.domain.api.volumemedicalcare;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class VolumeMedicalCareRequestRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private VolumeMedicalCareRequest request;

    @OneToOne(mappedBy = "requestRecord")
    private VolumeMedicalCare volumeMedicalCare;

    private int volumesMedicalCareProvided;

    public int codeDep;
    public String nameDep;

    private Date date_beg;
    private Date date_edit;
}
