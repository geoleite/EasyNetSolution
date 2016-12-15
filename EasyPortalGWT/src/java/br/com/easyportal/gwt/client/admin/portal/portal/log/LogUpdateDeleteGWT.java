package br.com.easyportal.gwt.client.admin.portal.portal.log;

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
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class LogUpdateDeleteGWT extends AlterarExcluirBaseGWT {
    private LogConsultGWT logConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private LogDAOGWT logDao = new LogDAOGWT();
    private LogTGWT logT;

    private TextField<String> log_nr_id = new TextField<String>();
    private TextField<String> log_tx_sistema = new TextField<String>();
    private TextField<String> log_tx_classe = new TextField<String>();
    private TextField<String> log_tx_metodo = new TextField<String>();
    private TextField<String> log_tx_usuario = new TextField<String>();
    private DateField log_dt_datahora = new DateField();
    private TextField<String> log_tx_status = new TextField<String>();
    private TextField<String> log_tx_ip = new TextField<String>();
    private TextField<String> log_tx_parametros = new TextField<String>();


    public LogUpdateDeleteGWT() {
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

    public void load(LogTGWT logT) {
	this.logT = logT;
		log_nr_id.setValue(logT.getLog_nr_id() + "");
		log_tx_sistema.setValue(logT.getLog_tx_sistema());
		log_tx_classe.setValue(logT.getLog_tx_classe());
		log_tx_metodo.setValue(logT.getLog_tx_metodo());
		log_tx_usuario.setValue(logT.getLog_tx_usuario());
		log_dt_datahora.setValue(logT.getLog_dt_datahora());
		log_tx_status.setValue(logT.getLog_tx_status());
		log_tx_ip.setValue(logT.getLog_tx_ip());
		log_tx_parametros.setValue(logT.getLog_tx_parametros());

    }
    public void btnUpdateAction(ButtonEvent ce) {
		logT.setLog_nr_id( Long.parseLong(log_nr_id.getValue()));
		logT.setLog_tx_sistema(log_tx_sistema.getValue());
		logT.setLog_tx_classe(log_tx_classe.getValue());
		logT.setLog_tx_metodo(log_tx_metodo.getValue());
		logT.setLog_tx_usuario(log_tx_usuario.getValue());
		logT.setLog_dt_datahora(log_dt_datahora.getValue());
		logT.setLog_tx_status(log_tx_status.getValue());
		logT.setLog_tx_ip(log_tx_ip.getValue());
		logT.setLog_tx_parametros(log_tx_parametros.getValue());

	logDao.alterar(logT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = logDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  logConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

	logDao.excluir(logT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = logDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  logConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
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

