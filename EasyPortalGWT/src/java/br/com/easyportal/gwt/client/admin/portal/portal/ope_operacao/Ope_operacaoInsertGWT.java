/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.admin.portal.portal.ope_operacao;

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
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Ope_operacaoInsertGWT extends CadastrarBaseGWT {

    private Ope_operacaoConsultGWT ope_operacaoConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Ope_operacaoDAOGWT ope_operacaoDao = new Ope_operacaoDAOGWT();
    private TextField<String> ope_tx_nome = new TextField<String>();
    private TextField<String> ope_tx_url = new TextField<String>();
    private TextField<String> ope_tx_descricao = new TextField<String>();
    private TextField<String> ope_tx_classe = new TextField<String>();
    private Radio rOpe_tx_statusAtivo = new Radio();
    private Radio rOpe_tx_statusInativo = new Radio();
    private RadioGroup rgOpe_tx_status = new RadioGroup("Status");
    private Sis_sistemaTGWT sisT;
    public Ope_operacaoInsertGWT() {
        setHeading("Cadastrar Operação");
        setModal(true);

        this.setSize("350", "270");

        getCpMaster().add(new LabelField("Nome:"));
        getCpMaster().add(ope_tx_nome);

        getCpMaster().add(new LabelField("Descrição:"));
        getCpMaster().add(ope_tx_descricao);

        getCpMaster().add(new LabelField("Class Java:"));
        getCpMaster().add(ope_tx_classe);

        getCpMaster().add(new LabelField("Status:"));
        getCpMaster().add(rgOpe_tx_status);
        rOpe_tx_statusAtivo.setValue(true);
        rOpe_tx_statusAtivo.setBoxLabel("Ativo");
        rOpe_tx_statusInativo.setBoxLabel("Inativo");
        rgOpe_tx_status.add(rOpe_tx_statusAtivo);
        rgOpe_tx_status.add(rOpe_tx_statusInativo);
    }

    public void btnInsertAction(ButtonEvent ce) {
        Ope_operacaoTGWT ope_operacaoT = new Ope_operacaoTGWT();
        ope_operacaoT.setSis_nr_id(sisT.getSis_nr_id());
        ope_operacaoT.setOpe_tx_nome(ope_tx_nome.getValue());
        ope_operacaoT.setOpe_tx_url(" ");
        ope_operacaoT.setOpe_tx_descricao(ope_tx_descricao.getValue());
        ope_operacaoT.setOpe_tx_classe(ope_tx_classe.getValue());
        ope_operacaoT.setOpe_tx_status(rOpe_tx_statusAtivo.getValue()?"A":"I");

        
        ope_operacaoDao.inserir(ope_operacaoT);
        Timer timer = new Timer() {

            public void run() {
                String msg = ope_operacaoDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        btnLimpartAction(null);
                        ope_operacaoConsult.load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    public void btnLimpartAction(ButtonEvent ce) {
        ope_tx_nome.setValue("");
        rOpe_tx_statusAtivo.setValue(true);
        ope_tx_url.setValue("");
        ope_tx_descricao.setValue("");
        ope_tx_classe.setValue("");

    }

    /**
     * @return the ope_operacaoConsult
     */
    public Ope_operacaoConsultGWT getOpe_operacaoConsult() {
        return ope_operacaoConsult;
    }

    /**
     * @param ope_operacaoConsult the ope_operacaoConsult to set
     */
    public void setOpe_operacaoConsult(Ope_operacaoConsultGWT ope_operacaoConsult) {
        this.ope_operacaoConsult = ope_operacaoConsult;
    }

    /**
     * @return the sisT
     */
    public Sis_sistemaTGWT getSisT() {
        return sisT;
    }

    /**
     * @param sisT the sisT to set
     */
    public void setSisT(Sis_sistemaTGWT sisT) {
        this.sisT = sisT;
    }
}
