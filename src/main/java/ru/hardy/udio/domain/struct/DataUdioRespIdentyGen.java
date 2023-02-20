package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "udio_datacontrol")
public class DataUdioRespIdentyGen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genId;

}
