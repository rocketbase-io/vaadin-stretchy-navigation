package io.rocketbase.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.rocketbase.vaadin.model.Style;

@Route("")
public class DemoView extends Div {

    VerticalLayout main = new VerticalLayout();

    public DemoView() {

        HorizontalLayout navLayout = new HorizontalLayout();
        StretchyNavigation sn = new StretchyNavigation(Style.NAVIGATION);
        sn.addMenuItem("Home", "Home");
        sn.addMenuItem("About", "About");
        sn.addMenuItem("User", "User");
        navLayout.add(sn);

        HorizontalLayout editLayout = new HorizontalLayout();
        StretchyNavigation snEdit = new StretchyNavigation(Style.EDIT_CONTENT);
        snEdit.addMenuItem("edit", "edit");
        snEdit.addMenuItem("delete", "delete");
        snEdit.addMenuItem("show", "show");
        editLayout.add(snEdit);

        HorizontalLayout addLayout = new HorizontalLayout();
        StretchyNavigation snAdd = new StretchyNavigation(Style.ADD_CONTENT);
        snAdd.addMenuItem("edit", "edit");
        snAdd.addMenuItem("delete", "delete");
        snAdd.addMenuItem("show", "show");
        snAdd.addMenuItem("add", "add");
        addLayout.add(snAdd);

        main.add(navLayout, editLayout, addLayout);
        add(main);
    }

}
