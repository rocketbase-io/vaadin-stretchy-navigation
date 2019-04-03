package io.rocketbase.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class DemoView extends Div {

    VerticalLayout main = new VerticalLayout();

    public DemoView() {

        HorizontalLayout navLayout = new HorizontalLayout();
        StretchyNavigation sn = new StretchyNavigation(Style.NAVIGATION);
        sn.addMenuItem("Home", "Home", "/home");
        sn.addMenuItem("About", "About", "/about");
        sn.addMenuItem("User", "User", "/user");
        navLayout.add(sn);

        HorizontalLayout editLayout = new HorizontalLayout();
        StretchyNavigation snEdit = new StretchyNavigation(Style.EDIT_CONTENT);
        snEdit.addMenuItem("edit", "edit", "/edit");
        snEdit.addMenuItem("delete", "delete", "/delete");
        snEdit.addMenuItem("show", "show", "/show");
        editLayout.add(snEdit);

        HorizontalLayout addLayout = new HorizontalLayout();
        StretchyNavigation snAdd = new StretchyNavigation(Style.ADD_CONTENT);
        snAdd.addMenuItem("edit", "edit", "/edit");
        snAdd.addMenuItem("delete", "delete", "/delete");
        snAdd.addMenuItem("show", "show", "/show");
        snAdd.addMenuItem("add", "add", "/add");
        addLayout.add(snAdd);

        main.add(navLayout, editLayout, addLayout);
        add(main);
    }
}
