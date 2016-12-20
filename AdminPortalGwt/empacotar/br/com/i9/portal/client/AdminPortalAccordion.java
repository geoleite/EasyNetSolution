/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client;
 
import br.com.easyportal.gwt.client.accordionModel.AMenuHandlerAccordion;
import br.com.easyportal.gwt.client.accordionModel.PortalAccordionGWT;
import br.com.i9.portal.client.portal.portal.log.LogConsultGWT;
import br.com.i9.portal.client.portal.portal.men_menu.Men_menuConsultGWT;
import br.com.i9.portal.client.portal.portal.men_menu.Men_menuConsultGWTori;
import br.com.i9.portal.client.portal.portal.met_metodo.Met_metodoConsultGWT;
import br.com.i9.portal.client.portal.portal.ope_operacao.Ope_operacaoConsultGWT;
import br.com.i9.portal.client.portal.portal.par_parametro.Par_parametroConsultGWT;
import br.com.i9.portal.client.portal.portal.per_perfil.Per_perfilConsultGWT;
import br.com.i9.portal.client.portal.portal.per_perfil.Per_perfilConsultGWT_1;
import br.com.i9.portal.client.portal.portal.sis_sistema.Sis_sistemaConsultGWT;
import br.com.i9.portal.client.portal.portal.usu_usuario.Usu_usuarioConsultGWT;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Window;
import java.util.HashMap;

/**
 *
 * @author geoleite
 */
public class AdminPortalAccordion extends AMenuHandlerAccordion {

    private HashMap<String, TabItem> itens = new HashMap<String, TabItem>();

    public AdminPortalAccordion() {
        setSystem("PORTAL");
    }

    @Override
    public void actionEventMenu(Object me, String acao, String url) {
        TabItem tabItem = null;
        tabItem = itens.get(acao);

        if (tabItem == null) {
            tabItem = new TabItem();
            tabItem.setClosable(true);
            tabItem.setLayout(new FitLayout());
            ContentPanel cp = new ContentPanel();
            cp.setFrame(false);
            cp.setBorders(false);
            cp.setHeaderVisible(false);
            cp.setBodyBorder(false);
            cp.setLayout(new FitLayout());
            if ("PORTAL.sis".equalsIgnoreCase(acao)) {
                tabItem.setText("Sistemas");
                cp.add(new Sis_sistemaConsultGWT());
            } else if ("PORTAL.usu".equalsIgnoreCase(acao)) {
                tabItem.setText("Usuários");
                cp.add(new Usu_usuarioConsultGWT());
            } else if ("PORTAL.par".equalsIgnoreCase(acao)) {
                tabItem.setText("Parâmetros");
                cp.add(new Par_parametroConsultGWT());
            } else if ("PORTAL.ope".equalsIgnoreCase(acao)) {
                tabItem.setText("Operações");
                cp.add(new Ope_operacaoConsultGWT());
            } else if ("PORTAL.per".equalsIgnoreCase(acao)) {
                tabItem.setText("Perfis");
                cp.add(new Per_perfilConsultGWT());
            } else if ("PORTAL.met".equalsIgnoreCase(acao)) {
                tabItem.setText("Métodos");
                cp.add(new Met_metodoConsultGWT());
            } else if ("PORTAL.men".equalsIgnoreCase(acao)) {
                tabItem.setText("Menu");
                cp.add(new Men_menuConsultGWT());
            } else if ("PORTAL.log".equalsIgnoreCase(acao)) {
                tabItem.setText("Log");
                cp.add(new LogConsultGWT());
            } else {
                MessageBox.alert("Opcao ainda nao implementada", "Em breve esta opcao estara disponivel!", null);
            }
            if (cp != null) {
                tabItem.add(cp);
                //Adiciona o tabitem se nÃ£o existir no tabPanel
                getPortalAccordionGWT().getTabPanel().add(tabItem);
                itens.put(acao, tabItem);
            }
        } else {
            TabItem tabTemp = getPortalAccordionGWT().getTabPanel().getItemByItemId(acao);
            if (tabTemp == null) {
                getPortalAccordionGWT().getTabPanel().add(tabItem);
            }
        }
        getPortalAccordionGWT().getTabPanel().setSelection(tabItem);
        getPortalAccordionGWT().layout();
    }

    @Override
    public void actionEventMenu(Object me, String acao) {
        actionEventMenu(me, acao, "");
    }
}
