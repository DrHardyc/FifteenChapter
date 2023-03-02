package ru.hardy.udio.domain.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.hardy.udio.domain.ResponseAnswerUdio;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(schema = "udio_datacontrol")
public class DataUdioRespIdenty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private String status;

    private Long identy;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dataudiorespidenty_id")
    private List<DataUdioResp> dataUdioResps;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

    public DataUdioRespIdenty(DataUdioRespIdentyGen dataUdioRespIdentyGen) {
        this.setStatus(ResponseAnswerUdio.PROCESSING);
        this.setIdenty(dataUdioRespIdentyGen.getGenId());
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public DataUdioRespIdenty() {
    }
}

