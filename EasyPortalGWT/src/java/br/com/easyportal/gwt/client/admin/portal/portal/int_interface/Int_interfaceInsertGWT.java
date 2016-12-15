/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.admin.portal.portal.int_interface;

import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
import br.com.easynet.gwt.client.CadastrarBaseGWT;

import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Int_interfaceInsertGWT extends CadastrarBaseGWT {

    private Int_interfaceConsultGWT int_interfaceConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Int_interfaceDAOGWT int_interfaceDao = new Int_interfaceDAOGWT();
    private TextField<String> int_tx_nome = new TextField<String>();

    public Int_interfaceInsertGWT() {
        setHeading("Cadastrar Interface");
        this.setSize("250", "150");
        getCpMaster().setLayout(new TableLayout(2));

        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(int_tx_nome);
    }

    public void btnInsertAction(ButtonEvent ce) {
        Int_interfaceTGWT int_interfaceT = new Int_interfaceTGWT();
        int_interfaceT.setInt_tx_nome(int_tx_nome.getValue());

        int_interfaceDao.inserir(int_interfaceT);
        Timer timer = new Timer() {

            public void run() {
                String msg = int_interfaceDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        btnLimpartAction(null);
                        int_interfaceConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    public void btnLimpartAction(ButtonEvent ce) {
        int_tx_nome.setValue("");
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
