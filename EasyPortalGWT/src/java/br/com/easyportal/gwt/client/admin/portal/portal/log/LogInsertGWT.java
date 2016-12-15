/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.admin.portal.portal.log;

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
public class LogInsertGWT extends CadastrarBaseGWT  {
    private LogConsultGWT logConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");

	private LogDAOGWT logDao = new LogDAOGWT();
    private TextField<String> log_nr_id = new TextField<String>();
    private TextField<String> log_tx_sistema = new TextField<String>();
    private TextField<String> log_tx_classe = new TextField<String>();
    private TextField<String> log_tx_metodo = new TextField<String>();
    private TextField<String> log_tx_usuario = new TextField<String>();
    private DateField log_dt_datahora = new DateField();
    private TextField<String> log_tx_status = new TextField<String>();
    private TextField<String> log_tx_ip = new TextField<String>();
    private TextField<String> log_tx_parametros = new TextField<String>();


    public LogInsertGWT() {
        this.setSize("500", "400");
        getCpMaster().setLayout(new TableLayout(2));
        getCpMaster().add(new LabelField("log_nr_id:"));
        getCpMaster().add(log_nr_id);

        getCpMaster().add(new LabelField("log_tx_sistema:"));
        getCpMaster().add(log_tx_sistema);

        getCpMaster().add(new LabelField("log_tx_classe:"));
        getCpMaster().add(log_tx_classe);

        getCpMaster().add(new LabelField("log_tx_metodo:"));
        getCpMaster().add(log_tx_metodo);

        getCpMaster().add(new LabelField("log_tx_usuario:"));
        getCpMaster().add(log_tx_usuario);

        getCpMaster().add(new LabelField("log_dt_datahora:"));
        getCpMaster().add(log_dt_datahora);

        getCpMaster().add(new LabelField("log_tx_status:"));
        getCpMaster().add(log_tx_status);

        getCpMaster().add(new LabelField("log_tx_ip:"));
        getCpMaster().add(log_tx_ip);

        getCpMaster().add(new LabelField("log_tx_parametros:"));
        getCpMaster().add(log_tx_parametros);


    }

    public void btnInsertAction(ButtonEvent ce){

    }
    public void btnLimpartAction(ButtonEvent ce){
			log_nr_id.setValue("");
		log_tx_sistema.setValue("");
		log_tx_classe.setValue("");
		log_tx_metodo.setValue("");
		log_tx_usuario.setValue("");
		log_dt_datahora.setValue(null);
		log_tx_status.setValue("");
		log_tx_ip.setValue("");
		log_tx_parametros.setValue("");

    }

   /**
     * @return the logConsult
     */
    public LogConsultGWT getLogConsult() {
        return logConsult;
    }

    /**
     * @param logConsult the logConsult to set
     */
    public void setLogConsult(LogConsultGWT logConsult) {
        this.logConsult = logConsult;
    }
}

