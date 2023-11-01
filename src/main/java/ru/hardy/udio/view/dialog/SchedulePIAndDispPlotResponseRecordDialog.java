package ru.hardy.udio.view.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.DTO.SchedulePIAndDispPlotRequestRecordDTO;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.view.grid.GridUtils;
import ru.hardy.udio.view.grid.SchedulePIAndDispPlotRequestRecordGrid;

import java.util.List;

@Service
public class SchedulePIAndDispPlotResponseRecordDialog {

    private final Dialog dialog = new Dialog();
    private final HorizontalLayout horizontalLayout = new HorizontalLayout();//Костыль Excel
    private UdioButton btnExcel;


    public SchedulePIAndDispPlotResponseRecordDialog(){
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
    private void initFooter(){
        btnExcel = new UdioButton(".xlsx", BtnVariant.XLS);
        dialog.getFooter().add(btnExcel);
    }


    public Dialog getSchedulePIAndDispPlotResponseRecordDialog(List<SchedulePIAndDispPlotRequestRecord> schedulePIAndDispPlotRequestRecords) {
        initFooter();
        TreeGrid<SchedulePIAndDispPlotRequestRecordDTO> grid = GridUtils.createNewDialogTreeGrid(horizontalLayout, btnExcel);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        SchedulePIAndDispPlotRequestRecordGrid schedulePIAndDispPlotRequestRecordGrid = new SchedulePIAndDispPlotRequestRecordGrid();

        schedulePIAndDispPlotRequestRecordGrid.getGrid(grid, schedulePIAndDispPlotRequestRecords);
        dialog.add(grid);

        return dialog;
    }
}
