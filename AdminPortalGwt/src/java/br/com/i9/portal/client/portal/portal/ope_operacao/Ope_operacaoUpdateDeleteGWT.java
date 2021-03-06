package br.com.i9.portal.client.portal.portal.ope_operacao;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Ope_operacaoService;
import br.com.i9.portal.client.rpc.Ope_operacaoServiceAsync;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;
import com.extjs.gxt.ui.client.data.ModelData;

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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Ope_operacaoUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Ope_operacaoConsultGWT ope_operacaoConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Ope_operacaoDAOGWT ope_operacaoDao = new Ope_operacaoDAOGWT();
    private Ope_operacaoTGWT ope_operacaoT;
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private EasyTextField<String> ope_tx_nome = new EasyTextField<String>();
    private Radio rAtivo = new Radio();
    private Radio rInativo = new Radio();
    private RadioGroup ope_tx_status = new RadioGroup();
    private EasyTextField<String> ope_tx_url = new EasyTextField<String>();
    private EasyTextField<String> ope_tx_descricao = new EasyTextField<String>();
    private EasyTextField<String> ope_tx_classe = new EasyTextField<String>();

    public Ope_operacaoUpdateDeleteGWT() {
        setHeading("Alterar Operacao");
        this.setSize("500", "310");
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);

        TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        LabelField lfSistema = new LabelField("Sistema:");
        getCpMaster().add(lfSistema);
        getCpMaster().add(cbSistema);

        LabelField lfope_tx_nome = new LabelField("Nome:");
        lfope_tx_nome.setId("lfope_tx_nome");
        getCpMaster().add(lfope_tx_nome);
        ope_tx_nome.setId("ope_tx_nome");
        ope_tx_nome.setMaxLength(80);
        ope_tx_nome.setWidth(200);
        getCpMaster().add(ope_tx_nome);

        LabelField lfope_tx_url = new LabelField("URL:");
        lfope_tx_url.setId("lfope_tx_url");
        getCpMaster().add(lfope_tx_url);
        ope_tx_url.setId("ope_tx_url");
        ope_tx_url.setMaxLength(250);
        ope_tx_url.setWidth(400);
        ope_tx_url.setUpperCase(false);
        ope_tx_url.setAllowCharSpecial(true);
        getCpMaster().add(ope_tx_url);

        LabelField lfope_tx_descricao = new LabelField("Descricao:");
        lfope_tx_descricao.setId("lfope_tx_descricao");
        getCpMaster().add(lfope_tx_descricao);
        ope_tx_descricao.setId("ope_tx_descricao");
        ope_tx_descricao.setMaxLength(500);
        ope_tx_descricao.setWidth(400);

        getCpMaster().add(ope_tx_descricao);

        LabelField lfope_tx_classe = new LabelField("Classe:");
        lfope_tx_classe.setId("lfope_tx_classe");
        getCpMaster().add(lfope_tx_classe);
        ope_tx_classe.setId("ope_tx_classe");
        ope_tx_classe.setMaxLength(250);
        ope_tx_classe.setWidth(400);
        ope_tx_classe.setUpperCase(false);
        ope_tx_classe.setAllowCharSpecial(true);
        getCpMaster().add(ope_tx_classe);

        LabelField lfope_tx_status = new LabelField("Status:");
        lfope_tx_status.setId("lfope_tx_status");
        getCpMaster().add(lfope_tx_status);
        ope_tx_status.setId("ope_tx_status");
        rAtivo.setBoxLabel("Ativo");
        rInativo.setBoxLabel("Inativo");
        ope_tx_status.add(rAtivo);
        ope_tx_status.add(rInativo);
        ope_tx_status.setValue(rAtivo);
        getCpMaster().add(ope_tx_status);

        cbSistema.setEmptyText("Sel. Sistema");
        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setMinChars(1);
        cbSistema.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbSistema.setTypeAhead(true);

    }

    private void loadSistemas(final int sisNrId) {

        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Erro ao execultar a consulta do sistema", null);
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                ListStore<Sis_sistemaTGWT> store = new ListStore<Sis_sistemaTGWT>();
                store.add(result);
                cbSistema.setStore(store);
                cbSistema.getView().refresh();
                cbSistema.setValue(cbSistema.getStore().findModel("sis_nr_id", sisNrId));

            }
        };
        Sis_sistemaServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getSis_sistemaService();
        serviceAsync.consult(callback);

//        
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
//                        if (sisT.getSis_nr_id() == sisNrId) {
//                            cbSistema.setValue(sisT);
//                            break;
//                        }
//                    }
//                    cbSistema.getView().refresh();
//                    layout();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void load(Ope_operacaoTGWT ope_operacaoT) {
        this.ope_operacaoT = ope_operacaoT;
        ope_tx_nome.setValue(ope_operacaoT.getOpe_tx_nome());
        ope_tx_status.setValue("A".equals(ope_operacaoT.getOpe_tx_status()) ? rAtivo : rInativo);
        ope_tx_url.setValue(ope_operacaoT.getOpe_tx_url());
        ope_tx_descricao.setValue(ope_operacaoT.getOpe_tx_descricao());
        ope_tx_classe.setValue(ope_operacaoT.getOpe_tx_classe());
        loadSistemas(ope_operacaoT.getSis_nr_id());
    }

    public void btnUpdateAction(ButtonEvent ce) {
        ope_operacaoT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
        ope_operacaoT.setOpe_tx_nome(ope_tx_nome.getValue());
        ope_operacaoT.setOpe_tx_status(ope_tx_status.getValue() == rAtivo ? "A" : "I");
        ope_operacaoT.setOpe_tx_url(ope_tx_url.getValue());
        ope_operacaoT.setOpe_tx_descricao(ope_tx_descricao.getValue());
        ope_operacaoT.setOpe_tx_classe(ope_tx_classe.getValue());

        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Erro ao execultar a operação", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                ope_operacaoConsult.load();
                setVisible(false);
            }
        };
        Ope_operacaoServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getOpe_operacaoService();
        serviceAsync.update(ope_operacaoT, callback);

//        ope_operacaoDao.alterar(ope_operacaoT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = ope_operacaoDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        ope_operacaoConsult.load();
//                        setVisible(false);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
//    }
    }

    public void btnDeltAction(ButtonEvent ce) {

        MessageBox.confirm("Aviso", "Tem certeza que deseja excluir registro?", new Listener<MessageBoxEvent>() {

            public void handleEvent(MessageBoxEvent be) {

                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
                    AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.info("ATENÇÃO", "Erro ao execultar a operação", null);
                        }

                        @Override
                        public void onSuccess(Void result) {
                            Info.display("Resultado", "Sucesso!");
                            ope_operacaoConsult.load();
                            setVisible(false);
                        }
                    };
                    Ope_operacaoServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getOpe_operacaoService();
                    serviceAsync.delete(ope_operacaoT, callback);
//                    ope_operacaoDao.excluir(ope_operacaoT);
//                    Timer timer = new Timer() {
//
//                        public void run() {
//                            String msg = ope_operacaoDao.getMsg();
//                            if (msg == null) {
//                                schedule(500);
//                            } else {
//                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                                    MessageBox.alert("Problemas", msg, null);
//                                } else {
//                                    Info.display("Resultado", msg);
//                                    ope_operacaoConsult.load();
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
}
