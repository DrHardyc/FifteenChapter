package ru.hardy.udio.view;


import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.task.ReportTask;
import ru.hardy.udio.service.taskservice.ReportTaskService;
import ru.hardy.udio.view.grid.ReportTaskGrid;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class TaskView extends VerticalLayout {

    @Autowired
    private ReportTaskService reportTaskService;

    private final Grid<ReportTask> grid = new Grid<>();

    public TaskView() {
        grid.setHeight("60em");
        add(grid);
    }

    @Override
    public void onAttach(AttachEvent attachEvent){
        GridListDataView<ReportTask> dnGetGridListDataView = grid.setItems(reportTaskService.getAll());
        ReportTaskGrid.getGrid(grid, dnGetGridListDataView);
    }
}
