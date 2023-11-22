package ru.hardy.udio.test;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.DNOut;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(schema = "udio_datacontrol", name = "people")
public class TestPeople extends InsuredPerson {
    @Id
    private Long id;

    private Integer sex;

    private Long idsrz; //идентификатор в БД ЕРЗЛ

    private Integer inv; //инвалидность
    private int mo_attach; //мо прикрепления

    private Date ds; // Дата смерти

    private List<DNGet> dngets; //случаи прохождения д-наблюдения

    private List<DNOut> dnouts; //случаи прохождения д-наблюдения
}
