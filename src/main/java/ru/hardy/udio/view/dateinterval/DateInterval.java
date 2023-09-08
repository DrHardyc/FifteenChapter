package ru.hardy.udio.view.dateinterval;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.combobox.ComboBox;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.combobox.UdioCombobox;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class DateInterval {

    public ComboBox<Month> getMonthInterval(){
        ComboBox<Month> monthPicker = new ComboBox<>("", Month.values());
        monthPicker.setItemLabelGenerator(
                m -> m.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()));
        monthPicker.setWidth(12, Unit.EM);
        monthPicker.setPlaceholder("месяц");
        return monthPicker;
    }

    public ComboBox<Integer> getYearInterval(){
        LocalDate now = LocalDate.now(ZoneId.systemDefault());
        List<Integer> selectableYears = IntStream
                .range(now.getYear() - 130, now.getYear() + 1).boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        ComboBox<Integer> yearPicker = new ComboBox<>("", selectableYears);
        yearPicker.setWidth(10, Unit.EM);
        yearPicker.setPlaceholder("год");
        return yearPicker;
    }
}
