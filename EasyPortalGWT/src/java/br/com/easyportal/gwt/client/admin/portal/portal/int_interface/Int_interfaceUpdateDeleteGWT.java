package br.com.easyportal.gwt.client.admin.portal.portal.int_interface;

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
public class Int_interfaceUpdateDeleteGWT extends AlterarExcluirBaseGWT {
    private Int_interfaceConsultGWT int_interfaceConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Int_interfaceDAOGWT int_interfaceDao = new Int_interfaceDAOGWT();
    private Int_interfaceTGWT int_interfaceT;

    private TextField<String> int_nr_id = new TextField<String>();
    private TextField<String> int_tx_nome = new TextField<String>();


    public Int_interfaceUpdateDeleteGWT() {
        this.setSize("500", "400");
	
        getCpMaster().setLayout(new TableLayout(2));
        getCpMaster().add(new LabelField("int_nr_id:"));
        getCpMaster().add(int_nr_id);

        getCpMaster().add(new LabelField("int_tx_nome:"));
        getCpMaster().add(int_tx_nome);


    }

    public void load(Int_interfaceTGWT int_interfaceT) {
	this.int_interfaceT = int_interfaceT;
		int_nr_id.setValue(int_interfaceT.getInt_nr_id() + "");
		int_tx_nome.setValue(int_interfaceT.getInt_tx_nome());

    }
    public void btnUpdateAction(ButtonEvent ce) {
		int_interfaceT.setInt_nr_id( Integer.parseInt(int_nr_id.getValue()));
		int_interfaceT.setInt_tx_nome(int_tx_nome.getValue());

	int_interfaceDao.alterar(int_interfaceT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = int_interfaceDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  int_interfaceConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

	int_interfaceDao.excluir(int_interfaceT);
	Timer timer = new Timer() {
	   public void run() {
 	     String msg = int_interfaceDao.getMsg();
 	     if (msg == null) {
		schedule(500);
	     } else {
		if (msg.toUpperCase().indexOf("FALHA") >=0 ) {
		  MessageBox.alert("Problemas", msg, null);
		} else {
		  Info.display("Resultado", msg);
		  int_interfaceConsult.load();
		  setVisible(false);
		}
	     }
	   }
        };
	timer.schedule(500);
    }


   /**
     * @return the int_interfaceConsult
     */
    public Int_interfaceConsultGWT getInt_interfaceConsult() {
        return int_interfaceConsult;
    }

    /**
     * @param int_interfaceConsult the int_interfaceConsult to set
     */
    public void setInt_interfaceConsult(Int_interfaceConsultGWT int_interfaceConsult) {
        this.int_interfaceConsult = int_interfaceConsult;
    }

}

