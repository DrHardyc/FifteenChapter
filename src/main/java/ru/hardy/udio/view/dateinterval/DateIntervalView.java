package ru.hardy.udio.view.dateinterval;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DateIntervalView {

    public ComboBox<Month> getMonthInterval(String name){
        ComboBox<Month> monthPicker = new ComboBox<>(name, Month.values());
        monthPicker.setItemLabelGenerator(
                m -> m.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()));
        monthPicker.setWidth(12, Unit.EM);
        return monthPicker;
    }

    public ComboBox<Integer> getYearInterval(String name){
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        List<Integer> selectableYears = IntStream
                .range(now.getYear() - 2, now.getYear() + 1).boxed()
                .collect(Collectors.toList());

        ComboBox<Integer> yearPicker = new ComboBox<>(name, selectableYears);
        yearPicker.setWidth(10, Unit.EM);
        return yearPicker;
    }
}
