package ru.hardy.udio.view.dialog;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.theme.lumo.LumoUtility;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Service;
import org.vaadin.olli.FileDownloadWrapper;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.dto.DNOutDto;
import ru.hardy.udio.service.DNGetService;
import ru.hardy.udio.service.ExcelService;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.view.grid.DNGetGrid;
import ru.hardy.udio.view.grid.DNOutDtoGrid;

import java.io.File;
import java.util.List;

@Service
public class DialogViewGen {

    private final Dialog dialog = new Dialog();
    private final HorizontalLayout horizontalLayout = new HorizontalLayout();//Костыль Excel

    public DialogViewGen(){
        Button closeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL),
                (event) -> dialog.close());
        closeButton.getStyle().set("margin-left", "auto");
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        dialog.setHeight("100vh");
        dialog.setWidth("100vw");
        dialog.getHeader().add(horizontalLayout);//Костыль Excel
        dialog.getHeader().add(closeButton);
        dialog.setCloseOnOutsideClick(false);
        dialog.setDraggable(true);
        dialog.setResizable(true);
        dialog.setModal(false);
    }

    public Dialog getDNGetDialog(DNGetService dnGetService, Long peopleId){
        Grid<DNGet> grid = new Grid<>(DNGet.class, false);
        grid.addColumn(DNGet::getNhistory);
        grid.addColumn(DNGet::getSpecialization);
        grid.setItems(dnGetService.getByPeopleId(peopleId));
        grid.setSizeFull();
        dialog.add(grid);
        return dialog;
    }

    public Dialog getDieReportDialog(List<DNOutDto> dnOutDtos){
        Label label = new Label();
        Grid<DNOutDto> grid = new Grid<>(DNOutDto.class, false);
        Button btnExcel = new UdioButton(".xlsx", BtnVariant.XLS);
        btnExcel.setEnabled(false);
        btnExcel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        DNOutDtoGrid dnOutDtoGrid = new DNOutDtoGrid();

        grid.addItemDoubleClickListener(e -> {
            Notification.show("Double Click!");
        });

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(selectionEvent -> {
            btnExcel.setEnabled(selectionEvent.getAllSelectedItems().size() > 0);
        });

        label.getStyle().set("margin-right", "auto");
        btnExcel.addClickListener(ev -> {
            horizontalLayout.removeAll();
            ExcelService excelService = new ExcelService();
            File file = excelService.getDNOutDto(grid.getSelectedItems());

            // Костыль имитирующий скачивание в эксель----
            Button downloadButton = new Button();
            downloadButton.setHeight("0px"); // что бы не было видно кнопки
            downloadButton.setWidth("0px");
            FileDownloadWrapper downloadButtonWrapper = new FileDownloadWrapper(file.getName(), file);
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
        GridListDataView<DNOutDto> dnOutGridListDataView = grid.setItems(dnOutDtos);
        dnOutDtoGrid.getGrid(grid, dnOutGridListDataView);
        dnOutGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(dnOutGridListDataView.getItemCount()));
        });
        dialog.getFooter().add(label, btnExcel);
        dialog.add(grid);

        return dialog;

    }

    public Dialog getMainReportDialog(List<DNGet> dnGets){
        Label label = new Label();
        Grid<DNGet> grid = new Grid<>(DNGet.class, false);
        Button btnExcel = new UdioButton(".xlsx", BtnVariant.XLS);
        btnExcel.setEnabled(false);
        btnExcel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        DNGetGrid dnGetGrid = new DNGetGrid();

        grid.addItemDoubleClickListener(e -> {
            Notification.show("Double Click!");
        });

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(selectionEvent -> {
            btnExcel.setEnabled(selectionEvent.getAllSelectedItems().size() > 0);
        });

        label.getStyle().set("margin-right", "auto");
        btnExcel.addClickListener(ev -> {
            horizontalLayout.removeAll();
            ExcelService excelService = new ExcelService();
            File file = excelService.getOtherDNGets(grid.getSelectedItems());

            // Костыль имитирующий скачивание в эксель----
            Button downloadButton = new Button();
            downloadButton.setHeight("0px"); // что бы не было видно кнопки
            downloadButton.setWidth("0px");
            FileDownloadWrapper downloadButtonWrapper = new FileDownloadWrapper(file.getName(), file);
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
        GridListDataView<DNGet> dnGetGridListDataView = grid.setItems(dnGets);
        dnGetGrid.getGrid(grid, dnGetGridListDataView);
        dnGetGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(dnGetGridListDataView.getItemCount()));
        });
        dialog.getFooter().add(label, btnExcel);
        dialog.add(grid);

        return dialog;
    }

    public Dialog getDetailDialog(boolean bPolicy){
        dialog.setHeight("50vh");
        dialog.setWidth("50vw");

        FormLayout formLayout = new FormLayout();

        TextField tfRegAddress = new UdioTextField();
        TextField tfLocationAddress = new UdioTextField();
        TextField tfMOAttach = new UdioTextField();
        Span spPolicy;
        if (bPolicy){
            spPolicy = new Span(createIcon(VaadinIcon.CHECK),
                    new Span("Действующий"));
            spPolicy.addClassNames(LumoUtility.TextColor.SUCCESS,
                    LumoUtility.Padding.SMALL,
                    LumoUtility.Background.BASE,
                    LumoUtility.BoxShadow.XSMALL,
                    LumoUtility.BorderRadius.LARGE);
        } else
        {
            spPolicy = new Span(createIcon(VaadinIcon.EXCLAMATION_CIRCLE_O),
                    new Span("Погашен"));
            spPolicy.getElement().getThemeList().add("badge error");
        }

        TextField tfCause = new UdioTextField();

        formLayout.addFormItem(tfRegAddress, "Адрес регистрации");
        formLayout.addFormItem(tfLocationAddress, "Адрес места жительства");
        formLayout.addFormItem(tfMOAttach, "МО прикрепления");
        formLayout.addFormItem(spPolicy, "Полис");
        formLayout.addFormItem(tfCause, "Причина");
        dialog.add(formLayout);

        return dialog;
    }

    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }


    @Data
    @EqualsAndHashCode(callSuper = true)
    private static class UdioTextField extends TextField{

        public UdioTextField(){
            this.setReadOnly(true);
        }
    }
}
