package br.com.i9.portal.client.portal.portal.per_perfil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.Constantes;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import java.util.HashMap;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;

/**
 *
 * @author geoleite
 */
public class Per_perfilUpdateDeleteGWT_1 extends AlterarExcluirBaseGWT implements IListenetResponse {

    public static final String PAGE = "i9factory/factory/per_perfil/per_perfilUpdateDeleteGWT.jsp";
    private Per_perfilConsultGWT consultGWT = new Per_perfilConsultGWT();
    private Per_perfilTGWT per_perfilTGWT = new Per_perfilTGWT();
    private Per_perfilUpdateDeleteGWT_1 updateDeleteGWT = this;
    private TextField<String> per_tx_nome = new TextField<String>();
    private RadioGroup radioGroup = new RadioGroup();
    private Radio ativo = new Radio();
    private Radio inativo = new Radio();

    public Per_perfilConsultGWT getConsultGWT() {
        return consultGWT;
    }

    /**
     * @param consultGWT the consultGWT to set
     */
    public void setConsultGWT(Per_perfilConsultGWT consultGWT) {
        this.consultGWT = consultGWT;
    }

    public Per_perfilUpdateDeleteGWT_1() {
        this.setSize(300, 150);
        this.setHeading("Alterar Perfil");

        TableLayout layout = new TableLayout(2);
        layout.setCellPadding(3);
        getCpMaster().setLayout(layout);

        ativo.setBoxLabel("Ativo");
        radioGroup.add(ativo);
        inativo.setBoxLabel("Inativo");
        radioGroup.add(inativo);
        per_tx_nome.setAllowBlank(false);

        getCpMaster().add(new Label("Nome:"));
        getCpMaster().add(per_tx_nome);

        getCpMaster().add(new Label("Status:"));
        getCpMaster().add(radioGroup);

        layout();
    }

    public void load(Per_perfilTGWT per_perfilTGWT) {
        per_tx_nome.setValue(per_perfilTGWT.getPer_tx_nome());
        this.per_perfilTGWT.setPer_nr_id(per_perfilTGWT.getPer_nr_id());
        this.per_perfilTGWT.setPer_tx_class(per_perfilTGWT.getPer_tx_class());

        if (per_perfilTGWT.getPer_tx_status().equalsIgnoreCase("A")) {
            ativo.setValue(true);
        } else {
            inativo.setValue(true);
        }
    }

//    @Override
    public void btnUpdateAction(ButtonEvent ce) {
        if (!isPeenchido()) {
            MessageBox.alert("Result", "preencha todos os campos", null);
        } else {
            btnAltAction(ce);
        }

    }

//    @Override
    public void btnDeltAction(ButtonEvent ce) {
        btnDelAction(ce);
    }

    private void btnAltAction(ButtonEvent sender) {
        String url = Constantes.URL + PAGE;//"portalgwt/exemplos/cadastro.jsp";
//            url = "http://localhost:8084/easyportalgwt/agenda/agenda/usu_usuario/usu_usuarioUpdateDeleteGWT.jsp";
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "update");
        param.put("per_perfilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
        param.put("per_perfilT.per_tx_nome", per_tx_nome.getValue());
        param.put("per_perfilT.per_tx_status", radioGroup.getValue().getBoxLabel().equalsIgnoreCase("Ativo") ? "A" : "I");
//            param.put("per_perfilT.per_tx_status", per_tx_status.getText());
        param.put("per_perfilT.per_tx_class", per_perfilTGWT.getPer_tx_class());
        EasyAccessURL eaurl = new EasyAccessURL(Per_perfilUpdateDeleteGWT_1.this);
        eaurl.accessJSonMap(url, param);

    }

    private void btnDelAction(ButtonEvent sender) {
        MessageBox.confirm("Aviso", "Confirma o remocao do perfil?", new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if ("Yes".equalsIgnoreCase(be.getButtonClicked().getText())) {
                    String url = Constantes.URL + PAGE;//"portalgwt/exemplos/cadastro.jsp";
                    //url = "http://localhost:8084/easyportalgwt/agenda/agenda/usu_usuario/usu_usuarioUpdateDeleteGWT.jsp";
                    HashMap<String, String> param = new HashMap<String, String>();
                    param.put("op", "delete");
                    param.put("per_perfilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
                    EasyAccessURL eaurl = new EasyAccessURL(Per_perfilUpdateDeleteGWT_1.this);
                    eaurl.accessJSonMap(url, param);
                }
            }
        });
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        consultGWT.load();

        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            //Set<String> keys = jsonObject.keySet();
            //Window.alert(usuario);
            //JSONObject resultado = jsonObject.get("resultado").isObject();
            MessageBox.alert("Result", jsonObject.get("resultado").toString(), null);
        }
    }

    public boolean isPeenchido() {
        return !("".equals(per_tx_nome.getValue()));
    }
}
