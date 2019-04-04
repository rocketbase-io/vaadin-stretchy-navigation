package io.rocketbase.vaadin;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import io.rocketbase.vaadin.model.MenuItem;
import io.rocketbase.vaadin.model.Style;

import java.util.ArrayList;
import java.util.List;

@Tag("vaadin-stretchy-navigation")
@HtmlImport("frontend://html/stretchy-navigation.html")
@StyleSheet("frontend://stretchy-navigation.css")
@JavaScript("frontend://stretchy-navigation.js")
@JavaScript("frontend://modernizr.js")
public class StretchyNavigation extends PolymerTemplate<StretchyNavigationModel> {

    private Style style;

    List<MenuItem> menuItemList;

    public StretchyNavigation(Style style) {
        super();
        this.style = style;
        this.menuItemList = new ArrayList<>();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        initNavigation();
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
    }

    protected void initNavigation() {
        getModel().setStyle(this.style.toString());
    }

    public void addMenuItem(String icon, String title) {

        MenuItem mi = MenuItem.builder()
                .icon(icon)
                .title(title)
                .build();

        menuItemList.add(mi);
        getModel().setMenuItems(menuItemList);
    }

    @EventHandler
    private void iconClicked() {
        System.out.println("hello .....");
    }

}
