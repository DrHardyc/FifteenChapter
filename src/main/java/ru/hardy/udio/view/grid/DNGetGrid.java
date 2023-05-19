package ru.hardy.udio.view.grid;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DNGet;

import java.util.function.Consumer;



@Service
public class DNGetGrid {
    public void getGrid(Grid<DNGet> grid, GridListDataView<DNGet> dnGetGridListDataView) {
        Grid.Column<DNGet> fioCol = grid.addColumn(DNGet::getFIO).setResizable(true).setSortable(true).setWidth("300px");
        Grid.Column<DNGet> moAttachCol = grid.addColumn(DNGet::getMOAttach).setResizable(true).setSortable(true);
        Grid.Column<DNGet> moCol = grid.addColumn(DNGet::getMo).setResizable(true).setSortable(true);
        Grid.Column<DNGet> sexCol = grid.addColumn(DNGet::getPeopleSex).setResizable(true).setSortable(true);
        Grid.Column<DNGet> diagCol = grid.addColumn(DNGet::getDiag).setResizable(true).setSortable(true);
        Grid.Column<DNGet> profileCol = grid.addColumn(DNGet::getSpecialization).setResizable(true).setSortable(true);
        Grid.Column<DNGet> ageCol = grid.addColumn(DNGet::getAge).setResizable(true).setSortable(true);
        Grid.Column<DNGet> invCol = grid.addColumn(DNGet::getPeopleInv).setResizable(true).setSortable(true);
        Grid.Column<DNGet> date1Col = grid.addColumn(new LocalDateTimeRenderer<>(
                DNGet::getLocalDateTimeDate_1, "dd.MM.yyyy")).setResizable(true).setComparator(DNGet::getLocalDateTimeDate_1);
        Grid.Column<DNGet> dateCallCol = grid.addColumn(new LocalDateTimeRenderer<>(
                DNGet::getLocalDateTimeDate_call, "dd.MM.yyyy")).setResizable(true).setComparator(DNGet::getLocalDateTimeDate_call);

        DNGetFilter dnGetFilter = new DNGetFilter(dnGetGridListDataView);
        grid.getHeaderRows().clear();
        HeaderRow headerRow = grid.appendHeaderRow();

        headerRow.getCell(fioCol).setComponent(
                createFilterHeader("ФИО", dnGetFilter::setFio));
        headerRow.getCell(moAttachCol).setComponent(
                createFilterHeader("МО прикрепления", dnGetFilter::setMo_attach));
        headerRow.getCell(moCol).setComponent(
                createFilterHeader("МО", dnGetFilter::setMo));
        headerRow.getCell(sexCol).setComponent(
                createFilterHeader("Пол", dnGetFilter::setSex));
        headerRow.getCell(diagCol).setComponent(
                createFilterHeader("Диагноз", dnGetFilter::setDiag));
        headerRow.getCell(profileCol).setComponent(
                createFilterHeader("Специальность врача", dnGetFilter::setProfile));
        headerRow.getCell(ageCol).setComponent(
                createFilterHeader("Возраст", dnGetFilter::setAge));
        headerRow.getCell(invCol).setComponent(
                createFilterHeader("Инвалидность", dnGetFilter::setInv));
        headerRow.getCell(date1Col).setComponent(
                createFilterHeader("Дата взятия", dnGetFilter::setDate_1));
        headerRow.getCell(dateCallCol).setComponent(
                createFilterHeader("Дата вызова", dnGetFilter::setDate_call));
    }

    private static class DNGetFilter{
        private final GridListDataView<DNGet> dataView;

        private String fio;
        private String mo_attach;
        private String mo;
        private String sex;
        private String diag;
        private String profile;
        private String age;
        private String inv;
        private String date_1;
        private String date_call;

        public void setFio(String fio) {
            this.fio = fio;
            this.dataView.refreshAll();
        }

        public void setMo_attach(String mo_attach) {
            this.mo_attach = mo_attach;
            this.dataView.refreshAll();
        }

        public void setMo(String mo) {
            this.mo = String.valueOf(mo);
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

        public void setProfile(String profile) {
            this.profile = profile;
            this.dataView.refreshAll();
        }

        public void setAge(String age) {
            this.age = age;
            this.dataView.refreshAll();
        }

        public void setInv(String inv) {
            this.inv = inv;
            this.dataView.refreshAll();
        }

        public void setDate_1(String date_1){
            this.date_1 = date_1;
            this.dataView.refreshAll();
        }

        public void setDate_call(String date_call){
            this.date_call = date_call;
            this.dataView.refreshAll();
        }

        public String getCount(){
            return String.valueOf(dataView.getItemCount());
        }

        public DNGetFilter(GridListDataView<DNGet> dataView) {
            this.dataView = dataView;
            this.dataView.addFilter(this::refresh);
        }
        public boolean refresh(DNGet dnGet) {
            boolean matchesFio = matches(dnGet.getFIO(), fio);
            boolean matchesMo_attach = matches(String.valueOf(dnGet.getMOAttach()), mo_attach);
            boolean matchesMo = matches(String.valueOf(dnGet.getMo()), mo);
            boolean matchesSex = matches(dnGet.getPeopleSex(), sex);
            boolean matchesDiag = matches(dnGet.getDiag(), diag);
            boolean matchesProfile = matches(String.valueOf(dnGet.getSpecialization()), profile);
            boolean matchesAge = matches(String.valueOf(dnGet.getAge()), age);
            boolean matchesInv = matches(dnGet.getPeopleInv(), inv);
            boolean matchesDate1 = matches(dnGet.getDate1String(), date_1);
            boolean matchesDateCall = matches(dnGet.getDateCallString(), date_call);

            return matchesFio && matchesMo_attach && matchesMo && matchesSex && matchesDiag && matchesProfile
                    && matchesAge && matchesInv && matchesDate1 && matchesDateCall;
        }

        private boolean matches(String value, String searchTerm) {

            if (searchTerm != null && searchTerm.equals(age) && parseAge(value, searchTerm)){
                if(age.contains(">") && !age.substring(1).isEmpty()){
                    return Integer.parseInt(value) > Integer.parseInt(searchTerm.substring(1));
                }
                if(age.contains("<") && !age.substring(1).isEmpty()){
                    return Integer.parseInt(value) < Integer.parseInt(searchTerm.substring(1));
                }
            }
            return searchTerm == null || searchTerm.isEmpty() || value.toLowerCase().contains(searchTerm.toLowerCase());
        }

        private boolean parseAge(String value, String searchTerm) {
            try
            {
                Integer.parseInt(value);
                Integer.parseInt(searchTerm.substring(1));
            } catch (Exception e){
                return false;
            }
            return true;
        }
    }
    private static Component createFilterHeader(String labelText, Consumer<String> filterChangeConsumer) {
        Label label = new Label(labelText);
        TextField textField = new TextField();
        label.getStyle().set("padding-top", "var(--lumo-space-m)")
                .set("font-size", "var(--lumo-font-size-xs)");
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.setClearButtonVisible(true);
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        textField.setWidthFull();
        textField.getStyle().set("max-width", "100%");
        textField.addValueChangeListener(
                e -> filterChangeConsumer.accept(e.getValue()));
        VerticalLayout layout = new VerticalLayout(label, textField);
        layout.getThemeList().clear();
        layout.getThemeList().add("spacing-xs");
        return layout;
    }
}
