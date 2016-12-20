/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.admin.portal.portal.res_recall_senha;

import static br.com.easynet.gwt.client.BaseGWT.EASY_LABELS;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.easynet.gwt.client.CRUDBaseGWT;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.Res_recall_senhaDAOGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Res_recall_senhaTGWT;
import br.com.easyportal.gwt.client.rpc.EasyPortalRPCFactory;
import br.com.easyportal.gwt.client.rpc.EasyPortalServiceAsync;

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
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author EasyNet
 */
public class Res_recall_senhaInsertGWT extends CRUDBaseGWT {

    protected DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    protected DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    protected Res_recall_senhaDAOGWT res_recall_senhaDao = new Res_recall_senhaDAOGWT();
    protected EasyTextField<String> res_tx_pergunta = new EasyTextField<String>();
    protected EasyTextField<String> res_tx_resposta = new EasyTextField<String>();

    public Res_recall_senhaInsertGWT() {
        getBtnDelete().setVisible(false);
        getBtnUpdate().setVisible(false);
        setMaximizable(false);
        setMinimizable(false);
        setResizable(false);
        setHeading("Cadastrar Parâmetros para Recuperação de Senha");
        this.setSize("310", "220");
        TableLayout tl = new TableLayout(1);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        res_tx_pergunta.setWidth(270);
        res_tx_resposta.setWidth(270);

        LabelField lfres_tx_pergunta = new LabelField("Pergunta:");
        lfres_tx_pergunta.setId("lfres_tx_pergunta");
        getCpMaster().add(lfres_tx_pergunta);
        res_tx_pergunta.setId("res_tx_pergunta");
        res_tx_pergunta.setMaxLength(200);
        getCpMaster().add(res_tx_pergunta);

        LabelField lfres_tx_resposta = new LabelField("Resp. Secreta:");
        lfres_tx_resposta.setId("lfres_tx_resposta");
        getCpMaster().add(lfres_tx_resposta);
        res_tx_resposta.setId("res_tx_resposta");
        res_tx_resposta.setMaxLength(200);
        getCpMaster().add(res_tx_resposta);

        res_tx_pergunta.setUpperCase(false);
        res_tx_pergunta.setAllowCharSpecial(true);

        res_tx_resposta.setUpperCase(false);
        res_tx_resposta.setAllowCharSpecial(true);
    }

    public boolean valide() {
        return true;
    }

    public void btnInsertAction(ButtonEvent ce) {
        if (valide()) {
            DebugMessage.message(this.getClass().getName(), "btnInsertAction");
            Res_recall_senhaTGWT res_recall_senhaT = new Res_recall_senhaTGWT();
            res_recall_senhaT.setRes_tx_pergunta(res_tx_pergunta.getValue());
            res_recall_senhaT.setRes_tx_resposta(res_tx_resposta.getValue());
            DebugMessage.message(this.getClass().getName(), "Res definido");
            EasyPortalServiceAsync epsAsync = EasyPortalRPCFactory.getEasyPortalService();
            AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                @Override
                public void onFailure(Throwable caught) {
                    String errorMsg = "";
                    if (caught != null && caught.getCause() != null) {
                        errorMsg = caught.getCause().getMessage();
                    } else {
                        errorMsg = caught.getMessage();
                    }
                    MessageBox.alert(EASY_LABELS.warning(), errorMsg, null);
                }

                @Override
                public void onSuccess(Void result) {
                    Info.display(EASY_LABELS.confirmation(), "Informação definida com sucesso!");
                    btnClearAction(null);
                    Res_recall_senhaInsertGWT.this.setVisible(false);
                }
            };
            epsAsync.insertRecallPassword(res_recall_senhaT, callback);
            /*            
             res_recall_senhaDao.insert(res_recall_senhaT);
             DebugMessage.message(this.getClass().getName(), "Res inserindo");
             Timer timer = new Timer() {

             public void run() {
             String msg = res_recall_senhaDao.getMsg();
             DebugMessage.message(this.getClass().getName(), "Res msg " + msg);
             if (msg == null) {
             schedule(500);
             } else {
             if (msg.toUpperCase().indexOf("FALHA") >= 0) {
             MessageBox.alert(EASY_LABELS.warning(), msg, null);
             } else {
             Info.display(EASY_LABELS.confirmation(), msg);
             btnClearAction(null);
             setVisible(false);
             }
             }
             }
             };
             timer.schedule(100);
             */
        }
    }

    public void btnClearAction(ButtonEvent ce) {
        res_tx_pergunta.setValue("");
        res_tx_resposta.setValue("");
    }
}
