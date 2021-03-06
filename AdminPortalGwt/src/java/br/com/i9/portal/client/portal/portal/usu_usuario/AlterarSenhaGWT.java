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
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class AlterarSenhaGWT extends AlterarExcluirBaseGWT {

    private Usu_usuarioConsultGWT usu_usuarioConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Usu_usuarioDAOGWT usu_usuarioDao = new Usu_usuarioDAOGWT();
    private Usu_usuarioTGWT usu_usuarioT;
    private LabelField usu_tx_nome = new LabelField();
    private LabelField usu_tx_login = new LabelField();
    private EasyTextField<String> usu_tx_senha = new EasyTextField<String>();
    private EasyTextField<String> usu_tx_confSenha = new EasyTextField<String>();

    public AlterarSenhaGWT() {
        setHeading("Alterar Senha Usuário");
        this.setSize("350", "230");
        TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        getBtnDel().setVisible(false);
        LabelField lfusu_tx_nome = new LabelField("Nome:");
        lfusu_tx_nome.setId("lfusu_tx_nome");
        getCpMaster().add(lfusu_tx_nome);
        usu_tx_nome.setId("usu_tx_nome");
        getCpMaster().add(usu_tx_nome);

        LabelField lfusu_tx_login = new LabelField("Login:");
        lfusu_tx_login.setId("lfusu_tx_login");
        getCpMaster().add(lfusu_tx_login);
        usu_tx_login.setId("usu_tx_login");
        getCpMaster().add(usu_tx_login);

        LabelField lfusu_tx_senha = new LabelField("Senha:");
        lfusu_tx_senha.setId("lfusu_tx_senha");
        getCpMaster().add(lfusu_tx_senha);
        usu_tx_senha.setId("usu_tx_senha");
        usu_tx_senha.setUpperCase(false);
        usu_tx_senha.setMaxLength(50);
        usu_tx_senha.setWidth(100);
        usu_tx_senha.setPassword(true);
        getCpMaster().add(usu_tx_senha);

        LabelField lfusu_tx_ConfSenha = new LabelField("Conf. Senha:");
        lfusu_tx_senha.setId("lfusu_tx_confsenha");
        getCpMaster().add(lfusu_tx_ConfSenha);

        usu_tx_confSenha.setPassword(true);
        usu_tx_confSenha.setId("usu_tx_confsenha");
        usu_tx_confSenha.setMaxLength(50);
        usu_tx_confSenha.setWidth(100);
        usu_tx_confSenha.setUpperCase(false);
        getCpMaster().add(usu_tx_confSenha);
    }

    public void load(Usu_usuarioTGWT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
        usu_tx_nome.setValue(usu_usuarioT.getUsu_tx_nome());
        usu_tx_login.setValue(usu_usuarioT.getUsu_tx_login());
    }

    private boolean valide() {
        String senha = usu_tx_senha.getValue();
        String confSenha = usu_tx_confSenha.getValue();
        boolean resultado = false;
        if (senha.trim().length() < 6) {
            MessageBox.alert("Falha", "A senha deve possuir 6 ou mais caracteres.", null);
        } else if (!senha.equals(confSenha)) {
            MessageBox.alert("Falha", "A senha não confirmada.", null);
        } else {
            resultado = true;
        }
        return resultado;
    }

    public void btnUpdateAction(ButtonEvent ce) {
        if (valide()) {
            
            usu_usuarioT.setUsu_tx_senha(usu_tx_senha.getValue());
            //usu_usuarioDao.alterarSenha(usu_usuarioT);
            AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                @Override
                public void onFailure(Throwable caught) {
                    MessageBox.info("AVISO", "Falha ao alterar senha: " + caught.getCause().getMessage(), null);
                }

                @Override
                public void onSuccess(Void result) {
                    Info.display("Mensagem", "Senha alterada com sucesso");
                    usu_usuarioConsult.load();
                    setVisible(false);
                }
            };
            Usu_usuarioServiceAsync async = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
            async.updatePassword(usu_usuarioT, usu_tx_senha.getValue(), callback);

//            Timer timer = new Timer() {
//
//                public void run() {
//                    String msg = usu_usuarioDao.getMsg();
//                    if (msg == null) {
//                        schedule(500);
//                    } else {
//                        if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                            MessageBox.alert("Problemas", msg, null);
//                        } else {
//                            Info.display("Resultado", msg);
//                            usu_usuarioConsult.load();
//                            setVisible(false);
//                        }
//                    }
//                }
//            };
//            timer.schedule(500);
        }
    }

    public void btnDeltAction(ButtonEvent ce) {

        MessageBox.confirm("Aviso", "Tem certeza que deseja excluir registro?", new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
                    usu_usuarioDao.excluir(usu_usuarioT);
                    Timer timer = new Timer() {

                        public void run() {
                            String msg = usu_usuarioDao.getMsg();
                            if (msg == null) {
                                schedule(500);
                            } else {
                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                                    MessageBox.alert("Problemas", msg, null);
                                } else {
                                    Info.display("Resultado", msg);
                                    usu_usuarioConsult.load();
                                    setVisible(false);
                                }
                            }
                        }
                    };
                    timer.schedule(500);
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
