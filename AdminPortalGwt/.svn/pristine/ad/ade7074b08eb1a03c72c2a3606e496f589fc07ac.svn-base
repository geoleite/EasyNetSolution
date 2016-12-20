/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.mep_men_per;

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
public class Mep_men_perInsertGWT extends CadastrarBaseGWT {

    private Mep_men_perConsultGWT mep_men_perConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Mep_men_perDAOGWT mep_men_perDao = new Mep_men_perDAOGWT();
    private EasyTextField<String> per_nr_id = new EasyTextField<String>();
    private EasyTextField<String> men_nr_id = new EasyTextField<String>();

    public Mep_men_perInsertGWT() {
        setHeading("Cadastrar Mep_men_per");
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

        LabelField lfmen_nr_id = new LabelField("men_nr_id:");
        lfmen_nr_id.setId("lfmen_nr_id");
        getCpMaster().add(lfmen_nr_id);
        men_nr_id.setId("men_nr_id");
        men_nr_id.setMaxLength(10);
        getCpMaster().add(men_nr_id);


    }

    public void btnInsertAction(ButtonEvent ce) {
        Mep_men_perTGWT mep_men_perT = new Mep_men_perTGWT();
        mep_men_perT.setPer_nr_id(Integer.parseInt(per_nr_id.getValue()));
        mep_men_perT.setMen_nr_id(Integer.parseInt(men_nr_id.getValue()));

        mep_men_perDao.inserir(mep_men_perT);
        Timer timer = new Timer() {

            public void run() {
                String msg = mep_men_perDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        btnLimpartAction(null);
                        mep_men_perConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    public void btnLimpartAction(ButtonEvent ce) {
        per_nr_id.setValue("");
        men_nr_id.setValue("");

    }

    /**
     * @return the mep_men_perConsult
     */
    public Mep_men_perConsultGWT getMep_men_perConsult() {
        return mep_men_perConsult;
    }

    /**
     * @param mep_men_perConsult the mep_men_perConsult to set
     */
    public void setMep_men_perConsult(Mep_men_perConsultGWT mep_men_perConsult) {
        this.mep_men_perConsult = mep_men_perConsult;
    }
}
