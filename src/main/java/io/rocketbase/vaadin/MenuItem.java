package io.rocketbase.vaadin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

    String icon;
    String title;
    String path;

    public String toJsonObject() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"icon\"" + icon);
        sb.append("\"title\"" + title);
        sb.append("\"path\"" + path);
        sb.append("}");
        return sb.toString();

    }
}
