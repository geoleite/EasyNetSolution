package br.com.i9.portal.client.portal.portal.men_menu;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;

import br.com.easynet.gwt.client.AlterarExcluirBaseGWT;
import br.com.easynet.gwt.client.component.EasyNumberField;
import br.com.easynet.gwt.client.component.EasyTextField;
import br.com.i9.portal.client.portal.portal.dao.Men_menuDAOGWT;
import br.com.i9.portal.client.portal.portal.dao.Sis_sistemaDAOGWT;
import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Sis_sistemaTGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Men_menuService;
import br.com.i9.portal.client.rpc.Men_menuServiceAsync;
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
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.SwallowEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Men_menuUpdateDeleteGWT extends AlterarExcluirBaseGWT {

    private Men_menuConsultGWT menuConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private Men_menuDAOGWT men_menuDao = new Men_menuDAOGWT();
    private Men_menuTGWT men_menuT;
    private EasyTextField<String> men_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> men_tx_url = new EasyTextField<String>();
    private EasyTextField<String> men_tx_acao = new EasyTextField<String>();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private EasyNumberField men_nr_ordem = new EasyNumberField();
    private RadioGroup men_tx_status = new RadioGroup();
    private Radio rAtivo = new Radio();
    private Radio rInativo = new Radio();
    private Men_menuTGWT menTPai;

    public Men_menuUpdateDeleteGWT() {
        setHeading("Alterar Menu");

        this.setSize("380", "300");
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);

        getCpMaster().setLayout(new TableLayout(2));

        getCpMaster().add(new LabelField("Sistema:"));
        getCpMaster().add(cbSistema);
        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setMinChars(1);
        cbSistema.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbSistema.setTypeAhead(true);

        getCpMaster().add(new LabelField("Nome:"));
        men_tx_nome.setUpperCase(false);
        men_tx_nome.setAllowCharSpecial(true);
        getCpMaster().add(men_tx_nome);
        men_tx_nome.setMaxLength(80);
        men_tx_nome.setWidth(200);

        getCpMaster().add(new LabelField("Açao GWT:"));
        getCpMaster().add(men_tx_acao);
        men_tx_acao.setAllowCharSpecial(true);
        men_tx_acao.setUpperCase(false);
        men_tx_acao.setMaxLength(80);
        men_tx_acao.setWidth(200);

        getCpMaster().add(new LabelField("Ordem:"));
        getCpMaster().add(men_nr_ordem);
        men_nr_ordem.setMaxLength(5);
        men_nr_ordem.setWidth(60);
        men_nr_ordem.setValue(1);

        getCpMaster().add(new LabelField("Url:"));
        getCpMaster().add(men_tx_url);
        men_tx_url.setUpperCase(false);
        men_tx_url.setAllowCharSpecial(true);
        men_tx_url.setMaxLength(80);
        men_tx_url.setWidth(250);
        men_tx_url.setValue("");

        getCpMaster().add(new LabelField("Status:"));
        getCpMaster().add(men_tx_status);
        rAtivo.setBoxLabel("Ativo");
        rInativo.setBoxLabel("Inativo");
        rAtivo.setValue(true);
        men_tx_status.add(rAtivo);
        men_tx_status.add(rInativo);

    }

    public void loadSistema() {

        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Falha ao consultar sistemas", null);
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                ListStore<Sis_sistemaTGWT> list = new ListStore<Sis_sistemaTGWT>();
                list.add(result);
                cbSistema.setStore(list);
                cbSistema.getView().refresh();
                cbSistema.setValue(cbSistema.getStore().findModel("sis_nr_id", men_menuT.getSis_nr_id()));
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
//                    for (int i = 0; i < list.getCount(); i++) {
//                        Sis_sistemaTGWT sisT = list.getAt(i);
//                        if (sisT.getSis_nr_id() == men_menuT.getSis_nr_id()) {
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

    public void load(Men_menuTGWT men_menuTGWT) {
        this.men_menuT = men_menuTGWT;
        men_tx_nome.setValue(men_menuT.getMen_tx_nome());
        men_tx_acao.setValue(men_menuT.getMen_tx_acao());
        men_tx_url.setValue(men_menuT.getMen_tx_url());
        men_nr_ordem.setValue(men_menuT.getMen_nr_ordem());
        men_tx_status.setValue("A".equals(men_menuTGWT.getMen_tx_status()) ? rAtivo : rInativo);
        loadSistema();
    }

    public void btnUpdateAction(ButtonEvent ce) {
        men_menuT.setMen_tx_nome(men_tx_nome.getValue());
        men_menuT.setMen_tx_acao(men_tx_acao.getValue());
        men_menuT.setMen_tx_url(men_tx_url.getValue());
        men_menuT.setMen_nr_ordem(men_nr_ordem.getValue().intValue());
        men_menuT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
        men_menuT.setMen_tx_status(men_tx_status.getValue() == rAtivo ? "A" : "I");

        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Falha ao execultar a operação", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                menuConsult.load();
                setVisible(false);
            }
        };
        Men_menuServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getMen_MenuService();
        serviceAsync.updade(men_menuT, callback);

//        men_menuDao.alterar(men_menuT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = men_menuDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        menuConsult.load();
//                        setVisible(false);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void btnDeltAction(ButtonEvent ce) {

        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Falha ao execultar a operação", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                menuConsult.load();
                setVisible(false);
            }
        };
        Men_menuServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getMen_MenuService();
        serviceAsync.delete(men_menuT, callback);

//        men_menuDao.excluir(men_menuT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = men_menuDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        menuConsult.load();
//                        setVisible(false);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    /**
     * @return the menuConsult
     */
    public Men_menuConsultGWT getMenuConsult() {
        return menuConsult;
    }

    /**
     * @param menuConsult the menuConsult to set
     */
    public void setMenuConsult(Men_menuConsultGWT menuConsult) {
        this.menuConsult = menuConsult;
    }

    /**
     * @return the menTPai
     */
    public Men_menuTGWT getMenTPai() {
        return menTPai;
    }

    /**
     * @param menTPai the menTPai to set
     */
    public void setMenTPai(Men_menuTGWT menTPai) {
        this.menTPai = menTPai;
    }
}
