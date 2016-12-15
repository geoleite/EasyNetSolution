/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.admin.portal.portal.usu_usuario;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;

import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
import br.com.easynet.gwt.client.CadastrarBaseGWT;

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
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Usu_usuarioInsertGWT extends CadastrarBaseGWT {

    private Usu_usuarioConsultGWT usu_usuarioConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Usu_usuarioDAOGWT usu_usuarioDao = new Usu_usuarioDAOGWT();
    private TextField<String> usu_tx_nome = new TextField<String>();
    private TextField<String> usu_tx_login = new TextField<String>();
    private TextField<String> usu_tx_senha = new TextField<String>();
    private TextField<String> confSenha = new TextField<String>();
    private TextField<String> usu_tx_email = new TextField<String>();
    private Radio rUsu_tx_statusAtivo = new Radio();
    private Radio rUsu_tx_statusInativo = new Radio();
    private RadioGroup rgUsu_tx_status = new RadioGroup("Status");

    public Usu_usuarioInsertGWT() {
        setHeading("Cadastrar Usuário");
        setModal(true);
        this.setSize("400", "350");

        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(usu_tx_nome);

        getCpMaster().add(new LabelField("Status:"));
        getCpMaster().add(rgUsu_tx_status);
        rUsu_tx_statusAtivo.setValue(true);
        rUsu_tx_statusAtivo.setBoxLabel("Ativo");
        rUsu_tx_statusInativo.setBoxLabel("Inativo");
        rgUsu_tx_status.add(rUsu_tx_statusAtivo);
        rgUsu_tx_status.add(rUsu_tx_statusInativo);

        usu_tx_senha.setPassword(true);
        confSenha.setPassword(true);

        getCpMaster().add(new LabelField("Email:"));
        getCpMaster().add(usu_tx_email);


        getCpMaster().add(new LabelField("Login:"));
        getCpMaster().add(usu_tx_login);
        getCpMaster().add(new LabelField("Senha:"));
        getCpMaster().add(usu_tx_senha);
        getCpMaster().add(new LabelField("Conf. Senha:"));
        getCpMaster().add(confSenha);

    }

    /**
     * Validando os campos de senha
     * @return
     */
    private boolean validandoSenhas() {
        boolean invalideSenha = false;
        // Valida se a senha é diferente de null, no mínimo com 6 caracteres e igual a senha
        if (confSenha.getValue() == null) {
            MessageBox.alert("Erro na senha", "Senha não pode ser nula!", null);
            invalideSenha = true;
        } else if (confSenha.getValue().trim().length() < 6) {
            MessageBox.alert("Erro na senha", "Senha deve conter 6 ou mais caracteres!", null);
            invalideSenha = true;
        } else if (!confSenha.getValue().equals(usu_tx_senha.getValue())) {
            MessageBox.alert("Ërro na senha", "Senha não confirmada!", null);
            invalideSenha = true;
        }
        if (invalideSenha) {
            return false;
        }
        return true;
    }

    public void btnInsertAction(ButtonEvent ce) {
        //Só submet se as senhas estiverem validadas
        if (validandoSenhas()) {
            Usu_usuarioTGWT usu_usuarioT = new Usu_usuarioTGWT();
            usu_usuarioT.setUsu_tx_nome(usu_tx_nome.getValue());
            usu_usuarioT.setUsu_tx_login(usu_tx_login.getValue());
            usu_usuarioT.setUsu_tx_senha(usu_tx_senha.getValue());
            usu_usuarioT.setUsu_tx_status(rUsu_tx_statusAtivo.getValue() ? "A" : "I");
            usu_usuarioT.setUsu_tx_email(usu_tx_email.getValue());
            usu_usuarioDao.inserir(usu_usuarioT, confSenha.getValue());
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
                            btnLimpartAction(null);
                            usu_usuarioConsult.load();
                            setVisible(false);
                        }
                    }
                }
            };
            timer.schedule(500);
        }
    }

    public void btnLimpartAction(ButtonEvent ce) {
        usu_tx_nome.setValue("");
        usu_tx_login.setValue("");
        usu_tx_senha.setValue("");
        rUsu_tx_statusAtivo.setValue(true);
        usu_tx_email.setValue("");
        confSenha.setValue("");

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
