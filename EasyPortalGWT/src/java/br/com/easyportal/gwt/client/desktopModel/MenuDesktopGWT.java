/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.desktopModel;

//import br.com.easynet.gwt.easyportal.client.display.EasyEngenharia.ActionMenu;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easyportal.gwt.client.AlterarSenhaGWT;
import br.com.easyportal.gwt.client.Constantes;
import br.com.easyportal.gwt.client.MenuEventoDesktopGWT;
import br.com.easyportal.gwt.client.SairIListenerUrl;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.desktop.client.StartMenu;
import com.extjs.gxt.desktop.client.TaskBar;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class MenuDesktopGWT implements IListenetResponse {

    private EasyLabels easyLabels = GWT.create(EasyLabels.class);
    protected Desktop desktop;
    private MessageBox mbProgress;
    private List<AMenuDesktop> listMenuDesktop = new ArrayList<AMenuDesktop>();

    public void loadMenu() {
        try {
            String url = Constantes.URL + "portalgwt/menu.jsp";
            EasyAccessURL eaurl = new EasyAccessURL(this);
            mbProgress = MessageBox.wait(easyLabels.waitRequest(), easyLabels.waitServer(), easyLabels.process());
            eaurl.accessJSon(url);
        } catch (Exception ex) {
            Window.alert("Error:" + ex.getMessage());
        }
    }

    public void loadMenu(String nomeSistema) {
        try {
            String url = Constantes.URL + "portalgwt/menu.jsp";
            EasyAccessURL eaurl = new EasyAccessURL(this);
            mbProgress = MessageBox.wait(easyLabels.waitRequest(), easyLabels.waitServer(), easyLabels.process());
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("sistema", nomeSistema);
            eaurl.accessJSonMap(url, param);
        } catch (Exception ex) {
            Window.alert("Error:" + ex.getMessage());
        }
    }

    public void addAMenuDesktop(AMenuDesktop menuDesktop) {
        getListMenuDesktop().add(menuDesktop);
    }

    public void setMenuTool(StartMenu menu) {

        SelectionListener<MenuEvent> listener = new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                com.extjs.gxt.ui.client.widget.Window w = null;
                if (ce instanceof MenuEvent) {
                    MenuEvent me = (MenuEvent) ce;
                    w = me.getItem().getData("window");
                } else {
                    w = ce.getComponent().getData("window");
                }
                if (!desktop.getWindows().contains(w)) {
                    desktop.addWindow(w);
                }
                if (w != null && !w.isVisible()) {
                    w.show();
                } else {
                    w.toFront();
                }
            }
        };

        MenuItem tool = new MenuItem(easyLabels.alterPass());
        tool.setIconStyle(easyLabels.settings());
        tool.addSelectionListener(listener);
        com.extjs.gxt.ui.client.widget.Window win = new com.extjs.gxt.ui.client.widget.Window();
        win.add(new AlterarSenhaGWT());
        win.setSize("300", "250");
        win.setHeaderVisible(true);
        win.setHeading(easyLabels.alterPass());
        win.setClosable(true);
        win.setMinimizable(true);

        tool.setData("window", win);
        menu.addTool(tool);

        menu.addToolSeperator();

        tool = new MenuItem(easyLabels.exit());
        tool.setIconStyle(easyLabels.logout());
        tool.addSelectionListener(new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {

                MessageBox mb = MessageBox.confirm(easyLabels.warning(), easyLabels.confirmOut(), new Listener<MessageBoxEvent>() {

                    public void handleEvent(MessageBoxEvent be) {

                        //Info.display("DEBUG", MessageBox.OK + " " +  be.getButtonClicked().getText());
                        if (easyLabels.yes().equalsIgnoreCase(be.getButtonClicked().getText())) {
                            btnSimAction(be.getButtonClicked());
                        }
                    }
                });

            }
        });

        tool.setData("window", menu);
        menu.addTool(tool);
    }

    public void btnSimAction(Widget sender) {
        try {
            DebugMessage.message(this.getClass().getName(), "Saindo do Sistema");
            EasyAccessURL eaurl = new EasyAccessURL(new SairIListenerUrl());
            String url = Constantes.URL + "portalgwt/principalpage.jsp?op=sair";
            eaurl.accessJSon(url);
        } catch (Exception e) {
            DebugMessage.message(DebugMessage.ERROR, this.getClass().getName(), easyLabels.operationFailure() + ":" + e.getMessage());
        }
    }

    public void read(JSONValue jsonValue) {

        SelectionListener<MenuEvent> listener = new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                com.extjs.gxt.ui.client.widget.Window w = null;

                if (ce instanceof MenuEvent) {
                    MenuEvent me = (MenuEvent) ce;
                    com.extjs.gxt.ui.client.widget.Window win = null;
                    //AMenuDesktop ecm = new ActionMenu();
                    String acao = me.getItem().getData("window");

                    if (getListMenuDesktop() != null) {
                        for (int i = 0; i < getListMenuDesktop().size(); i++) {
                            AMenuDesktop menu = getListMenuDesktop().get(i);
                            com.extjs.gxt.ui.client.widget.Window wTemp = menu.getWindowAcao(acao);
                            if (wTemp != null) {
                                w = wTemp;
                                break;
                            }
                        }
                    }


                } else {
                    w = ce.getComponent().getData("window");
                }
                if (!desktop.getWindows().contains(w)) {
                    desktop.addWindow(w);
                }
                if (w != null && !w.isVisible()) {
                    w.show();
                } else {
                    w.toFront();
                }
            }
        };

        // Monta o menu baseado nas informacoes do servidor

        JSONObject jsonObject;
        mbProgress.close();
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONObject resultado = jsonObject.get("resultado").isObject();
            JSONArray menuContent = resultado.get("menu").isArray();
            TaskBar taskBar = desktop.getTaskBar();
            StartMenu menuBar = taskBar.getStartMenu();
            setMenuTool(menuBar);


            for (int i = 1; i < menuContent.size(); i++) {

                JSONValue menu = menuContent.get(i);
                String nome = menu.isObject().get("nome").toString();
                nome = nome.replace('"', ' ').trim();
                String acao = menu.isObject().get("acao").toString();
                acao = acao.replace('"', ' ').trim();
                String sistema = menu.isObject().get("sistema").toString();
                sistema = sistema.replace('"', ' ').trim();

                MenuItem item = new MenuItem(nome);
                getMenuEvento(sistema, acao, item);
                item.addSelectionListener(listener);
                menuBar.add(item);
                item.setIconStyle("bogus");
                JSONArray submenu = menu.isObject().get("submenu").isArray();
                montaSubMenu(item, listener, submenu);
            }

        }

    }

    /**
     * Monta recursivamente os submenus
     * @param item
     * @param submenu
     */
    private void montaSubMenu(MenuItem item, SelectionListener<MenuEvent> listener, JSONArray submenu) {

        if (submenu != null && submenu.size() > 1) {
            Menu sub = new Menu();
            item.setSubMenu(sub);
            MenuItem subMenu1 = new MenuItem();
            for (int j = 1; j < submenu.size(); j++) {
                JSONValue sm1 = submenu.get(j);
                String subnome = sm1.isObject().get("nome").toString();
                subnome = subnome.replace('"', ' ').trim();
                String subacao = sm1.isObject().get("acao").toString();
                String sistema = sm1.isObject().get("sistema").toString();
                sistema = sistema.replace('"', ' ').trim();
                subacao = subacao.replace('"', ' ').trim();
                MenuItem subitem = new MenuItem(subnome);
                getMenuEvento(sistema, subacao, subitem);
                subitem.addSelectionListener(listener);
                subitem.setIconStyle("bogus");
                item.getSubMenu().add(subitem);
                JSONArray submenu1 = sm1.isObject().get("submenu").isArray();
                montaSubMenu(subitem, listener, submenu1);

            }

        }
    }

    /**
     * Obtem o tratamento de Eventos do menu pelo nome do Sistema
     * @param sistema
     * @return
     */
    public MenuEventoDesktopGWT getMenuEvento(String sistema, String acao, MenuItem item) {
        MessageBox.alert(easyLabels.problem(), easyLabels.systemNotDefined(), null);
        MenuEventoDesktopGWT menuEvento = null;
        menuEvento = new MenuEventoDesktopGWT(desktop, item);
        DebugMessage.message(this.getClass().getName(), "Sistema " + sistema + " item " + item.getText());
        if ("Engenharia".equalsIgnoreCase(sistema)) {
            item.setData("window", acao);
        } else {
            MessageBox.alert(easyLabels.problem(), easyLabels.optionNotImplemented(), null);
        }

        return menuEvento;
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
     * @return the listMenuDesktop
     */
    public List<AMenuDesktop> getListMenuDesktop() {
        return listMenuDesktop;
    }

    /**
     * @param listMenuDesktop the listMenuDesktop to set
     */
    public void setListMenuDesktop(List<AMenuDesktop> listMenuDesktop) {
        this.listMenuDesktop = listMenuDesktop;
    }
}
