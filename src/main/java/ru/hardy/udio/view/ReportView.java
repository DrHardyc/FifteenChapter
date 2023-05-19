package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.task.ReportTask;
import ru.hardy.udio.domain.task.StatusTask;
import ru.hardy.udio.service.DNGetService;
import ru.hardy.udio.service.ExcelService;
import ru.hardy.udio.service.ServiceUtil;
import ru.hardy.udio.service.task.ReportTaskService;
import ru.hardy.udio.view.dateinterval.DateInterval;
import ru.hardy.udio.view.dialog.DialogView;

import java.time.Instant;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class ReportView extends VerticalLayout {

    @Autowired
    private DNGetService dnGetService;

    @Autowired
    private ReportTaskService reportTaskService;

    private String username;
    private final ServiceUtil serviceUtil = new ServiceUtil();
    private final DateInterval dateInterval = new DateInterval();
    private final ComboBox<Month> monthPickerBeg = dateInterval.getMonthInterval("Месяц начала периода");
    private final ComboBox<Month> monthPickerEnd = dateInterval.getMonthInterval("Месяц окончания периода");
    private final ComboBox<Integer> yearPickerBeg = dateInterval.getYearInterval("Год начала периода");
    private final ComboBox<Integer> yearPickerEnd = dateInterval.getYearInterval("Год окончания периода");
    private final Button btnSearch = new Button("Найти");

    private final ComboBox<String> comboBox = new ComboBox<>("Данные");


    public ReportView() {
        List<String> cbItems = new ArrayList<>();
        cbItems.add("Все");
        cbItems.add("ДН-терапефт");

        username = SecurityContextHolder.getContext().getAuthentication().getName();
        comboBox.setItems(cbItems);

        TabSheet tabSheet = new TabSheet();

        Label lblAll = new Label("Все");
        VerticalLayout vlAll = new VerticalLayout();
        vlAll.add();
        tabSheet.add(lblAll, vlAll);

        Label lblDNTherapist = new Label("ДН-терапефт");
        VerticalLayout vlDNTherapist = new VerticalLayout();
        tabSheet.add(lblDNTherapist, vlDNTherapist);


        add(comboBox, yearPickerBeg, yearPickerEnd, monthPickerBeg, monthPickerEnd, btnSearch);
    }

    @Override
    public void onAttach(AttachEvent attachEvent) {

        btnSearch.addClickListener(e -> {

            if (checkNullDateInterval()) {
                if (comboBox.getValue().equals("Все")) {
                    List<DNGet> dnGets = changeDataFromDateInterval();
                    Dialog dialog = new DialogView().getMainReportDialog(dnGets);
                    add(dialog);
                    dialog.open();
                } else if (comboBox.getValue().equals("ДН-терапефт")) {
                    Notification.show("Задача поставлена в очередь выполнения").setPosition(Notification.Position.TOP_END);

                    new Thread(() -> {
                        Long id = 0L;
                        String filename = UUID.randomUUID() + "_ДН-терапефт.xlsx";
                        try {
                            id = reportTaskService.add(
                                    new ReportTask("ДН-Терапефт", "", StatusTask.progress, "", username));
                            ExcelService excelService = new ExcelService();
                            excelService.getDNTherapistReportSample(dnGetService.getAllTherapist(),
                                    String.valueOf(monthPickerBeg.getValue().getValue()), yearPickerBeg.getValue().toString(), filename);
                            reportTaskService.updateStatus(StatusTask.success,"Успешно", filename, id);
                        } catch (Exception err){
                            if (id != 0L){
                                reportTaskService.updateStatus(StatusTask.error, err.getMessage(), filename, id);
                            } else err.getStackTrace();
                        }
                    }).start();
                }
            } else {
                Notification.show("Введите значения периодов!").setPosition(Notification.Position.TOP_END);
            }
        });
    }

    private List<DNGet> changeDataFromDateInterval(){
        if (monthPickerBeg.getValue() != null && monthPickerEnd.getValue() != null && yearPickerBeg.getValue() != null
                && yearPickerEnd.getValue() != null) {
            return dnGetService.getAllWithDateInterval(
                    serviceUtil.transformDate(String.valueOf(monthPickerBeg.getValue().getValue()),
                            yearPickerBeg.getValue().toString(), ru.hardy.udio.domain.report.DNTherapistReport.DateInterval.minDate),
                    serviceUtil.transformDate(String.valueOf(monthPickerEnd.getValue().getValue()),
                            yearPickerEnd.getValue().toString(), ru.hardy.udio.domain.report.DNTherapistReport.DateInterval.maxDate));
        }
        return null;
    }
    private boolean checkNullDateInterval(){
        return monthPickerBeg.getValue() != null && monthPickerEnd.getValue() != null && yearPickerBeg.getValue() != null
                && yearPickerEnd.getValue() != null;
    }
}
