package ru.hardy.udio.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.Route;
import lombok.Data;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Route(layout = MainView.class)
@RolesAllowed({"ROLE_TFOMS", "ROLE_ADMIN"})
public class TestView extends VerticalLayout {

    public TestView(){
        List<DataTest> dataTestList = new ArrayList<>();
        dataTestList.add(new DataTest("testrow1", 1));
        dataTestList.add(new DataTest("testrow2", 2));

        Grid<DataTest> myGrid = new Grid<>();
        myGrid.addColumn(DataTest::getName).setHeader("Name");
        myGrid.addColumn(DataTest::getNum).setHeader("Num");
        myGrid.addColumn(DataTest::hashCode).setHeader("Hash");
        myGrid.setItems(dataTestList);

        VerticalLayout vlDashboard = new VerticalLayout();
        vlDashboard.setSizeFull();
        myGrid.setWidth("100%");
        VerticalLayout vlPayment = new VerticalLayout();
        vlDashboard.add(myGrid);
        H1 h1 = new H1("TestingTab1");
        Button button = new Button("New Tab");
        vlPayment.add(h1, button);

        TabSheet tabSheet = new TabSheet();
        button.addClickListener(e -> {
           VerticalLayout verticalLayout = new VerticalLayout();
           verticalLayout.add(myGrid);
           tabSheet.add("test", verticalLayout);
           add(tabSheet);
        });

        tabSheet.add("Dashboard", vlDashboard).setTooltipText("Даша за бортом!");
        tabSheet.add("Payment", vlPayment).setTooltipText("Эй, мент");
        add(tabSheet);
    }

    @Data
    static class DataTest {
         private String name;
         private int num;

         public DataTest(String name, int num){
             this.name = name;
             this.num = num;
         }
    }
}
