package io.rocketbase.vaadin.spring;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.rocketbase.vaadin.StretchyNavigation;
import io.rocketbase.vaadin.model.MenuItem;
import io.rocketbase.vaadin.model.Style;

import java.io.IOException;

@Route
public class MainView extends VerticalLayout {

    VerticalLayout main = new VerticalLayout();
    Div styleDiv = new Div();

    ComboBox<String> comboBox = new ComboBox<>("Navigation Style");

    StretchyNavigation stretchyNavigation;

    public MainView() throws IOException {

        this.addDemoStyleChooser();

        main.add(createNavigationView());

        comboBox.setItems(Style.NAVIGATION.toString(), Style.ADD_CONTENT.toString(), Style.EDIT_CONTENT.toString());
        comboBox.setValue(Style.NAVIGATION.toString());

        comboBox.addValueChangeListener(data -> {
            if (data.getValue() != null && !data.getValue().isEmpty()) {
                this.main.removeAll();
                this.main.add(styleDiv);
                this.stretchyNavigation.deleteAllMenuItems();
                if (data.getValue().equals(Style.NAVIGATION.toString())) {
                    stretchyNavigation.changeStyle(Style.NAVIGATION);
                    main.add(this.createNavigationView());
                }

                if (data.getValue().equals(Style.ADD_CONTENT.toString())) {
                    stretchyNavigation.changeStyle(Style.ADD_CONTENT);
                    main.add(this.createAddContentView());
                }

                if (data.getValue().equals(Style.EDIT_CONTENT.toString())) {
                    stretchyNavigation.changeStyle(Style.EDIT_CONTENT);
                    main.add(this.createEditContentView());
                }

            }
        });

        setSizeFull();
        getStyle().set("background-color", "#003c5d");
        add(main);

    }

    private VerticalLayout createNavigationView() {

        VerticalLayout layer = new VerticalLayout();

        if (this.stretchyNavigation != null) {
            stretchyNavigation.changeStyle(Style.NAVIGATION);
        } else {
            stretchyNavigation = new StretchyNavigation(Style.NAVIGATION);
            addNavigationListener();
        }

        stretchyNavigation.addMenuItem(VaadinIcon.HOME.create(), "Home", true);
        stretchyNavigation.addMenuItem(VaadinIcon.USER.create(), "User", false);
        stretchyNavigation.addMenuItem(VaadinIcon.SERVER.create(), "Server", false);
        stretchyNavigation.addMenuItem(VaadinIcon.STOCK.create(), "Stock", false);
        stretchyNavigation.addMenuItem(VaadinIcon.STORAGE.create(), "Store", false);

        layer.add(stretchyNavigation);

        return layer;
    }

    private VerticalLayout createEditContentView() {

        VerticalLayout layer = new VerticalLayout();
        layer.setWidth("fit-content");
        layer.setPadding(false);
        layer.setSpacing(false);
        layer.getStyle().set("position", "relative");
        layer.getStyle().set("margin", "auto");

        if (this.stretchyNavigation != null) {
            stretchyNavigation.changeStyle(Style.EDIT_CONTENT);
        } else {
            stretchyNavigation = new StretchyNavigation(Style.EDIT_CONTENT);
            addNavigationListener();
        }

        stretchyNavigation.addMenuItem(VaadinIcon.EDIT.create(), "Edit", false);
        stretchyNavigation.addMenuItem(VaadinIcon.DOWNLOAD.create(), "Download", false);
        stretchyNavigation.addMenuItem(VaadinIcon.TRASH.create(), "Delete", false);
        stretchyNavigation.addMenuItem(VaadinIcon.HEART.create(), "Like", false);
        stretchyNavigation.addMenuItem(VaadinIcon.SHARE.create(), "Share", false);

        Image image = new Image("frontend/img/img.png", "Text");
        image.getStyle().set("max-width", "430px");
        layer.add(stretchyNavigation);
        layer.add(image);

        return layer;
    }

    private VerticalLayout createAddContentView() {

        VerticalLayout layer = new VerticalLayout();
        if (this.stretchyNavigation != null) {
            stretchyNavigation.changeStyle(Style.ADD_CONTENT);
        } else {
            stretchyNavigation = new StretchyNavigation(Style.ADD_CONTENT);
            addNavigationListener();
        }

        stretchyNavigation.addMenuItem(VaadinIcon.FILE.create(), "File Upload", false);
        stretchyNavigation.addMenuItem(VaadinIcon.PICTURE.create(), "Image", false);
        stretchyNavigation.addMenuItem(VaadinIcon.LINK.create(), "Link", false);
        stretchyNavigation.addMenuItem(VaadinIcon.MOVIE.create(), "Video", false);

        layer.add(stretchyNavigation);

        return layer;
    }

    private void addNavigationListener() {
        stretchyNavigation.addNavigationListener(stretchyEvent -> {
            MenuItem menuItem = stretchyEvent.getItemClicked();
            Notification.show("Clicked: " + menuItem.getTitle());
        });
    }

    private void addDemoStyleChooser() {
        styleDiv.getStyle().set("background-color", "white");
        styleDiv.getStyle().set("padding", "1% 2%");
        H3 header = new H3("vaadin-stretchy-navigation");
        styleDiv.add(header, new Hr(), comboBox);
        main.add(styleDiv);
    }

}
