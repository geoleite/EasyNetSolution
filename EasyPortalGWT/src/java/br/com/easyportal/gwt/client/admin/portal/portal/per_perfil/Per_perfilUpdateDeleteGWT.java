package br.com.easyportal.gwt.client.admin.portal.portal.per_perfil;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;

import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
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
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Per_perfilUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Per_perfilConsultGWT per_perfilConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Per_perfilDAOGWT per_perfilDao = new Per_perfilDAOGWT();
    private Per_perfilTGWT per_perfilT;
    private TextField<String> per_tx_nome = new TextField<String>();
    private TextField<String> per_tx_class = new TextField<String>();
    private Radio rPer_tx_statusAtivo = new Radio();
    private Radio rPer_tx_statusInativo = new Radio();
    private RadioGroup rgPer_tx_status = new RadioGroup("Status");

    public Per_perfilUpdateDeleteGWT() {
        setHeading("Alterar Perfil");
        setModal(true);
        this.setSize("300", "230");


        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(per_tx_nome);

        getCpMaster().add(new LabelField("Status:"));
        getCpMaster().add(rgPer_tx_status);
        rPer_tx_statusAtivo.setValue(true);
        rPer_tx_statusAtivo.setBoxLabel("Ativo");
        rPer_tx_statusInativo.setBoxLabel("Inativo");
        rgPer_tx_status.add(rPer_tx_statusAtivo);
        rgPer_tx_status.add(rPer_tx_statusInativo);

        getCpMaster().add(new LabelField("Class Java:"));
        getCpMaster().add(per_tx_class);


    }

    public void load(Per_perfilTGWT per_perfilT) {
        this.per_perfilT = per_perfilT;
        per_tx_nome.setValue(per_perfilT.getPer_tx_nome());
        per_tx_class.setValue(per_perfilT.getPer_tx_class());
        if (per_perfilT.getPer_tx_status() == "A") {
            rPer_tx_statusAtivo.setValue(true);
        } else {
            rPer_tx_statusInativo.setValue(true);
        }
    }

    public void btnUpdateAction(ButtonEvent ce) {
        per_perfilT.setPer_tx_nome(per_tx_nome.getValue());
        per_perfilT.setPer_tx_status(rPer_tx_statusAtivo.getValue()?"A":"I");
        per_perfilT.setPer_tx_class(per_tx_class.getValue());

        per_perfilDao.alterar(per_perfilT);
        Timer timer = new Timer() {

            public void run() {
                String msg = per_perfilDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        per_perfilConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

        per_perfilDao.excluir(per_perfilT);
        Timer timer = new Timer() {

            public void run() {
                String msg = per_perfilDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        per_perfilConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    /**
     * @return the per_perfilConsult
     */
    public Per_perfilConsultGWT getPer_perfilConsult() {
        return per_perfilConsult;
    }

    /**
     * @param per_perfilConsult the per_perfilConsult to set
     */
    public void setPer_perfilConsult(Per_perfilConsultGWT per_perfilConsult) {
        this.per_perfilConsult = per_perfilConsult;
    }
}
