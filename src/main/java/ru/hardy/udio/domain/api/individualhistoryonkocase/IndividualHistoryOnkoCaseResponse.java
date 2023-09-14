package ru.hardy.udio.domain.api.individualhistoryonkocase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryOnkoCaseResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
