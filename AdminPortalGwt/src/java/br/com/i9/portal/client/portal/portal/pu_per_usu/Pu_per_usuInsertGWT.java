/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.i9.portal.client.portal.portal.pu_per_usu;

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
public class Pu_per_usuInsertGWT extends CadastrarBaseGWT  {
    private Pu_per_usuConsultGWT pu_per_usuConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");

	private Pu_per_usuDAOGWT pu_per_usuDao = new Pu_per_usuDAOGWT();
    private EasyTextField<String> per_nr_id = new EasyTextField<String>();
    private EasyTextField<String> usu_nr_id = new EasyTextField<String>();


    public Pu_per_usuInsertGWT() {
	setHeading("Cadastrar Pu_per_usu");
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

	LabelField lfusu_nr_id = new LabelField("usu_nr_id:");
        lfusu_nr_id.setId("lfusu_nr_id");
	getCpMaster().add(lfusu_nr_id);
        usu_nr_id.setId("usu_nr_id");
        usu_nr_id.setMaxLength(10);
        getCpMaster().add(usu_nr_id);


    }

    public void btnInsertAction(ButtonEvent ce){
	Pu_per_usuTGWT pu_per_usuT = new Pu_per_usuTGWT();
			pu_per_usuT.setPer_nr_id( Integer.parseInt(per_nr_id.getValue()));
		pu_per_usuT.setUsu_nr_id( Integer.parseInt(usu_nr_id.getValue()));

        pu_per_usuDao.inserir(pu_per_usuT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = pu_per_usuDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  btnLimpartAction(null);
		  pu_per_usuConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
    }
    public void btnLimpartAction(ButtonEvent ce){
			per_nr_id.setValue("");
		usu_nr_id.setValue("");

    }

   /**
     * @return the pu_per_usuConsult
     */
    public Pu_per_usuConsultGWT getPu_per_usuConsult() {
        return pu_per_usuConsult;
    }

    /**
     * @param pu_per_usuConsult the pu_per_usuConsult to set
     */
    public void setPu_per_usuConsult(Pu_per_usuConsultGWT pu_per_usuConsult) {
        this.pu_per_usuConsult = pu_per_usuConsult;
    }
}

