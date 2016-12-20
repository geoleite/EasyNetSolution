/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.BaseGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.i18n.EasyLabels;
import br.com.easyportal.gwt.client.rpc.EasyPortalRPCFactory;
import br.com.easyportal.gwt.client.rpc.EasyPortalServiceAsync;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class AlterarSenhaGWT extends BaseGWT implements IListenetResponse {

    private EasyLabels easyLabels = GWT.create(EasyLabels.class);
    private LabelField lblSenha = new LabelField(easyLabels.pass() + ":");
    private LabelField lblSenhaNew = new LabelField(easyLabels.newPass() + ":");
    private LabelField lblSenhaConfirm = new LabelField(easyLabels.confirmPass() + ":");
    private TextField<String> pass = new TextField<String>();
    private TextField<String> passNew = new TextField<String>();
    private TextField<String> passConfirm = new TextField<String>();
//    private PasswordTextBox pass = new PasswordTextBox();
//    private PasswordTextBox passNew = new PasswordTextBox();
//    private PasswordTextBox passConfirm = new PasswordTextBox();
    private Button btnOk = new Button(easyLabels.alter());
    private Button btnClose = new Button(easyLabels.close());
    private MessageBox mbProgress = new MessageBox();
    private AlterarSenhaGWT alsgwt = this;

    public AlterarSenhaGWT() {
        setMinimizable(false);
        setMaximizable(false);
        setClosable(false);
        setModal(true);
        setHeading(easyLabels.alterPass());
        setSize(300, 150);
        remove(getCpTop());
        remove(getCpBotton());
        remove(getCpLeft());
        remove(getCpRight());

        pass.setPassword(true);
        passNew.setPassword(true);
        passConfirm.setPassword(true);

        getCpMaster().setLayout(new TableLayout(2));
        getCpMaster().add(lblSenha);
        getCpMaster().add(pass);
        getCpMaster().add(lblSenhaNew);
        getCpMaster().add(passNew);
        getCpMaster().add(lblSenhaConfirm);
        getCpMaster().add(passConfirm);
        getCpMaster().addButton(btnOk);
        getCpMaster().addButton(btnClose);
        btnOk.setIcon(ICONS.aplicar());

        btnOk.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                btnOkAction(be);
            }
        });

        btnClose.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                setVisible(false);
            }
        });


    }

    private boolean valideSenha() {
        String senha = passNew.getValue();
        String confSenha = passConfirm.getValue();
        if (senha == null || senha.trim().length() < 6) {
            MessageBox.alert(easyLabels.warning(), "Senha nao pode ser em branco, e deve possui mais que 6 caracteres.", null);
        } else if (!senha.equals(confSenha)) {
            MessageBox.alert(easyLabels.warning(), "Senha nao confirmada.", null);
        } else {
            return true;
        }
        passNew.setValue("");
        passConfirm.setValue("");
        return false;
    }

    public void btnOkAction(ButtonEvent sender) {
//        String url = Constantes.URL + "portalgwt/alterarsenha.jsp?op=alterarSenha&senhaatual="
//                + pass.getValue() + "&confnovasenha=" + passConfirm.getValue() + "&novasenha=" + passNew.getValue();
//
//        EasyAccessURL eaurl = new EasyAccessURL(this);
//
//        eaurl.accessJSon(url);
        
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

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
            public void onSuccess(Void result) {
                Info.display(easyLabels.operationResult(), "Senha alterada com sucesso!");
                AlterarSenhaGWT.this.setVisible(false);   
                finalizeAction();
            }
        };
        EasyPortalServiceAsync epsAsync =  EasyPortalRPCFactory.getEasyPortalService();
        epsAsync.alterPassword(pass.getValue(), passNew.getValue(), 
                               passConfirm.getValue(), callback);
    }

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
                //TabItem ti = Portal2GWT.tab.getItemByItemId(easyLabels.alterPass());
                //Portal2GWT.tab.remove(ti);
                setVisible(false);
            } else {
                MessageBox mb = new MessageBox();
                mb.alert(easyLabels.operationFailure(), valor, null);
            }
        }
    }

    /**
     * @return the btnClose
     */
    public Button getBtnClose() {
        return btnClose;
    }

    /**
     * @param btnClose the btnClose to set
     */
    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }

    public void finalizeAction() {
        
    }
}
