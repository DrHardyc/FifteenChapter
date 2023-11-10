package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.grid.Grid;
import ru.hardy.udio.domain.api.hospitalization.dto.HospitalizationPatientDTO;

import java.util.List;

public class HospitalizationGrid {


    public void getGrid(Grid<HospitalizationPatientDTO> grid, List<HospitalizationPatientDTO> volumeMedicalCareDTOS) {

        grid.addColumn(HospitalizationPatientDTO::getCodeHospitalization).setHeader("Тип госпитализации").setResizable(true);
        grid.addColumn(HospitalizationPatientDTO::getReasonNotHospitalization).setHeader("Причина по которой не было госпитализации");
        grid.addColumn(HospitalizationPatientDTO::getDateEvent).setHeader("Дата госпитализации");
        grid.addColumn(HospitalizationPatientDTO::getMainDiagnosis).setHeader("Основной диагноз");
        grid.addColumn(HospitalizationPatientDTO::getCodeMO).setHeader("Кщд МО");
        grid.addColumn(HospitalizationPatientDTO::getCodeDep).setHeader("Код отделения");
        grid.addColumn(HospitalizationPatientDTO::getNameDep).setHeader("Наименование отделения");

        grid.setItems(volumeMedicalCareDTOS);

    }
}
