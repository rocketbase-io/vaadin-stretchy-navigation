package io.rocketbase.vaadin;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
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

        H3 header = new H3("vaadin-stretchy-navigation");

        HorizontalLayout styleChoseLayout = new HorizontalLayout();
        styleChoseLayout.add(comboBox);

        HorizontalLayout layout = new HorizontalLayout();
        StretchyNavigation stretchyNavigation = new StretchyNavigation(Style.NAVIGATION);
        stretchyNavigation.addMenuItem("Home", "Home");
        stretchyNavigation.addMenuItem("About", "About");
        stretchyNavigation.addMenuItem("User", "User");
        stretchyNavigation.addNavigationListener(stretchyEvent -> {
            MenuItem menuItem = stretchyEvent.getItemClicked();
            System.out.println("Clicked: " + menuItem.getTitle());
        });
        layout.add(stretchyNavigation);


        comboBox.setItems(Style.NAVIGATION.toString(), Style.ADD_CONTENT.toString(), Style.EDIT_CONTENT.toString());
        comboBox.addValueChangeListener(data -> {
            if (data.getValue() != null && !data.getValue().isEmpty()) {
                Notification.show("Changed to: " + data.getValue());
                stretchyNavigation.changeStyle(Style.valueOf(data.getValue()));
            }
        });

        main.add(header, styleChoseLayout, layout);
        add(main);
    }

}
