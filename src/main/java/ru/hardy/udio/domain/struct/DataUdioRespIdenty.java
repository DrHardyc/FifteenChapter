package ru.hardy.udio.domain.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(schema = "udio_datacontrol")
public class DataUdioRespIdenty {

    public static final String PROCESSING = "В обработке";
    public static final String PROCESSING_ERR = "Ошибка обработки";
    public static final String PROCESSING_END = "Обработка завершена";


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private Long identy;

    private String status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "dataudiorespidenty_id")
    private List<DataUdioResp> dataUdioResps;

    public DataUdioRespIdenty(DataUdioRespIdentyGen dataUdioRespIdentyGen) {
        this.status = PROCESSING;
        this.setIdenty(dataUdioRespIdentyGen.getGenId());
    }

    public DataUdioRespIdenty() {
    }
}

