/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

/**
 *
 *  @author geoleite
 */
public class MenuEventoDesktopGWT extends SelectionListener<ComponentEvent> {

    private Desktop desktop;
    private MenuItem item;

    public MenuEventoDesktopGWT(Desktop desktop, MenuItem item) {
        setDesktop(desktop);
        setItem(item);
    }

    @Override
    public void componentSelected(ComponentEvent ce) {
        com.extjs.gxt.ui.client.widget.Window w = null;
        if (ce instanceof MenuEvent) {
            MenuEvent me = (MenuEvent) ce;
            w = me.getItem().getData("window");
        } else {
            w = ce.getComponent().getData("window");
        }
        if (!desktop.getWindows().contains(w)) {
            getDesktop().addWindow(w);
        }
        if (w != null && !w.isVisible()) {
            w.show();
        } else {
            w.toFront();
        }
    }

    /**
     * @return the desktop
     */
    public Desktop getDesktop() {
        return desktop;
    }

    /**
     * @param desktop the desktop to set
     */
    public void setDesktop(Desktop desktop) {
        this.desktop = desktop;
    }

    /**
     * @return the item
     */
    public MenuItem getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(MenuItem item) {
        this.item = item;
    }
}
