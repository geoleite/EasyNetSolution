/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.admin.portal.portal.pi_per_int;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;

import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
import br.com.easynet.gwt.client.CadastrarBaseGWT;

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
 * @author geoleite
 */
public class Pi_per_intInsertGWT extends CadastrarBaseGWT  {
    private Pi_per_intConsultGWT pi_per_intConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");

	private Pi_per_intDAOGWT pi_per_intDao = new Pi_per_intDAOGWT();
    private TextField<String> int_nr_id = new TextField<String>();
    private TextField<String> per_nr_id = new TextField<String>();


    public Pi_per_intInsertGWT() {
        this.setSize("500", "400");
        getCpMaster().setLayout(new TableLayout(2));
        getCpMaster().add(new LabelField("int_nr_id:"));
        getCpMaster().add(int_nr_id);

        getCpMaster().add(new LabelField("per_nr_id:"));
        getCpMaster().add(per_nr_id);


    }

    public void btnInsertAction(ButtonEvent ce){
	Pi_per_intTGWT pi_per_intT = new Pi_per_intTGWT();
			pi_per_intT.setInt_nr_id( Integer.parseInt(int_nr_id.getValue()));
		pi_per_intT.setPer_nr_id( Integer.parseInt(per_nr_id.getValue()));

        pi_per_intDao.inserir(pi_per_intT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = pi_per_intDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  btnLimpartAction(null);
		  pi_per_intConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
    }
    public void btnLimpartAction(ButtonEvent ce){
			int_nr_id.setValue("");
		per_nr_id.setValue("");

    }

   /**
     * @return the pi_per_intConsult
     */
    public Pi_per_intConsultGWT getPi_per_intConsult() {
        return pi_per_intConsult;
    }

    /**
     * @param pi_per_intConsult the pi_per_intConsult to set
     */
    public void setPi_per_intConsult(Pi_per_intConsultGWT pi_per_intConsult) {
        this.pi_per_intConsult = pi_per_intConsult;
    }
}

