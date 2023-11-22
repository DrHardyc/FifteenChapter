package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.service.AChartService;
import ru.hardy.udio.service.nsiservice.MedicalOrganizationService;
import ru.hardy.udio.view.grid.GridUtils;
import ru.hardy.udio.view.grid.MedicalOrganizationGrid;
import ru.hardy.udio.view.span.CMSpan;

import java.util.ArrayList;
import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class MOView extends VerticalLayout{

    @Autowired
    private MedicalOrganizationService medicalOrganizationService;

    @Autowired
    private MedicalOrganizationGrid medicalOrganizationGrid;

    public MOView(){

    }

    @Override
    public void onAttach(AttachEvent attachEvent){
        Grid<MedicalOrganization> grid = new Grid<>();
        GridListDataView<MedicalOrganization> medicalOrganizationGridListDataView = grid.setItems(medicalOrganizationService.getAll());
        medicalOrganizationGrid.getGrid(grid, medicalOrganizationGridListDataView);
        setSizeFull();
        grid.setHeight("50em");
        add(grid);
    }

}
