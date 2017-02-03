/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.accordionModel;

import br.com.easynet.gwt.client.BaseGWT;
import br.com.easynet.gwt.client.CPBaseGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easyportal.gwt.client.AlterarSenhaGWT;
import br.com.easyportal.gwt.client.Constantes;
import br.com.easyportal.gwt.client.IPrincipalPortal;
import br.com.easyportal.gwt.client.PortalBase;
import br.com.easyportal.gwt.client.SairIListenerUrl;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Res_recall_senhaDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.res_recall_senha.Res_recall_senhaInsertGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Men_menuTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Per_perfilTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Res_recall_senhaTGWT;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import br.com.easyportal.gwt.client.rpc.EasyPortalRPCFactory;
import br.com.easyportal.gwt.client.rpc.EasyPortalServiceAsync;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.TreePanelEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.Field.FieldImages;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class PortalAccordionGWT extends PortalBase implements IPrincipalPortal {

    protected String logedUser = "";
    protected EasyLabels easyLabels = GWT.create(EasyLabels.class);
    protected ToolBar toolBar = new ToolBar();
    protected Button btnUsuarioLogado = new Button("Usuario Logado");
    protected Button btnPerfil = new Button("Perfis do Usuario");
    protected LabelField labelFieldVersion = new LabelField();
    protected FillToolItem fillToolItem = new FillToolItem();
    protected MenuItem miAlterarSenha = new MenuItem(easyLabels.alterPass());
    protected MenuItem miDefinirRecallSenha = new MenuItem(easyLabels.secretAnswer());
    protected MenuItem miSair = new MenuItem(easyLabels.exit());
    protected List<AMenuHandlerAccordion> menuHandler = new ArrayList<AMenuHandlerAccordion>();
    protected MenuPortalAccordionGWT menuPortalAccordionGWT = new MenuPortalAccordionGWT();
    protected TabPanel tabPanel = new TabPanel();
    protected TabItem tiWelcome = new TabItem(easyLabels.welcome());
    private static ListStore<Per_perfilTGWT> listPer;
    protected String obsText = "";
    protected String urlImageHeader;
    protected int alturaBanner;
    protected ContentPanel cpHeader = new ContentPanel();

    public PortalAccordionGWT() {

//        remove(getCpBotton());
//        remove(getCpTop());
//        remove(getCpRight());
        getCpBotton().setVisible(false);
        getCpTop().setVisible(false);
        getCpRight().setVisible(false);

        getDataWEST().setSize(200);
        getCpLeft().setLayout(new AccordionLayout());
        getCpLeft().setHeading(easyLabels.menu());
        Window.setMargin("0");
        //Window.setTitle(easyLabels.easyPortalSystem());
        setHeaderVisible(false);
        //setWidth("100%");
        //setBodyStyle("heigth: 100%");// height:100%;

        //setHeight(Window.getClientHeight() - alturaBanner);
        //setTopComponent(toolBar);
        setScrollMode(Scroll.AUTO);
        tabPanel.add(tiWelcome);
        getCpMaster().setLayout(new FitLayout());
        getCpMaster().add(tabPanel);

        tabPanel.setTabScroll(true);
        tabPanel.setAnimScroll(true);

    }

    public PortalAccordionGWT(String urlImageHeader, int alturaBanner) {
        this();
        Image img = new Image(urlImageHeader);
        cpHeader.setHeaderVisible(false);
        cpHeader.setFrame(false);
        cpHeader.setBodyBorder(false);
        cpHeader.setBorders(false);
        cpHeader.setLayout(new FitLayout());
        ContentPanel cpImg = new ContentPanel(new RowLayout(Orientation.VERTICAL));
        cpImg.setHeaderVisible(false);
        cpImg.setBorders(false);
        cpImg.setBodyBorder(false);
        cpImg.setFrame(false);
        cpImg.add(img);
        cpImg.setHeight(alturaBanner);
        //cpHeader.setTopComponent(cpImg);
        cpHeader.add(cpImg);
        cpHeader.setBottomComponent(toolBar);
        //cpHeader.setHeight(57);
        setTopComponent(cpHeader);

//        getCpTop().setVisible(false);
//        getCpTop().add(img);
//        getCpTop().setHeaderVisible(false);
//        getCpTop().setFrame(false);
//        getCpTop().setBodyBorder(false);
//
//        getDataNORTH().setSize(alturaBanner);
//        getDataNORTH().setMinSize(alturaBanner);
//        getDataNORTH().setMaxSize(alturaBanner);
//
        setHeaderVisible(false);
        setFrame(false);
        setBodyBorder(false);
    }

    public PortalAccordionGWT(int alturaBanner) {
        this();
        setWidth("100%");
        setHeight(Window.getClientHeight() - alturaBanner);
    }

    public String toString() {
        return "Portal OK";
    }

    public void addMenuHandler(AMenuHandlerAccordion menuHandlerAccordion) {
        menuHandler.add(menuHandlerAccordion);
        menuHandlerAccordion.setPortalAccordionGWT(this);
    }

    public void createMenu() {
        getMenuPortalAccordionGWT().setPortalAccordionGWT(this);
        getMenuPortalAccordionGWT().setMenuHandler(menuHandler);
        createToolBar();
        createMenuOptions();

    }

    /**
     * Monta o menu fixo e padrão
     *
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
        //miAlterarSenha.setIcon(ICONS.chave());
        miSair.setIcon(ICONS.sair());
        miAlterarSenha.addSelectionListener(eventMenu);
        miDefinirRecallSenha.addSelectionListener(eventMenu);
        miSair.addSelectionListener(eventMenu);
        btnUsuarioLogado.setMenu(subMenu);
        btnUsuarioLogado.setText(getLogedUser());

        getToolBar().add(btnUsuarioLogado);
        if (listPer != null) {
            //Button btnPerfil = new Button("Perfis do Usuario");
            getBtnPerfil().setMenu(getMenuPerfis());
            getToolBar().add(getBtnPerfil());
        }
        getToolBar().add(fillToolItem);
        getToolBar().add(getLabelFieldVersion());
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

    protected void createMenuOptions() {
        DebugMessage.message(this.getClass().getName(), "Montando menu");
        AsyncCallback<List<Men_menuTGWT>> callback = new AsyncCallback<List<Men_menuTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                String errorMsg = "";
                if (caught != null && caught.getCause() != null) {
                    errorMsg = caught.getCause().getMessage();
                } else {
                    errorMsg = caught.getMessage();
                }
                MessageBox.alert(easyLabels.warning(), errorMsg, null);
            }

            @Override
            public void onSuccess(List<Men_menuTGWT> result) {
                DebugMessage.message(this.getClass().getName(), "Resultado recebido " + result.size());
                Listener<TreePanelEvent<ItemData>> listenerTreePanel = new Listener<TreePanelEvent<ItemData>>() {

                    public void handleEvent(TreePanelEvent<ItemData> be) {
                        AMenuHandlerAccordion menuHandler = (AMenuHandlerAccordion) be.getItem().getMenuHandler();// ce.getButton().getData(AMenuHandler.MENU_HANDLER);
                        menuSelecionado(be, menuHandler, be.getItem().getAction(), be.getItem().getPage());
                    }
                };

                List<ContentPanel> listMenu = new ArrayList<ContentPanel>();
                for (int i = 0; i < result.size(); i++) {
                    Men_menuTGWT menT = result.get(i);
                    ContentPanel cpItem = new ContentPanel();
                    cpItem.setHeading(menT.getMen_tx_nome());
                    cpItem.setIcon(getIcone(menT.getMen_tx_icone()));
                    listMenu.add(cpItem);
                    montaSubMenu(cpItem, listenerTreePanel, menT.getSubMenu());
                }
                DebugMessage.message(this.getClass().getName(), "Menu construido");
                for (ContentPanel contentPanel : listMenu) {
                    //getToolBar().add(button);
                    getCpLeft().add(contentPanel);
                    DebugMessage.message(this.getClass().getName(), "Menu Panel " + contentPanel.getHeading());

                }
                DebugMessage.message(this.getClass().getName(), "Menu Montado ");
                getCpLeft().layout();
            }
        };
        DebugMessage.message(this.getClass().getName(), "Consultando na base menu");
        EasyPortalServiceAsync epsAsync = EasyPortalRPCFactory.getEasyPortalService();
        epsAsync.getUserMenu(getNomeSistema(), callback);
    }

    private boolean montaSubMenu(ContentPanel cp, Listener<TreePanelEvent<ItemData>> listener, List<Men_menuTGWT> subMenu) {

        if (subMenu != null && subMenu.size() > 0) {
            TreeStore<ItemData> treeStore = new TreeStore<ItemData>();
            for (int j = 0; j < subMenu.size(); j++) {
                Men_menuTGWT menT = subMenu.get(j);
                String subnome = menT.getMen_tx_nome();
                String subacao = menT.getMen_tx_acao();
                String subicone = menT.getMen_tx_icone();
                String sistema = menT.getSistema();
                String pagina = menT.getMen_tx_url();
                pagina = pagina.replace('"', ' ').trim();
                ItemData itemData = new ItemData();
                itemData.setName(subnome);
                itemData.setAction(subacao);
                itemData.setIcon(subicone);
                itemData.setSystem(sistema);
                itemData.setPage(pagina);
                itemData.setMenuHandler(getMenuHandler(subacao));
                treeStore.add(itemData, false);
            }
            if (subMenu.size() >= 1) {
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

    private AMenuHandlerAccordion getMenuHandler(String item) {
        AMenuHandlerAccordion handler = null;
        //Descobre o tratador de eventos dinamicamente a partir da lista de
        //tratadores de evento do menu

        for (AMenuHandlerAccordion aHandler : getMenuHandler()) {
            if (item != null && item.toUpperCase().indexOf(aHandler.getSystem()) > -1) {
                aHandler.setPortalAccordionGWT(this);
                handler = aHandler;
                break;
            }
        }

        return handler;
    }

    private void menuSelecionado(Object event, AMenuHandlerAccordion menuHandler, String acao, String page) {
        if (page == null || page.trim().length() == 0) {
            menuHandler.actionEventMenu(event, acao);
        } else {
            menuHandler.actionEventMenu(event, acao, page);
        }
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
     * Constroi no toolbar as opcoes do menu dinamicamente
     */
    private void createMenuOptionsOld() {
//aki menu
        if (getNomeSistema() != null && getNomeSistema().trim().length() > 0) {
            getMenuPortalAccordionGWT().consultMenu(getNomeSistema());
        } else {
            getMenuPortalAccordionGWT().consultMenu();
        }
        Timer timer = new Timer() {

            @Override
            public void run() {
                List<ContentPanel> listMenu = getMenuPortalAccordionGWT().getList();
                if (listMenu == null) {
                    schedule(500);
                } else {
                    for (ContentPanel contentPanel : listMenu) {
                        //getToolBar().add(button);
                        getCpLeft().add(contentPanel);
                    }
                    getCpLeft().layout();
                }
            }
        };
        timer.schedule(500);
    }

    public void exiting() {
        final MessageBox mb = MessageBox.wait("Atencao", "Registrando Saida do Sistema!", null);
        
        EasyPortalServiceAsync epsAsync = EasyPortalRPCFactory.getEasyPortalService();
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                if (mb != null && mb.isVisible()) {
                    mb.close();
                }
                com.google.gwt.user.client.Window.Location.reload();
            }

            @Override
            public void onSuccess(Void result) {
                if (mb != null && mb.isVisible()) {
                    mb.close();
                }
                com.google.gwt.user.client.Window.Location.reload();
            }
        };
        epsAsync.getOut(callback);
/*        
        SairIListenerUrl sairListener = new SairIListenerUrl();
        final MessageBox mb = MessageBox.wait("Atencao", "Registrando Saida do Sistema!", null);
        sairListener.setMb(mb);
        EasyAccessURL eaurl = new EasyAccessURL(sairListener);
        String url = Constantes.URL + "portalgwt/principalpage.jsp?op=sair&gwt=true";
        eaurl.accessJSon(url);
        Timer timer = new Timer() {

            @Override
            public void run() {
                if (mb != null && mb.isVisible()) {
                    mb.close();
                }
            }
        };
        timer.schedule(3000);
        */
    }

    private void updatePas() {
        AlterarSenhaGWT altSenhaGWT = new AlterarSenhaGWT();
        altSenhaGWT.setVisible(true);
    }

    /**
     * Trata os eventos do menu
     *
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
    }

    /**
     * @return the easyLabels
     */
    public EasyLabels getEasyLabels() {
        return easyLabels;
    }

    /**
     * @param easyLabels the easyLabels to set
     */
    public void setEasyLabels(EasyLabels easyLabels) {
        this.easyLabels = easyLabels;
    }

    /**
     * @return the toolBar
     */
    public ToolBar getToolBar() {
        return toolBar;
    }

    /**
     * @param toolBar the toolBar to set
     */
    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
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
                }
            };
            tm.scheduleRepeating(time);
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

    /**
     * @return the obsText
     */
    public String getObsText() {
        return obsText;
    }

    /**
     * @param obsText the obsText to set
     */
    public void setObsText(String obsText) {
        this.obsText = obsText;
        getLabelFieldVersion().setText(obsText);
    }

    /**
     * @return the urlImageHeader
     */
    public String getUrlImageHeader() {
        return urlImageHeader;
    }

    /**
     * @param urlImageHeader the urlImageHeader to set
     */
    public void setUrlImageHeader(String urlImageHeader) {
        this.urlImageHeader = urlImageHeader;
    }

    /**
     * @return the alturaBanner
     */
    public int getAlturaBanner() {
        return alturaBanner;
    }

    /**
     * @param alturaBanner the alturaBanner to set
     */
    public void setAlturaBanner(int alturaBanner) {
        this.alturaBanner = alturaBanner;
    }

    /**
     * @return the btnPerfil
     */
    public Button getBtnPerfil() {
        return btnPerfil;
    }

    /**
     * @param btnPerfil the btnPerfil to set
     */
    public void setBtnPerfil(Button btnPerfil) {
        this.btnPerfil = btnPerfil;
    }

    /**
     * @return the labelFieldVersion
     */
    public LabelField getLabelFieldVersion() {
        return labelFieldVersion;
    }

    /**
     * @param labelFieldVersion the labelFieldVersion to set
     */
    public void setLabelFieldVersion(LabelField labelFieldVersion) {
        this.labelFieldVersion = labelFieldVersion;
    }

    /**
     * @return the fillToolItem
     */
    public FillToolItem getFillToolItem() {
        return fillToolItem;
    }

    /**
     * @param fillToolItem the fillToolItem to set
     */
    public void setFillToolItem(FillToolItem fillToolItem) {
        this.fillToolItem = fillToolItem;
    }

    /**
     * @return the menuPortalAccordionGWT
     */
    public MenuPortalAccordionGWT getMenuPortalAccordionGWT() {
        return menuPortalAccordionGWT;
    }

    /**
     * @param menuPortalAccordionGWT the menuPortalAccordionGWT to set
     */
    public void setMenuPortalAccordionGWT(MenuPortalAccordionGWT menuPortalAccordionGWT) {
        this.menuPortalAccordionGWT = menuPortalAccordionGWT;
    }
}
