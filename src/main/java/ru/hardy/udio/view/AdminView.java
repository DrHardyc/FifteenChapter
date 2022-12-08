package ru.hardy.udio.view;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import ru.hardy.udio.service.ExcelService;
import ru.hardy.udio.service.UIUtil;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;

@Route(layout = MainView.class)
@RolesAllowed("ROLE_ADMIN")
public class AdminView extends VerticalLayout {

    private final HorizontalLayout horizontalLayout = new HorizontalLayout();;
    private final Tab tabExcelTest = new Tab("Тест экселя");
    private final Tab payment = new Tab("Payment");
    private final Tab shipping = new Tab("Shipping");

    private final Button button;
    private final UIUtil uiUtil = new UIUtil();

    public AdminView() {

        ExcelService excelService = new ExcelService();
        button = uiUtil.InitButtonOK(new Button("Test"));
        button.addClickListener(e -> {
            try {
                horizontalLayout.add(uiUtil.initAnchorDownload(excelService.testRead()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Tabs tabs = new Tabs(tabExcelTest, payment, shipping);
        tabs.addSelectedChangeListener(
                event -> setContent(event.getSelectedTab()));
        setContent(tabs.getSelectedTab());
        add(tabs, horizontalLayout);
    }

    private void setContent(Tab tab) {
        horizontalLayout.removeAll();

        if (tab.equals(tabExcelTest)) {
            horizontalLayout.add(button);
        } else if (tab.equals(payment)) {
            horizontalLayout.add(new Paragraph("This is the Payment tab"));
        } else {
            horizontalLayout.add(new Paragraph("This is the Shipping tab"));
        }
    }
}
