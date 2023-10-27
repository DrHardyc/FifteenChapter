package ru.hardy.udio.domain.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PeopleInfo extends InsuredPerson {
    private List<PADataPatientRequestRecord> visitsCallsList;
    private List<IndividualInformingRequestRecord> informingList;
    private List<InsuranceCase> insuranceCaseList;

    public PeopleInfo(People people, List<PADataPatientRequestRecord> visitsCallsList,
                      List<IndividualInformingRequestRecord> informingList,
                      List<InsuranceCase> insuranceCaseList){
        this.setSurname(people.getSurname());
        this.setName(people.getName());
        this.setPatronymic(people.getPatronymic());
        this.setDateBirth(people.getDateBirth());
        this.setEnp(people.getEnp());
        this.setInsuranceCaseList(insuranceCaseList);
        this.setVisitsCallsList(visitsCallsList);
        this.setInformingList(informingList);
    }


    public int getInsuranceCase() {
        return this.insuranceCaseList.size();
    }


    public int getVisitsCalls() {
        return visitsCallsList.size();
    }


    public int getInforming() {
        return informingList.size();
    }

    public PeopleInfo(){

    }
}
