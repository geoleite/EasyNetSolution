/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.men_menu;

import br.com.easynet.gwt.client.CadastrarBaseGWT;
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

import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
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
public class Men_menuInsertGWT extends CadastrarBaseGWT {

    private Men_menuConsultGWT menuConsult;
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private Men_menuDAOGWT men_menuDao = new Men_menuDAOGWT();
    private EasyTextField<String> men_tx_nome = new EasyTextField<String>();
    private EasyTextField<String> men_tx_url = new EasyTextField<String>();
    private EasyTextField<String> men_tx_acao = new EasyTextField<String>();
    private EasyTextField<String> men_tx_icone = new EasyTextField<String>();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private EasyNumberField men_nr_ordem = new EasyNumberField();
    private RadioGroup men_tx_status = new RadioGroup();
    private Radio rAtivo = new Radio();
    private Radio rInativo = new Radio();
    private Men_menuTGWT menTPai;
    private TreeStore<MenuTree> treeStore;
    private TreePanel<MenuTree> treePanel;
    private CheckBox cbNivel0 = new CheckBox();

    public Men_menuInsertGWT() {
        setHeading("Cadastrar Menu");
        this.setSize("380", "300");
        setResizable(false);
        setMaximizable(false);
        setMinimizable(false);

        TableLayout tl = new TableLayout(2);
        tl.setCellSpacing(4);
        getCpMaster().setLayout(tl);

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
        men_tx_acao.setUpperCase(false);
        men_tx_acao.setAllowCharSpecial(true);
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

        getCpMaster().add(new LabelField("Ícone:"));
        getCpMaster().add(men_tx_icone);
        men_tx_icone.setUpperCase(false);
        men_tx_icone.setMaxLength(80);
        men_tx_icone.setWidth(120);
        men_tx_icone.setValue("icone");

        getCpMaster().add(new LabelField("Status:"));
        getCpMaster().add(men_tx_status);
        rAtivo.setBoxLabel("Ativo");
        rInativo.setBoxLabel("Inativo");
        rAtivo.setValue(true);
        men_tx_status.add(rAtivo);
        men_tx_status.add(rInativo);

        getCpMaster().add(new LabelField("Nivel:"));
        getCpMaster().add(cbNivel0);
        cbNivel0.setBoxLabel("Indica nivel zero");

        loadSistema();
    }

    public void loadSistema() {
        
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
//                    layout();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        cbNivel0.setValue(menTPai == null);
    }

    public void btnInsertAction(ButtonEvent ce) {
        if (cbSistema.getValue() == null) {
            MessageBox.alert("Faclha", "É necessário selecionar um sistema.", null);
            return;
        }
        if (men_tx_nome.getValue() == null) {
            MessageBox.alert("Falha", "É necessário informar um nome.", null);
            return;
        }

        Men_menuTGWT men_menuTGWT = new Men_menuTGWT();
        men_menuTGWT.setMen_tx_nome(men_tx_nome.getValue());
        men_menuTGWT.setMen_tx_acao(men_tx_acao.getValue());
        men_menuTGWT.setMen_tx_icone(men_tx_icone.getValue());
        men_menuTGWT.setMen_tx_url(men_tx_url.getValue());
        men_menuTGWT.setMen_nr_ordem(men_nr_ordem.getValue().intValue());
        men_menuTGWT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
        men_menuTGWT.setMen_tx_status(men_tx_status.getValue() == rAtivo ? "A" : "I");
        if (cbNivel0.getValue()) {
            men_menuTGWT.setSupermenu_nr_id(0);
        } else {
            men_menuTGWT.setSupermenu_nr_id(getMenTPai().getMen_nr_id());
        }
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Falha ao realizar operação", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                btnLimpartAction(null);
                menuConsult.load();
                //getModeloLoad().load();
                setVisible(false);
            }
        };
        Men_menuServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getMen_MenuService();
        serviceAsync.insert(men_menuTGWT, callback);

        men_menuDao.inserir(men_menuTGWT);
        Timer timer = new Timer() {

            public void run() {
                String msg = men_menuDao.getMsg();
                if (msg == null) {
                    schedule(500);
                } else {
                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                        MessageBox.alert("Problemas", msg, null);
                    } else {
                        Info.display("Resultado", msg);
                        btnLimpartAction(null);
                        menuConsult.load();
                        //getModeloLoad().load();
                        setVisible(false);
                    }
                }
            }
        };
        timer.schedule(500);
    }

    public void btnLimpartAction(ButtonEvent ce) {
        men_tx_nome.setValue("");
        men_tx_status.setValue(rAtivo);
    }

    /**
     * @return the treeStore
     */
    public TreeStore<MenuTree> getTreeStore() {
        return treeStore;
    }

    /**
     * @param treeStore the treeStore to set
     */
    public void setTreeStore(TreeStore<MenuTree> treeStore) {
        this.treeStore = treeStore;
    }

    /**
     * @return the treePanel
     */
    public TreePanel<MenuTree> getTreePanel() {
        return treePanel;
    }

    /**
     * @param treePanel the treePanel to set
     */
    public void setTreePanel(TreePanel<MenuTree> treePanel) {
        this.treePanel = treePanel;
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
}
