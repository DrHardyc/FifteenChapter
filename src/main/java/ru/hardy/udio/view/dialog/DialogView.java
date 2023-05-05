package ru.hardy.udio.view.dialog;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridMultiSelectionModel;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.stereotype.Service;
import org.vaadin.olli.FileDownloadWrapper;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.service.DNGetService;
import ru.hardy.udio.service.ExcelService;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.view.grid.GridView;

import java.io.File;
import java.util.Collection;
import java.util.List;

@Service
public class DialogView extends Dialog {

    private final Dialog dialog = new Dialog();
    private final HorizontalLayout horizontalLayout = new HorizontalLayout();//Костыль Excel

    public DialogView(){
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

    public Dialog getKeyGenDialog(TokenService tokenService){
        Button btnKeyGen = new Button("Создать");

        TextField tfKeyGen = new TextField();
        tfKeyGen.setWidthFull();

        TextField tfLpu = new TextField();
        tfLpu.setWidthFull();
        tfKeyGen.setReadOnly(true);

        VerticalLayout vlKeyGen = new VerticalLayout();
        vlKeyGen.setHorizontalComponentAlignment(FlexComponent.Alignment.END, btnKeyGen);
        vlKeyGen.setWidth("500px");

        btnKeyGen.addClickListener(e -> {
            if(tfLpu.getValue().isEmpty()) Notification.show("Введите код LPU");
            else {
                tfKeyGen.setValue(tokenService.getHashWithKey(tokenService.genToken(tfLpu.getValue()).getKey()));
            }
        });

        vlKeyGen.add(tfLpu, tfKeyGen, btnKeyGen);
        dialog.add(vlKeyGen);

        return dialog;
    }

    public Dialog getDNGetDialog(DNGetService dnGetService, Long peopleId){
        Grid<DNGet> grid = new Grid<>(DNGet.class, false);
        grid.addColumn(DNGet::getNhistory);
        grid.addColumn(DNGet::getProfil);
        grid.setItems(dnGetService.getByPeopleId(peopleId));
        grid.setSizeFull();
        dialog.add(grid);
        return dialog;
    }

    public Dialog getMainReportDialog(List<DNGet> dnGets){
        Label label = new Label();
        Grid<DNGet> grid = new Grid<>(DNGet.class, false);
        Button btnExcel = new Button("Выгрузить в Excel");
        btnExcel.setEnabled(false);
        btnExcel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        GridView gridView = new GridView();

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
            FileDownloadWrapper downloadButtonWrapper = new FileDownloadWrapper("fff.xlsx", file);
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
        gridView.getDnGetGrid(grid, dnGetGridListDataView);
        dnGetGridListDataView.addItemCountChangeListener(ev -> {
            label.setText(String.valueOf(dnGetGridListDataView.getItemCount()));
        });
        dialog.getFooter().add(label, btnExcel);
        dialog.add(grid);

        return dialog;
    }
}
