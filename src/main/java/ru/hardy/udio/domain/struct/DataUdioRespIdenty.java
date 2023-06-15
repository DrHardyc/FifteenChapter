package ru.hardy.udio.domain.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.ResponseAnswerUdio;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Table(schema = "udio_datacontrol")
public class DataUdioRespIdenty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "data_udio_resp_identy_seq")
    @SequenceGenerator(name = "data_udio_resp_identy_seq", allocationSize = 1)
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

