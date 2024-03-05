package ru.hardy.udio.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.regul.*;
import ru.hardy.udio.domain.regul.grid.RegULUI;
import ru.hardy.udio.domain.regul.importfromfms.Platel;
import ru.hardy.udio.domain.regul.importfromfms.PlatelMapper;
import ru.hardy.udio.report.Reports;
import ru.hardy.udio.service.regulservice.FileUlService;
import ru.hardy.udio.service.regulservice.RegULUIService;
import ru.hardy.udio.view.grid.RegUlGrid;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_REGUL")
public class RegULView extends VerticalLayout {

    public RegULView(){
        Button btnPrint = new Button(new Icon(VaadinIcon.PRINT));
        btnPrint.setEnabled(false);
        List<String> textList = new ArrayList<>();
        String filename = "report.pdf";
        Reports reports = new Reports();
        btnPrint.addClickListener(event -> {
            reports.createPDFReport(filename, textList.get(0), textList.get(1), textList.get(2), textList.get(3),
                    textList.get(4), textList.get(5));
            StreamResource resource = new StreamResource(filename,
                    () -> {
                        try {
                            return new ByteArrayInputStream(FileUtils.readFileToByteArray(new File(filename)));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            Anchor anchor = new Anchor(resource, "");
            anchor.getElement().callJsFunction("click");
            add(anchor);
        });

        Grid<RegULUI> grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener(selectionEvent -> {
            btnPrint.setEnabled(true);
            textList.add(selectionEvent.getFirstSelectedItem().get().getName());
            textList.add(selectionEvent.getFirstSelectedItem().get().getInn());
            textList.add(selectionEvent.getFirstSelectedItem().get().getKpp());
            textList.add(selectionEvent.getFirstSelectedItem().get().getOgrn());
            textList.add(selectionEvent.getFirstSelectedItem().get().getRegNFoms());
            textList.add(selectionEvent.getFirstSelectedItem().get().getAddress());
        });
        GridListDataView<RegULUI> regULUIGridListDataView = grid.setItems(RegULUIService.getAll());
        RegUlGrid.getGrid(grid, regULUIGridListDataView);
        setSizeFull();
        grid.setHeight("50em");

        add(btnPrint, grid);
    }
}
