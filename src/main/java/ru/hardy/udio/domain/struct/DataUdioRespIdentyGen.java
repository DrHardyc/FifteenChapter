package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Entity
@Data
@Table(schema = "udio_datacontrol")
public class DataUdioRespIdentyGen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "data_udio_resp_identy_gen_seq")
    @SequenceGenerator(name = "data_udio_resp_identy_gen_seq", allocationSize = 1)
    private Long genId;

    private Date date_beg;
    private Date date_edit;

    public DataUdioRespIdentyGen(){
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

}
