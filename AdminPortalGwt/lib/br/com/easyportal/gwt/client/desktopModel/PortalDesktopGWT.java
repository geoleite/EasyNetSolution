/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.desktopModel;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easyportal.gwt.client.Constantes;
import br.com.easyportal.gwt.client.IPrincipalPortal;
import br.com.easyportal.gwt.client.MenuGWT;
import br.com.easyportal.gwt.client.SairIListenerUrl;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Mod_moduloDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Res_recall_senhaDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.res_recall_senha.Res_recall_senhaInsertGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.res_recall_senha.Res_recall_senhaUpdateDeleteGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Mod_moduloTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Per_perfilTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Res_recall_senhaTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.desktop.client.Shortcut;
import com.extjs.gxt.desktop.client.StartMenu;
import com.extjs.gxt.desktop.client.TaskBar;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author geoleite
 */
public class PortalDesktopGWT implements IPrincipalPortal {
    protected Res_recall_senhaDAOGWT res_recall_senhaDAOGWT = new Res_recall_senhaDAOGWT();
    /**
     * @return the usuarioLogado
     */
    public static Usu_usuarioTGWT getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * @param aUsuarioLogado the usuarioLogado to set
     */
    public static void setUsuarioLogado(Usu_usuarioTGWT aUsuarioLogado) {
        usuarioLogado = aUsuarioLogado;
    }

    private EasyLabels easyLabels = GWT.create(EasyLabels.class);
    private Desktop desktop;// = new Desktop();
    private MenuGWT menu;
    private static ListStore<Per_perfilTGWT> listPer;
    private Mod_moduloDAOGWT mod_moduloDAOGWT = new Mod_moduloDAOGWT();
    private Map<String, AbstractImagePrototype> icons = new TreeMap<String, AbstractImagePrototype>();
    private String login = "";
    private String pass = "";
    private String nomeSistema;
    private static Usu_usuarioTGWT usuarioLogado;

    public void insertUpdateRecallSenha() {
        if (getUsuarioLogado() != null) {
            Res_recall_senhaTGWT resT = new Res_recall_senhaTGWT();
            resT.setUsu_nr_id(getUsuarioLogado().getUsu_nr_id());
            res_recall_senhaDAOGWT.findByPK(resT);
            Timer timer = new Timer() {

                @Override
                public void run() {
                    if (!res_recall_senhaDAOGWT.isFinalized()) {
                        schedule(500);
                    } else {
                        Res_recall_senhaTGWT resT = res_recall_senhaDAOGWT.getRes_recall_senhaT();
                        if (resT.getRes_tx_pergunta() == null || resT.getRes_tx_pergunta().trim().length() == 0) {
                            Res_recall_senhaInsertGWT resInsert = new Res_recall_senhaInsertGWT();
                            resInsert.setVisible(true);
                        } else {
                            Res_recall_senhaUpdateDeleteGWT resUpdateDeleteGWT = new Res_recall_senhaUpdateDeleteGWT();
                            resUpdateDeleteGWT.load(resT);
                            resUpdateDeleteGWT.setVisible(true);
                        }
                    }
                }
            };
            timer.schedule(1000);
        }
    }
    
    public void checaRecallSenha() {
        if (getUsuarioLogado() != null) {
            Res_recall_senhaTGWT resT = new Res_recall_senhaTGWT();
            resT.setUsu_nr_id(getUsuarioLogado().getUsu_nr_id());
            res_recall_senhaDAOGWT.findByPK(resT);
            Timer timer = new Timer() {

                @Override
                public void run() {
                    if (!res_recall_senhaDAOGWT.isFinalized()) {
                        schedule(500);
                    } else {
                        Res_recall_senhaTGWT resT = res_recall_senhaDAOGWT.getRes_recall_senhaT();
                        if (resT.getRes_tx_pergunta() == null || resT.getRes_tx_pergunta().trim().length() == 0) {
                            Res_recall_senhaInsertGWT resInsert = new Res_recall_senhaInsertGWT();
                            resInsert.setVisible(true);
                        }
                    }
                }
            };
            timer.schedule(1000);
        }
    }
    /**
     * Carrega os módulos do Sistema
     */
    public void loadModulos() {
        try {
            //Recurso para carregar os módulos de acesso do usuário
            mod_moduloDAOGWT.getByPerfil();
            Timer timer = new Timer() {

                @Override
                public void run() {
                    if (!mod_moduloDAOGWT.isFinalized()) {
                        schedule(500);
                    } else {

                        ListStore<Mod_moduloTGWT> list = mod_moduloDAOGWT.getList();
                        if (list != null && list.getCount() > 0) {
                            SelectionListener<ComponentEvent> shortcutListener = new SelectionListener<ComponentEvent>() {

                                @Override
                                public void componentSelected(ComponentEvent ce) {
                                    itemSelected(ce);
                                }
                            };
                            DebugMessage.message(this.getClass().getName(), "Modulos " + list.getCount());
                            for (int i = 0; i < list.getCount(); i++) {
                                Mod_moduloTGWT modT = list.getAt(i);
                                Window win = new Window();
                                win.setHeading("" + modT.getMod_tx_nome());
                                win.setSize(400, 400);
                                win.setMinimizable(true);
                                win.setMaximizable(true);
                                String acao = modT.getMod_tx_acao();
                                if (acao != null && acao.indexOf("http") == 0) {
                                    if ("S".equalsIgnoreCase(modT.getMod_tx_autologin())) {
                                        //acao = modT.getMod_tx_urllogin();
                                        String urlAutenticacao = modT.getMod_tx_urllogin();
                                        urlAutenticacao += "&singlesignon=true";
                                        urlAutenticacao = urlAutenticacao.replaceAll(";LOGIN;", login);
                                        urlAutenticacao = urlAutenticacao.replaceAll(";PASSWORD;", pass);
                                        //autenticationBackground(urlAutenticacao, win, acao);
                                        
                                        win.setUrl(urlAutenticacao);
                                    } else {
                                        DebugMessage.message(this.getClass().getName(), "Acao: " + acao);
                                        win.setUrl(acao);
                                    }
                                    
                                }
                                Shortcut s1 = new Shortcut();
                                s1.setText(modT.getMod_tx_nome());
                                s1.setId("grid-win-shortcut");
                                s1.setData("modules", win);
                                if (icons != null) {
                                    AbstractImagePrototype icon = icons.get(modT.getMod_tx_icone());
                                    if (icon != null) {
                                        s1.setIcon(icon);
                                    }
                                }
                                s1.addSelectionListener(shortcutListener);
                                desktop.addShortcut(s1);
                            }
                            //desktop.
                        }
                    }
                }
            };
            timer.schedule(500);
        } catch (Exception e) {
            DebugMessage.message(DebugMessage.ERROR, this.getClass().getName(), e.getMessage());
        }
    }

    /**
     * Realiazando autenticação em outro sistema
     * @param url
     */
    private void autenticationBackground(String url, final Window win, final String acao) {
        IListenetResponse listener = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    JSONObject resultado = jsonObject.get("resultado").isObject();
                    String valor = resultado.get("autenticacao").isString().stringValue();
                    DebugMessage.message(this.getClass().getName(), "Single sign on " + valor);
                    if ("true".equalsIgnoreCase(valor)) {
                       win.setUrl(acao);
                    }
                }
            }
        };
        EasyAccessURL access = new EasyAccessURL(listener);
        access.accessJSon(url);
    }

    public void addIcon(String nameIcon, AbstractImagePrototype icon) {
        icons.put(nameIcon, icon);
    }

    public int sizeIcons() {
        return icons.size();
    }

    public AbstractImagePrototype getIcon(String nameIcon) {
        return icons.get(nameIcon);
    }

    private void itemSelected(ComponentEvent ce) {
        Window w;
        if (ce instanceof MenuEvent) {
            MenuEvent me = (MenuEvent) ce;
            w = me.getItem().getData("modules");
        } else {
            w = ce.getComponent().getData("modules");
        }
        if (!desktop.getWindows().contains(w)) {
            desktop.addWindow(w);
        }
        if (w != null && !w.isVisible()) {
            w.setVisible(true);
        } else {
            w.toFront();
        }
        w.maximize();
    }

    public void gerarMenu(Desktop desktopTemp) {
        desktop = desktopTemp;
        SelectionListener<ComponentEvent> listener = new SelectionListener<ComponentEvent>() {

            @Override
            public void componentSelected(ComponentEvent ce) {
                Window w = null;
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
        TaskBar taskBar = desktop.getTaskBar();
        StartMenu menu = taskBar.getStartMenu();

        menu.setHeading(easyLabels.easyPortalSystem());
        menu.setIconStyle("user");

    }

    private void exiting() {
        try {
            DebugMessage.message(this.getClass().getName(), "Saindo do Sistema");
            EasyAccessURL eaurl = new EasyAccessURL(new SairIListenerUrl());
            String url = Constantes.URL + "portalgwt/principalpage.jsp?op=sair&gwt=true";
            eaurl.accessJSon(url);
        } catch (Exception ex) {
        }
    }

    public native static void execute(String command)/*-{
    eval(command);
    }-*/;

    public void read(JSONValue jsonValue) {
    }

    public void readString(String dados) {
        Info.display("Event", "Inscricao efetuada com sucesso!");
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

    /**
     * @return the icons
     */
    public Map<String, AbstractImagePrototype> getIcons() {
        return icons;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the nomeSistema
     */
    public String getNomeSistema() {
        return nomeSistema;
    }

    /**
     * @param nomeSistema the nomeSistema to set
     */
    public void setNomeSistema(String nomeSistema) {
        this.nomeSistema = nomeSistema;
    }
}
