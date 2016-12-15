/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easyportal.gwt.client.desktopModel.MenuDesktopGWT;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;

/**
 *
 * @author geoleite
 */
public class MenuTesteDesktop extends MenuDesktopGWT {

    public MenuEventoDesktopGWT getMenuEvento(String sistema, String acao, MenuItem item) {
        DebugMessage.message(this.getClass().getName(), "Novo Menu");
        MenuEventoDesktopGWT menuEvento = null;
        menuEvento = new MenuEventoDesktopGWT(desktop, item);

        if ("EASYPORTAL".equals(sistema)) {
            item.setData("window", acao);
        } else {
            MessageBox.alert("Falha na execução", "Opção ainda não implementada", null);
        }

        return menuEvento;
    }
}
