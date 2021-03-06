/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.par_parametro;

import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.CadastrarBaseGWT;
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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Par_parametroInsertGWT extends CadastrarBaseGWT {

    private Par_parametroConsultGWT par_parametroConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Par_parametroDAOGWT par_parametroDao = new Par_parametroDAOGWT();
    private EasyTextField<String> par_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> par_tx_valor = new EasyTextField<String>();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();

    public Par_parametroInsertGWT() {
        setHeading("Cadastrar Parâmetro");
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

        loadSistema();
    }

    private void loadSistema() {
        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                ListStore<Sis_sistemaTGWT> listStore = new ListStore<Sis_sistemaTGWT>();
                listStore.add(result);
                cbSistema.setStore(listStore);
                cbSistema.getView().refresh();
            }
        };
        Sis_sistemaServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getSis_sistemaService();
        serviceAsync.consult(callback);
        
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
//                    cbSistema.getView().refresh();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void btnInsertAction(ButtonEvent ce) {
        Par_parametroTGWT par_parametroT = new Par_parametroTGWT();
        par_parametroT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
        par_parametroT.setPar_tx_nome(par_tx_nome.getValue());
        par_parametroT.setPar_tx_valor(par_tx_valor.getValue());
        
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                btnLimpartAction(null);
                par_parametroConsult.load();
            }
        };
        Par_parametroServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getPar_ParametroService();
        serviceAsync.insert(par_parametroT, callback);

//        par_parametroDao.inserir(par_parametroT);
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
//                        btnLimpartAction(null);
//                        par_parametroConsult.load();
//                        setVisible(false);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void btnLimpartAction(ButtonEvent ce) {
        cbSistema.setValue(null);
        par_tx_nome.setValue("");
        par_tx_valor.setValue("");
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
