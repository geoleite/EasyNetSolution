package br.com.i9.portal.client.portal.portal.per_perfil;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.google.gwt.user.client.ui.Grid;
import java.util.HashMap;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import br.com.easynet.gwt.client.*;
import br.com.i9.portal.client.Constantes;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;

/**
 *
 * @author geoleite
 */
public class Per_perfilInsertGWT_1 extends CadastrarBaseGWT implements IListenetResponse {

    public static final String PAGE = "i9factory/factory/per_perfil/per_perfilInsertGWT.jsp";
    private Per_perfilConsultGWT consultGWT = new Per_perfilConsultGWT();

    public Per_perfilConsultGWT getConsultGWT() {
        return consultGWT;
    }

    /**
     * @param consultGWT the consultGWT to set
     */
    public void setConsultGWT(Per_perfilConsultGWT consultGWT) {
        this.consultGWT = consultGWT;
    }
    private TextField<String> per_tx_nome = new TextField<String>();

    public Per_perfilInsertGWT_1() {
        this.setHeading("Cadastrar Perfil");
        this.setSize(300, 100);
        TableLayout layout = new TableLayout(2);
        layout.setCellPadding(3);
        getCpMaster().setLayout(layout);

        per_tx_nome.setWidth(250);
        per_tx_nome.setAllowBlank(false);
        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(per_tx_nome);
        layout();
    }
//    @Override

    public void btnInsertAction(ButtonEvent ce) {
        if (!isPeenchido()) {
            MessageBox.alert("Result", "preencha todos campos", null);
        } else {
            btnCadAction(ce);
        }

    }

    private void btnCadAction(ButtonEvent sender) {
        String url = Constantes.URL + PAGE;//"portalgwt/exemplos/cadastro.jsp";
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("op", "insert");
        param.put("per_perfilT.per_tx_nome", per_tx_nome.getValue());

        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSonMap(url, param);
    }

    public void read(JSONValue jsonValue) {
        consultGWT.load();
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            String result = jsonObject.get("resultado").isString().stringValue();
            if (result.indexOf("sucesso") > 0) {
                Info.display("Resultado", result);
                setVisible(false);
            } else {
                MessageBox.alert("Problema", result, null);
            }
            //Set<String> keys = jsonObject.keySet();
            //Window.alert(usuario);
            //JSONObject resultado = jsonObject.get("resultado").isObject();
            //MessageBox.alert("Resultado", .toString(), null);
//            JSONObject msg = resultado.get("mensagem").isObject();
//            Window.alert("Mensagem " + msg.toString());
        }
    }

    public boolean isPeenchido() {
        return !("".equals(per_tx_nome.getValue()));
    }
}
