/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.webModel;

import br.com.easynet.gwt.client.BaseGWT;
import br.com.easynet.gwt.client.CPBaseGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.AlterarSenhaGWT;
import br.com.easyportal.gwt.client.Constantes;
import br.com.easyportal.gwt.client.IPrincipalPortal;
import br.com.easyportal.gwt.client.PortalBase;
import br.com.easyportal.gwt.client.SairIListenerUrl;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Per_perfilTGWT;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author geoleite 
 */
public class PortalWebGWT extends PortalBase implements IPrincipalPortal {

    private String logedUser = "";
    private EasyLabels easyLabels = GWT.create(EasyLabels.class);
    private ToolBar toolBar = new ToolBar();
    private Button btnUsuarioLogado = new Button("Usuario Logado");
    private MenuItem miAlterarSenha = new MenuItem(easyLabels.alterPass());
    private MenuItem miDefinirRecallSenha = new MenuItem(easyLabels.secretAnswer());
    private MenuItem miSair = new MenuItem(easyLabels.exit());
    private List<AMenuHandler> menuHandler = new ArrayList<AMenuHandler>();
    private MenuPortalWebGWT menuPortalWebGWT = new MenuPortalWebGWT();
    private TabPanel tabPanel = new TabPanel();
    private TabItem tiWelcome = new TabItem(easyLabels.welcome());
    private static ListStore<Per_perfilTGWT> listPer;

    public PortalWebGWT() {
        setBorders(false);
        setBodyBorder(false);
        remove(getCpBotton());
        remove(getCpTop());
        remove(getCpRight());
        remove(getCpLeft());
        Window.setMargin("0");
        Window.setTitle(easyLabels.easyPortalSystem());
        setHeaderVisible(false);
        setTopComponent(toolBar);
        setScrollMode(Scroll.AUTO);
        tabPanel.add(tiWelcome);
        getCpMaster().setTopComponent(tabPanel);
    }

    public PortalWebGWT(int alturaBanner) {
        this();
        setWidth("100%");
        setHeight(Window.getClientHeight() - alturaBanner);
    }

    public void addMenuHandler(AMenuHandler menuHandlerWeb) {
        menuHandler.add(menuHandlerWeb);
        menuHandlerWeb.setPortalWebGWT(this);
    }

    public void createMenu() {
        menuPortalWebGWT.setPortalWebGWT(this);
        menuPortalWebGWT.setMenuHandler(menuHandler);

        createToolBar();
        createMenuOptions();
    }

    private Menu getMenuPerfis() {
        Menu subMenu = new Menu();
        for (int i = 0; i < listPer.getCount(); i++) {
            Per_perfilTGWT perT = listPer.getAt(i);
            MenuItem mi = new MenuItem(perT.getPer_tx_nome());
            subMenu.add(mi);
        }
        return subMenu;
    }

    /**
     * Monta o menu fixo e padrão
     * @return
     */
    private void createToolBar() {

        SelectionListener eventMenu = new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                eventoMenu(ce);
            }
        };

        //Definindo o menu fixo representando o usuário logado
        Menu subMenu = new Menu();
        miAlterarSenha.setIcon(ICONS.chave());
        miDefinirRecallSenha.setIcon(ICONS.cadeado());

        //miSair.setIcon(ICONS.exit());
        subMenu.add(miAlterarSenha);
        subMenu.add(miDefinirRecallSenha);

        SeparatorMenuItem smi = new SeparatorMenuItem();
        subMenu.add(smi);
        subMenu.add(miSair);
        btnUsuarioLogado.setIcon(ICONS.user());
        miAlterarSenha.setIcon(ICONS.chave());
        miSair.setIcon(ICONS.sair());
        miAlterarSenha.addSelectionListener(eventMenu);
        miDefinirRecallSenha.addSelectionListener(eventMenu);

        miSair.addSelectionListener(eventMenu);
        btnUsuarioLogado.setMenu(subMenu);
        btnUsuarioLogado.setText(logedUser);

        toolBar.add(btnUsuarioLogado);
        toolBar.add(btnUsuarioLogado);
        if (listPer != null) {
            Button btnPerfil = new Button("Perfis do Usuario");
            btnPerfil.setMenu(getMenuPerfis());
            toolBar.add(btnPerfil);
        }

        // Motando o menu dinamico

    }

    /**
     * Constroi no toolbar as opcoes do menu dinamicamente
     */
    private void createMenuOptions() {

        menuPortalWebGWT.consultMenu();
        Timer timer = new Timer() {

            @Override
            public void run() {
                List<Button> listMenu = menuPortalWebGWT.getList();
                if (listMenu == null) {
                    schedule(500);
                } else {
                    for (Button button : listMenu) {
                        toolBar.add(button);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    private void exiting() {
        try {
            EasyAccessURL eaurl = new EasyAccessURL(new SairIListenerUrl());
            String url = Constantes.URL + "portalgwt/principalpage.jsp?op=sair";
            eaurl.accessJSon(url);
        } catch (Exception ex) {
        }
    }

    private void updatePas() {
        AlterarSenhaGWT altSenhaGWT = new AlterarSenhaGWT();
        altSenhaGWT.show();
    }

    /**
     * Trata os eventos do menu
     * @param me
     */
    private void eventoMenu(MenuEvent me) {
        if (me.getItem() == miAlterarSenha) {
            updatePas();
        } else if (me.getItem() == miSair) {
            exiting();
        } else if (me.getItem() == miDefinirRecallSenha) {
            insertUpdateRecallSenha();
        }
    }

    /**
     * @param toolBar the toolBar to set
     */
    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    /**
     * @return the logedUser
     */
    public String getLogedUser() {
        return logedUser;
    }

    /**
     * @param logedUser the logedUser to set
     */
    public void setLogedUser(String logedUser) {
        this.logedUser = logedUser;
        if (logedUser != null) {
            btnUsuarioLogado.setText(logedUser);
        }
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
     * @return the tabPanel
     */
    public TabPanel getTabPanel() {
        return tabPanel;
    }

    /**
     * @param tabPanel the tabPanel to set
     */
    public void setTabPanel(TabPanel tabPanel) {
        this.tabPanel = tabPanel;
    }

    /**
     * @return the tiWelcome
     */
    public TabItem getTiWelcome() {
        return tiWelcome;
    }

    /**
     * @param tiWelcome the tiWelcome to set
     */
    public void setTiWelcome(TabItem tiWelcome) {
        this.tiWelcome = tiWelcome;
    }

    public void checkSession(final int time) {
        final IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    boolean authenticationOK = true;
                    JSONObject resultado = jsonObject.get("resultado").isObject();
                    String valor = resultado.get("autenticacao").toString();
                    valor = valor.replace('"', ' ').trim();
                    String usuario = resultado.get("usuario").toString();
                    usuario = usuario.replace('"', ' ').trim();
                    if (!"true".equals(valor)) {
                        exiting();
                    }
                }
            }
        };
        if (time > 0) {
            Timer tm = new Timer() {

                @Override
                public void run() {

                    EasyAccessURL access = new EasyAccessURL(listener);
                    String url = Constantes.URL + "portalgwt/authentication.jsp";
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("op", "verificarSessao");//op=autenticar
                    access.accessJSonMap(url, param);
                    schedule(time);
                }
            };
            tm.schedule(time);
        }
    }

    /**
     * @return the listPer
     */
    public static ListStore<Per_perfilTGWT> getListPer() {
        return listPer;
    }

    /**
     * @param listPer the listPer to set
     */
    public void setListPer(ListStore<Per_perfilTGWT> listPer) {
        this.listPer = listPer;
    }
}
