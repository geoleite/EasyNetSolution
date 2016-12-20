package br.com.easyportal.gwt.client.admin.portal.portal.con_componente_negado;

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
public class Con_componente_negadoUpdateDeleteGWT extends AlterarExcluirBaseGWT {
    private Con_componente_negadoConsultGWT con_componente_negadoConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Con_componente_negadoDAOGWT con_componente_negadoDao = new Con_componente_negadoDAOGWT();
    private Con_componente_negadoTGWT con_componente_negadoT;

    private TextField<String> con_nr_id = new TextField<String>();
    private TextField<String> int_nr_id = new TextField<String>();
    private TextField<String> per_nr_id = new TextField<String>();
    private TextField<String> con_tx_visivel = new TextField<String>();
    private TextField<String> con_tx_ativo = new TextField<String>();


    public Con_componente_negadoUpdateDeleteGWT() {
        this.setSize("500", "400");
	
        getCpMaster().setLayout(new TableLayout(2));
        getCpMaster().add(new LabelField("con_nr_id:"));
        getCpMaster().add(con_nr_id);

        getCpMaster().add(new LabelField("int_nr_id:"));
        getCpMaster().add(int_nr_id);

        getCpMaster().add(new LabelField("per_nr_id:"));
        getCpMaster().add(per_nr_id);

        getCpMaster().add(new LabelField("con_tx_visivel:"));
        getCpMaster().add(con_tx_visivel);

        getCpMaster().add(new LabelField("con_tx_ativo:"));
        getCpMaster().add(con_tx_ativo);


    }

    public void load(Con_componente_negadoTGWT con_componente_negadoT) {
	this.con_componente_negadoT = con_componente_negadoT;
		con_nr_id.setValue(con_componente_negadoT.getCon_nr_id() + "");
		int_nr_id.setValue(con_componente_negadoT.getInt_nr_id() + "");
		per_nr_id.setValue(con_componente_negadoT.getPer_nr_id() + "");
		con_tx_visivel.setValue(con_componente_negadoT.getCon_tx_visivel());
		con_tx_ativo.setValue(con_componente_negadoT.getCon_tx_ativo());

    }
    public void btnUpdateAction(ButtonEvent ce) {
		con_componente_negadoT.setCon_nr_id( Integer.parseInt(con_nr_id.getValue()));
		con_componente_negadoT.setInt_nr_id( Integer.parseInt(int_nr_id.getValue()));
		con_componente_negadoT.setPer_nr_id( Integer.parseInt(per_nr_id.getValue()));
		con_componente_negadoT.setCon_tx_visivel(con_tx_visivel.getValue());
		con_componente_negadoT.setCon_tx_ativo(con_tx_ativo.getValue());

	con_componente_negadoDao.alterar(con_componente_negadoT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = con_componente_negadoDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  con_componente_negadoConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

	con_componente_negadoDao.excluir(con_componente_negadoT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = con_componente_negadoDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  con_componente_negadoConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
    }


   /**
     * @return the con_componente_negadoConsult
     */
    public Con_componente_negadoConsultGWT getCon_componente_negadoConsult() {
        return con_componente_negadoConsult;
    }

    /**
     * @param con_componente_negadoConsult the con_componente_negadoConsult to set
     */
    public void setCon_componente_negadoConsult(Con_componente_negadoConsultGWT con_componente_negadoConsult) {
        this.con_componente_negadoConsult = con_componente_negadoConsult;
    }

}

