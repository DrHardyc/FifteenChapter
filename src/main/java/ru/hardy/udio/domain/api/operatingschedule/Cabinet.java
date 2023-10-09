package ru.hardy.udio.domain.api.operatingschedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class Cabinet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestRecord_id", nullable = false)
    @JsonIgnore
    private OperatingScheduleRequestRecord requestRecord;

    private int cabinetNum;
    private String scheduleCabinet;

    private Date dateBeg;
    private Date dateEdit;
    private JPanel panel1;
    private JSlider slider1;
}
