/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.webModel;

import br.com.easyportal.gwt.client.IMenuHandler;
import com.extjs.gxt.ui.client.event.MenuEvent;

/**
 *
 * @author geoleite
 */
public abstract class AMenuHandler implements IMenuHandler {

    public static final String MENU_HANDLER="menu_handler";
    public static final String MENU_ACTION="menu_action";
    private String system;
    private PortalWebGWT portalWebGWT;
    public abstract void actionEventMenu(Object me, String acao);

    /**
     * @return the system
     */
    public String getSystem() {
        return system;
    }

    /**
     * @param system the system to set
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * @return the portalWebGWT
     */
    public PortalWebGWT getPortalWebGWT() {
        return portalWebGWT;
    }

    /**
     * @param portalWebGWT the portalWebGWT to set
     */
    public void setPortalWebGWT(PortalWebGWT portalWebGWT) {
        this.portalWebGWT = portalWebGWT;
    }
}
