package ru.hardy.udio.domain.api.choosingmo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(schema = "udio_tfoms")
@Component
public class ChoosingMO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int codeMO;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    @JsonIgnore
    private People people;

    @OneToOne(fetch = FetchType.LAZY)
    private ChoosingMORequestRecord requestRecord;
    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;


    public ChoosingMO(){

    }

}
