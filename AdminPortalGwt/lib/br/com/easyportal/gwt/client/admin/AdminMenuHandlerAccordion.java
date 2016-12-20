/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.admin;

import br.com.easyportal.gwt.client.accordionModel.AMenuHandlerAccordion;
import br.com.easyportal.gwt.client.admin.portal.portal.log.LogConsultGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.ope_operacao.Ope_operacaoConsultGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.per_perfil.Per_perfilConsultGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.sis_sistema.Sis_sistemaConsultGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.usu_usuario.Usu_usuarioConsultGWT;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Timer;
import java.util.HashMap;

/**
 *
 * @author geoleite
 */
public class AdminMenuHandlerAccordion extends AMenuHandlerAccordion {

    private HashMap<String, TabItem> itens = new HashMap<String, TabItem>();

    public AdminMenuHandlerAccordion() {
        setSystem("PORTAL");
    }

    @Override
    public void actionEventMenu(Object me, String acao) {
        TabItem tabItem = null;
        if ("Portal.Sistemas".equalsIgnoreCase(acao)) {
            getCpConsultarSistemas();
//            if (itens.containsKey(acao)) {
//                tabItem = itens.get(acao);
//                tabItem.removeAll();
//            } else {
//                tabItem = new TabItem("Distribuidoras");
//                tabItem.setClosable(true);
//                tabItem.setId(acao);
//                itens.put(acao, tabItem);
//            }
//            tabItem.setLayout(new FitLayout());
//            //tabItem.add(getCpDistribuidoraConsulta());
//            tabItem.layout();

        } else if ("Portal.Logs".equalsIgnoreCase(acao)) {
            getCpConsultarLog();
        } else if ("Portal.Usuarios".equalsIgnoreCase(acao)) {
            getCpConsultarUsuarios();
//            if (itens.containsKey(acao)) {
//                tabItem = itens.get(acao);
//                tabItem.removeAll();
//            } else {
//                tabItem = new TabItem("Distribuidoras");
//                tabItem.setClosable(true);
//                tabItem.setId(acao);
//                itens.put(acao, tabItem);
//            }
//            tabItem.setLayout(new FitLayout());
//            //tabItem.add(getCpDistribuidoraConsulta());
//            tabItem.layout();

        } else if ("I9IM.ListarRegioes".equalsIgnoreCase(acao)) {
            getCpConsultarPerfis();
        } else if ("Portal.Perfis".equalsIgnoreCase(acao)) {
            getCpConsultarPerfis();
//            if (itens.containsKey(acao)) {
//                tabItem = itens.get(acao);
//                tabItem.removeAll();
//            } else {
//                tabItem = new TabItem("Distribuidoras");
//                tabItem.setClosable(true);
//                tabItem.setId(acao);
//                itens.put(acao, tabItem);
//            }
//            tabItem.setLayout(new FitLayout());
//            //tabItem.add(getCpDistribuidoraConsulta());
//            tabItem.layout();
        } else {
            MessageBox.alert("Opcão ainda não implementada", "Em breve esta opção estará disponível!", null);
        }
        //Adiciona o tabitem se não existir no tabPanel
        if (getPortalAccordionGWT().getTabPanel().getItemByItemId(acao)
                == null) {
            getPortalAccordionGWT().getTabPanel().add(tabItem);
        }

        getPortalAccordionGWT().getTabPanel().setSelection(tabItem);
        getPortalAccordionGWT().layout();
    }

    /**
     * Monta a tela de Consulta de Sistemas
     * @return
     */
    private ContentPanel getCpConsultarSistemas() {

        final Sis_sistemaConsultGWT conSis = new Sis_sistemaConsultGWT();
        final ContentPanel cp = new ContentPanel();
        cp.setHeaderVisible(false);
        cp.setLayout(new FitLayout());
        Timer timer = new Timer() {

            @Override
            public void run() {
                if (!conSis.isExibir()) {
                    schedule(500);
                } else {
                    //cp.add(notaIns);
                    conSis.show();
                }
            }
        };
        timer.schedule(500);
        return cp;
    }

    private ContentPanel getCpConsultarLog() {
        final LogConsultGWT conLog = new LogConsultGWT();
        conLog.setVisible(true);
        final ContentPanel cp = new ContentPanel();
//        cp.setHeaderVisible(false);
//        cp.setLayout(new FitLayout());
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                if (!conUsu.isExibir()) {
//                    schedule(500);
//                } else {
//                    //cp.add(notaIns);
//                    conUsu.show();
//
//
//                }
//            }
//        };
//        timer.schedule(500);
        return cp;

    }

    /**
     * Tela para consultar os usuários
     * @return
     */
    private ContentPanel getCpConsultarUsuarios() {

        final Usu_usuarioConsultGWT conUsu = new Usu_usuarioConsultGWT();
        conUsu.show();
        final ContentPanel cp = new ContentPanel();
//        cp.setHeaderVisible(false);
//        cp.setLayout(new FitLayout());
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                if (!conUsu.isExibir()) {
//                    schedule(500);
//                } else {
//                    //cp.add(notaIns);
//                    conUsu.show();
//
//
//                }
//            }
//        };
//        timer.schedule(500);


        return cp;

    }

    /**
     * Tela para consultar os perfis
     * @return
     */
    private ContentPanel getCpConsultarPerfis() {

        final Per_perfilConsultGWT conPer = new Per_perfilConsultGWT();
        conPer.show();
        final ContentPanel cp = new ContentPanel();
        return cp;
    }

    

}
