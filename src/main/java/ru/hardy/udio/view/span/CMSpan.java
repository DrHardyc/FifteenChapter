package ru.hardy.udio.view.span;


import com.vaadin.flow.component.html.Span;

public class CMSpan extends Span {

    public CMSpan(String text){
        this.setText(text);
        this.setHeight("20px");
        this.setWidth("20px");

        this.getStyle().set("border-radius", "50%");
        this.getStyle().set("color", "#4A59E7");
        this.getStyle().set("text-align", "center");
        this.getStyle().set("border", "1px inset #4A59E7");
        this.getStyle().set("font-size", "12px");
        this.getStyle().set("margin-left", "-15px");
        this.getStyle().set("margin-bottom", "20px");
    }
}
