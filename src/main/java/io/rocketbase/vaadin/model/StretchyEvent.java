package io.rocketbase.vaadin.model;

import com.vaadin.flow.component.ComponentEvent;
import io.rocketbase.vaadin.StretchyNavigation;
import lombok.Data;

@Data
public class StretchyEvent extends ComponentEvent<StretchyNavigation> {

    private MenuItem itemClicked;

    public StretchyEvent(StretchyNavigation source, boolean fromClient,
                         MenuItem menuItemClicked) {

        super(source, fromClient);
        this.itemClicked = menuItemClicked;
    }

}
