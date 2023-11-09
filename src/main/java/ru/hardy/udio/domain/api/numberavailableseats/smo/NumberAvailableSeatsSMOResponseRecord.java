package ru.hardy.udio.domain.api.numberavailableseats.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIDepartmentResponseRecord;
import ru.hardy.udio.domain.api.numberavailableseats.dto.NumberAvailableSeatsDTO;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeatsSMOResponseRecord extends APIDepartmentResponseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private NumberAvailableSeatsSMOResponse response;

    @JsonIgnore
    private int respCode;
    @JsonIgnore
    private String respMessage;

    private int currentDay;
    private int day1;
    private int day2;
    private int day3;
    private int day4;
    private int day5;
    private int day6;
    private int day7;
    private int day8;
    private int day9;
    private int day10;


    public NumberAvailableSeatsSMOResponseRecord(NumberAvailableSeatsDTO numberAvailableSeatsDTO,
                                                 NumberAvailableSeatsSMOResponse numberAvailableSeatsSMOResponse) {
        this.setResponse(numberAvailableSeatsSMOResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setNameDep(numberAvailableSeatsDTO.getName());
        this.setCodeDep(numberAvailableSeatsDTO.getCode());
        this.setCurrentDay(numberAvailableSeatsDTO.getCurrentDay());
        this.setDay1(numberAvailableSeatsDTO.getDay1());
        this.setDay2(numberAvailableSeatsDTO.getDay2());
        this.setDay3(numberAvailableSeatsDTO.getDay3());
        this.setDay4(numberAvailableSeatsDTO.getDay4());
        this.setDay5(numberAvailableSeatsDTO.getDay5());
        this.setDay6(numberAvailableSeatsDTO.getDay6());
        this.setDay7(numberAvailableSeatsDTO.getDay7());
        this.setDay8(numberAvailableSeatsDTO.getDay8());
        this.setDay9(numberAvailableSeatsDTO.getDay9());
        this.setDay10(numberAvailableSeatsDTO.getDay10());
    }

    public NumberAvailableSeatsSMOResponseRecord() {

    }
}
