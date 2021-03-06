/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.sis_sistema;

import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.CadastrarBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;

import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class Sis_sistemaInsertGWT extends CadastrarBaseGWT {

    private Sis_sistemaConsultGWT sis_sistemaConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Sis_sistemaDAOGWT sis_sistemaDao = new Sis_sistemaDAOGWT();
    private EasyTextField<String> sis_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> sis_tx_descricao = new EasyTextField<String>();
    private Radio rAtivo = new Radio();
    private Radio rInativo = new Radio();
    private RadioGroup sis_tx_status = new RadioGroup();

    public Sis_sistemaInsertGWT() {
        setHeading("Cadastrar Sistema");
        this.setSize("420", "180");
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);

        TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        LabelField lfsis_tx_nome = new LabelField("Nome:");
        lfsis_tx_nome.setId("lfsis_tx_nome");
        getCpMaster().add(lfsis_tx_nome);
        sis_tx_nome.setId("sis_tx_nome");
        sis_tx_nome.setMaxLength(80);
        sis_tx_nome.setWidth(150);
        getCpMaster().add(sis_tx_nome);

        LabelField lfsis_tx_descricao = new LabelField("Descricao:");
        lfsis_tx_descricao.setId("lfsis_tx_descricao");
        getCpMaster().add(lfsis_tx_descricao);
        sis_tx_descricao.setId("sis_tx_descricao");
        sis_tx_descricao.setMaxLength(500);
        sis_tx_descricao.setWidth(300);
        getCpMaster().add(sis_tx_descricao);

        LabelField lfsis_tx_status = new LabelField("Status:");
        lfsis_tx_status.setId("lfsis_tx_status");
        getCpMaster().add(lfsis_tx_status);
        sis_tx_status.setId("sis_tx_status");
        rAtivo.setBoxLabel("Ativo");
        rInativo.setBoxLabel("Inativo");
        sis_tx_status.add(rAtivo);
        sis_tx_status.add(rInativo);
        sis_tx_status.setValue(rAtivo);
        getCpMaster().add(sis_tx_status);

    }

    public void btnInsertAction(ButtonEvent ce) {
        Sis_sistemaTGWT sis_sistemaT = new Sis_sistemaTGWT();
        sis_sistemaT.setSis_tx_nome(sis_tx_nome.getValue());
        sis_sistemaT.setSis_tx_descricao(sis_tx_descricao.getValue());
        sis_sistemaT.setSis_tx_status(sis_tx_status.getValue() == rAtivo ? "A" : "I");

        AsyncCallback<Void> asyncCallback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Falha ao realizar a operação", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                btnLimpartAction(null);
                sis_sistemaConsult.load();
                setVisible(false);
            }
        };
        Sis_sistemaServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getSis_sistemaService();
        serviceAsync.insert(sis_sistemaT, asyncCallback);
//
//        sis_sistemaDao.inserir(sis_sistemaT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = sis_sistemaDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        btnLimpartAction(null);
//                        sis_sistemaConsult.load();
//                        setVisible(false);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void btnLimpartAction(ButtonEvent ce) {
        sis_tx_nome.setValue("");
        sis_tx_descricao.setValue("");
        sis_tx_status.setValue(rAtivo);
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
