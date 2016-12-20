/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easynet.gwt.client.theme.I9ThemeBlack;
import br.com.easynet.gwt.client.theme.I9ThemeDarkGray;
import br.com.easyportal.gwt.client.accordionModel.PortalAccordionGWT;
import com.extjs.gxt.themes.client.Access;
import com.extjs.gxt.themes.client.Slate;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.util.ThemeManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Main entry point.
 *
 * @author georgejunior
 */
public class EasyPortalGWTEntryPoint implements EntryPoint {

    /**
     * Creates a new instance of EasyPortalGWTEntryPoint
     */
    public EasyPortalGWTEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    public void onModuleLoad() {
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
        if (appServer == null || appServer.trim().length() ==0) {
            appServer = "i9taxisrv";
        }
        Constantes.URL  = "/" + appServer + "/";
        DebugMessage.message(this.getClass().getName(), Constantes.URL);

        ThemeManager.register(Slate.SLATE);
        ThemeManager.register(Access.ACCESS);
        ThemeManager.register(I9ThemeBlack.I9THEMEBLACK);
        ThemeManager.register(I9ThemeDarkGray.I9THEMEDARKGRAY);
        ThemeManager.register(Access.ACCESS);

        


        GXT.setDefaultTheme(Theme.GRAY, true);
        br.com.easyportal.gwt.client.Constantes.URL = Constantes.URL;

        PortalAccordionGWT portal = new PortalAccordionGWT("images/topo.png", 30);
        portal.carregaParametrosSistema("EASYPORTAL");
        portal.setObsText("Versão: 2.0.0");

        AuthenticationGWT authentication = new AuthenticationGWT();
        //authentication.setModoAuthenticationDigital(false);
        //Utilizado para realizar o login automático qnd for feito através do certificado digital
        String logincert = com.google.gwt.user.client.Window.Location.getParameter("logincert");
        if (logincert != null) {
            authentication.checkAuthentication();
        }
        authentication.setPortal(portal);
        authentication.setVisible(true);
    }
}
