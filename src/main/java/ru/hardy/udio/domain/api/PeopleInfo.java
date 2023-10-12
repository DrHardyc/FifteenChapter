package ru.hardy.udio.domain.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.struct.People;

@Data
@EqualsAndHashCode(callSuper = true)
public class PeopleInfo extends InsuredPerson {
    private int insuranceCase;
    private int visitsCalls;
    private int informing;

    public PeopleInfo(People people, int insuranceCase, int visitsCalls, int informing){
        this.setSurname(people.getSurname());
        this.setName(people.getName());
        this.setPatronymic(people.getPatronymic());
        this.setDateBirth(people.getDateBirth());
        this.setEnp(people.getEnp());
        this.setInsuranceCase(insuranceCase);
        this.setVisitsCalls(visitsCalls);
        this.setInforming(informing);
    }

    public PeopleInfo(){

    }
}
