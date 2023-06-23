package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.DNOut;
import ru.hardy.udio.domain.task.ReportTask;
import ru.hardy.udio.domain.task.TaskStatus;
import ru.hardy.udio.service.DNGetService;
import ru.hardy.udio.service.DNOutService;
import ru.hardy.udio.service.ExcelService;
import ru.hardy.udio.service.UtilService;
import ru.hardy.udio.service.taskservice.ReportTaskService;
import ru.hardy.udio.view.dateinterval.DateInterval;
import ru.hardy.udio.view.dialog.DialogViewGen;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class ReportView extends VerticalLayout {

    @Autowired
    private DNGetService dnGetService;

    @Autowired
    private ReportTaskService reportTaskService;

    @Autowired
    private DNOutService dnOutService;

    private final String username;
    private final UtilService utilService = new UtilService();
    private final DateInterval dateInterval = new DateInterval();
    private final ComboBox<Month> monthPickerBeg = dateInterval.getMonthInterval("Месяц начала периода");
    private final ComboBox<Month> monthPickerEnd = dateInterval.getMonthInterval("Месяц окончания периода");
    private final ComboBox<Integer> yearPickerBeg = dateInterval.getYearInterval("Год начала периода");
    private final ComboBox<Integer> yearPickerEnd = dateInterval.getYearInterval("Год окончания периода");
    private final Button btnSearch = new Button("Найти");

    private final ComboBox<String> comboBox = new ComboBox<>("Данные");


    public ReportView() {
        List<String> cbItems = new ArrayList<>();
        cbItems.add("Файлы");
        cbItems.add("Все");
        cbItems.add("Снятые");
        cbItems.add("ДН-терапефт");
        cbItems.add("ОНКО");
        cbItems.add("КАРДИО");

        username = SecurityContextHolder.getContext().getAuthentication().getName();
        comboBox.setItems(cbItems);

        add(comboBox, yearPickerBeg, yearPickerEnd, monthPickerBeg, monthPickerEnd, btnSearch);
    }

    @Override
    public void onAttach(AttachEvent attachEvent) {

        btnSearch.addClickListener(e -> {
            DialogViewGen dialogViewGen = new DialogViewGen();
            if (comboBox.getValue().equals("Файлы")){

            }else if (comboBox.getValue().equals("Снятые")){
                List<DNOut> dnOuts = dnOutService.getAll();
                Dialog dialog = dialogViewGen.getDieReportDialog(dnOuts);
                add(dialog);
                dialog.open();
            } else if (comboBox.getValue().equals("Все")) {
                Dialog dialog = dialogViewGen.getMainReportDialog(dnGetService.getAll());
                add(dialog);
                dialog.open();
            } else {
                if (checkNullDateInterval(comboBox.getValue())) {
                    String period = "с " + monthPickerBeg.getValue().getValue() + "." +
                            yearPickerBeg.getValue().toString() + "\n" + "по " +
                            monthPickerEnd.getValue().getValue() + "." + yearPickerEnd.getValue().toString();
                    if (comboBox.getValue().equals("ДН-терапефт")) {
                        Notification.show("Задача поставлена в очередь выполнения").setPosition(Notification.Position.TOP_END);
                        new Thread(() -> {
                            Long id = 0L;
                            String filename = UUID.randomUUID() + "_ДН-терапефт.xlsx";
                            try {
                                id = reportTaskService.add(
                                        new ReportTask("ДН-Терапефт", "", TaskStatus.progress, "",
                                                username, period));
                                ExcelService excelService = new ExcelService();
                                excelService.getDNTherapistReportSample(dnGetService.getAllTherapist(),
                                        String.valueOf(monthPickerBeg.getValue().getValue()),
                                        String.valueOf(monthPickerEnd.getValue().getValue()),
                                        yearPickerBeg.getValue().toString(),
                                        yearPickerEnd.getValue().toString(), filename);
                                reportTaskService.updateStatus(TaskStatus.success, "Успешно", filename, id);
                            } catch (Exception err) {
                                if (id != 0L) {
                                    reportTaskService.updateStatus(TaskStatus.error, err.getMessage(), filename, id);
                                } else err.getStackTrace();
                            }
                        }).start();
                    } else if (comboBox.getValue().equals("ОНКО")) {
                        Notification.show("Задача поставлена в очередь выполнения").setPosition(Notification.Position.TOP_END);

                        new Thread(() -> {
                            Long id = 0L;
                            String filename = UUID.randomUUID() + "_ONKO.xlsx";
                            try {
                                id = reportTaskService.add(
                                        new ReportTask("ОНКО", "", TaskStatus.progress, "", username, period));
                                ExcelService excelService = new ExcelService();
                                excelService.getONKOReport(dnGetService.getAllONKO(),
                                        String.valueOf(monthPickerBeg.getValue().getValue()),
                                        String.valueOf(monthPickerEnd.getValue().getValue()),
                                        yearPickerBeg.getValue().toString(),
                                        yearPickerEnd.getValue().toString(), filename);
                                reportTaskService.updateStatus(TaskStatus.success, "Успешно", filename, id);
                            } catch (Exception err) {
                                if (id != 0L) {
                                    reportTaskService.updateStatus(TaskStatus.error, err.getMessage(), filename, id);
                                } else err.getStackTrace();
                            }
                        }).start();
                    } else if (comboBox.getValue().equals("КАРДИО")) {
                        Notification.show("Задача поставлена в очередь выполнения").setPosition(Notification.Position.TOP_END);

                        new Thread(() -> {
                            Long id = 0L;
                            String filename = UUID.randomUUID() + "_КАРДИО.xlsx";
                            try {
                                id = reportTaskService.add(
                                        new ReportTask("КАРДИО", "", TaskStatus.progress, "", username, period));
                                ExcelService excelService = new ExcelService();
                                excelService.getKARDIOReport(dnGetService.getAllKARDIO(),
                                        String.valueOf(monthPickerBeg.getValue().getValue()),
                                        String.valueOf(monthPickerEnd.getValue().getValue()),
                                        yearPickerBeg.getValue().toString(),
                                        yearPickerEnd.getValue().toString(), filename);
                                reportTaskService.updateStatus(TaskStatus.success, "Успешно", filename, id);
                            } catch (Exception err) {
                                if (id != 0L) {
                                    reportTaskService.updateStatus(TaskStatus.error, err.getMessage(), filename, id);
                                } else err.getStackTrace();
                            }
                        }).start();
                    }
                } else {
                    Notification.show("Введите значения периодов!").setPosition(Notification.Position.TOP_END);
                }
            }
        });

        comboBox.addValueChangeListener(e -> {
            if (e.getValue().equals("Файлы")){
                hideComponent(false);
            } else if (e.getValue().equals("Снятые")){
                hideComponent(false);
            } else if (e.getValue().equals("Все")) {
                hideComponent(false);
            } else if (e.getValue().equals("ДН-терапефт")){
                hideComponent(true);
            } else if (e.getValue().equals("ОНКО")){
                hideComponent(true);
            } else if (e.getValue().equals("КАРДИО")){
                hideComponent(true);
            }
        });
    }

    private void hideComponent(boolean flag) {
        yearPickerBeg.setVisible(flag);
        yearPickerEnd.setVisible(flag);
        monthPickerBeg.setVisible(flag);
        monthPickerEnd.setVisible(flag);
    }

    private List<DNGet> changeDataFromDateInterval(){
        if (monthPickerBeg.getValue() != null && monthPickerEnd.getValue() != null && yearPickerBeg.getValue() != null
                && yearPickerEnd.getValue() != null) {
            return dnGetService.getAllWithDateInterval(
                    utilService.transformDate(String.valueOf(monthPickerBeg.getValue().getValue()),
                            yearPickerBeg.getValue().toString(), ru.hardy.udio.domain.report.DateInterval.minDate),
                    utilService.transformDate(String.valueOf(monthPickerEnd.getValue().getValue()),
                            yearPickerEnd.getValue().toString(), ru.hardy.udio.domain.report.DateInterval.maxDate));
        }
        return null;
    }
    private boolean checkNullDateInterval(String cbValue) {
        if (cbValue.equals("Все")) {
            return monthPickerBeg.getValue() != null && monthPickerEnd.getValue() != null && yearPickerBeg.getValue() != null
                    && yearPickerEnd.getValue() != null;
        } else return monthPickerBeg.getValue() != null && yearPickerBeg.getValue() != null;
    }
}
