package ru.hardy.udio.domain.api.individualhistoryonkocase;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class IndividualHistoryOnkoCaseResponse extends InsuredPerson {

    private int resultRequestCode;

    private List<IndividualHistoryOnkoCaseResponseRecord> insuranceCase;
}
