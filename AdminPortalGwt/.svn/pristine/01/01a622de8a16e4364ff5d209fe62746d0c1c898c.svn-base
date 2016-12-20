package br.com.i9.portal.client.portal.portal.res_recall_senha;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;

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
        getBtnDelete().setVisible(true);

        setHeading("Alterar Res_recall_senha");
    }

    public void load(Res_recall_senhaTGWT res_recall_senhaT) {
        this.res_recall_senhaT = res_recall_senhaT;
        usu_nr_id.setValue(res_recall_senhaT.getUsu_nr_id() + "");
        res_tx_pergunta.setValue(res_recall_senhaT.getRes_tx_pergunta());
        res_tx_resposta.setValue(res_recall_senhaT.getRes_tx_resposta());
        res_dt_ultimoacesso.setValue(res_recall_senhaT.getRes_dt_ultimoacesso());
        res_nr_tentativas.setValue(res_recall_senhaT.getRes_nr_tentativas() + "");

    }

    public void btnUpdateAction(ButtonEvent ce) {
        if (valide()) {
            res_recall_senhaT.setUsu_nr_id(Integer.parseInt(usu_nr_id.getValue()));
            res_recall_senhaT.setRes_tx_pergunta(res_tx_pergunta.getValue());
            res_recall_senhaT.setRes_tx_resposta(res_tx_resposta.getValue());
            res_recall_senhaT.setRes_dt_ultimoacesso(res_dt_ultimoacesso.getValue());
            res_recall_senhaT.setRes_nr_tentativas(Integer.parseInt(res_nr_tentativas.getValue()));

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
                            res_recall_senhaConsult.load();
                            setVisible(false);
                        }
                    }
                }
            };
            timer.schedule(100);
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
                                    res_recall_senhaConsult.load();
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
     * @return the res_recall_senhaConsult
     */
    public Res_recall_senhaConsultGWT getRes_recall_senhaConsult() {
        return res_recall_senhaConsult;
    }

    /**
     * @param res_recall_senhaConsult the res_recall_senhaConsult to set
     */
    public void setRes_recall_senhaConsult(Res_recall_senhaConsultGWT res_recall_senhaConsult) {
        this.res_recall_senhaConsult = res_recall_senhaConsult;
    }
}
