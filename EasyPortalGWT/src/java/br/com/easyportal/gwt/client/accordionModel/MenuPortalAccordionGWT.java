/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.accordionModel;

import br.com.easynet.gwt.client.BaseGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.Constantes;
import br.com.easyportal.gwt.client.webModel.AMenuHandler;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author geoleite
 */
public class MenuPortalAccordionGWT implements IListenetResponse {
    private PortalAccordionGWT portalAccordionGWT;
    private List<ContentPanel> list = null;
    private List<AMenuHandlerAccordion> menuHandler;

    public void consultMenu() {
        try {
            list = null;
            EasyAccessURL access = new EasyAccessURL(this);
            access.accessJSonNoMessage(Constantes.URL + "portalgwt/menu.jsp");
        } catch (Exception ex) {
        }
    }

    public void consultMenu(String nomeSistema) {
        try {
            String url = Constantes.URL + "portalgwt/menu.jsp";
            EasyAccessURL eaurl = new EasyAccessURL(this);
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("sistema", nomeSistema);
            eaurl.accessJSonMapNoMessage(url, param);
        } catch (Exception ex) {
        }

    }

    private void menuSelecionado(Object event, AMenuHandlerAccordion menuHandler, String acao, String page) {
        if (page == null || page.trim().length() ==0 ) {
            menuHandler.actionEventMenu(event, acao);
        } else {
            menuHandler.actionEventMenu(event, acao, page);
        }
    }

    public void read(JSONValue jsonValue) {


        Listener<TreePanelEvent<ItemData>> listenerTreePanel = new Listener<TreePanelEvent<ItemData>>() {

            public void handleEvent(TreePanelEvent<ItemData> be) {
                AMenuHandlerAccordion menuHandler = (AMenuHandlerAccordion) be.getItem().getMenuHandler();// ce.getButton().getData(AMenuHandler.MENU_HANDLER);
                menuSelecionado(be, menuHandler, be.getItem().getAction(), be.getItem().getPage());
            }
        };
        // Monta o menu baseado nas informacoes do servidor

        JSONObject jsonObject;

        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONObject resultado = jsonObject.get("resultado").isObject();
            JSONArray menuContent = resultado.get("menu").isArray();
            list = new Vector<ContentPanel>();
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

                ContentPanel cpItem = new ContentPanel();
                cpItem.setHeading(nome);
                cpItem.setIcon(getIcone(icone));
                JSONArray submenu = menu.isObject().get("submenu").isArray();
                
                cpItem.setLayout(new TableLayout(1));
                if (montaSubMenu(cpItem, listenerTreePanel, submenu)) {
                    //btnItem.setMenu(subMenu);
                    //Info.display("DEBUG", "ponto3 " + );
                    //TreePanel<ItemData> treePanel = new TreePanel<ItemData>(treeStore);
                    //cpItem.add(treePanel);
                } else {
                    // Não há subitem
                    //btnItem.setData(AMenuHandler.MENU_HANDLER, getMenuHandler(acao));
                    //btnItem.setData(AMenuHandler.MENU_ACTION, acao);
                    //btnItem.addSelectionListener(listenerButton);
                }
                list.add(cpItem);
            }
        }
    }
    /**
     * Obtém o hander do menu de acordo com o sistema
     * @param item
     * @return
     */
    private AMenuHandlerAccordion getMenuHandler(String item) {
        AMenuHandlerAccordion handler = null;
        //Descobre o tratador de eventos dinamicamente a partir da lista de
        //tratadores de evento do menu

        for (AMenuHandlerAccordion aHandler : getMenuHandler()) {
            if (item != null && item.toUpperCase().indexOf(aHandler.getSystem()) > -1 ) {
                aHandler.setPortalAccordionGWT(getPortalAccordionGWT());
                handler = aHandler;
                break;
            }
        }
        
        return handler;
    }

    public AbstractImagePrototype getIcone(String iconeName) {
        AbstractImagePrototype icon = null;
        if ("novo".equals(iconeName)) {
            icon = PortalAccordionGWT.ICONS.add();
        } else if ("listar".equals(iconeName)) {
            icon = PortalAccordionGWT.ICONS.list_items();
        } else if ("relatorio".equals(iconeName)) {
            icon = PortalAccordionGWT.ICONS.chart();
        } else if ("usuarios".equals(iconeName)) {
            //icon = PortalWebGWT.ICONS.users();
        } else if ("usuario".equals(iconeName)) {
            icon = PortalAccordionGWT.ICONS.user();
        } else if ("sair".equals(iconeName)) {
            //icon = PortalWebGWT.ICONS.exit();
        } else {
            icon = PortalAccordionGWT.ICONS.menu_show();
        }
        return icon;
    }

    /**
     * Monta recursivamente os submenus
     * @param item
     * @param submenu
     */
    private boolean montaSubMenu(ContentPanel cp, Listener<TreePanelEvent<ItemData>> listener, JSONArray submenu) {

        if (submenu != null && submenu.size() > 1) {
            //Menu sub = menu;//new Menu();
            //item.setSubMenu(sub);
            //MenuItem subMenu1 = new MenuItem();
            TreeStore<ItemData> treeStore = new TreeStore<ItemData>();
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

                ItemData itemData = new ItemData();
                itemData.setName(subnome);
                itemData.setAction(subacao);
                itemData.setIcon(subicone);
                itemData.setSystem(sistema);
                itemData.setPage(pagina); 
                itemData.setMenuHandler(getMenuHandler(subacao));
                treeStore.add(itemData, false);

                JSONArray submenu1 = sm1.isObject().get("submenu").isArray();
            }
            if (submenu.size() > 1) {
                TreePanel<ItemData> treePanel = new TreePanel<ItemData>(treeStore);
                treePanel.setDisplayProperty("name");
                treePanel.getStyle().setLeafIcon(BaseGWT.ICONS.form());
                treePanel.addListener(Events.OnClick, listener);
                cp.setLayout(new FitLayout());
                cp.add(treePanel);
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
        
    }

    public void getSubMenuEvento(String sistema, String nome, String acao, String pagina, MenuItem item) {


        item.setData("pagina", pagina);
        item.setData("nome", nome);
       
    }

    /**
     * @return the list
     */
    public List<ContentPanel> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<ContentPanel> list) {
        this.list = list;
    }

    /**
     * @return the menuHandler
     */
    public List<AMenuHandlerAccordion> getMenuHandler() {
        return menuHandler;
    }

    /**
     * @param menuHandler the menuHandler to set
     */
    public void setMenuHandler(List<AMenuHandlerAccordion> menuHandler) {
        this.menuHandler = menuHandler;
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


}
