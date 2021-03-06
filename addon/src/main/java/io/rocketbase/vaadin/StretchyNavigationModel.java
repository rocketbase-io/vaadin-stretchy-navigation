package io.rocketbase.vaadin;

import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import io.rocketbase.vaadin.model.MenuItem;

import java.util.List;

public interface StretchyNavigationModel extends TemplateModel {

    void setStyle(String style);

    @Include({"icon", "title"})
    void setMenuItems(List<MenuItem> menuItems);

    @Include({"icon", "title"})
    void setActive(MenuItem menuItems);
}
