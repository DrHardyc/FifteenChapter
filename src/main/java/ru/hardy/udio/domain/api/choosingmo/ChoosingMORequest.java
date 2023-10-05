package ru.hardy.udio.domain.api.choosingmo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class ChoosingMORequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reqID;
    private int codeMO;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    private List<ChoosingMORequestRecord> patients;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;
}