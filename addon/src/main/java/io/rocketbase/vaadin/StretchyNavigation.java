package io.rocketbase.vaadin;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.ModelItem;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.shared.Registration;
import io.rocketbase.vaadin.model.MenuItem;
import io.rocketbase.vaadin.model.StretchyEvent;
import io.rocketbase.vaadin.model.Style;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Tag("vaadin-stretchy-navigation")
@HtmlImport("frontend://html/stretchy-navigation.html")
@StyleSheet("frontend://css/icons-menu.css")
public class StretchyNavigation extends PolymerTemplate<StretchyNavigationModel> {

    private Style style;

    private List<MenuItem> menuItemList;

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

    public void addMenuItem(Icon icon, String title, boolean active) {

        MenuItem mi = MenuItem.builder()
                .icon(icon.getElement().toString())
                .title(title)
                .build();
        menuItemList.add(mi);
        getModel().setMenuItems(menuItemList);
        addElement(icon.getElement());

        if (active)
            this.updateActiveElement(mi);
    }

    private void addElement(Element el) {
        this.addIcon(el);
    }

    @EventHandler
    private void itemClicked(@ModelItem MenuItem item) {
        updateActiveElement(item);
        StretchyEvent event = new StretchyEvent(this, false, item);
        fireEvent(event);
    }

    public Registration addNavigationListener(
            ComponentEventListener<StretchyEvent> listener) {
        return addListener(StretchyEvent.class, listener);
    }

    public void changeStyle(Style style) {
        this.style = style;
        getModel().setStyle(this.style.toString());
    }

    private void updateActiveElement(MenuItem item) {
        getModel().setActive(item);
    }

    private void addIcon(Element el) {
        el.setAttribute("slot", el.toString());
        el.getClassList().add("menu-item-icon");
        getElement().appendChild(el);
    }

    public void deleteAllMenuItems() {
        getElement().removeAllChildren();
        this.menuItemList.clear();
    }
}
