/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client;

import br.com.easyportal.gwt.client.accordionModel.PortalAccordionGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Per_perfilTGWT;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 *
 * @author geoleite
 */
public class PortalAccordionSDPCommonGWT extends PortalAccordionGWT {

    private static final String PERFIL_ADM="ITBI.Adm";
    public PortalAccordionSDPCommonGWT() {
        //tabPanel.add(tiWelcome);
        this(0);
    }

    public PortalAccordionSDPCommonGWT(int alturaBanner) {
        super(alturaBanner);
        //tabPanel.add(tiWelcome);
        getTabPanel().remove(getTiWelcome());
    }

    public void createMenu() {
        super.createMenu();

        AdminPortalAccordion menuHandler = (AdminPortalAccordion) getMenuHandler().get(0);
        //menuHandler.actionEventMenu(null, "ITBI.Uf");

//        ListStore<Per_perfilTGWT> listPerfis = getListPer();

//        boolean adm = false;
//        for (int i = 0; i < listPerfis.getCount(); i++) {
//            Per_perfilTGWT perT = listPerfis.getAt(i);
//            if (PERFIL_ADM.equalsIgnoreCase(perT.getPer_tx_nome())) {
//                adm = true;
//                break;
//            }
//        }
//        if (adm){
//            menuHandler.actionEventMenu(null, "I9SAT.map");
//        } else {
//            menuHandler.actionEventMenu(null, "I9SAT.mapCliente");
//        }
        
    }
}
