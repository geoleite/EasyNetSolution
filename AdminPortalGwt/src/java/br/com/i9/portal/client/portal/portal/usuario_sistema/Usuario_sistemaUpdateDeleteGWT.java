/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.usuario_sistema;

import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.i9.portal.client.Constantes;
import br.com.i9.portal.client.portal.portal.transfer.Usuario_sistemaTGWT;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.TextBox;
import java.util.HashMap;

/**
 *
 * @author I9
 */
public class Usuario_sistemaUpdateDeleteGWT extends AlterarExcluirBaseGWT implements br.com.easynet.gwt.client.IListenetResponse {

    public static final String PAGE = "portal/portal/usuario_sistema/usuario_sistemaUpdateDeleteGWT.jsp";
    private TextField<String> usu_tx_nome = new TextField<String>();
    private TextBox usu_tx_status = new TextBox();
    private TextField<String> usu_tx_email = new TextField<String>();
    private RadioGroup radioGroup = new RadioGroup();
    private Radio ativo = new Radio();
    private Radio inativo = new Radio();
    private Usuario_sistemaConsultGWT consultGWT = new Usuario_sistemaConsultGWT();
    private Usuario_sistemaTGWT usuario_sistemaTGWT = new Usuario_sistemaTGWT();
    private Usuario_sistemaUpdateDeleteGWT updateDeleteGWT = this;

    public Usuario_sistemaUpdateDeleteGWT() {
        this.setSize(400, 240);
        this.setHeading("Alterar Usuario");
        TableLayout layout = new TableLayout(2);
        layout.setCellPadding(3);
        getCpMaster().setLayout(layout);

        usu_tx_nome.setWidth(300);
        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(usu_tx_nome);

        ativo.setBoxLabel("Ativo");
        radioGroup.add(ativo);
        inativo.setBoxLabel("Inativo");
        radioGroup.add(inativo);

        getCpMaster().add(new LabelField("Status:"));
        getCpMaster().add(radioGroup);

        usu_tx_email.setWidth(300);
        getCpMaster().add(new LabelField("E_mail:"));
        getCpMaster().add(usu_tx_email);
    }

    public void load(Usuario_sistemaTGWT usuario_sistemaTGWT) {
        try {
            this.usuario_sistemaTGWT = usuario_sistemaTGWT;
            usu_tx_nome.setValue(usuario_sistemaTGWT.getUsu_tx_nome());
            usu_tx_email.setValue(usuario_sistemaTGWT.getUsu_tx_email());
            if (usuario_sistemaTGWT.getUsu_tx_status().equalsIgnoreCase("A")) {
                ativo.setValue(true);
            } else {
                inativo.setValue(true);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void btnUpdateAction(ButtonEvent ce) {
        if (isPreenchido()) {
            btnAltAction(ce);
        } else {
            MessageBox.alert("Result", "preecha todos campos!", null);
        }
    }

    @Override
    public void btnDeltAction(ButtonEvent ce) {
        btnDelAction(ce);
    }

    public boolean valido() {
        boolean result = true;
        return result;
    }

    private void btnAltAction(ButtonEvent sender) {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "update");
        param.put("usu_usuarioT.usu_tx_nome", usu_tx_nome.getValue());
        param.put("usu_usuarioT.usu_tx_email", usu_tx_email.getValue());
        param.put("usu_usuarioT.usu_tx_status", radioGroup.getValue().getBoxLabel().equalsIgnoreCase("ativo") ? "A" : "I");
        param.put("usu_usuarioT.usu_nr_id", usuario_sistemaTGWT.getUsu_nr_id() + "");
        param.put("usu_usuarioT.usu_tx_login", usuario_sistemaTGWT.getUsu_tx_login() + "");
        //param.put("usu_usuarioT.usu_tx_senha", usuario_sistemaTGWT.getUsu_tx_senha() + "");
        eaurl.accessJSonMap(Constantes.URL + PAGE, param);
    }

    private void btnDelAction(ButtonEvent sender) {
        MessageBox.confirm("Aviso", "Confirma o remocao do usuario?", new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if ("Yes".equalsIgnoreCase(be.getButtonClicked().getText())) {
                    EasyAccessURL eaurl = new EasyAccessURL(Usuario_sistemaUpdateDeleteGWT.this);
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("op", "delete");
                    param.put("usu_usuarioT.usu_nr_id", usuario_sistemaTGWT.getUsu_nr_id() + "");
                    eaurl.accessJSonMap(Constantes.URL + PAGE, param);
                }
            }
        });
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

    public void read(JSONValue jsonValue) {
        consultGWT.load();
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            String result = jsonObject.get("resultado").isString().stringValue();
            if (result.indexOf("sucesso") > 0) {
                Info.display("Resultado", result);
            } else {
                MessageBox.alert("Problemas", result, null);
            }
            setVisible(false);
        }
    }

    public boolean isPreenchido() {
        return !(("".equals(usu_tx_email.getValue()))
                || ("".equals(usu_tx_nome.getValue())));
    }
}
