package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import org.springframework.stereotype.Service;
import org.vaadin.olli.FileDownloadWrapper;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.numberavailableseats.dto.NumberAvailableSeatsDTO;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.dto.SchedulePIAndDispPlotDTO;
import ru.hardy.udio.domain.api.volumemedicalcare.dto.VolumeMedicalCareDTO;
import ru.hardy.udio.service.ExcelService;

import java.io.File;
import java.util.Objects;

@Service
public class GridUtils {
    public static Grid createNewDialogGrid(HorizontalLayout horizontalLayout, Button btnExcel) {
        Grid grid = new Grid<>();

        grid.addItemDoubleClickListener(e -> {
            Notification.show("Double Click!");
        });

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(selectionEvent -> {
            btnExcel.setEnabled(selectionEvent.getAllSelectedItems().size() > 0);
        });
        btnExcel.setEnabled(false);
        btnExcel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnExcel.addClickListener(ev -> {
            horizontalLayout.removeAll();
            File file = null;
            ExcelService excelService = new ExcelService();
            for (Object object : grid.getSelectedItems()){
                if (object instanceof PADataPatientRequestRecord)
                    file = excelService.getPADataPatientRequestRecord(grid.getSelectedItems());
                if (object instanceof IndividualInformingRequestRecord)
                    file = excelService.getIndividualInformingRequestRecord(grid.getSelectedItems());
                if (object instanceof InsuranceCase)
                    file = excelService.getInsuranceCase(grid.getSelectedItems());
                if (object instanceof NumberAvailableSeatsDTO)
                    file = excelService.getNumberAvailableSeatsDTO(grid.getSelectedItems());
                break;
            }

            // Костыль имитирующий скачивание в эксель----
            Button downloadButton = new Button();
            downloadButton.setHeight("0px"); // что бы не было видно кнопки
            downloadButton.setWidth("0px");
            FileDownloadWrapper downloadButtonWrapper = new FileDownloadWrapper(Objects.requireNonNull(file).getName(), file);
            downloadButtonWrapper.wrapComponent(downloadButton);
            horizontalLayout.add(downloadButtonWrapper);
            downloadButton.clickInClient();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //---------------------------------------------
        });

        grid.setSizeFull();
        return grid;
    }

    public static TreeGrid<SchedulePIAndDispPlotDTO> createNewDialogSchedulePIAndDispPlotDTOGrid(HorizontalLayout horizontalLayout, Button btnExcel) {
        TreeGrid<SchedulePIAndDispPlotDTO> grid = new TreeGrid<>();
        btnExcel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnExcel.addClickListener(ev -> {
            horizontalLayout.removeAll();
            //File file = null;
            ExcelService excelService = new ExcelService();
            File file = excelService.getSchedulePIAndDispPlotRequestRecordDTO(grid);

            // Костыль имитирующий скачивание в эксель----
            Button downloadButton = new Button();
            downloadButton.setHeight("0px"); // что бы не было видно кнопки
            downloadButton.setWidth("0px");
            FileDownloadWrapper downloadButtonWrapper = new FileDownloadWrapper(Objects.requireNonNull(file).getName(), file);
            downloadButtonWrapper.wrapComponent(downloadButton);
            horizontalLayout.add(downloadButtonWrapper);
            downloadButton.clickInClient();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //---------------------------------------------
        });

        grid.setSizeFull();
        return grid;
    }

    public static TreeGrid<VolumeMedicalCareDTO> createNewDialogVolumeMedicalCareDTOGrid(HorizontalLayout horizontalLayout, Button btnExcel) {
        TreeGrid<VolumeMedicalCareDTO> grid = new TreeGrid<>();
        btnExcel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        btnExcel.addClickListener(ev -> {
            horizontalLayout.removeAll();
            //File file = null;
            ExcelService excelService = new ExcelService();
            File file = excelService.getVolumeMedicalCareDTO(grid);

            // Костыль имитирующий скачивание в эксель----
            Button downloadButton = new Button();
            downloadButton.setHeight("0px"); // что бы не было видно кнопки
            downloadButton.setWidth("0px");
            FileDownloadWrapper downloadButtonWrapper = new FileDownloadWrapper(Objects.requireNonNull(file).getName(), file);
            downloadButtonWrapper.wrapComponent(downloadButton);
            horizontalLayout.add(downloadButtonWrapper);
            downloadButton.clickInClient();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //---------------------------------------------
        });

        grid.setSizeFull();
        return grid;
    }

}
