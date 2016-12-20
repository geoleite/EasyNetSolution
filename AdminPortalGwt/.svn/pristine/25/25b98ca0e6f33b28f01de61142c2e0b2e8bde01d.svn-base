/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easyportal.gwt.client.AuthenticationGWT;
import br.com.easyportal.gwt.client.accordionModel.PortalAccordionGWT;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.google.gwt.core.client.EntryPoint;

/**
 * Main entry point.
 *
 * @author geoleite
 */
public class MainEntryPoint implements EntryPoint {

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    public void onModuleLoad() {

        //Definindo que a app deve ser depurada
/*        boolean debug = false;
         String debugOpcao = com.google.gwt.user.client.Window.Location.getParameter("debug");
         if (debugOpcao != null && debugOpcao.trim().length() > 0) {
         try {
         debug = Boolean.parseBoolean(debugOpcao);
         } catch (Exception e) {
         }
         }
        
         DebugMessage.setDebug(debug);

         String appServer = com.google.gwt.user.client.Window.Location.getParameter("app");
         if (appServer == null || appServer.trim().length() ==0) {
         appServer = "adminportalsrv";
         }
         Constantes.URL = "/" + appServer + "/";
         //br.com.easyportal.gwt.client.Constantes.URL  = "/" + appServer + "/";
         DebugMessage.message(this.getClass().getName(), br.com.easyportal.gwt.client.Constantes.URL);

         ThemeManager.register(Slate.SLATE);
         ThemeManager.register(Access.ACCESS);
         ThemeManager.register(I9ThemeBlack.I9THEMEBLACK);
         ThemeManager.register(I9ThemeDarkGray.I9THEMEDARKGRAY);
         ThemeManager.register(Access.ACCESS);

         GXT.setDefaultTheme(Theme.GRAY, true);
         //br.com.easyportal.gwt.client.Constantes.URL = br.com.easyportal.gwt.client.Constantes.URL;

        
         PortalAccordionGWT portal = new PortalAccordionGWT("images/topo.png", 30);
         portal.carregaParametrosSistema("EASYPORTAL");
         portal.setObsText("Versão: 2.0.0");
         AdminPortalAccordion adminMenu = new AdminPortalAccordion();
         portal.addMenuHandler(adminMenu);
        
         AuthenticationGWT authentication = new AuthenticationGWT();
         Button b = new Button("fechar");
         b.addClickHandler(new ClickHandler() {

         @Override
         public void onClick(ClickEvent event) {
                
         }
         });
        
         //authentication.setModoAuthenticationDigital(false);
         //Utilizado para realizar o login automático qnd for feito através do certificado digital
         String logincert = com.google.gwt.user.client.Window.Location.getParameter("logincert");
         if (logincert != null) {
         authentication.checkAuthentication();
         }
         authentication.setPortal(portal);
         authentication.setVisible(true);    
         */
        EasyAccessURL.setNrMaxTentativas(0);
        EasyAccessURL.setTimeMaxWait(9999999);

        //Definindo que a app deve ser depurada
        boolean debug = false;
        String debugOpcao = com.google.gwt.user.client.Window.Location.getParameter("debug");
        if (debugOpcao != null && debugOpcao.trim().length() > 0) {
            try {
                debug = Boolean.parseBoolean(debugOpcao);
            } catch (Exception e) {
            }
        }
        DebugMessage.setDebug(debug);
        String appServer = com.google.gwt.user.client.Window.Location.getParameter("app");
        if (appServer == null || appServer.trim().length() == 0) {
            appServer = "adminportalsrv";
        }
        Constantes.URL = "/" + appServer + "/";
        
        PortalAccordionGWT portal = new PortalAccordionGWT("images/topo.png", 30);

         AdminPortalAccordion adminMenu = new AdminPortalAccordion();
         portal.addMenuHandler(adminMenu);

        final AuthenticationGWT authentication = new AuthenticationGWT();
        authentication.setPortal(portal);
        authentication.setVisible(true);
        com.extjs.gxt.ui.client.widget.button.Button b = new com.extjs.gxt.ui.client.widget.button.Button(" close", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                authentication.setVisible(false);
            }
        });


        authentication.getCpMaster().addButton(b);
    }
}
