package ru.hardy.udio.view.grid;


import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import ru.hardy.udio.domain.regul.grid.RegULUI;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RegUlGrid {

    public static void getGrid(Grid<RegULUI> grid, GridListDataView<RegULUI> regULUIGridListDataView) {
        Grid.Column<RegULUI> innCol = grid
                .addColumn(RegULUI::getInn)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<RegULUI> ogrnCol = grid
                .addColumn(RegULUI::getOgrn)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<RegULUI> regNFomsCol = grid
                .addColumn(RegULUI::getRegNFoms)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<RegULUI> nameCol = grid
                .addColumn(RegULUI::getName)
                .setResizable(true)
                .setSortable(true);
        Grid.Column<RegULUI> dateCol = grid
                .addColumn(new LocalDateRenderer<>(
                        RegULUI::getLocalDateDate, "dd.MM.yyyy"))
                .setResizable(true)
                .setComparator(RegULUI::getLocalDateDate);

        RegULUIGridFilter regULUIGridFilter =
                new RegULUIGridFilter(regULUIGridListDataView);
        grid.getHeaderRows().clear();
        grid.setMultiSort(true, Grid.MultiSortPriority.APPEND);
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(innCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "ИНН", regULUIGridFilter::setInn));
        headerRow.getCell(ogrnCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "ОГРН", regULUIGridFilter::setOgrn));
        headerRow.getCell(regNFomsCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Номер ТФОМС", regULUIGridFilter::setRegNFoms));
        headerRow.getCell(nameCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Наименование/ФИО", regULUIGridFilter::setName));
        headerRow.getCell(dateCol)
                .setComponent(GridUtils
                        .createFilterHeader(
                                "Дата загрузки", regULUIGridFilter::setDate));
    }

    private static class RegULUIGridFilter {
        private final GridListDataView<RegULUI> dataView;

        private String inn;
        private String ogrn;
        private String regNFoms;
        private String name;
        private String date;

        public void setInn(String inn) {
            this.inn = inn;
            this.dataView.refreshAll();
        }

        public void setOgrn(String ogrn) {
            this.ogrn = ogrn;
            this.dataView.refreshAll();
        }

        public void setRegNFoms(String regNFoms) {
            this.regNFoms = regNFoms;
            this.dataView.refreshAll();
        }

        public void setName(String name) {
            this.name = name;
            this.dataView.refreshAll();
        }

        public void setDate(String date) {
            this.date = date;
            this.dataView.refreshAll();
        }

        public RegULUIGridFilter(GridListDataView<RegULUI> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }
        public boolean refresh(RegULUI regULUI) {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

            boolean matchesInn = matches(regULUI.getInn(), inn);
            boolean matchesOgrn = matches(regULUI.getOgrn(), ogrn);
            boolean matchesRegNFoms = matches(regULUI.getRegNFoms(), regNFoms);
            boolean matchesName = matches(regULUI.getName(), name);
            boolean matchesDate = matches(dateFormat.format(regULUI.getDate()), date);

            return matchesInn && matchesOgrn && matchesRegNFoms && matchesName && matchesDate;
        }

        private boolean matches(String value, String searchTerm) {
            if (value != null)
                return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
            return false;
        }
    }
}
