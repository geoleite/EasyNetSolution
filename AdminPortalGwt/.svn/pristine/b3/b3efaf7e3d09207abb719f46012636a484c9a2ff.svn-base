package br.com.i9.portal.client.portal.portal.sis_sistema;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;

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
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

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
    private EasyTextField<String> sis_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> sis_tx_descricao = new EasyTextField<String>();
    private Radio rAtivo = new Radio();
    private Radio rInativo = new Radio();
    private RadioGroup sis_tx_status = new RadioGroup();

    public Sis_sistemaUpdateDeleteGWT() {
        setHeading("Alterar Sistema");
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

    public void load(Sis_sistemaTGWT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
        sis_tx_nome.setValue(sis_sistemaT.getSis_tx_nome());
        sis_tx_descricao.setValue(sis_sistemaT.getSis_tx_descricao());
        sis_tx_status.setValue("A".equals(sis_sistemaT.getSis_tx_status()) ? rAtivo : rInativo);
        layout();

    }

    public void btnUpdateAction(ButtonEvent ce) {
        sis_sistemaT.setSis_tx_nome(sis_tx_nome.getValue());
        sis_sistemaT.setSis_tx_descricao(sis_tx_descricao.getValue());
        sis_sistemaT.setSis_tx_status(sis_tx_status.getValue() == rAtivo ? "A" : "I");
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Falha ao realizar a operação", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                sis_sistemaConsult.load();
                setVisible(false);
            }
        };
        Sis_sistemaServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getSis_sistemaService();
        serviceAsync.update(sis_sistemaT, callback);
//
//        sis_sistemaDao.alterar(sis_sistemaT);
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
//                        sis_sistemaConsult.load();
//                        setVisible(false);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

        MessageBox.confirm("Aviso", "Tem certeza que deseja excluir registro?", new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
                    AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                        @Override
                        public void onFailure(Throwable caught) {

                        }

                        @Override
                        public void onSuccess(Void result) {
                            Info.display("Resultado", "Sucesso!");
                            sis_sistemaConsult.load();
                            setVisible(false);
                        }
                    };
                    Sis_sistemaServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getSis_sistemaService();
                    serviceAsync.delete(sis_sistemaT, callback);
//        
//                    sis_sistemaDao.excluir(sis_sistemaT);
//                    Timer timer = new Timer() {
//
//                        public void run() {
//                            String msg = sis_sistemaDao.getMsg();
//                            if (msg == null) {
//                                schedule(500);
//                            } else {
//                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                                    MessageBox.alert("Problemas", msg, null);
//                                } else {
//                                    Info.display("Resultado", msg);
//                                    sis_sistemaConsult.load();
//                                    setVisible(false);
//                                }
//                            }
//                        }
//                    };
//                    timer.schedule(500);
                }
            }
        });
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
