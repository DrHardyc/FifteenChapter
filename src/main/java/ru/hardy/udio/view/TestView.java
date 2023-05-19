package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.RolesAllowed;
import org.apache.catalina.webresources.FileResource;
import org.hibernate.dialect.SpannerDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import ru.hardy.udio.domain.report.DNTherapistReport.WorkingAgeSex;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.task.ReportTask;
import ru.hardy.udio.domain.task.StatusTask;
import ru.hardy.udio.service.DNGetService;
import ru.hardy.udio.service.report.DNTherapistReportService;
import ru.hardy.udio.service.task.ReportTaskService;
import ru.hardy.udio.view.grid.ReportTaskGrid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.Month;
import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed({"ROLE_ADMIN"})
public class TestView extends VerticalLayout {

    @Autowired
    private DNGetService dnGetService;

    @Autowired
    private ReportTaskService reportTaskService;

    private List<ReportTask> reportTaskList = null;
    private final Grid<ReportTask> grid = new Grid<>();


    public TestView() {

        Avatar avatar = new Avatar();

        add(grid);


        Anchor anchor = new Anchor(new StreamResource("DNTh.xlsx",
                () -> {
                    try {
                        return new FileInputStream(("samples\\DNTh.xlsx"));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }),
                "A document");


        anchor.getElement().setAttribute("router-ignore", true);

        Button button = new Button("Test");
        Span span = new Span(String.valueOf(22));
        Tooltip tooltip = Tooltip.forComponent(span);
        tooltip.setText(span.getText() + " выполненных новых задач");
        Span caption = new Span("Выполненные");
        //caption.getStyle().set("background", "#F5F5F5");

        Span span1 = createBadge(3);

        TabSheet tabSheet = new TabSheet();
        Tab tab = new Tab(caption, span);
       // tab.getStyle().set("background", "#7CFC00");
        Tab tab1 = new Tab(new Span("Выполняются"), span1);
        HorizontalLayout horizontalLayout = new HorizontalLayout(avatar, span);
        HorizontalLayout horizontalLayout1 = new HorizontalLayout();
        //horizontalLayout.add(new TextField("asdf"));
//        tabSheet.add(tab, horizontalLayout);
//        tabSheet.add(tab1, horizontalLayout1);

        span.getStyle().set("border-radius", "10px");
        span.getStyle().set("background", "#ffcccc");
        span.getStyle().set("padding", "2px");
        span.getStyle().set("font-size", "x-small");
        span.getStyle().set("margin-left", "-15px");
        span.getStyle().set("margin-bottom", "20px");
//        span.getStyle().set("border", "1px solid");
        //span.getStyle().set("border-width", "1px");


//        span2.getStyle().set("height", "150px");
//        span2.getStyle().set("background-color", "#008000");
//        span2.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");

        //add(avatar);
        add(horizontalLayout);
        button.addClickListener(ev -> {


//            DNTherapistReportService dnTherapistReportService = new DNTherapistReportService();
//
//            System.out.println(dnTherapistReportService.getCountCalling(dnGetService.getAllTherapist(), WorkingAgeSex.W_older_55,
//                    "I11, I20.1, I20.8, I20.9, I25.0, I25.1, I25.2, I25.5, I25.6, I25.8, I25.9", "5", "2023"));
        });
        add(button, anchor);
    }

    private Span createBadge(int value) {
        Span badge = new Span(String.valueOf(value));
        badge.getElement().getThemeList().add("badge small contrast");
        badge.getStyle().set("margin-inline-start", "var(--lumo-space-xs)");
        return badge;
    }

    @Override
    public void onAttach(AttachEvent attachEvent){
        GridListDataView<ReportTask> dnGetGridListDataView = grid.setItems(reportTaskService.getAll());
        ReportTaskGrid.getGrid(grid, dnGetGridListDataView);
    }

}


