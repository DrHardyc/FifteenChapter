package ru.hardy.udio.domain.api.schedulepianddispplot.DTO;

import lombok.Data;
import ru.hardy.udio.domain.api.schedulepianddispplot.MonthEvent;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;

import java.util.List;

@Data
public class SchedulePIAndDispPlotDTO {
    private String name;
    private int month1;
    private int month2;
    private int month3;
    private int month4;
    private int month5;
    private int month6;
    private int month7;
    private int month8;
    private int month9;
    private int month10;
    private int month11;
    private int month12;

    private SchedulePIAndDispPlotDTO parent;

    public SchedulePIAndDispPlotDTO(List<MonthEventDTO> months, String typeName,
                                    SchedulePIAndDispPlotDTO parent){

        this.setName(typeName);
        this.setParent(parent);
        months.forEach(month -> {
            switch (month.getMonth()){
                case 1 -> this.month1 = month.getQuantityPlan();
                case 2 -> this.month2 = month.getQuantityPlan();
                case 3 -> this.month3 = month.getQuantityPlan();
                case 4 -> this.month4 = month.getQuantityPlan();
                case 5 -> this.month5 = month.getQuantityPlan();
                case 6 -> this.month6 = month.getQuantityPlan();
                case 7 -> this.month7 = month.getQuantityPlan();
                case 8 -> this.month8 = month.getQuantityPlan();
                case 9 -> this.month9 = month.getQuantityPlan();
                case 10 -> this.month10 = month.getQuantityPlan();
                case 11 -> this.month11 = month.getQuantityPlan();
                case 12 -> this.month12 = month.getQuantityPlan();
            }
        });
    }

    public SchedulePIAndDispPlotDTO(String nameDep,
                                    SchedulePIAndDispPlotRequestRecord schedulePIAndDispPlotRequestRecord){
        this.setName(nameDep);
        this.month1 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 1)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month2 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 2)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month3 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 3)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month4 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 4)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month5 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 5)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month6 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 6)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month7 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 7)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month8 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 8)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month9 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 9)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month10 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 10)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month11 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 11)
                .mapToInt(MonthEvent::getQuantityPlan).sum();
        this.month12 = schedulePIAndDispPlotRequestRecord
                .getMonths()
                .stream()
                .filter(monthEvent -> monthEvent.getMonth() == 12)
                .mapToInt(MonthEvent::getQuantityPlan).sum();

    }
}
