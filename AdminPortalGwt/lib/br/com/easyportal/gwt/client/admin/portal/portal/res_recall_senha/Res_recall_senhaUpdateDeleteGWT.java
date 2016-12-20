package br.com.easyportal.gwt.client.admin.portal.portal.res_recall_senha;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import static br.com.easynet.gwt.client.BaseGWT.EASY_LABELS;
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
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class Res_recall_senhaUpdateDeleteGWT extends Res_recall_senhaInsertGWT {

    private Res_recall_senhaTGWT res_recall_senhaT;

    public Res_recall_senhaUpdateDeleteGWT() {
        getBtnClear().setVisible(false);
        getBtnInsert().setVisible(false);
        getBtnUpdate().setVisible(true);
        getBtnDelete().setVisible(false);

        setHeading("Alterar Parâmetros para recuperação de Senha");
    }

    public void load(Res_recall_senhaTGWT res_recall_senhaT) {
        this.res_recall_senhaT = res_recall_senhaT;
        res_tx_pergunta.setValue(res_recall_senhaT.getRes_tx_pergunta());
        res_tx_resposta.setValue(res_recall_senhaT.getRes_tx_resposta());
    }

    public void btnUpdateAction(ButtonEvent ce) {
        if (valide()) {
            res_recall_senhaT.setRes_tx_pergunta(res_tx_pergunta.getValue());
            res_recall_senhaT.setRes_tx_resposta(res_tx_resposta.getValue());
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
                    Res_recall_senhaUpdateDeleteGWT.this.setVisible(false);
                }
            };
            epsAsync.updateRecallPassword(res_recall_senhaT, callback);
/*            
            res_recall_senhaDao.update(res_recall_senhaT);
            Timer timer = new Timer() {

                public void run() {
                    String msg = res_recall_senhaDao.getMsg();
                    if (msg == null) {
                        schedule(500);
                    } else {
                        if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                            MessageBox.alert(EASY_LABELS.warning(), msg, null);
                        } else {
                            Info.display(EASY_LABELS.confirmation(), msg);
                            setVisible(false);
                        }
                    }
                }
            };
            timer.schedule(100);
*/
        }
    }

    public void btnDeletetAction(ButtonEvent ce) {

        MessageBox.confirm(EASY_LABELS.warning(), EASY_LABELS.messageDeleteConfirm(), new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
                    res_recall_senhaDao.delete(res_recall_senhaT);
                    Timer timer = new Timer() {

                        public void run() {
                            String msg = res_recall_senhaDao.getMsg();
                            if (msg == null) {
                                schedule(500);
                            } else {
                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                                    MessageBox.alert(EASY_LABELS.warning(), msg, null);
                                } else {
                                    Info.display(EASY_LABELS.confirmation(), msg);
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
}
