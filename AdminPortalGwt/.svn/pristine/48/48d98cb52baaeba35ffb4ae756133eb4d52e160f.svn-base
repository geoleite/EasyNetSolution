/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.i9.portal.client.portal.portal.log;

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
public class LogInsertGWT extends CadastrarBaseGWT  {
    private LogConsultGWT logConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");

	private LogDAOGWT logDao = new LogDAOGWT();
    private EasyTextField<String> log_nr_id = new EasyTextField<String>();
    private EasyTextField<String> log_tx_sistema = new EasyTextField<String>();
    private EasyTextField<String> log_tx_classe = new EasyTextField<String>();
    private EasyTextField<String> log_tx_metodo = new EasyTextField<String>();
    private EasyTextField<String> log_tx_usuario = new EasyTextField<String>();
    private DateField log_dt_datahora = new DateField();
    private EasyTextField<String> log_tx_status = new EasyTextField<String>();
    private EasyTextField<String> log_tx_ip = new EasyTextField<String>();
    private EasyTextField<String> log_tx_parametros = new EasyTextField<String>();


    public LogInsertGWT() {
	setHeading("Cadastrar Log");
        this.setSize("500", "400");
	TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

	LabelField lflog_nr_id = new LabelField("log_nr_id:");
        lflog_nr_id.setId("lflog_nr_id");
	getCpMaster().add(lflog_nr_id);
        log_nr_id.setId("log_nr_id");
        log_nr_id.setMaxLength(19);
        getCpMaster().add(log_nr_id);

	LabelField lflog_tx_sistema = new LabelField("log_tx_sistema:");
        lflog_tx_sistema.setId("lflog_tx_sistema");
	getCpMaster().add(lflog_tx_sistema);
        log_tx_sistema.setId("log_tx_sistema");
        log_tx_sistema.setMaxLength(200);
        getCpMaster().add(log_tx_sistema);

	LabelField lflog_tx_classe = new LabelField("log_tx_classe:");
        lflog_tx_classe.setId("lflog_tx_classe");
	getCpMaster().add(lflog_tx_classe);
        log_tx_classe.setId("log_tx_classe");
        log_tx_classe.setMaxLength(200);
        getCpMaster().add(log_tx_classe);

	LabelField lflog_tx_metodo = new LabelField("log_tx_metodo:");
        lflog_tx_metodo.setId("lflog_tx_metodo");
	getCpMaster().add(lflog_tx_metodo);
        log_tx_metodo.setId("log_tx_metodo");
        log_tx_metodo.setMaxLength(100);
        getCpMaster().add(log_tx_metodo);

	LabelField lflog_tx_usuario = new LabelField("log_tx_usuario:");
        lflog_tx_usuario.setId("lflog_tx_usuario");
	getCpMaster().add(lflog_tx_usuario);
        log_tx_usuario.setId("log_tx_usuario");
        log_tx_usuario.setMaxLength(200);
        getCpMaster().add(log_tx_usuario);

	LabelField lflog_dt_datahora = new LabelField("log_dt_datahora:");
        lflog_dt_datahora.setId("lflog_dt_datahora");
	getCpMaster().add(lflog_dt_datahora);
        log_dt_datahora.setId("log_dt_datahora");
        log_dt_datahora.setMaxLength(29);
        getCpMaster().add(log_dt_datahora);

	LabelField lflog_tx_status = new LabelField("log_tx_status:");
        lflog_tx_status.setId("lflog_tx_status");
	getCpMaster().add(lflog_tx_status);
        log_tx_status.setId("log_tx_status");
        log_tx_status.setMaxLength(1);
        getCpMaster().add(log_tx_status);

	LabelField lflog_tx_ip = new LabelField("log_tx_ip:");
        lflog_tx_ip.setId("lflog_tx_ip");
	getCpMaster().add(lflog_tx_ip);
        log_tx_ip.setId("log_tx_ip");
        log_tx_ip.setMaxLength(15);
        getCpMaster().add(log_tx_ip);

	LabelField lflog_tx_parametros = new LabelField("log_tx_parametros:");
        lflog_tx_parametros.setId("lflog_tx_parametros");
	getCpMaster().add(lflog_tx_parametros);
        log_tx_parametros.setId("log_tx_parametros");
        log_tx_parametros.setMaxLength(5000);
        getCpMaster().add(log_tx_parametros);


    }

    public void btnInsertAction(ButtonEvent ce){
	LogTGWT logT = new LogTGWT();
			logT.setLog_nr_id( Long.parseLong(log_nr_id.getValue()));
		logT.setLog_tx_sistema(log_tx_sistema.getValue());
		logT.setLog_tx_classe(log_tx_classe.getValue());
		logT.setLog_tx_metodo(log_tx_metodo.getValue());
		logT.setLog_tx_usuario(log_tx_usuario.getValue());
		logT.setLog_dt_datahora(log_dt_datahora.getValue());
		logT.setLog_tx_status(log_tx_status.getValue());
		logT.setLog_tx_ip(log_tx_ip.getValue());
		logT.setLog_tx_parametros(log_tx_parametros.getValue());

        logDao.inserir(logT);
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
		  btnLimpartAction(null);
		  logConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
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

