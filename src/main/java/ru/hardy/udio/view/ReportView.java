package ru.hardy.udio.view;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.service.DNGetService;
import ru.hardy.udio.view.dateinterval.DateIntervalView;
import ru.hardy.udio.view.dialog.DialogNum;
import ru.hardy.udio.view.dialog.DialogView;
import ru.hardy.udio.view.grid.GridView;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Route(layout = MainView.class)
@RolesAllowed("ROLE_TFOMS")
public class ReportView extends VerticalLayout {

    @Autowired
    private DNGetService dnGetService;

    private final DateIntervalView dateIntervalView = new DateIntervalView();
    private final ComboBox<Month> monthPickerBeg = dateIntervalView.getMonthInterval("Месяц начала периода");
    private final ComboBox<Month> monthPickerEnd = dateIntervalView.getMonthInterval("Месяц окончания периода");
    private final ComboBox<Integer> yearPickerBeg = dateIntervalView.getYearInterval("Год начала периода");
    private final ComboBox<Integer> yearPickerEnd = dateIntervalView.getYearInterval("Год окончания периода");
    private final Button btnSearch = new Button("Найти");


    public ReportView() {

        add(yearPickerBeg,yearPickerEnd, monthPickerBeg, monthPickerEnd, btnSearch);
    }

    @Override
    public void onAttach(AttachEvent attachEvent) {
        btnSearch.addClickListener(e -> {
            if (checkNullDateInterval()){
                List<DNGet> dnGets = changeDataFromDateInterval();
                Dialog dialog = new DialogView().getMainReportDialog(dnGets);
                add(dialog);
                dialog.open();
            } else {
                Notification.show("Введите значения периодов!");
            }
        });
    }

    private List<DNGet> changeDataFromDateInterval(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateBeg;
        Date dateEnd;
        if (monthPickerBeg.getValue() != null && monthPickerEnd.getValue() != null && yearPickerBeg.getValue() != null
                && yearPickerEnd.getValue() != null) {
            try {
                dateBeg = dateFormat.parse("26." + monthPickerBeg.getValue().getValue() + "." + yearPickerBeg.getValue());
                dateEnd = dateFormat.parse("25." + monthPickerEnd.getValue().getValue() + "." + yearPickerEnd.getValue());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return dnGetService.getAllWithDateInterval(dateBeg, dateEnd);
        }
        return null;
    }
    private boolean checkNullDateInterval(){
        return monthPickerBeg.getValue() != null && monthPickerEnd.getValue() != null && yearPickerBeg.getValue() != null
                && yearPickerEnd.getValue() != null;
    }
}
