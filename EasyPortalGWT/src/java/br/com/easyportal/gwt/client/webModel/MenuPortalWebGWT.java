/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.webModel;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.Constantes;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author ${user}
 */
public class MenuPortalWebGWT implements IListenetResponse {

    private PortalWebGWT portalWebGWT;
    private List<Button> list = null;
    private List<AMenuHandler> menuHandler;

    public void consultMenu() {
        try {
            list = null;
            EasyAccessURL access = new EasyAccessURL(this);
            access.accessJSon(Constantes.URL + "portalgwt/menu.jsp");
        } catch (Exception ex) {
        }
    }

    private void menuSelecionado(Object event, AMenuHandler menuHandler, String acao) {
        menuHandler.actionEventMenu(event, acao);

    }

    public void read(JSONValue jsonValue) {
        SelectionListener<MenuEvent> listener = new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                //com.extjs.gxt.ui.client.widget.Window w = null;

                AMenuHandler menuHandler = (AMenuHandler) ce.getItem().getData(AMenuHandler.MENU_HANDLER);
                menuSelecionado(ce, menuHandler, (String) ce.getItem().getData(AMenuHandler.MENU_ACTION));

            }
        };

        SelectionListener<ButtonEvent> listenerButton = new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                AMenuHandler menuHandler = (AMenuHandler) ce.getButton().getData(AMenuHandler.MENU_HANDLER);
                menuSelecionado(ce, menuHandler, (String) ce.getButton().getData(AMenuHandler.MENU_ACTION));
            }
        };
        // Monta o menu baseado nas informacoes do servidor

        JSONObject jsonObject;

        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONObject resultado = jsonObject.get("resultado").isObject();
            JSONArray menuContent = resultado.get("menu").isArray();
            //Window.alert(menuContent.isArray().toString());
            //menuBar.addItem(getMenuPadrao());
            //menu.add(getMenuPadrao());
            setList(new Vector<Button>());
            for (int i = 1; i < menuContent.size(); i++) {

                JSONValue menu = menuContent.get(i);
                String nome = menu.isObject().get("nome").toString();
                nome = nome.replace('"', ' ').trim();
                String acao = menu.isObject().get("acao").toString();
                acao = acao.replace('"', ' ').trim();

                String icone = menu.isObject().get("icone").toString();

                icone = icone.replace('"', ' ').trim();
                String sistema = menu.isObject().get("sistema").toString();
                sistema = sistema.replace('"', ' ').trim();
                String pagina = menu.isObject().get("pagina").toString();
                pagina = pagina.replace('"', ' ').trim();

                //MenuEvento menuEvento = getMenuEvento(sistema);
                //menuEvento.setActionDisplay(acao);
                Button btnItem = new Button(nome);

                btnItem.setIcon(getIcone(icone));
                JSONArray submenu = menu.isObject().get("submenu").isArray();
                Menu subMenu = new Menu();
                if (montaSubMenu(subMenu, listener, submenu)) {
                    btnItem.setMenu(subMenu);
                } else {
                    // Não há subitem
                    btnItem.setData(AMenuHandler.MENU_HANDLER, getMenuHandler(acao));
                    btnItem.setData(AMenuHandler.MENU_ACTION, acao);
                    btnItem.addSelectionListener(listenerButton);
                }
                getList().add(btnItem);
            }
        }
    }

    /**
     * Obtém o hander do menu de acordo com o sistema
     * @param item
     * @return
     */
    private AMenuHandler getMenuHandler(String item) {
        AMenuHandler handler = null;
        //Descobre o tratador de eventos dinamicamente a partir da lista de
        //tratadores de evento do menu

        for (AMenuHandler aHandler : menuHandler) {
            if (item != null && item.toUpperCase().indexOf(aHandler.getSystem()) > -1 ) {
                aHandler.setPortalWebGWT(portalWebGWT);
                handler = aHandler;
                break;
            }
        }

//        if (item != null && item.toUpperCase().indexOf("I9IM.") > -1) {
//            I9IMMenuHandler i9im = new I9IMMenuHandler();
//            i9im.setPrincipalGWT(principalGWT);
//            handler = i9im;
//        } else {
//            handler = null;
//        }
        return handler;
    }

    private AbstractImagePrototype getIcone(String iconeName) {
        AbstractImagePrototype icon = null;
        if ("novo".equals(iconeName)) {
            icon = PortalWebGWT.ICONS.add();
        } else if ("listar".equals(iconeName)) {
            icon = PortalWebGWT.ICONS.list_items();
        } else if ("relatorio".equals(iconeName)) {
            icon = PortalWebGWT.ICONS.chart();
        } else if ("usuarios".equals(iconeName)) {
            //icon = PortalWebGWT.ICONS.users();
        } else if ("usuario".equals(iconeName)) {
            icon = PortalWebGWT.ICONS.user();
        } else if ("sair".equals(iconeName)) {
            //icon = PortalWebGWT.ICONS.exit();
        } else {
            icon = PortalWebGWT.ICONS.menu_show();
        }
        return icon;
    }

    /**
     * Monta recursivamente os submenus
     * @param item
     * @param submenu
     */
    private boolean montaSubMenu(Menu menu, SelectionListener<MenuEvent> listener, JSONArray submenu) {

        if (submenu != null && submenu.size() > 1) {
            Menu sub = menu;//new Menu();
            //item.setSubMenu(sub);
            MenuItem subMenu1 = new MenuItem();

            for (int j = 1; j < submenu.size(); j++) {
                JSONValue sm1 = submenu.get(j);
                String subnome = sm1.isObject().get("nome").toString();
                subnome = subnome.replace('"', ' ').trim();
                String subacao = sm1.isObject().get("acao").toString();
                String subicone = sm1.isObject().get("icone").toString();
                subicone = subicone.replace('"', ' ').trim();
                String sistema = sm1.isObject().get("sistema").toString();
                sistema = sistema.replace('"', ' ').trim();
                String pagina = sm1.isObject().get("pagina").toString();
                pagina = pagina.replace('"', ' ').trim();

                subacao = subacao.replace('"', ' ').trim();


                MenuItem subitem = new MenuItem(subnome);
                subitem.setIcon(getIcone(subicone));
                subitem.setData(AMenuHandler.MENU_HANDLER, getMenuHandler(subacao));
                subitem.setData(AMenuHandler.MENU_ACTION, subacao);
                getSubMenuEvento(sistema, subnome, subacao, pagina, subitem);
                subitem.addSelectionListener(listener);
                //subitem.setIconStyle("bogus");
                menu.add(subitem);

                JSONArray submenu1 = sm1.isObject().get("submenu").isArray();

                //montaSubMenu(subitem, listener, submenu1);
            }
            if (submenu.size() > 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtem o tratamento de Eventos do menu pelo nome do Sistema
     * @param sistema
     * @return
     */
    public void getMenuEvento(String sistema, String nome, String acao, String pagina, Button item) {
        Menu subMenu = new Menu();
        MenuItem mi = new MenuItem(nome);
        item.setMenu(subMenu);
        //ToolBar toolBar = new ToolBar();
/*
        item.setData("pagina", pagina);
        item.setData("nome", nome);
        //        Window.alert(pagina + " " + acao);
        if ("EasyMultiPro".equals(sistema)) {
        item.setData("window", acao);
        } else if ("EasyInMap".equals(sistema)) {
        item.setData("window", acao);
        } else if ("TVCorporativa".equals(sistema)) {
        item.setData("window", acao);
        } else if ("EasyCartorio".equals(sistema)) {
        //Classe responsável por montar o menur do sistema de Cartorio
        //EasyCartorioMenu ecm = new EasyCartorioMenu();
        //win = ecm.getWindowAcao(acao);
        item.setData("window", acao);
        } else {
        //MessageBox.alert("Falha na execução", "Opção ainda não implementada", null);
        }*/
        //toolBar.add(item);

        //return toolBar;
    }

    public void getSubMenuEvento(String sistema, String nome, String acao, String pagina, MenuItem item) {


        item.setData("pagina", pagina);
        item.setData("nome", nome);
        /*
        //        Window.alert(pagina + " " + acao);
        if ("EasyMultiPro".equals(sistema)) {
        item.setData("window", acao);
        } else if ("EasyInMap".equals(sistema)) {
        item.setData("window", acao);
        } else if ("TVCorporativa".equals(sistema)) {
        item.setData("window", acao);
        } else if ("EasyCartorio".equals(sistema)) {
        //Classe responsável por montar o menur do sistema de Cartorio
        //EasyCartorioMenu ecm = new EasyCartorioMenu();
        //win = ecm.getWindowAcao(acao);
        item.setData("window", acao);
        } else {
        //MessageBox.alert("Falha na execução", "Opção ainda não implementada", null);
        }*/
    }

    /**
     * @return the list
     */
    public List<Button> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Button> list) {
        this.list = list;
    }

    /**
     * @return the menuHandler
     */
    public List<AMenuHandler> getMenuHandler() {
        return menuHandler;
    }

    /**
     * @param menuHandler the menuHandler to set
     */
    public void setMenuHandler(List<AMenuHandler> menuHandler) {
        this.menuHandler = menuHandler;
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
