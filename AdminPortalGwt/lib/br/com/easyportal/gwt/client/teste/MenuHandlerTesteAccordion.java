/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.teste;

import br.com.easyportal.gwt.client.webModel.AMenuHandler;
import br.com.easyportal.gwt.client.IMenuHandler;
import br.com.easyportal.gwt.client.accordionModel.AMenuHandlerAccordion;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.TabItem;

/**
 *
 * @author geoleite
 */
public class MenuHandlerTesteAccordion extends AMenuHandlerAccordion {
    public MenuHandlerTesteAccordion() {
        setSystem("I9IM");
    }
    @Override
    public void actionEventMenu(Object me, String acao) {
        getPortalAccordionGWT().getTabPanel().add(new TabItem("teste"));
        getPortalAccordionGWT().getTabPanel().recalculate();
        getPortalAccordionGWT().getCpMaster().layout();
    }

}
