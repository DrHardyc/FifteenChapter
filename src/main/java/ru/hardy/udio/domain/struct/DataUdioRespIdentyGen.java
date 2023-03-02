package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@Table(schema = "udio_datacontrol")
public class DataUdioRespIdentyGen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genId;

    private Date date_beg;
    private Date date_edit;

    public DataUdioRespIdentyGen(){
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

}
