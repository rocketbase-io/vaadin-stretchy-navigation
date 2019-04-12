package io.rocketbase.vaadin.spring;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.rocketbase.vaadin.StretchyNavigation;
import io.rocketbase.vaadin.model.MenuItem;
import io.rocketbase.vaadin.model.Style;

import java.io.IOException;

@Route
public class MainView extends VerticalLayout {

    VerticalLayout main = new VerticalLayout();
    ComboBox<String> comboBox = new ComboBox<>("Navigation Style");
    StretchyNavigation stretchyNavigation;

    public MainView() throws IOException {
        Div styleDiv = new Div();
        styleDiv.getStyle().set("background-color", "white");
        styleDiv.getStyle().set("padding", "1% 2%");
        H3 header = new H3("vaadin-stretchy-navigation");
        styleDiv.add(header, new Hr(), comboBox);

        HorizontalLayout layout = new HorizontalLayout();
        stretchyNavigation = new StretchyNavigation(Style.NAVIGATION);
        stretchyNavigation.addMenuItem(VaadinIcon.HOME.create(), "Home", true);
        stretchyNavigation.addMenuItem(VaadinIcon.USER.create(), "User", false);
        stretchyNavigation.addMenuItem(VaadinIcon.SERVER.create(), "Server", false);
        stretchyNavigation.addMenuItem(VaadinIcon.STOCK.create(), "Stock", false);
        stretchyNavigation.addMenuItem(VaadinIcon.STORAGE.create(), "Store", false);
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

                this.stretchyNavigation.deleteAllMenuItems();
                if (data.getValue().equals(Style.NAVIGATION.toString())) {
                    stretchyNavigation.changeStyle(Style.NAVIGATION);
                    stretchyNavigation.addMenuItem(VaadinIcon.HOME.create(), "Home", true);
                    stretchyNavigation.addMenuItem(VaadinIcon.USER.create(), "User", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.SERVER.create(), "Server", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.STOCK.create(), "Stock", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.STORAGE.create(), "Store", false);
                }

                if (data.getValue().equals(Style.ADD_CONTENT.toString())) {
                    stretchyNavigation.changeStyle(Style.ADD_CONTENT);
                    stretchyNavigation.addMenuItem(VaadinIcon.FILE.create(), "File Upload", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.PICTURE.create(), "Image", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.LINK.create(), "Link", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.MOVIE.create(), "Video", false);
                }

                if (data.getValue().equals(Style.EDIT_CONTENT.toString())) {
                    stretchyNavigation.changeStyle(Style.EDIT_CONTENT);
                    stretchyNavigation.addMenuItem(VaadinIcon.EDIT.create(), "Edit", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.DOWNLOAD.create(), "Download", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.TRASH.create(), "Delete", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.HEART.create(), "Like", false);
                    stretchyNavigation.addMenuItem(VaadinIcon.SHARE.create(), "Share", false);
                }

            }
        });

        main.add(styleDiv, layout);

        setSizeFull();
        getStyle().set("background-color", "#003c5d");
        add(main);

    }

}
