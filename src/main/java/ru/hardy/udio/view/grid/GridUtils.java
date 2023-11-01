package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.hierarchy.TreeDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.olli.FileDownloadWrapper;
import ru.hardy.udio.domain.api.individualhistoryonkocase.InsuranceCase;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.SchedulePIAndDispPlotRequestRecordDTO;
import ru.hardy.udio.service.ExcelService;

import java.io.File;
import java.util.Objects;
import java.util.function.Consumer;

@Service
public class GridUtils {
    @Autowired
    private SchedulePIAndDispPlotRequestRecordGrid schedulePIAndDispPlotRequestRecordGrid;

    public static Component createFilterHeader(String labelText, Consumer<String> filterChangeConsumer) {
        Span label = new Span(labelText);
        TextField textField = new TextField();
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");
        return layout;
    }

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

    public static TreeGrid<SchedulePIAndDispPlotRequestRecordDTO> createNewDialogTreeGrid(HorizontalLayout horizontalLayout, Button btnExcel) {
        TreeGrid<SchedulePIAndDispPlotRequestRecordDTO> grid = new TreeGrid<>();

//        grid.setSelectionMode(Grid.SelectionMode.MULTI);
//        grid.addSelectionListener(selectionEvent -> {
//            btnExcel.setEnabled(selectionEvent.getAllSelectedItems().size() > 0);
//        });
        //btnExcel.setEnabled(false);
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
}
