package br.com.i9.portal.client.portal.portal.par_parametro;

import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Par_parametroService;
import br.com.i9.portal.client.rpc.Par_parametroServiceAsync;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Par_parametroUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Par_parametroConsultGWT par_parametroConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Par_parametroDAOGWT par_parametroDao = new Par_parametroDAOGWT();
    private Par_parametroTGWT par_parametroT;
    private EasyTextField<String> par_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> par_tx_valor = new EasyTextField<String>();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();

    public Par_parametroUpdateDeleteGWT() {
        setHeading("Alterar Parâmetro");
        this.setSize("400", "180");
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);

        TableLayout tl = new TableLayout(2);
        tl.setCellPadding(4);
        getCpMaster().setLayout(tl);

        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbSistema.setTypeAhead(true);
        cbSistema.setMinChars(1);

        LabelField lfsis_nr_id = new LabelField("Sistema:");
        lfsis_nr_id.setId("lfsis_nr_id");
        getCpMaster().add(lfsis_nr_id);
        getCpMaster().add(cbSistema);

        LabelField lfpar_tx_nome = new LabelField("Parâmetro:");
        lfpar_tx_nome.setId("lfpar_tx_nome");
        getCpMaster().add(lfpar_tx_nome);
        par_tx_nome.setId("par_tx_nome");
        par_tx_nome.setMaxLength(100);
        par_tx_nome.setWidth(150);
        par_tx_nome.setUpperCase(false);
        getCpMaster().add(par_tx_nome);

        LabelField lfpar_tx_valor = new LabelField("Valor:");
        lfpar_tx_valor.setId("lfpar_tx_valor");
        getCpMaster().add(lfpar_tx_valor);
        par_tx_valor.setId("par_tx_valor");
        par_tx_valor.setMaxLength(500);
        par_tx_valor.setWidth(250);
        par_tx_valor.setAllowCharSpecial(true);
        par_tx_valor.setUpperCase(false);
        getCpMaster().add(par_tx_valor);

    }

    private void loadSistema(final int sis_nr_id) {
        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Falha ao execultar a operação", null);
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                //                    cbSistema.setStore(list);
                ListStore<Sis_sistemaTGWT> store = new ListStore<Sis_sistemaTGWT>();
                store.add(result);
                cbSistema.setStore(store);
                cbSistema.getView().refresh();
                cbSistema.setValue(cbSistema.getStore().findModel("sis_nr_id", sis_nr_id));
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
//                        if (sisT.getSis_nr_id() == par_parametroT.getSis_nr_id()) {
//                            cbSistema.setValue(sisT);
//                            break;
//                        }
//                    }
//                    cbSistema.getView().refresh();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void load(Par_parametroTGWT par_parametroT) {
        this.par_parametroT = par_parametroT;
        par_tx_nome.setValue(par_parametroT.getPar_tx_nome());
        //¡¢¶
        String valor = par_parametroT.getPar_tx_valor();
        //valor = valor.replaceAll("<", "¢");
        //valor = valor.replaceAll(">", "¶");
        par_tx_valor.setValue(valor);
        loadSistema(par_parametroT.getSis_nr_id());
    }

    public void btnUpdateAction(ButtonEvent ce) {
        par_parametroT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
        par_parametroT.setPar_tx_nome(par_tx_nome.getValue());
        par_parametroT.setPar_tx_valor(par_tx_valor.getValue());

        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Erro ao execultar a operação!", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resposta", "sucesso!");
                par_parametroConsult.load();
                
                setVisible(false);
            }
        };
        Par_parametroServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getPar_ParametroService();
        serviceAsync.update(par_parametroT, callback);

//        par_parametroDao.alterar(par_parametroT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = par_parametroDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        par_parametroConsult.load();
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
                            MessageBox.info("ATENÇÃO", "Erro ao execultar a operação!", null);
                        }

                        @Override
                        public void onSuccess(Void result) {
                            Info.display("Resposta", "sucesso!");
                        }
                    };
                    Par_parametroServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getPar_ParametroService();
                    serviceAsync.delete(par_parametroT, callback);
//                    par_parametroDao.excluir(par_parametroT);
//                    Timer timer = new Timer() {
//
//                        public void run() {
//                            String msg = par_parametroDao.getMsg();
//                            if (msg == null) {
//                                schedule(500);
//                            } else {
//                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                                    MessageBox.alert("Problemas", msg, null);
//                                } else {
//                                    Info.display("Resultado", msg);
//                                    par_parametroConsult.load();
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
     * @return the par_parametroConsult
     */
    public Par_parametroConsultGWT getPar_parametroConsult() {
        return par_parametroConsult;
    }

    /**
     * @param par_parametroConsult the par_parametroConsult to set
     */
    public void setPar_parametroConsult(Par_parametroConsultGWT par_parametroConsult) {
        this.par_parametroConsult = par_parametroConsult;
    }
}
