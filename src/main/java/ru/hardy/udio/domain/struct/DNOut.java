package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "udio_tfoms")
public class DNOut{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private People people;

    private String reason_dereg; //причина снятия с учета



    public DNOut(People people, String reason_dereg) {
        this.people = people;
        this.reason_dereg = reason_dereg;

    }

    public DNOut() {

    }
}
