package br.com.easyportal.gwt.client.admin.portal.portal.sis_sistema;

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
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Sis_sistemaUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Sis_sistemaConsultGWT sis_sistemaConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Sis_sistemaDAOGWT sis_sistemaDao = new Sis_sistemaDAOGWT();
    private Sis_sistemaTGWT sis_sistemaT;
    private TextField<String> sis_nr_id = new TextField<String>();
    private TextField<String> sis_tx_nome = new TextField<String>();
    private TextField<String> sis_tx_descricao = new TextField<String>();
    private Radio rSis_tx_statusAtivo = new Radio();
    private Radio rSis_tx_statusInativo = new Radio();
    private RadioGroup rgSis_tx_status = new RadioGroup("Status");

    public Sis_sistemaUpdateDeleteGWT() {
        setHeading("Alterando Sistema");
        setModal(true);
        this.setSize("400", "200");

        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(sis_tx_nome);

        getCpMaster().add(new LabelField("Descrição:"));
        getCpMaster().add(sis_tx_descricao);

        getCpMaster().add(new LabelField("Status:"));
        getCpMaster().add(rgSis_tx_status);
        rSis_tx_statusAtivo.setValue(true);
        rSis_tx_statusAtivo.setBoxLabel("Ativo");
        rSis_tx_statusInativo.setBoxLabel("Inativo");
        rgSis_tx_status.add(rSis_tx_statusAtivo);
        rgSis_tx_status.add(rSis_tx_statusInativo);
    }

    public void load(Sis_sistemaTGWT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
        sis_nr_id.setValue(sis_sistemaT.getSis_nr_id() + "");
        sis_tx_nome.setValue(sis_sistemaT.getSis_tx_nome());
        sis_tx_descricao.setValue(sis_sistemaT.getSis_tx_descricao());
        
        if ("A".equalsIgnoreCase(sis_sistemaT.getSis_tx_status())) {
            rSis_tx_statusAtivo.setValue(true);
        } else {
            rSis_tx_statusInativo.setValue(true);
        }

    }

    public void btnUpdateAction(ButtonEvent ce) {
        sis_sistemaT.setSis_nr_id(Integer.parseInt(sis_nr_id.getValue()));
        sis_sistemaT.setSis_tx_nome(sis_tx_nome.getValue());
        sis_sistemaT.setSis_tx_descricao(sis_tx_descricao.getValue());
        sis_sistemaT.setSis_tx_status(rSis_tx_statusAtivo.getValue()?"A":"I");

        sis_sistemaDao.alterar(sis_sistemaT);
        Timer timer = new Timer() {

            public void run() {
                String msg = sis_sistemaDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        sis_sistemaConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

        sis_sistemaDao.excluir(sis_sistemaT);
        Timer timer = new Timer() {

            public void run() {
                String msg = sis_sistemaDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        sis_sistemaConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    /**
     * @return the sis_sistemaConsult
     */
    public Sis_sistemaConsultGWT getSis_sistemaConsult() {
        return sis_sistemaConsult;
    }

    /**
     * @param sis_sistemaConsult the sis_sistemaConsult to set
     */
    public void setSis_sistemaConsult(Sis_sistemaConsultGWT sis_sistemaConsult) {
        this.sis_sistemaConsult = sis_sistemaConsult;
    }
}
