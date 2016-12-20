/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.usuario_sistema;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PasswordTextBox;
import java.util.HashMap;
import br.com.easynet.gwt.client.*;
import br.com.i9.portal.client.Constantes;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;

/**
 *
 * @author I9
 */
public class Usuario_sistemaInsertGWT extends CadastrarBaseGWT implements br.com.easynet.gwt.client.IListenetResponse {

    public static final String PAGE = "portal/portal/usuario_sistema/usuario_sistemaInsertGWT.jsp";
    private Usuario_sistemaConsultGWT consultGWT = new Usuario_sistemaConsultGWT();
    private TextField<String> usu_tx_nome = new TextField<String>();
    private TextField<String> usu_tx_login = new TextField<String>();
    private TextField<String> usu_tx_email = new TextField<String>();
    private PasswordTextBox usu_tx_senha = new PasswordTextBox();
    private PasswordTextBox confirmacaoSenha = new PasswordTextBox();

    public Usuario_sistemaInsertGWT() {
        this.setHeading("Cadastro Usuario");
        TableLayout layout = new TableLayout(2);
        layout.setCellPadding(3);

        this.setSize(400, 240);
        //usu_tx_nome
        usu_tx_email.setAllowBlank(false);
        usu_tx_login.setAllowBlank(false);
        usu_tx_nome.setAllowBlank(false);

        getCpMaster().setLayout(layout);

        usu_tx_nome.setWidth(300);
        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(usu_tx_nome);

        usu_tx_email.setWidth(300);
        getCpMaster().add(new LabelField("E_mail:"));
        getCpMaster().add(usu_tx_email);

        getCpMaster().add(new LabelField("Login:"));
        getCpMaster().add(usu_tx_login);

        getCpMaster().add(new LabelField("Senha:"));
        getCpMaster().add(usu_tx_senha);

        getCpMaster().add(new LabelField("Confirmação da senha:"));
        getCpMaster().add(confirmacaoSenha);

        layout();
    }

    /**
     * @return the consultGWT
     */
    public Usuario_sistemaConsultGWT getConsultGWT() {
        return consultGWT;
    }

    /**
     * @param consultGWT the consultGWT to set
     */
    public void setConsultGWT(Usuario_sistemaConsultGWT consultGWT) {
        this.consultGWT = consultGWT;
    }

    @Override
    public void btnInsertAction(ButtonEvent ce) {
        if (!(usu_tx_senha.getText().equals(confirmacaoSenha.getText()))) {
            MessageBox.alert("Result", "Senhas incorretas!", null);
        } else if ((isPreenchido()) && (usu_tx_senha.getText().equals(confirmacaoSenha.getText()))) {
            btnCadAction(ce);
        } else {
            MessageBox.alert("Result", "preencha todos campos!", null);
        }
    }

    private boolean valideSenha() {
        String senha = usu_tx_senha.getValue();
        String confSenha = confirmacaoSenha.getValue();
        if (senha == null || senha.trim().length() < 4) {
            MessageBox.alert("Erro", "Senha nao pode ser em branco, e deve possui mais que 4 caracteres.", null);
        } else if (!senha.equals(confSenha)) {
            MessageBox.alert("Erro", "Senha nao confirmada.", null);
        } else {
            return true;
        }
        usu_tx_senha.setValue("");
        confirmacaoSenha.setValue("");
        return false;
    }

    private void btnCadAction(ButtonEvent sender) {
        try {
            if (valideSenha()) {
                String url = Constantes.URL + PAGE;//"portalgwt/exemplos/cadastro.jsp";
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("op", "insert");
                param.put("usu_usuarioT.usu_tx_nome", usu_tx_nome.getValue());
                param.put("usu_usuarioT.usu_tx_login", usu_tx_login.getValue());
                param.put("usu_usuarioT.usu_tx_email", usu_tx_email.getValue());
                param.put("usu_usuarioT.usu_tx_senha", usu_tx_senha.getText());

                EasyAccessURL eaurl = new EasyAccessURL(this);
                eaurl.accessJSonMap(url, param);
            }

        } catch (Exception ex) {
        }
    }

    public void clear() {
//        car_tx_nome.setValue("");
//        car_tx_marca.setValue("");
    }

    public void read(JSONValue jsonValue) {
        consultGWT.load();
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            MessageBox.alert("Result", jsonObject.get("resultado").toString(), null);
            if ("Cadastro efetuado com sucesso!".equalsIgnoreCase(EasyContainer.clearAspas(jsonObject.get("resultado").toString()))) {
                clear();
                setVisible(false);
            }
        }
    }

    public boolean isPreenchido() {
        return !(("".equals(usu_tx_email.getValue()))
                || ("".equals(usu_tx_login.getValue()))
                || ("".equals(usu_tx_nome.getValue()))
                || ("".equals(usu_tx_senha.getValue()))
                || ("".equals(confirmacaoSenha.getValue())));
    }
}
