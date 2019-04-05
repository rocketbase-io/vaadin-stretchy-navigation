package io.rocketbase.vaadin;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.rocketbase.vaadin.model.MenuItem;
import io.rocketbase.vaadin.model.Style;

@Route("")
public class DemoView extends Div {

    VerticalLayout main = new VerticalLayout();
    ComboBox<String> comboBox = new ComboBox<>("Navigation Style");

    public DemoView() {

        Div styleDiv = new Div();
        styleDiv.getStyle().set("background-color", "white");
        styleDiv.getStyle().set("padding", "1% 2%");
        H3 header = new H3("vaadin-stretchy-navigation");
        styleDiv.add(header, new Hr(), comboBox);

        HorizontalLayout layout = new HorizontalLayout();
        StretchyNavigation stretchyNavigation = new StretchyNavigation(Style.NAVIGATION);
        stretchyNavigation.addMenuItem(FontAwesomeIcons.HOME.getIconName(), "Home");
        stretchyNavigation.addMenuItem(FontAwesomeIcons.ID_CARD.getIconName(), "About");
        stretchyNavigation.addMenuItem(FontAwesomeIcons.USER.getIconName(), "User");
        stretchyNavigation.addNavigationListener(stretchyEvent -> {
            MenuItem menuItem = stretchyEvent.getItemClicked();
            Notification.show("Clicked: " + menuItem.getTitle());
        });
        layout.add(stretchyNavigation);

        comboBox.setItems(Style.NAVIGATION.toString(), Style.ADD_CONTENT.toString(), Style.EDIT_CONTENT.toString());
        comboBox.setValue(Style.NAVIGATION.toString());
        comboBox.addValueChangeListener(data -> {
            if (data.getValue() != null && !data.getValue().isEmpty()) {
                Notification.show("Changed to: " + data.getValue());
                stretchyNavigation.changeStyle(Style.valueOf(data.getValue()));
            }
        });

        main.add(styleDiv, layout);

        setSizeFull();
        getStyle().set("background-color", "#003c5d");
        add(main);

    }

}
