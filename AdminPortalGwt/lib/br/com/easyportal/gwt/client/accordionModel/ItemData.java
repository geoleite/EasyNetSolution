/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.accordionModel;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 *
 * @author geoleite
 */
public class ItemData extends BaseModel{

    public AMenuHandlerAccordion getMenuHandler() {
        return (AMenuHandlerAccordion)get("menu_handler");
    }
    public void setMenuHandler(AMenuHandlerAccordion menuHandler) {
        set("menu_handler", menuHandler);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getSystem() {
        return get("system");
    }

    public void setSystem(String system) {
        set("system", system);
    }
    
    public String getAction() {
        return get("action");
    }

    public void setAction(String action) {
        set("action", action);
    }

    public String getIcon() {
        return get("icon");
    }

    public void setIcon(String icon) {
        set("icon", icon);
    }

    public String getPage() {
        return get("page");
    }

    public void setPage(String page) {
        set("page", page);
    }
}
