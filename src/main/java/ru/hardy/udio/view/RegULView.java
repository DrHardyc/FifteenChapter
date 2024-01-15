package ru.hardy.udio.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.regul.*;
import ru.hardy.udio.domain.regul.grid.RegULUI;
import ru.hardy.udio.domain.regul.importfromfms.Platel;
import ru.hardy.udio.domain.regul.importfromfms.PlatelMapper;
import ru.hardy.udio.service.regulservice.FileUlService;
import ru.hardy.udio.service.regulservice.RegULUIService;
import ru.hardy.udio.view.grid.RegUlGrid;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_REGUL")
public class RegULView extends VerticalLayout {

    public RegULView(){

        Grid<RegULUI> grid = new Grid<>();

        GridListDataView<RegULUI> regULUIGridListDataView = grid.setItems(RegULUIService.getAll());
        RegUlGrid.getGrid(grid, regULUIGridListDataView);
        setSizeFull();
        grid.setHeight("50em");
        add(grid);
    }
}
