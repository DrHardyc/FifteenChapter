package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.hardy.udio.domain.button.BtnVariant;
import ru.hardy.udio.domain.button.UdioButton;
import ru.hardy.udio.domain.combobox.UdioCombobox;
import ru.hardy.udio.domain.struct.DNOut;
import ru.hardy.udio.domain.struct.dto.DNOutDto;
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

    @Autowired
    private ExcelService excelService;

    private final String username;

    private final HorizontalLayout hlBegPeriod = new HorizontalLayout();
    private final HorizontalLayout hlEndPeriod = new HorizontalLayout();
    private final DateInterval dateInterval = new DateInterval();
    private final ComboBox<Month> monthPickerBeg = dateInterval.getMonthInterval();
    private final ComboBox<Month> monthPickerEnd = dateInterval.getMonthInterval();
    private final ComboBox<Integer> yearPickerBeg = dateInterval.getYearInterval();
    private final ComboBox<Integer> yearPickerEnd = dateInterval.getYearInterval();
    private final Button btnSearch = new UdioButton("Найти", BtnVariant.SEARCH);

    private final ComboBox<String> comboBox = new UdioCombobox<>();


    public ReportView() {
        FormLayout formLayout = new FormLayout();

        hlBegPeriod.setPadding(false);
        hlBegPeriod.setSpacing(false);
        hlBegPeriod.add(monthPickerBeg, yearPickerBeg);
        hlBegPeriod.getThemeList().add("spacing-xs");

        hlEndPeriod.setPadding(false);
        hlEndPeriod.setSpacing(false);
        hlEndPeriod.add(monthPickerEnd, yearPickerEnd);
        hlEndPeriod.getThemeList().add("spacing-xs");

        formLayout.addFormItem(comboBox, "Данные");
        formLayout.addFormItem(hlBegPeriod, "c");
        formLayout.addFormItem(hlEndPeriod, "по");

        formLayout.setColspan(btnSearch, 1);
        formLayout.setWidth("26%");
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("200px", 1, FormLayout.ResponsiveStep.LabelsPosition.ASIDE)
        //    new FormLayout.ResponsiveStep("400px", 2)
        );

        List<String> cbItems = new ArrayList<>();
        cbItems.add("Файлы");
        cbItems.add("Все");
        cbItems.add("Снятые");
        cbItems.add("ДН-терапефт");
        cbItems.add("ДН-онколог");
        cbItems.add("ОНКО");
        cbItems.add("КАРДИО");

        username = SecurityContextHolder.getContext().getAuthentication().getName();
        comboBox.setItems(cbItems);

        add(formLayout, btnSearch);
    }

    @Override
    public void onAttach(AttachEvent attachEvent) {

        btnSearch.addClickListener(e -> {
            DialogViewGen dialogViewGen = new DialogViewGen();
            if (comboBox.getValue().equals("Файлы")){

            }else if (comboBox.getValue().equals("Снятые")){
                List<DNOutDto> dnOutsDtos = dnOutService.getAll();
                Dialog dialog = dialogViewGen.getDieReportDialog(dnOutsDtos);
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

                                excelService.getDNOnkoTherapiReportSample(
                                        String.valueOf(monthPickerBeg.getValue().getValue()),
                                        String.valueOf(monthPickerEnd.getValue().getValue()),
                                        yearPickerBeg.getValue().toString(), yearPickerEnd.getValue().toString(),
                                        filename, "76");
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
                                excelService.getONKOReport(
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
                                excelService.getKARDIOReport(
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
                    } else if (comboBox.getValue().equals("ДН-онколог")) {
                        Notification.show("Задача поставлена в очередь выполнения").setPosition(Notification.Position.TOP_END);
                        new Thread(() -> {
                            Long id = 0L;
                            String filename = UUID.randomUUID() + "_ДН-онколог.xlsx";
                            try {
                                id = reportTaskService.add(
                                        new ReportTask("ДН-онколог", "", TaskStatus.progress, "",
                                                username, period));
                                excelService.getDNOnkoTherapiReportSample(
                                        String.valueOf(monthPickerBeg.getValue().getValue()),
                                        String.valueOf(monthPickerEnd.getValue().getValue()),
                                        yearPickerBeg.getValue().toString(), yearPickerEnd.getValue().toString(),
                                        filename, "41");
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
                enabledComponent(false);
            } else if (e.getValue().equals("Снятые")){
                enabledComponent(false);
            } else if (e.getValue().equals("Все")) {
                enabledComponent(false);
            } else if (e.getValue().equals("ДН-терапефт")){
                enabledComponent(true);
            } else if (e.getValue().equals("ДН-онколог")){
                enabledComponent(true);
            } else if (e.getValue().equals("ОНКО")){
                enabledComponent(true);
            } else if (e.getValue().equals("КАРДИО")){
                enabledComponent(true);
            }
        });
    }

    private void enabledComponent(boolean flag) {
        hlBegPeriod.setEnabled(flag);
        hlEndPeriod.setEnabled(flag);
    }

    private boolean checkNullDateInterval(String cbValue) {
        if (cbValue.equals("Все")) {
            return monthPickerBeg.getValue() != null && monthPickerEnd.getValue() != null && yearPickerBeg.getValue() != null
                    && yearPickerEnd.getValue() != null;
        } else return monthPickerBeg.getValue() != null && yearPickerBeg.getValue() != null;
    }
}
