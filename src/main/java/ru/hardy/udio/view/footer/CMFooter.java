package ru.hardy.udio.view.footer;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import jakarta.annotation.security.PermitAll;
import ru.hardy.udio.view.MainView;

@Route(layout = MainView.class)
@PermitAll
public class CMFooter extends VerticalLayout implements RouterLayout {

    private Div container = new Div();

    private Div footer = new Div();

    public CMFooter() {
        add(container, footer);
        footer.add(new Text("I'm a fixed footer!"));
    }

    public void showRouterLayoutContent(HasElement content) {
        container.removeAll();
        container.getElement().setChild(0, content.getElement());
    }
}