/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.CPBaseGWT;

import br.com.easyportal.gwt.client.desktopModel.AMenuDesktop;
import br.com.easyportal.gwt.client.desktopModel.PortalDesktopGWT;
import br.com.easyportal.gwt.client.desktopModel.MenuDesktopGWT;
import br.com.easyportal.gwt.client.webModel.AMenuHandler;
import br.com.easyportal.gwt.client.webModel.PortalWebGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easynet.gwt.client.icons.ExampleIcons;
import br.com.easyportal.gwt.client.accordionModel.AMenuHandlerAccordion;
import br.com.easyportal.gwt.client.accordionModel.PortalAccordionGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Mod_moduloDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Per_perfilDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Mod_moduloTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Per_perfilTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.desktop.client.StartMenu;
import com.extjs.gxt.desktop.client.TaskBar;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class CPAuthenticationGWT extends CPBaseGWT implements IListenetResponse {

    public EasyLabels easyLabels = GWT.create(EasyLabels.class);
    private boolean modoAuthenticatonDigital = false;
    public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);
    private TextField<String> txtUserName = new TextField<String>();
    private LabelField lblUserName = new LabelField(easyLabels.user() + ":");
    private LabelField lblPassword = new LabelField(easyLabels.pass() + ":");
    private TextField<String> ptbPassword = new TextField<String>();
    private Button btnEntrar = new Button(easyLabels.enter());
    private MessageBox mbProgress;
    private static IPrincipalPortal portal;
    private Per_perfilDAOGWT perDao = new Per_perfilDAOGWT();
    private boolean authenticationOK = false;
    private String usuario = "";
    private String trocarSenha = "N";
    private String nomeSistema;
    private LabelField forgotPassword = new LabelField("<a href=\"#\">" + easyLabels.forgotPassword() + "</a>");
    private MenuDesktopGWT menuDesktop = new MenuDesktopGWT();
    public static String GWT_LOGIN = "gwtlogin";
    private Usu_usuarioTGWT usuT = new Usu_usuarioTGWT();
    private Timer timerOutAutentication = new Timer() {

        @Override
        public void run() {
            if (!authenticationOK) {
                Window.Location.reload();
            }
        }
    };

    public CPAuthenticationGWT() {
        setIcon(ICONS.cadeado());
        setHeading(easyLabels.authentication());
        setBodyBorder(false);
        setBorders(false);

        getCpTop().setVisible(false);
        getCpLeft().setVisible(false);
        getCpRight().setVisible(false);
        getCpBotton().setVisible(false);

        ptbPassword.setPassword(true);
        TableLayout tl = new TableLayout(2);
        tl.setCellSpacing(6);
        getCpMaster().setLayout(tl);

        getCpMaster().setLayout(tl);
        getCpMaster().setFrame(false);
        getCpMaster().add(lblUserName);
        getCpMaster().add(txtUserName);
        getCpMaster().add(lblPassword);
        getCpMaster().add(ptbPassword);
        getCpMaster().addButton(btnEntrar);

        getDataEAST().setSplit(false);
        getDataCENTER().setSplit(false);
        getDataWEST().setSplit(false);
        getDataNORTH().setSplit(false);
        getDataSOUTH().setSplit(false);

        setBottomComponent(forgotPassword);

        setSize(250, 160);

        forgotPassword.addListener(Events.OnClick, new Listener<BaseEvent>() {

            public void handleEvent(BaseEvent be) {
                forgotPasswordAction();
            }
        });

        btnEntrar.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                logar();
            }
        });

        CPKeyEvento eventoKey = new CPKeyEvento();
        eventoKey.setCPAuthenticationGWT(this);
        txtUserName.addKeyListener(eventoKey);
        ptbPassword.addKeyListener(eventoKey);

        txtUserName.focus();
    }

    /**
     * Implementando o auto login
     * @param login
     * @param senha
     */
    public void autoLogin(String login, String senha) {
        txtUserName.setValue(login);
        ptbPassword.setValue(senha);
        logar();
    }

    public void logar() {
        if (getTxtUserName().getValue().trim().length() == 0 || getPtbPassword().getValue().trim().length() == 0) {
            MessageBox.alert("", easyLabels.rulesAutentication(), null);
            return;
        }
        modoAuthenticatonDigital = false;
        authenticationOK = false;
        //timerOutAutentication.schedule(10000);
        EasyAccessURL access = new EasyAccessURL(this);
        String url = Constantes.URL + "portalgwt/authentication.jsp";
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "autenticar");//op=autenticar
        param.put("usu_usuarioT.usu_tx_login", getTxtUserName().getValue());
        param.put("usu_usuarioT.usu_tx_senha", getPtbPassword().getValue());
        //mbProgress = MessageBox.wait(easyLabels.waitRequest(), easyLabels.authenticating(), easyLabels.process());
        access.accessJSonMap(url, param);
    }

    public void checkAuthentication() {
        IListenetResponse response = new IListenetResponse() {

            public void read(JSONValue jsonValue) {
                JSONObject jsonObject;
                if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                    authenticationOK = true;
                    JSONObject resultado = jsonObject.get("resultado").isObject();
                    String valor = resultado.get("autenticacao").isString().stringValue();
                    //valor = valor.replace('"', ' ').trim();
                    usuario = resultado.get("usuario").isString().stringValue();
                    trocarSenha = resultado.get("trocarsenha").isString().stringValue();
                    try {
                        String codigo = resultado.get("codigo").isString().stringValue();
                        String login = resultado.get("login").isString().stringValue();
                        String status = resultado.get("status").isString().stringValue();
                        String email = resultado.get("email").isString().stringValue();
                        DebugMessage.message(this.getClass().getName(), "Checing Dados do usuario " + codigo + " " + login + " " + status + " " + email);
                        usuT.setUsu_nr_id(Integer.parseInt(codigo));
                        usuT.setUsu_tx_login(login);
                        usuT.setUsu_tx_status(status);
                        usuT.setUsu_tx_email(email);
                    } catch (Exception e) {
                        DebugMessage.message(DebugMessage.ERROR, this.getClass().getName(), "Erro ao ler dados do usuário logado.");
                    }
                    usuT.setUsu_tx_nome(usuario);                    //usuario = usuario.replace('"', ' ').trim();
                    if ("true".equals(valor)) {
                        //AuthenticationGWT.this.setVisible(false);

                        if ("S".equalsIgnoreCase(trocarSenha)) {
                            AlterarSenhaGWT alterarSenhaGWT = new AlterarSenhaGWT() {

                                public void read(JSONValue jsonValue) {
                                    //mbProgress.close();
                                    JSONObject jsonObject;
                                    if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                                        JSONObject resultado = jsonObject.get("resultado").isObject();
                                        String valor = resultado.get("mensagem").toString();
                                        valor = valor.replace('"', ' ').trim();
                                        String result = resultado.get("result").toString();
                                        result = result.replace('"', ' ').trim();
                                        if ("true".equals(result)) {
                                            //Operacao executadao com sucesso.
                                            Info.display(easyLabels.operationResult(), valor);
                                            setVisible(false);
                                            //Consultando Perfil do usuario após troca de senha
                                            consultarPerfil();
                                        } else {
                                            MessageBox mb = new MessageBox();
                                            mb.alert(easyLabels.operationFailure(), valor, null);
                                        }
                                    }
                                }
                            };
                            alterarSenhaGWT.getBtnClose().setVisible(false);
                            alterarSenhaGWT.setVisible(true);
                        } else {
                            //Consultando Perfil do usuario
                            consultarPerfil();
                        }
                    }
                }
            }
        };
        EasyAccessURL access = new EasyAccessURL(response);
        String url = Constantes.URL + "portalgwt/authentication.jsp";
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "verificarSessao");//op=autenticar
        //mbProgress = MessageBox.wait(easyLabels.waitRequest(), easyLabels.authenticating(), easyLabels.process());
        access.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {

        //mbProgress.close();
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            authenticationOK = true;
            JSONObject resultado = jsonObject.get("resultado").isObject();
            String valor = resultado.get("autenticacao").isString().stringValue();
            //valor = valor.replace('"', ' ').trim();
            usuario = resultado.get("usuario").isString().stringValue();
            trocarSenha = resultado.get("trocarsenha").isString().stringValue();
            try {
                String codigo = resultado.get("codigo").isString().stringValue();
                String login = resultado.get("login").isString().stringValue();
                String status = resultado.get("status").isString().stringValue();
                String email = resultado.get("email").isString().stringValue();
                DebugMessage.message(this.getClass().getName(), "Dados do usuario " + codigo + " " + login + " " + status + " " + email);
                usuT.setUsu_nr_id(Integer.parseInt(codigo));
                usuT.setUsu_tx_login(login);
                usuT.setUsu_tx_status(status);
                usuT.setUsu_tx_email(email);
            } catch (Exception e) {
                DebugMessage.message(DebugMessage.ERROR, this.getClass().getName(), "Erro ao ler dados do usuário logado.");
            }
            usuT.setUsu_tx_nome(usuario);             //usuario = usuario.replace('"', ' ').trim();
            if ("true".equals(valor)) {
                //this.setVisible(false);
                //RootPanel.detachNow(this);
                RootPanel.get(GWT_LOGIN).remove(this);
                if ("S".equalsIgnoreCase(trocarSenha)) {
                    AlterarSenhaGWT alterarSenhaGWT = new AlterarSenhaGWT() {

                        public void read(JSONValue jsonValue) {
                            //mbProgress.close();
                            JSONObject jsonObject;
                            if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                                JSONObject resultado = jsonObject.get("resultado").isObject();
                                String valor = resultado.get("mensagem").toString();
                                valor = valor.replace('"', ' ').trim();
                                String result = resultado.get("result").toString();
                                result = result.replace('"', ' ').trim();
                                if ("true".equals(result)) {
                                    //Operacao executadao com sucesso.
                                    Info.display(easyLabels.operationResult(), valor);
                                    setVisible(false);
                                    //Consultando Perfil do usuario após troca de senha
                                    consultarPerfil();
                                } else {
                                    MessageBox mb = new MessageBox();
                                    mb.alert(easyLabels.operationFailure(), valor, null);
                                }
                            }
                        }
                    };
                    alterarSenhaGWT.getBtnClose().setVisible(false);
                    alterarSenhaGWT.setVisible(true);
                } else {
                    //Consultando Perfil do usuario
                    DebugMessage.message(this.getClass().getName(), "Consultando Perfil");
                    consultarPerfil();
                }


            } else {
                if (modoAuthenticatonDigital) {
                    MessageBox mb = new MessageBox();
                    String valorMsg = easyLabels.certificationsNotInformed();
                    mb.alert(easyLabels.problem(), valorMsg, null);
                } else {
                    MessageBox mb = new MessageBox();
                    String valorMsg = easyLabels.loginFault();
                    mb.alert(easyLabels.problem(), valorMsg, new Listener<MessageBoxEvent>() {

                        public void handleEvent(MessageBoxEvent be) {
                            setVisible(true);
                        }
                    });

                }
            }
        }
        mbProgress.close();
    }

    private void consultarPerfil() {

        perDao.perfisUsuarioLogado();
        Timer timer = new Timer() {

            @Override
            public void run() {
                ListStore<Per_perfilTGWT> list = perDao.getList();
                if (list == null) {
                    schedule(500);
                } else {
                    //Versao Desktop
                    if (portal instanceof PortalDesktopGWT) {
                        DebugMessage.message(this.getClass().getName(), "Rodando Portal Desktop");
                        if (menuDesktop == null) {
                            MessageBox.alert("ATENCAO", "NAO FOI DEFINIDO A O MENUDESKTOP.", null);
                            return;
                        }
                        //MenuDesktopGWT menu = new MenuDesktopGWT();
                        List<AMenuDesktop> listMenuDesktop = new ArrayList<AMenuDesktop>();
                        menuDesktop.setListMenuDesktop(listMenuDesktop);
                        PortalDesktopGWT pdGWT = (PortalDesktopGWT) portal;
                        pdGWT.setNomeSistema(nomeSistema);
                        pdGWT.setLogin(txtUserName.getValue());
                        pdGWT.setPass(ptbPassword.getValue());
                        pdGWT.setListPer(list);
                        TaskBar taskBar = pdGWT.getDesktop().getTaskBar();
                        DebugMessage.message(this.getClass().getName(), "Motando Modulos");
                        StartMenu startMenuDesktop = taskBar.getStartMenu();
                        //Carrega os módulos de acesso do usuario
                        pdGWT.loadModulos();
                        DebugMessage.message(this.getClass().getName(), "Motando menu Desktop " + usuario);
                        startMenuDesktop.setHeading(usuario);
                        menuDesktop.setDesktop(pdGWT.getDesktop());
                        if (nomeSistema != null && nomeSistema.trim().length() > 0) {
                            //Carrega o menu por sistema
                            menuDesktop.loadMenu(nomeSistema);
                        } else {
                            //Carrega o menu independente do sistema
                            menuDesktop.loadMenu();
                        }
                        pdGWT.checaRecallSenha();
                    } else if (portal instanceof PortalWebGWT) {
                        //Versao WEB
                        DebugMessage.message(this.getClass().getName(), "Rodando Portal Web");
                        PortalWebGWT pwGWT = (PortalWebGWT) portal;
                        pwGWT.setNomeSistema(nomeSistema);
                        pwGWT.setLogedUser(usuario);
                        pwGWT.setListPer(list);
                        pwGWT.createMenu();
                        //adiciona na tela o portal modelo web

                        Viewport viewport = new Viewport();
                        viewport.setLayout(new RowLayout());
                        viewport.add(pwGWT, new RowData(1, 1));
                        RootPanel.get("gwt").add(viewport);
                        pwGWT.checaRecallSenha();
                    } else if (portal instanceof PortalAccordionGWT) {
                        //Versao Accordion
                        DebugMessage.message(this.getClass().getName(), "Rodando Portal Accordion");
                        PortalAccordionGWT paGWT = (PortalAccordionGWT) portal;
                        paGWT.setNomeSistema(nomeSistema);
                        paGWT.setLogedUser(usuario);
                        paGWT.setListPer(list);
                        paGWT.createMenu();
                        //adiciona na tela o portal modelo web

                        Viewport viewport = new Viewport();
                        viewport.setLayout(new RowLayout());
                        viewport.add(paGWT, new RowData(1, 1));

                        RootPanel.get("gwt").add(viewport);
                        paGWT.checaRecallSenha();
                    }
                }
            }
        };
        timer.schedule(500);
    }

    public void forgotPasswordAction() {
        //MessageBox.alert("ATENCAO", "Ainda nao implementado!", null);
        GerarNovaSenha gerarNovaSenha = new GerarNovaSenha();
        gerarNovaSenha.setVisible(true);
    }

    /**
     * @return the portal
     */
    public static IPrincipalPortal getPortal() {
        return portal;
    }

    /**
     * @param aPortal the portal to set
     */
    public static void setPortal(IPrincipalPortal aPortal) {
        portal = aPortal;
    }

    /**
     * @return the modoAuthenticatonDigital
     */
    public boolean isModoAuthenticatonDigital() {
        return modoAuthenticatonDigital;
    }

    /**
     * @param modoAuthenticatonDigital the modoAuthenticatonDigital to set
     */
    public void setModoAuthenticationDigital(boolean modoAuthenticatonDigital) {
        this.modoAuthenticatonDigital = modoAuthenticatonDigital;
    }

    /**
     * @return the txtUserName
     */
    public TextField<String> getTxtUserName() {
        return txtUserName;
    }

    /**
     * @param txtUserName the txtUserName to set
     */
    public void setTxtUserName(TextField<String> txtUserName) {
        this.txtUserName = txtUserName;
    }

    /**
     * @return the lblUserName
     */
    public LabelField getLblUserName() {
        return lblUserName;
    }

    /**
     * @param lblUserName the lblUserName to set
     */
    public void setLblUserName(LabelField lblUserName) {
        this.lblUserName = lblUserName;
    }

    /**
     * @return the lblPassword
     */
    public LabelField getLblPassword() {
        return lblPassword;
    }

    /**
     * @param lblPassword the lblPassword to set
     */
    public void setLblPassword(LabelField lblPassword) {
        this.lblPassword = lblPassword;
    }

    /**
     * @return the ptbPassword
     */
    public TextField<String> getPtbPassword() {
        return ptbPassword;
    }

    /**
     * @param ptbPassword the ptbPassword to set
     */
    public void setPtbPassword(TextField<String> ptbPassword) {
        this.ptbPassword = ptbPassword;
    }

    /**
     * @return the btnEntrar
     */
    public Button getBtnEntrar() {
        return btnEntrar;
    }

    /**
     * @param btnEntrar the btnEntrar to set
     */
    public void setBtnEntrar(Button btnEntrar) {
        this.btnEntrar = btnEntrar;
    }

    /**
     * @return the menuDesktop
     */
    public MenuDesktopGWT getMenuDesktop() {
        return menuDesktop;
    }

    /**
     * @param menuDesktop the menuDesktop to set
     */
    public void setMenuDesktop(MenuDesktopGWT menuDesktop) {
        this.menuDesktop = menuDesktop;
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

class CPKeyEvento extends KeyListener {

    private CPAuthenticationGWT authenticationGWT;

    /**
     * Fires on key down.
     *
     * @param event the component event
     */
    public void componentKeyDown(ComponentEvent event) {
    }

    /**
     * Fires on key press.
     *
     * @param event the component event
     */
    public void componentKeyPress(ComponentEvent event) {
    }

    /**
     * Fires on key up.
     *
     * @param event the component event
     */
    public void componentKeyUp(ComponentEvent event) {
        if (event.getKeyCode() == 13) {// enter
            authenticationGWT.logar();
        }
    }

    /**
     * @param authenticationGWT the authenticationGWT to set
     */
    public void setCPAuthenticationGWT(CPAuthenticationGWT authenticationGWT) {
        this.authenticationGWT = authenticationGWT;
    }
}
