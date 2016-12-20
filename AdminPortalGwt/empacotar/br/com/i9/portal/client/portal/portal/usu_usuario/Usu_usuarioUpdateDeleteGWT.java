package br.com.i9.portal.client.portal.portal.usu_usuario;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Usu_usuarioService;
import br.com.i9.portal.client.rpc.Usu_usuarioServiceAsync;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import java.util.HashMap;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Timer;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class Usu_usuarioUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Usu_usuarioConsultGWT usu_usuarioConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Usu_usuarioDAOGWT usu_usuarioDao = new Usu_usuarioDAOGWT();
    private Usu_usuarioTGWT usu_usuarioT;
    private EasyTextField<String> usu_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> usu_tx_login = new EasyTextField<String>();
    private EasyTextField<String> usu_tx_email = new EasyTextField<String>();
//    private Radio rAtivo = new Radio();
//    private Radio rInativo = new Radio();
//    private RadioGroup usu_tx_status = new RadioGroup();
    private ComboStatus scvAtivo = new ComboStatus("Ativo");
    private ComboStatus scvInativo = new ComboStatus("Inativo");
    private ComboStatus scvBloqueado = new ComboStatus("Bloqueado");
    private ComboBox<ComboStatus> usu_tx_status = new ComboBox<ComboStatus>();
    
    private Radio rSim = new Radio();
    private Radio rNao = new Radio();
    private RadioGroup usu_tx_trocarsenha = new RadioGroup();

    public Usu_usuarioUpdateDeleteGWT() {
        setHeading("Alterar Usuário");
        this.setSize("400", "300");
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);

        TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        LabelField lfusu_tx_nome = new LabelField("Nome:");
        lfusu_tx_nome.setId("lfusu_tx_nome");
        getCpMaster().add(lfusu_tx_nome);        
        usu_tx_nome.setId("usu_tx_nome");
        usu_tx_nome.setMaxLength(80);
        usu_tx_nome.setWidth(200);
        getCpMaster().add(usu_tx_nome);

        LabelField lfusu_tx_login = new LabelField("Login:");
        lfusu_tx_login.setId("lfusu_tx_login");
        getCpMaster().add(lfusu_tx_login);
        usu_tx_login.setId("usu_tx_login");
        usu_tx_login.setAllowCharSpecial(true);
        usu_tx_login.setMaxLength(20);
        usu_tx_login.setWidth(100);
        usu_tx_login.setUpperCase(false);
        getCpMaster().add(usu_tx_login);

        LabelField lfusu_tx_email = new LabelField("Email:");
        lfusu_tx_email.setId("lfusu_tx_email");
        getCpMaster().add(lfusu_tx_email);
        usu_tx_email.setId("usu_tx_email");
        usu_tx_email.setUpperCase(false);
        usu_tx_email.setAllowCharSpecial(true);
        usu_tx_email.setMaxLength(150);
        usu_tx_email.setWidth(200);
        usu_tx_email.setUpperCase(false);
        getCpMaster().add(usu_tx_email);

        LabelField lfusu_tx_trocarsenha = new LabelField("Trocar Senha Login:");
        lfusu_tx_trocarsenha.setId("lfusu_tx_trocarsenha");
        getCpMaster().add(lfusu_tx_trocarsenha);
        rSim.setBoxLabel("Sim");
        rNao.setBoxLabel("Não");
        usu_tx_trocarsenha.add(rSim);
        usu_tx_trocarsenha.add(rNao);
        usu_tx_trocarsenha.setValue(rNao);
        getCpMaster().add(usu_tx_trocarsenha);

        LabelField lfusu_tx_status = new LabelField("Status:");
        lfusu_tx_status.setId("lfusu_tx_status");
        getCpMaster().add(lfusu_tx_status);
        //rAtivo.setBoxLabel("Ativo");
        //rInativo.setBoxLabel("Inativo");
        //usu_tx_status.add(rAtivo);
        //usu_tx_status.add(rInativo);
        //usu_tx_status.setValue(rAtivo);

        usu_tx_status.setEditable(false);
        ListStore<ComboStatus> listCombo = new ListStore<ComboStatus>();
        listCombo.add(scvAtivo);
        listCombo.add(scvInativo);
        listCombo.add(scvBloqueado);
        usu_tx_status.setDisplayField("value");
        usu_tx_status.setTriggerAction(ComboBox.TriggerAction.ALL);
        usu_tx_status.setTypeAhead(true);
        usu_tx_status.setStore(listCombo);
        usu_tx_status.setValue(scvAtivo);
        getCpMaster().add(usu_tx_status);

        Timer timer = new Timer() {

            @Override
            public void run() {
                Usu_usuarioUpdateDeleteGWT.this.layout();
                Usu_usuarioUpdateDeleteGWT.this.layout(true);
            }
        };
        timer.schedule(1000);

    }

    public void load(Usu_usuarioTGWT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
        usu_tx_nome.setValue(usu_usuarioT.getUsu_tx_nome());
        usu_tx_login.setValue(usu_usuarioT.getUsu_tx_login());
        usu_tx_email.setValue(usu_usuarioT.getUsu_tx_email());
        //usu_tx_status.setValue("A".equals(usu_usuarioT.getUsu_tx_status())?rAtivo:rInativo);
        usu_tx_status.setValue(getStatusUsuarioCombo(usu_usuarioT.getUsu_tx_status()));
        usu_tx_trocarsenha.setValue("S".equals(usu_usuarioT.getUsu_tx_trocarsenha())?rSim:rNao);
        usu_tx_status.getView().refresh();
        layout();
    }

    /**
     * Obtem o status para ser exibido na tela
     * @param status
     * @return
     */
    private ComboStatus getStatusUsuarioCombo(String status) {
        if ("A".equalsIgnoreCase(status)) {
            return scvAtivo;
        } else if ("I".equalsIgnoreCase(status)) {
            return scvInativo;
        } else {
            return scvBloqueado;
        }
    }

    /**
     * Obtem o status para inserir na base
     * @param cb
     * @return
     */
    private String getStatusUsuario(ComboStatus cb) {
        if (scvAtivo == cb) {
            return "A";
        } else if (scvInativo == cb) {
            return "I";
        } else {
            return "B";
        }
    }

    public void btnUpdateAction(ButtonEvent ce) {
        usu_usuarioT.setUsu_tx_nome(usu_tx_nome.getValue());
        usu_usuarioT.setUsu_tx_login(usu_tx_login.getValue());
        usu_usuarioT.setUsu_tx_status(getStatusUsuario(usu_tx_status.getValue()));
        usu_usuarioT.setUsu_tx_trocarsenha(usu_tx_trocarsenha.getValue()==rSim?"S":"N");
        usu_usuarioT.setUsu_tx_email(usu_tx_email.getValue());
        
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                usu_usuarioConsult.load();
                setVisible(false);
            }
        };
        Usu_usuarioServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
        serviceAsync.update(usu_usuarioT, callback);
//
//        usu_usuarioDao.alterar(usu_usuarioT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = usu_usuarioDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        usu_usuarioConsult.load();
//                        setVisible(false);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

        MessageBox.confirm("Aviso", "Tem certeza que deseja excluir registro?", new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
                    AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void onSuccess(Void result) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                    Usu_usuarioServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
                    serviceAsync.delete(usu_usuarioT, callback);
//                    usu_usuarioDao.excluir(usu_usuarioT);
//                    Timer timer = new Timer() {
//
//                        public void run() {
//                            String msg = usu_usuarioDao.getMsg();
//                            if (msg == null) {
//                                schedule(500);
//                            } else {
//                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                                    MessageBox.alert("Problemas", msg, null);
//                                } else {
//                                    Info.display("Resultado", msg);
//                                    usu_usuarioConsult.load();
//                                    setVisible(false);
//                                }
//                            }
//                        }
//                    };
//                    timer.schedule(500);
                }
            }
        });
    }

    /**
     * @return the usu_usuarioConsult
     */
    public Usu_usuarioConsultGWT getUsu_usuarioConsult() {
        return usu_usuarioConsult;
    }

    /**
     * @param usu_usuarioConsult the usu_usuarioConsult to set
     */
    public void setUsu_usuarioConsult(Usu_usuarioConsultGWT usu_usuarioConsult) {
        this.usu_usuarioConsult = usu_usuarioConsult;
    }
}
