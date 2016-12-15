package br.com.i9.portal.client.portal.portal.met_metodo;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Met_metodoService;
import br.com.i9.portal.client.rpc.Met_metodoServiceAsync;
import br.com.i9.portal.client.rpc.Ope_operacaoService;
import br.com.i9.portal.client.rpc.Ope_operacaoServiceAsync;
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
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.user.client.Timer;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.core.client.GWT;
//import com.google.gwt.dev.js.rhino.TokenStream;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author geoleite
 */

public class Met_metodoUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Met_metodoConsultGWT met_metodoConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Met_metodoDAOGWT met_metodoDao = new Met_metodoDAOGWT();
    private Met_metodoTGWT met_metodoT;
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private Ope_operacaoDAOGWT opeDao = new Ope_operacaoDAOGWT();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private ComboBox<Ope_operacaoTGWT> cbOperacao = new ComboBox<Ope_operacaoTGWT>();
    private EasyTextField<String> met_tx_metodo = new EasyTextField<String>();
    private EasyTextField<String> met_tx_descricao = new EasyTextField<String>();
    private Radio rAtivo = new Radio();
    private Radio rInativo = new Radio();
    private RadioGroup met_tx_status = new RadioGroup();

    public Met_metodoUpdateDeleteGWT() {
        setHeading("Alterar Método");
        this.setSize("500", "250");
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);
        TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        LabelField lfSistema = new LabelField("Sistema:");
        lfSistema.setId("lfsistema");
        getCpMaster().add(lfSistema);
        getCpMaster().add(cbSistema);

        LabelField lfOperacao = new LabelField("Operação:");
        lfOperacao.setId("lfoperacao");
        getCpMaster().add(lfOperacao);
        cbOperacao.setWidth(300);
        getCpMaster().add(cbOperacao);

        LabelField lfmet_tx_metodo = new LabelField("Metodo:");
        lfmet_tx_metodo.setId("lfmet_tx_metodo");
        getCpMaster().add(lfmet_tx_metodo);
        met_tx_metodo.setId("met_tx_metodo");
        met_tx_metodo.setMaxLength(100);
        met_tx_metodo.setWidth(200);
        met_tx_metodo.setUpperCase(false);
        met_tx_metodo.setAllowCharSpecial(true);
        getCpMaster().add(met_tx_metodo);

        LabelField lfmet_tx_status = new LabelField("Status:");
        lfmet_tx_status.setId("lfmet_tx_status");
        getCpMaster().add(lfmet_tx_status);
        rAtivo.setBoxLabel("Ativo");
        rInativo.setBoxLabel("Inativo");
        met_tx_status.add(rAtivo);
        met_tx_status.add(rInativo);
        met_tx_status.setValue(rAtivo);
        getCpMaster().add(met_tx_status);

        LabelField lfmet_tx_descricao = new LabelField("Descrição:");
        lfmet_tx_descricao.setId("lfmet_tx_descricao");
        getCpMaster().add(lfmet_tx_descricao);
        met_tx_descricao.setId("met_tx_descricao");
        met_tx_descricao.setMaxLength(80);
        met_tx_descricao.setWidth(300);
        getCpMaster().add(met_tx_descricao);

        cbSistema.setEmptyText("Sel. Sistema");
        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setMinChars(1);
        cbSistema.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbSistema.setTypeAhead(true);

        cbOperacao.setEmptyText("Sel. Operacao");
        cbOperacao.setDisplayField("ope_tx_nome");
        cbOperacao.setMinChars(1);
        cbOperacao.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbOperacao.setTypeAhead(true);
        cbOperacao.setWidth(300);

        cbSistema.addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<Sis_sistemaTGWT>>() {

            public void handleEvent(SelectionChangedEvent<Sis_sistemaTGWT> be) {
                loadOperacoes();
            }
        });
    }

    private void loadOperacoes() {
        Ope_operacaoTGWT opeT = new Ope_operacaoTGWT();
        opeT.setSis_nr_id(met_metodoT.getSis_nr_id());

        AsyncCallback<List<Ope_operacaoTGWT>> callback = new AsyncCallback<List<Ope_operacaoTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Erro ao consultar Operação", null);
            }

            @Override
            public void onSuccess(List<Ope_operacaoTGWT> result) {
                ListStore<Ope_operacaoTGWT> list = new ListStore<Ope_operacaoTGWT>();
                list.add(result);
                cbOperacao.setStore(list);
                cbOperacao.getView().refresh();
                cbOperacao.setValue(cbOperacao.getStore().findModel("ope_nr_id", met_metodoT.getOpe_nr_id()));

            }
        };
        Ope_operacaoServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getOpe_operacaoService();
        serviceAsync.consultBySistema(opeT, callback);

//        opeDao.consultBySistema(opeT);
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                ListStore<Ope_operacaoTGWT> list = opeDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//                    cbOperacao.setStore(list);
//                    for (int i = 0; i < list.getCount(); i++) {
//                        Ope_operacaoTGWT opeT = list.getAt(i);
//                        if (opeT.getOpe_nr_id() == met_metodoT.getOpe_nr_id()) {
//                            cbOperacao.setValue(opeT);
//                            break;
//                        }
//                    }
//                    cbOperacao.getView().refresh();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    private void loadSistemas(final Met_metodoTGWT met_metodoT) {

        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Erro ao consultar sistema", null);
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                ListStore<Sis_sistemaTGWT> store = new ListStore<Sis_sistemaTGWT>();
                store.add(result);
                cbSistema.setStore(store);
                cbSistema.getView().refresh();
                cbSistema.setValue(cbSistema.getStore().findModel("sis_nr_id", met_metodoT.getSis_nr_id()));
                //loadOperacoes();

            }
        };
        Sis_sistemaServiceAsync async = EasyAdmPortalRPCFactory.getSis_sistemaService();
        async.consult(callback);

//        sisDao.consultarTodos();
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                ListStore<Sis_sistemaTGWT> list = sisDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//                    cbSistema.setStore(list);
//                    for (int i = 0; i < list.getCount(); i++) {
//                        Sis_sistemaTGWT sisT = list.getAt(i);
//                        if (sisT.getSis_nr_id() == met_metodoT.getSis_nr_id()) {
//                            cbSistema.setValue(sisT);
//                            loadOperacoes();
//                            break;
//                        }
//                    }
//                    cbSistema.getView().refresh();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void load(Met_metodoTGWT met_metodoT) {
        this.met_metodoT = met_metodoT;
        met_tx_metodo.setValue(met_metodoT.getMet_tx_metodo());
        met_tx_status.setValue("A".equals(met_metodoT.getMet_tx_status()) ? rAtivo : rInativo);
        met_tx_descricao.setValue(met_metodoT.getMet_tx_descricao());
        loadSistemas(met_metodoT);
    }

    public void btnUpdateAction(ButtonEvent ce) {
        met_metodoT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
        met_metodoT.setOpe_nr_id(cbOperacao.getValue().getOpe_nr_id());
        met_metodoT.setMet_tx_metodo(met_tx_metodo.getValue());
        met_metodoT.setMet_tx_status(met_tx_status.getValue() == rAtivo ? "A" : "I");
        met_metodoT.setMet_tx_descricao(met_tx_descricao.getValue());

        AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Falha ao reaalizar a operação", null);
            }

            @Override
            public void onSuccess(Boolean result) {
                Info.display("Resposta", "Sucesso!");
                met_metodoConsult.load();
                setVisible(false);
            }
        };
        Met_metodoServiceAsync async = EasyAdmPortalRPCFactory.getMet_MetodoService();
        async.update(met_metodoT, callback);

//        
//        met_metodoDao.alterar(met_metodoT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = met_metodoDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        met_metodoConsult.load();
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
                    met_metodoDao.excluir(met_metodoT);
                    AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.info("IMPORTANTE", "Falha ao reaalizar a operação", null);
                        }

                        @Override
                        public void onSuccess(Boolean result) {
                            Info.display("Resposta", "Sucesso!");
                        }
                    };
                    Met_metodoServiceAsync async = EasyAdmPortalRPCFactory.getMet_MetodoService();
                    async.delete(met_metodoT, callback);
                    
//                    Timer timer = new Timer() {
//
//                        public void run() {
//                            String msg = met_metodoDao.getMsg();
//                            if (msg == null) {
//                                schedule(500);
//                            } else {
//                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                                    MessageBox.alert("Problemas", msg, null);
//                                } else {
//                                    Info.display("Resultado", msg);
//                                    met_metodoConsult.load();
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
     * @return the met_metodoConsult
     */
    public Met_metodoConsultGWT getMet_metodoConsult() {
        return met_metodoConsult;
    }

    /**
     * @param met_metodoConsult the met_metodoConsult to set
     */
    public void setMet_metodoConsult(Met_metodoConsultGWT met_metodoConsult) {
        this.met_metodoConsult = met_metodoConsult;
    }
}
