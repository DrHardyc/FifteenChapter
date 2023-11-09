package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.grid.Grid;
import ru.hardy.udio.domain.api.numberavailableseats.dto.NumberAvailableSeatsDTO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NumberAvailableSeatsGrid {
    public void getGrid(Grid<NumberAvailableSeatsDTO> grid, List<NumberAvailableSeatsDTO> numberAvailableSeatsDTOS) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        grid.addColumn(NumberAvailableSeatsDTO::getName).setHeader("Отделение").setResizable(true);
        grid.addColumn(NumberAvailableSeatsDTO::getCurrentDay).setHeader("Свободно на текущий день");
        grid.addColumn(NumberAvailableSeatsDTO::getDay1).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(1, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay2).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(2, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay3).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(3, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay4).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(4, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay5).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(5, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay6).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(6, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay7).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(7, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay8).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(8, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay9).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(9, ChronoUnit.DAYS))));
        grid.addColumn(NumberAvailableSeatsDTO::getDay10).setHeader(simpleDateFormat.format(Date.from(Instant.now().plus(10, ChronoUnit.DAYS))));

        grid.setItems(numberAvailableSeatsDTOS);
    }
}
