/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.ope_operacao;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.BaseBorderLayoutGWT;
import br.com.i9.portal.client.Constantes;
import br.com.i9.portal.client.portal.portal.dao.Met_metodoDAOGWT;
import br.com.i9.portal.client.portal.portal.dao.Ope_operacaoDAOGWT;
import br.com.i9.portal.client.portal.portal.dao.Opm_met_ope_perDAOGWT;
import br.com.i9.portal.client.portal.portal.dao.Sis_sistemaDAOGWT;
import br.com.i9.portal.client.portal.portal.met_metodo.ListMetodoGWT;
import br.com.i9.portal.client.portal.portal.per_perfil.Per_perfilConsultGWT_1;
import br.com.i9.portal.client.portal.portal.transfer.Met_metodoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Ope_operacaoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Opm_met_ope_perTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Sis_sistemaTGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Met_metodoService;
import br.com.i9.portal.client.rpc.Met_metodoServiceAsync;
import br.com.i9.portal.client.rpc.Ope_operacaoService;
import br.com.i9.portal.client.rpc.Ope_operacaoServiceAsync;
import br.com.i9.portal.client.rpc.Opm_met_ope_perService;
import br.com.i9.portal.client.rpc.Opm_met_ope_perServiceAsync;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;
import com.google.gwt.json.client.JSONValue;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.Listener;
import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.dnd.GridDragSource;
import com.extjs.gxt.ui.client.dnd.GridDropTarget;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;

import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author geoleite
 */
public class Ope_operacaoConsultGWT1 extends BaseBorderLayoutGWT {

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private List<ColumnConfig> configMetodos = new Vector();
    final Ope_operacaoConsultGWT1 ope_operacaoConsultGWT = this;
    private Per_perfilConsultGWT_1 consultGWT = new Per_perfilConsultGWT_1();
    private Per_perfilTGWT per_perfilTGWT = new Per_perfilTGWT();
    private ListMetodoGWT metodosVinc = new ListMetodoGWT();
    private ListMetodoGWT metodosNaoVinc = new ListMetodoGWT();
    private GridDropTarget targetVinc;
    private GridDropTarget targetNotVinc;
    private BorderLayoutData dataWEST = new BorderLayoutData(LayoutRegion.WEST, 100);
    private BorderLayoutData dataEAST = new BorderLayoutData(LayoutRegion.EAST, 100);
    private ContentPanel cpLeste = new ContentPanel(new FillLayout());
    private ContentPanel cpOeste = new ContentPanel(new FillLayout());
    private Ope_operacaoTGWT ope_operacaoTGWT;
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private Ope_operacaoDAOGWT opeDao = new Ope_operacaoDAOGWT();
    private Met_metodoDAOGWT metDaoVinculado = new Met_metodoDAOGWT();
    private Met_metodoDAOGWT metDaoNaoVinculado = new Met_metodoDAOGWT();

    public Ope_operacaoConsultGWT1() {
        setLayout(new BorderLayout());
        getDataEAST().setSize(280);
        setModal(true);
        this.setSize("580", "330");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Ope_operacaoTGWT>> numberRenderer = new NumberCellRenderer<Grid<Ope_operacaoTGWT>>(currency);
        ColumnConfig column = null;
        column = new ColumnConfig();
        column.setId("ope_tx_descricao");
        column.setHeader("Operações");
        column.setWidth(200);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("met_tx_metodo");
        column.setHeader("Método");
        column.setWidth(200);
        configMetodos.add(column);

        ToolBar tb = new ToolBar();
        tb.add(cbSistema);
        getCpMaster().setTopComponent(tb);

        cbSistema.setEmptyText("Sel. Sistema");
        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setMinChars(1);
        cbSistema.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbSistema.setTypeAhead(true);

        cbSistema.addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<Sis_sistemaTGWT>>() {

            public void handleEvent(SelectionChangedEvent<Sis_sistemaTGWT> be) {
                loadOperacoes();
            }
        });

    }

    private void loadOperacoes() {
        Sis_sistemaTGWT sisT = cbSistema.getValue();
        Ope_operacaoTGWT opeT = new Ope_operacaoTGWT();
        opeT.setSis_nr_id(sisT.getSis_nr_id());

        AsyncCallback<List<Ope_operacaoTGWT>> callback = new AsyncCallback<List<Ope_operacaoTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Importante", "Erro ao consultar as operações", null);
            }

            @Override
            public void onSuccess(List<Ope_operacaoTGWT> result) {
                ListStore<Ope_operacaoTGWT> list = new ListStore<Ope_operacaoTGWT>();
                list.add(result);
                getCpMaster().removeAll();
                ColumnModel cm = new ColumnModel(configs);

                Grid<Ope_operacaoTGWT> grid = new Grid<Ope_operacaoTGWT>(list, cm);
                grid.setLoadMask(true);

                grid.setStyleAttribute("borderTop", "none");
                grid.setBorders(true);
                grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<Ope_operacaoTGWT>>() {

                    public void handleEvent(SelectionChangedEvent<Ope_operacaoTGWT> be) {
                        ope_operacaoTGWT = be.getSelectedItem();
                        montarTela(ope_operacaoTGWT);
                    }
                });
                grid.setStripeRows(true);
                getCpMaster().add(grid);
                getCpMaster().layout();
            }
        };
        Ope_operacaoServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getOpe_operacaoService();
        serviceAsync.consultBySistema(opeT, callback);

//        opeDao.consultBySistema(opeT);
//        Timer timer = new Timer() {
//            
//            @Override
//            public void run() {
//                getCpMaster().removeAll();
//                ListStore<Ope_operacaoTGWT> list = opeDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//                    ColumnModel cm = new ColumnModel(configs);
//                    
//                    Grid<Ope_operacaoTGWT> grid = new Grid<Ope_operacaoTGWT>(list, cm);
//                    grid.setLoadMask(true);
//                    
//                    grid.setStyleAttribute("borderTop", "none");
//                    grid.setBorders(true);
//                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//                    grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<Ope_operacaoTGWT>>() {
//                        
//                        public void handleEvent(SelectionChangedEvent<Ope_operacaoTGWT> be) {
//                            ope_operacaoTGWT = be.getSelectedItem();
//                            montarTela();
//                        }
//                    });
//                    grid.setStripeRows(true);
//                    getCpMaster().add(grid);
//                    getCpMaster().layout();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    private void loadSistemas() {
        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Importante", "Erro ao consultar os sistemas", null);
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                ListStore<Sis_sistemaTGWT> list = new ListStore<Sis_sistemaTGWT>();
                list.add(result);
                cbSistema.setStore(list);
                if (list.getCount() > 0) {
                    cbSistema.setValue(list.getAt(0));
                    loadOperacoes();
                }
                cbSistema.getView().refresh();
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
//                    if (list.getCount() > 0) {
//                        cbSistema.setValue(list.getAt(0));
//                        loadOperacoes();
//                    }
//                    cbSistema.getView().refresh();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void montarTela(Ope_operacaoTGWT opt) {
        Met_metodoTGWT metT = new Met_metodoTGWT();
        metT.setOpe_nr_id(opt.getOpe_nr_id());

        getCpRight().removeAll();
        final ContentPanel cpNotVinc = new ContentPanel(new FillLayout());
        final ContentPanel cpVinc = new ContentPanel(new FillLayout());
        cpVinc.setBodyBorder(false);
        cpVinc.setScrollMode(Scroll.AUTOX);
        cpNotVinc.setBodyBorder(false);
        cpNotVinc.setHeading("Não Vinculados");
        cpVinc.setHeading("Vinculados");

        cpNotVinc.setHeight(ope_operacaoConsultGWT.getHeight() / 2);

        ContentPanel cp = new ContentPanel(new FillLayout());
        cp.setScrollMode(Scroll.AUTOX);
        cp.setHeaderVisible(false);

        cp.add(cpNotVinc);
        cp.add(cpVinc);

        getCpRight().add(cp);

        AsyncCallback<List<Met_metodoTGWT>> callback = new AsyncCallback<List<Met_metodoTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Importante", "Falha ao realizar consulta métodos vinculadas", null);
            }

            @Override
            public void onSuccess(List<Met_metodoTGWT> result) {
                ListStore<Met_metodoTGWT> list = new ListStore<Met_metodoTGWT>();
                list.add(result);
               

                final ColumnModel cmvinc = new ColumnModel(configMetodos);
                Grid<Met_metodoTGWT> grid_vinc = new Grid<Met_metodoTGWT>(list, cmvinc);

                grid_vinc.setLoadMask(true);

                grid_vinc.setBorders(true);
                cpVinc.add(grid_vinc);

                GridDragSource gridSourceVinc = new GridDragSource(grid_vinc);
                GridDropTarget targetVinc = new GridDropTarget(grid_vinc) {

                    protected void onDragDrop(DNDEvent event) {
                        List<Met_metodoTGWT> lis = event.getData();                        
                        if (!lis.isEmpty()) {
                            for (Met_metodoTGWT met_metodoTGWT : lis) {
                                adicionarMetodo(met_metodoTGWT);
                            }
                        }
                        super.onDragDrop(event);
                    }
                };
                targetVinc.setAllowSelfAsSource(false);
                layout();
            }
        };
        Met_metodoServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getMet_MetodoService();
        serviceAsync.consultByOperacaoPerfil(metT, per_perfilTGWT, callback);

        AsyncCallback<List<Met_metodoTGWT>> asyncCallbackNot = new AsyncCallback<List<Met_metodoTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Importante", "Falha ao realizar consulta métodos não vinculadas", null);
            }

            @Override
            public void onSuccess(List<Met_metodoTGWT> result) {
                ListStore<Met_metodoTGWT> list = new ListStore<Met_metodoTGWT>();
                list.add(result);
                
                ColumnModel cmnotvinc = new ColumnModel(configMetodos);
                Grid<Met_metodoTGWT> grid_notvinc = new Grid<Met_metodoTGWT>(list, cmnotvinc);
                grid_notvinc.setLoadMask(true);

                grid_notvinc.setBorders(true);
                cpNotVinc.add(grid_notvinc);
                GridDragSource gridSourceNotVinc = new GridDragSource(grid_notvinc);
                GridDropTarget targetNotVinc = new GridDropTarget(grid_notvinc) {

                    protected void onDragDrop(DNDEvent event) {
                        
                        List<Met_metodoTGWT> lis = event.getData();
                        if (!lis.isEmpty()) {
                            for (Met_metodoTGWT met_metodoTGWT : lis) {
                                removerMetodo(met_metodoTGWT);
                            }
                        }
                        super.onDragDrop(event);
                    }
                };
                targetNotVinc.setAllowSelfAsSource(false);
                layout();
            }
        };

        Met_metodoServiceAsync serviceAsyncNOT = EasyAdmPortalRPCFactory.getMet_MetodoService();
        serviceAsyncNOT.consultByOperacaoNaoPerfil(metT, per_perfilTGWT, asyncCallbackNot);

        layout();
        doLayout();

//        metDaoVinculado.consultByOperacaoPerfil(metT, per_perfilTGWT);
//        metDaoNaoVinculado.consultByOperacaoNaoPerfil(metT, per_perfilTGWT);
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                getCpRight().removeAll();
//                ListStore<Met_metodoTGWT> listVinculado = metDaoVinculado.getList();
//                ListStore<Met_metodoTGWT> listNaoVinculado = metDaoNaoVinculado.getList();
//                if (listVinculado == null || listNaoVinculado == null) {
//                    schedule(500);
//                } else {
//                    ContentPanel cpNotVinc = new ContentPanel(new FillLayout());
//                    ContentPanel cpVinc = new ContentPanel(new FillLayout());
//                    cpNotVinc.setBodyBorder(false);
//                    cpVinc.setBodyBorder(false);
//                    cpVinc.setScrollMode(Scroll.AUTOX);
//                    cpNotVinc.setHeight(ope_operacaoConsultGWT.getHeight() / 2);
//
//                    cpNotVinc.setHeaderVisible(true);
//                    cpNotVinc.setBodyBorder(false);
//                    cpNotVinc.setHeading("Não Vinculados");
//                    ColumnModel cmnotvinc = new ColumnModel(configMetodos);
//
//                    Grid<Met_metodoTGWT> grid_notvinc = new Grid<Met_metodoTGWT>(listNaoVinculado, cmnotvinc);
//                    grid_notvinc.setLoadMask(true);
//
//                    grid_notvinc.setBorders(true);
//                    cpNotVinc.add(grid_notvinc);
//
//                    cpVinc.setLayout(new FitLayout());
//
//                    cpVinc.setHeading("Vinculados");
//                    cpVinc.setHeaderVisible(true);
//
//                    final ColumnModel cmvinc = new ColumnModel(configMetodos);
//                    Grid<Met_metodoTGWT> grid_vinc = new Grid<Met_metodoTGWT>(listVinculado, cmvinc);
//
//                    grid_vinc.setLoadMask(true);
//
//                    grid_vinc.setBorders(true);
//                    cpVinc.add(grid_vinc);
//
//                    GridDragSource gridSourceVinc = new GridDragSource(grid_vinc);
//                    GridDragSource gridSourceNotVinc = new GridDragSource(grid_notvinc);
//                    GridDropTarget targetVinc = new GridDropTarget(grid_vinc) {
//
//                        protected void onDragDrop(DNDEvent event) {
//
//                            List<Met_metodoTGWT> lis = event.getData();
//                            if (!lis.isEmpty()) {
//                                for (Met_metodoTGWT met_metodoTGWT : lis) {
//                                    adicionarMetodo(met_metodoTGWT);
//                                }
//                            }
//                            super.onDragDrop(event);
//                        }
//                    };
//                    targetVinc.setAllowSelfAsSource(false);
//
//                    GridDropTarget targetNotVinc = new GridDropTarget(grid_notvinc) {
//
//                        protected void onDragDrop(DNDEvent event) {
//                            List<Met_metodoTGWT> lis = event.getData();
//                            if (!lis.isEmpty()) {
//                                for (Met_metodoTGWT met_metodoTGWT : lis) {
//                                    removerMetodo(met_metodoTGWT);
//                                }
//                            }
//                            super.onDragDrop(event);
//                        }
//                    };
//
//                    targetNotVinc.setAllowSelfAsSource(false);
//                    ContentPanel cp = new ContentPanel(new FillLayout());
//                    cp.setScrollMode(Scroll.AUTOX);
//                    cp.setHeaderVisible(false);
//
//                    cp.add(cpNotVinc);
//                    cp.add(cpVinc);
//
//                    getCpRight().add(cp);
//
//                    layout();
//                    doLayout();
//
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void adicionarMetodo(Met_metodoTGWT met_metodoTGWT) {

        //final Opm_met_ope_perDAOGWT opmDao = new Opm_met_ope_perDAOGWT();
        Opm_met_ope_perTGWT opmT = new Opm_met_ope_perTGWT();
        opmT.setSis_nr_id(met_metodoTGWT.getSis_nr_id());
        opmT.setMet_nr_id(met_metodoTGWT.getMet_nr_id());
        opmT.setOpe_nr_id(met_metodoTGWT.getOpe_nr_id());
        
        opmT.setPer_nr_id(per_perfilTGWT.getPer_nr_id());
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Importante", "Falha ao adicionar o método", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                montarTela(ope_operacaoTGWT);
            }
        };

        Opm_met_ope_perServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getOpm_Met_Ope_PerService();
        serviceAsync.insert(opmT, callback);

//        opmDao.inserir(opmT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = opmDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        montarTela(ope_operacaoTGWT);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void removerMetodo(Met_metodoTGWT met_metodoTGWT) {
        final Opm_met_ope_perDAOGWT opmDao = new Opm_met_ope_perDAOGWT();
        Opm_met_ope_perTGWT opmT = new Opm_met_ope_perTGWT();
        opmT.setSis_nr_id(met_metodoTGWT.getSis_nr_id());
        opmT.setMet_nr_id(met_metodoTGWT.getMet_nr_id());
        opmT.setOpe_nr_id(met_metodoTGWT.getOpe_nr_id());
        opmT.setPer_nr_id(per_perfilTGWT.getPer_nr_id());
        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Importante", "Falha ao adicionar o método", null);
            }

            @Override
            public void onSuccess(Void result) {
                Info.display("Resultado", "Sucesso!");
                montarTela(ope_operacaoTGWT);
            }
        };

        Opm_met_ope_perServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getOpm_Met_Ope_PerService();
        serviceAsync.delete(opmT, callback);

//        opmDao.excluir(opmT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                String msg = opmDao.getMsg();
//                if (msg == null) {
//                    schedule(500);
//                } else {
//                    if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                        MessageBox.alert("Problemas", msg, null);
//                    } else {
//                        Info.display("Resultado", msg);
//                        montarTela(ope_operacaoTGWT);
//                    }
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void load(Per_perfilTGWT per_perfilTGWT) {
        this.per_perfilTGWT = per_perfilTGWT;
        this.setHeading("Operacoes do Perfil: " + per_perfilTGWT.getPer_tx_nome());
        loadSistemas();
    }

    /**
     * @return the per_perfilTGWT
     */
    public Per_perfilTGWT getPer_perfilTGWT() {
        return per_perfilTGWT;
    }

    /**
     * @param per_perfilTGWT the per_perfilTGWT to set
     */
    public void setPer_perfilTGWT(Per_perfilTGWT per_perfilTGWT) {
        this.per_perfilTGWT = per_perfilTGWT;
    }

    /**
     * @return the consultGWT
     */
    public Per_perfilConsultGWT_1 getConsultGWT() {
        return consultGWT;
    }

    /**
     * @param consultGWT the consultGWT to set
     */
    public void setConsultGWT(Per_perfilConsultGWT_1 consultGWT) {
        this.consultGWT = consultGWT;
    }

    /**
     * @return the ope_operacaoTGWT
     */
    public Ope_operacaoTGWT getOpe_operacaoTGWT() {
        return ope_operacaoTGWT;
    }

    /**
     * @param ope_operacaoTGWT the ope_operacaoTGWT to set
     */
    public void setOpe_operacaoTGWT(Ope_operacaoTGWT ope_operacaoTGWT) {
        this.ope_operacaoTGWT = ope_operacaoTGWT;
    }
}

class Resposta implements IListenetResponse {

    public void read(JSONValue jsonValue) {
        Info.display(" entrou ", " entrou");
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {

            Info.display("info", EasyContainer.clearAspas(jsonObject.get("resultado").toString()));
        }

    }
}
