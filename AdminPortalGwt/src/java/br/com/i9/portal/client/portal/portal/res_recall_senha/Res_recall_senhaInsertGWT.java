/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.i9.portal.client.portal.portal.res_recall_senha;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.CRUDBaseGWT;

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

/**
 *
 * @author EasyNet
 */
public class Res_recall_senhaInsertGWT extends CRUDBaseGWT  {
    protected Res_recall_senhaConsultGWT res_recall_senhaConsult;
    protected DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    protected DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");

    protected Res_recall_senhaDAOGWT res_recall_senhaDao = new Res_recall_senhaDAOGWT();
    protected EasyTextField<String> usu_nr_id = new EasyTextField<String>();
    protected EasyTextField<String> res_tx_pergunta = new EasyTextField<String>();
    protected EasyTextField<String> res_tx_resposta = new EasyTextField<String>();
    protected DateField res_dt_ultimoacesso = new DateField();
    protected EasyTextField<String> res_nr_tentativas = new EasyTextField<String>();


    public Res_recall_senhaInsertGWT() {
        getBtnDelete().setVisible(false);
        getBtnUpdate().setVisible(false);
	setHeading("Cadastrar Res_recall_senha");
        this.setSize("500", "400");
	TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

	LabelField lfusu_nr_id = new LabelField("usu_nr_id:");
        lfusu_nr_id.setId("lfusu_nr_id");
	getCpMaster().add(lfusu_nr_id);
        usu_nr_id.setId("usu_nr_id");
        usu_nr_id.setMaxLength(10);
        getCpMaster().add(usu_nr_id);

	LabelField lfres_tx_pergunta = new LabelField("res_tx_pergunta:");
        lfres_tx_pergunta.setId("lfres_tx_pergunta");
	getCpMaster().add(lfres_tx_pergunta);
        res_tx_pergunta.setId("res_tx_pergunta");
        res_tx_pergunta.setMaxLength(200);
        getCpMaster().add(res_tx_pergunta);

	LabelField lfres_tx_resposta = new LabelField("res_tx_resposta:");
        lfres_tx_resposta.setId("lfres_tx_resposta");
	getCpMaster().add(lfres_tx_resposta);
        res_tx_resposta.setId("res_tx_resposta");
        res_tx_resposta.setMaxLength(200);
        getCpMaster().add(res_tx_resposta);

	LabelField lfres_dt_ultimoacesso = new LabelField("res_dt_ultimoacesso:");
        lfres_dt_ultimoacesso.setId("lfres_dt_ultimoacesso");
	getCpMaster().add(lfres_dt_ultimoacesso);
        res_dt_ultimoacesso.setId("res_dt_ultimoacesso");
        res_dt_ultimoacesso.setMaxLength(29);
        getCpMaster().add(res_dt_ultimoacesso);

	LabelField lfres_nr_tentativas = new LabelField("res_nr_tentativas:");
        lfres_nr_tentativas.setId("lfres_nr_tentativas");
	getCpMaster().add(lfres_nr_tentativas);
        res_nr_tentativas.setId("res_nr_tentativas");
        res_nr_tentativas.setMaxLength(10);
        getCpMaster().add(res_nr_tentativas);


    }

    public boolean valide() {
        return true;
    }

    public void btnInsertAction(ButtonEvent ce){
        if (valide()) {
	  Res_recall_senhaTGWT res_recall_senhaT = new Res_recall_senhaTGWT();
	  		res_recall_senhaT.setUsu_nr_id( Integer.parseInt(usu_nr_id.getValue()));
		res_recall_senhaT.setRes_tx_pergunta(res_tx_pergunta.getValue());
		res_recall_senhaT.setRes_tx_resposta(res_tx_resposta.getValue());
		res_recall_senhaT.setRes_dt_ultimoacesso(res_dt_ultimoacesso.getValue());
		res_recall_senhaT.setRes_nr_tentativas( Integer.parseInt(res_nr_tentativas.getValue()));

          res_recall_senhaDao.insert(res_recall_senhaT);
	  Timer timer = new Timer() {
	     public void run() {
 	       String msg = res_recall_senhaDao.getMsg();
 	       if (msg == null) {
		  schedule(500);
	       } else {
		  if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		    MessageBox.alert(EASY_LABELS.warning(), msg, null);
		  } else {
		    Info.display(EASY_LABELS.confirmation(), msg);
		    btnClearAction(null);
		    res_recall_senhaConsult.load();
		    setVisible(false);
		  }
	       }
	     }
          };
	  timer.schedule(100);
	}
    }
    public void btnClearAction(ButtonEvent ce){
			usu_nr_id.setValue("");
		res_tx_pergunta.setValue("");
		res_tx_resposta.setValue("");
		res_dt_ultimoacesso.setValue(null);
		res_nr_tentativas.setValue("");

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

