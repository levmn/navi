package br.com.fiap.navi.views.components;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;

public class NaviTextArea extends TextArea {

    public NaviTextArea(String label, Icon icon) {
        super();
        this.setLabel(label);
        this.setWidthFull();
        this.setMinHeight("300px");
        this.setClearButtonVisible(true);
        this.setMaxLength(1000);
        this.setValueChangeMode(ValueChangeMode.EAGER);
        this.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText(e.getValue().length() + "/" + 1000);
        });
        this.setPrefixComponent(icon);
    }

}
