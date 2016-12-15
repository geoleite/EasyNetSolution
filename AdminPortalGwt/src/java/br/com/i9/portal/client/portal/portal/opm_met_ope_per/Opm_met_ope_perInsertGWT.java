/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.i9.portal.client.portal.portal.opm_met_ope_per;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
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
public class Opm_met_ope_perInsertGWT extends CadastrarBaseGWT  {
    private Opm_met_ope_perConsultGWT opm_met_ope_perConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");

	private Opm_met_ope_perDAOGWT opm_met_ope_perDao = new Opm_met_ope_perDAOGWT();
    private EasyTextField<String> per_nr_id = new EasyTextField<String>();
    private EasyTextField<String> ope_nr_id = new EasyTextField<String>();
    private EasyTextField<String> sis_nr_id = new EasyTextField<String>();
    private EasyTextField<String> met_nr_id = new EasyTextField<String>();


    public Opm_met_ope_perInsertGWT() {
	setHeading("Cadastrar Opm_met_ope_per");
        this.setSize("500", "400");
	TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

	LabelField lfper_nr_id = new LabelField("per_nr_id:");
        lfper_nr_id.setId("lfper_nr_id");
	getCpMaster().add(lfper_nr_id);
        per_nr_id.setId("per_nr_id");
        per_nr_id.setMaxLength(10);
        getCpMaster().add(per_nr_id);

	LabelField lfope_nr_id = new LabelField("ope_nr_id:");
        lfope_nr_id.setId("lfope_nr_id");
	getCpMaster().add(lfope_nr_id);
        ope_nr_id.setId("ope_nr_id");
        ope_nr_id.setMaxLength(10);
        getCpMaster().add(ope_nr_id);

	LabelField lfsis_nr_id = new LabelField("sis_nr_id:");
        lfsis_nr_id.setId("lfsis_nr_id");
	getCpMaster().add(lfsis_nr_id);
        sis_nr_id.setId("sis_nr_id");
        sis_nr_id.setMaxLength(10);
        getCpMaster().add(sis_nr_id);

	LabelField lfmet_nr_id = new LabelField("met_nr_id:");
        lfmet_nr_id.setId("lfmet_nr_id");
	getCpMaster().add(lfmet_nr_id);
        met_nr_id.setId("met_nr_id");
        met_nr_id.setMaxLength(10);
        getCpMaster().add(met_nr_id);


    }

    public void btnInsertAction(ButtonEvent ce){
	Opm_met_ope_perTGWT opm_met_ope_perT = new Opm_met_ope_perTGWT();
			opm_met_ope_perT.setPer_nr_id( Integer.parseInt(per_nr_id.getValue()));
		opm_met_ope_perT.setOpe_nr_id( Integer.parseInt(ope_nr_id.getValue()));
		opm_met_ope_perT.setSis_nr_id( Integer.parseInt(sis_nr_id.getValue()));
		opm_met_ope_perT.setMet_nr_id( Integer.parseInt(met_nr_id.getValue()));

        opm_met_ope_perDao.inserir(opm_met_ope_perT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = opm_met_ope_perDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  btnLimpartAction(null);
		  opm_met_ope_perConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
    }
    public void btnLimpartAction(ButtonEvent ce){
			per_nr_id.setValue("");
		ope_nr_id.setValue("");
		sis_nr_id.setValue("");
		met_nr_id.setValue("");

    }

   /**
     * @return the opm_met_ope_perConsult
     */
    public Opm_met_ope_perConsultGWT getOpm_met_ope_perConsult() {
        return opm_met_ope_perConsult;
    }

    /**
     * @param opm_met_ope_perConsult the opm_met_ope_perConsult to set
     */
    public void setOpm_met_ope_perConsult(Opm_met_ope_perConsultGWT opm_met_ope_perConsult) {
        this.opm_met_ope_perConsult = opm_met_ope_perConsult;
    }
}

