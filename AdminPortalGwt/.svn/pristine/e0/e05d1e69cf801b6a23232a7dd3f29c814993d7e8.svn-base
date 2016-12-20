package br.com.i9.portal.client.portal.portal.log;

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
public class LogUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private LogConsultGWT logConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private LogDAOGWT logDao = new LogDAOGWT();
    private LogTGWT logT;
    private EasyTextField<String> log_nr_id = new EasyTextField<String>();
    private LabelField log_tx_sistema = new LabelField();
    private LabelField log_tx_classe = new LabelField();
    private LabelField log_tx_metodo = new LabelField();
    private LabelField log_tx_usuario = new LabelField();
    private LabelField log_dt_datahora = new LabelField();
    private LabelField log_tx_status = new LabelField();
    private LabelField log_tx_ip = new LabelField();
    private LabelField log_tx_parametros = new LabelField();

    public LogUpdateDeleteGWT() {
        setHeading("Visualizar Log");
        getBtnDel().setVisible(false);
        getBtnUpdate().setVisible(false);
        this.setSize("500", "400");
        TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        LabelField lflog_tx_sistema = new LabelField("Sistema:");
        lflog_tx_sistema.setId("lflog_tx_sistema");
        getCpMaster().add(lflog_tx_sistema);
        log_tx_sistema.setId("log_tx_sistema");
        getCpMaster().add(log_tx_sistema);

        LabelField lflog_tx_usuario = new LabelField("log_tx_usuario:");
        lflog_tx_usuario.setId("lflog_tx_usuario");
        getCpMaster().add(lflog_tx_usuario);
        log_tx_usuario.setId("log_tx_usuario");
        getCpMaster().add(log_tx_usuario);

        LabelField lflog_tx_classe = new LabelField("Classe:");
        lflog_tx_classe.setId("lflog_tx_classe");
        getCpMaster().add(lflog_tx_classe);
        log_tx_classe.setId("log_tx_classe");
        getCpMaster().add(log_tx_classe);

        LabelField lflog_tx_metodo = new LabelField("Método:");
        lflog_tx_metodo.setId("lflog_tx_metodo");
        getCpMaster().add(lflog_tx_metodo);
        log_tx_metodo.setId("log_tx_metodo");
        getCpMaster().add(log_tx_metodo);

        LabelField lflog_dt_datahora = new LabelField("Data/Hora:");
        lflog_dt_datahora.setId("lflog_dt_datahora");
        getCpMaster().add(lflog_dt_datahora);
        log_dt_datahora.setId("log_dt_datahora");
        getCpMaster().add(log_dt_datahora);

        LabelField lflog_tx_status = new LabelField("Status:");
        lflog_tx_status.setId("lflog_tx_status");
        getCpMaster().add(lflog_tx_status);
        log_tx_status.setId("log_tx_status");
        getCpMaster().add(log_tx_status);

        LabelField lflog_tx_ip = new LabelField("Ip Acesso:");
        lflog_tx_ip.setId("lflog_tx_ip");
        getCpMaster().add(lflog_tx_ip);
        log_tx_ip.setId("log_tx_ip");
        getCpMaster().add(log_tx_ip);

        LabelField lflog_tx_parametros = new LabelField("Parâmetros:");
        lflog_tx_parametros.setId("lflog_tx_parametros");
        getCpMaster().add(lflog_tx_parametros);
        log_tx_parametros.setId("log_tx_parametros");
        getCpMaster().add(log_tx_parametros);
    }

    public void load(LogTGWT logT) {
        this.logT = logT;
        log_nr_id.setValue(logT.getLog_nr_id() + "");
        log_tx_sistema.setValue(logT.getLog_tx_sistema());
        log_tx_classe.setValue(logT.getLog_tx_classe());
        log_tx_metodo.setValue(logT.getLog_tx_metodo());
        log_tx_usuario.setValue(logT.getLog_tx_usuario());
        log_dt_datahora.setValue(dtfDateTime.format(logT.getLog_dt_datahora()));
        log_tx_status.setValue("S".equals(logT.getLog_tx_status())?"OK":"ERRO");
        log_tx_ip.setValue(logT.getLog_tx_ip());
        log_tx_parametros.setValue(logT.getLog_tx_parametros());
    }

    public void btnUpdateAction(ButtonEvent ce) {
    }

    public void btnDeltAction(ButtonEvent ce) {
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
