package ru.hardy.udio.domain.regul;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Table(schema = "regul", name = "snp_sender_people")
public class SNPSenderPeople {
    @Id
    private Long id;

    private String surname;
    private String name;
    private String patronymic;
}
