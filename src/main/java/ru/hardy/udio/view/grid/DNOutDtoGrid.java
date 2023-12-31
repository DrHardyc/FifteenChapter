package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import ru.hardy.udio.domain.struct.dto.DNOutDto;
import ru.hardy.udio.service.UtilService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DNOutDtoGrid {

    public void getGrid(Grid<DNOutDto> grid, GridListDataView<DNOutDto> dnOutDtoGridListDataView) {
        Grid.Column<DNOutDto> fioCol = grid.addColumn(DNOutDto::getFIO).setResizable(true).setSortable(true).setWidth("300px");
        Grid.Column<DNOutDto> ageCol = grid.addColumn(DNOutDto::getAge).setResizable(true).setSortable(true);
        Grid.Column<DNOutDto> sexCol = grid.addColumn(DNOutDto::getNumberSex).setResizable(true).setSortable(true);
        Grid.Column<DNOutDto> diagCol = grid.addColumn(DNOutDto::getDiags).setResizable(true).setSortable(true);
        Grid.Column<DNOutDto> dsCol = grid.addColumn(new LocalDateRenderer<>(
                DNOutDto::getLocalDateDs, "dd.MM.yyyy")).setResizable(true).setComparator(DNOutDto::getLocalDateDs);
        Grid.Column<DNOutDto> date1Col = grid.addColumn(DNOutDto::getDate_1).setResizable(true).setComparator(DNOutDto::getDate_1);

        DNOutDtoFilter DNOutDtoFilter = new DNOutDtoFilter(dnOutDtoGridListDataView);
        grid.getHeaderRows().clear();
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(fioCol).setComponent(
                GridUtils.createFilterHeader("ФИО", DNOutDtoFilter::setFio));
        headerRow.getCell(ageCol).setComponent(
                GridUtils.createFilterHeader("Возраст", DNOutDtoFilter::setAge));
        headerRow.getCell(sexCol).setComponent(
                GridUtils.createFilterHeader("Пол", DNOutDtoFilter::setSex));
        headerRow.getCell(diagCol).setComponent(
                GridUtils.createFilterHeader("Диагноз", DNOutDtoFilter::setDiag));
        headerRow.getCell(dsCol).setComponent(
                GridUtils.createFilterHeader("Дата смерти", DNOutDtoFilter::setDs));
        headerRow.getCell(date1Col).setComponent(
                GridUtils.createFilterHeader("Дата взятия", DNOutDtoFilter::setDate_1));
    }

    private static class DNOutDtoFilter {
        private final GridListDataView<DNOutDto> dataView;
        private String fio;
        private String age;
        private String sex;
        private String diag;
        private String ds;
        private String date_1;

        public void setFio(String fio) {
            this.fio = fio;
            this.dataView.refreshAll();
        }

        public void setAge(String age) {
            this.age = age;
            this.dataView.refreshAll();
        }

        public void setSex(String sex) {
            this.sex = sex;
            this.dataView.refreshAll();
        }

        public void setDiag(String diag) {
            this.diag = diag;
            this.dataView.refreshAll();
        }

        public void setDs(String ds) {
            this.ds = ds;
            this.dataView.refreshAll();
        }

        public void setDate_1(String date_1) {
            this.date_1 = date_1;
            this.dataView.refreshAll();
        }

        public String getCount(){
            return String.valueOf(dataView.getItemCount());
        }

        public DNOutDtoFilter(GridListDataView<DNOutDto> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }
        public boolean refresh(DNOutDto dnOutDto) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            boolean matchesFio = matches(dnOutDto.getFIO(), fio);
            boolean matchesEnp = matches(String.valueOf(dnOutDto.getAge()), age);
            boolean matchesSex = matches(dnOutDto.getStringSexId(), sex);
            boolean matchesDiag = matches(dnOutDto.getDiags(), diag);
            boolean matchesDs = matches(dateFormat.format(dnOutDto.getDs()), ds);
            boolean matchesDate1 = matches(dnOutDto.getDate_1(), date_1);

            return matchesFio && matchesEnp && matchesDs && matchesDate1 && matchesSex && matchesDiag;
        }

        private boolean matches(String value, String searchTerm) {
            UtilService utilService = new UtilService();
            if (searchTerm != null && searchTerm.equals(age) && utilService.parseAge(value, searchTerm)){
                if(age.contains(">") && !age.substring(1).isEmpty()){
                    return Integer.parseInt(value) > Integer.parseInt(searchTerm.substring(1));
                }
                if(age.contains("<") && !age.substring(1).isEmpty()){
                    return Integer.parseInt(value) < Integer.parseInt(searchTerm.substring(1));
                }
            }
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }
    }
}
