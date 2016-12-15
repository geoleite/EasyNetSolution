/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.accordionModel;

import br.com.easyportal.gwt.client.IMenuHandler;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author geoleite
 */
public abstract class AMenuHandlerAccordion implements IMenuHandler {

    public static final String MENU_HANDLER="menu_handler";
    public static final String MENU_ACTION="menu_action";
    private String system;
    private PortalAccordionGWT portalAccordionGWT;
    public abstract void actionEventMenu(Object me, String acao);
    public void actionEventMenu(Object me, String acao, String url) {
        
    }
    private Map<String, TabItem> mapItens = new HashMap<String, TabItem>();


    public String toString() {
        return "instancia ok";
    }
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
     * @return the portalAccordionGWT
     */
    public PortalAccordionGWT getPortalAccordionGWT() {
        return portalAccordionGWT;
    }

    /**
     * @param portalAccordionGWT the portalAccordionGWT to set
     */
    public void setPortalAccordionGWT(PortalAccordionGWT portalAccordionGWT) {
        this.portalAccordionGWT = portalAccordionGWT;
    }

    /**
     * @return the mapItens
     */
    public Map<String, TabItem> getMapItens() {
        return mapItens;
    }

    /**
     * @param mapItens the mapItens to set
     */
    public void setMapItens(Map<String, TabItem> mapItens) {
        this.mapItens = mapItens;
    }
}
