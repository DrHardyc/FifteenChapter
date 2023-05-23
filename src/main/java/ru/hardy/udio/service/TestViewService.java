package ru.hardy.udio.service;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestViewService {
    public void CreateTabSheet(TabSheet tabSheet, String name, List<Component> componentList){
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(componentList);
        tabSheet.add(name, verticalLayout);
    }

    public void CreateTabSheet(TabSheet tabSheet, String name, Component componentList){
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.add(componentList);
        tabSheet.add(name, verticalLayout);
    }
}
