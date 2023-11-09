package ru.hardy.udio.domain.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PeopleInfo extends InsuredPerson {
    private List<PADataPatientRequestRecord> visitsCallsList;
    private List<IndividualInformingRequestRecord> informingList;
    private List<InsuranceCase> insuranceCaseList;
}
