/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.met_metodo;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Met_metodoService;
import br.com.i9.portal.client.rpc.Met_metodoServiceAsync;
import br.com.i9.portal.client.rpc.Ope_operacaoService;
import br.com.i9.portal.client.rpc.Ope_operacaoServiceAsync;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;

import com.google.gwt.json.client.JSONValue;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.event.SelectionEvent;
import com.extjs.gxt.ui.client.event.Listener;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.gwt.core.client.GWT;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import java.util.Date;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class Met_metodoConsultGWT extends ConsultarBaseGWT {

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Met_metodoDAOGWT met_metodoDao = new Met_metodoDAOGWT();
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private Ope_operacaoDAOGWT opeDao = new Ope_operacaoDAOGWT();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private ComboBox<Ope_operacaoTGWT> cbOperacao = new ComboBox<Ope_operacaoTGWT>();

    public Met_metodoConsultGWT() {
        setHeaderVisible(false);
        this.setSize("500", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Met_metodoTGWT>> numberRenderer = new NumberCellRenderer<Grid<Met_metodoTGWT>>(currency);
        /*
         GridCellRenderer<Stock> change = new GridCellRenderer<Stock>() {
         public String render(Stock model, String property, ColumnData config, int rowIndex,
         int colIndex, ListStore<Stock> store) {
         double val = (Double) model.get(property);
         String style = val < 0 ? "red" : "green";
         return "<span style='color:" + style + "'>" + number.format(val) + "</span>";
         }
         };
         GridCellRenderer<Stock> gridNumber = new GridCellRenderer<Stock>() {
         public String render(Stock model, String property, ColumnData config, int rowIndex,
         int colIndex, ListStore<Stock> store) {
         return numberRenderer.render(null, property, model.get(property));
         }
         };
         */

        GridCellRenderer<Met_metodoTGWT> gridStatus = new GridCellRenderer<Met_metodoTGWT>() {

            public Object render(Met_metodoTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Met_metodoTGWT> store, Grid<Met_metodoTGWT> grid) {
                String txt = "<div style='color:cor'>valor</div>";
                String cor = "green";
                String valor = "Ativo";
                if ("I".equals(model.getMet_tx_status())) {
                    cor = "red";
                    valor = "Inativo";
                }
                txt = txt.replaceAll("cor", cor);
                txt = txt.replaceAll("valor", valor);
                return txt;

            }
        };

        ColumnConfig column = null;

        column = new ColumnConfig();
        column.setId("met_tx_metodo");
        column.setHeader("Metodo");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("met_tx_descricao");
        column.setHeader("Descricao");
        column.setWidth(300);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("met_tx_status");
        column.setHeader("Status");
        column.setWidth(100);
        column.setRenderer(gridStatus);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

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

        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(cbSistema);
        getToolBarMaster().add(cbOperacao);

        cbSistema.addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<Sis_sistemaTGWT>>() {

            public void handleEvent(SelectionChangedEvent<Sis_sistemaTGWT> be) {
                loadOperacoes();
            }
        });

        cbOperacao.addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<Ope_operacaoTGWT>>() {

            public void handleEvent(SelectionChangedEvent<Ope_operacaoTGWT> be) {
                load();
            }
        });

        loadSistemas();
    }

    private void loadOperacoes() {
        Ope_operacaoTGWT opeT = new Ope_operacaoTGWT();
        opeT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());

        AsyncCallback<List<Ope_operacaoTGWT>> callback = new AsyncCallback<List<Ope_operacaoTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Erro ao realizar ao consultar operações", null);
            }

            @Override
            public void onSuccess(List<Ope_operacaoTGWT> result) {
                ListStore<Ope_operacaoTGWT> list = new ListStore<Ope_operacaoTGWT>();
                list.add(result);
                cbOperacao.setStore(list);
                cbOperacao.getView().refresh();
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
//                    cbOperacao.getView().refresh();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    private void loadSistemas() {
        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Erro ao consultar sistemas", null);
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                ListStore<Sis_sistemaTGWT> store = new ListStore<Sis_sistemaTGWT>();
                store.add(result);
                cbSistema.setStore(store);
                cbSistema.getView().refresh();
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
//                    cbSistema.getView().refresh();
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void btnAtualizarAction(ButtonEvent be) {
        super.btnAtualizarAction(be);
    }

    public void btnNovoAction(ButtonEvent be) {
        Met_metodoInsertGWT met_metodoInsertGWT = new Met_metodoInsertGWT();
        if (cbSistema.getValue() != null) {
            met_metodoInsertGWT.setSisNrId(cbSistema.getValue().getSis_nr_id());
            if (cbOperacao.getValue() != null) {
                met_metodoInsertGWT.setOpeNrId(cbOperacao.getValue().getOpe_nr_id());
                met_metodoInsertGWT.loadSistemas();
            } else {
                met_metodoInsertGWT.loadSistemas();
            }
        } else {
            met_metodoInsertGWT.loadSistemas();
        }
        met_metodoInsertGWT.setMet_metodoConsult(this);
        met_metodoInsertGWT.setModal(true);
        met_metodoInsertGWT.show();

    }

    private GridCellRenderer<Met_metodoTGWT> getEditarRender() {
        return new GridCellRenderer<Met_metodoTGWT>() {

            public Object render(final Met_metodoTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Met_metodoTGWT> store, Grid<Met_metodoTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Met_metodoUpdateDeleteGWT met_metodoUpdateDeleteGWT = new Met_metodoUpdateDeleteGWT();
                        met_metodoUpdateDeleteGWT.setMet_metodoConsult(Met_metodoConsultGWT.this);
                        met_metodoUpdateDeleteGWT.load(model);
                        met_metodoUpdateDeleteGWT.setVisible(true);
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Alterar dados.");
                b.setIcon(ICONS.edit());
                b.setId("btnEditar");

                return b;
            }
        };
    }

    public void load() {
        Met_metodoTGWT metT = new Met_metodoTGWT();
        metT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
        metT.setOpe_nr_id(cbOperacao.getValue().getOpe_nr_id());

        AsyncCallback<List<Met_metodoTGWT>> callback = new AsyncCallback<List<Met_metodoTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Met_metodoTGWT> result) {
                List lista = getCpMaster().getItems();
                if (lista.size() > 0) {
                    getCpMaster().removeAll();
                }

                ColumnModel cm = new ColumnModel(configs);

                PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(result);
                PagingLoader<PagingLoadResult<Met_metodoTGWT>> loader = new BasePagingLoader<PagingLoadResult<Met_metodoTGWT>>(proxy);
                loader.setRemoteSort(true);

                ListStore<Met_metodoTGWT> store = new ListStore<Met_metodoTGWT>(loader);
                store.add(result);
                getToolBarPage().setPageSize(20);
                getToolBarPage().bind(loader);
                loader.load(0, 20);

                Grid<Met_metodoTGWT> grid = new Grid<Met_metodoTGWT>(store, cm);
                grid.setId("grid");
                grid.setLoadMask(true);

                grid.setStyleAttribute("borderTop", "none");
                grid.setBorders(true);
                grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                getCpMaster().add(grid);
                getCpMaster().layout();
            }
        };
        Met_metodoServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getMet_MetodoService();
        serviceAsync.consultByOperacao(metT, callback);

//        met_metodoDao.consultByOperacao(metT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                ListStore<Met_metodoTGWT> list = met_metodoDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//                    List lista = getCpMaster().getItems();
//                    if (lista.size() > 0) {
//                        getCpMaster().removeAll();
//                    }
//
//                    ColumnModel cm = new ColumnModel(configs);
//
//                    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
//                    PagingLoader<PagingLoadResult<Met_metodoTGWT>> loader = new BasePagingLoader<PagingLoadResult<Met_metodoTGWT>>(proxy);
//                    loader.setRemoteSort(true);
//
//                    ListStore<Met_metodoTGWT> store = new ListStore<Met_metodoTGWT>(loader);
//                    store.add(list.getModels());
//                    getToolBarPage().setPageSize(20);
//                    getToolBarPage().bind(loader);
//                    loader.load(0, 20);
//
//                    Grid<Met_metodoTGWT> grid = new Grid<Met_metodoTGWT>(store, cm);
//                    grid.setId("grid");
//                    grid.setLoadMask(true);
//
//                    grid.setStyleAttribute("borderTop", "none");
//                    grid.setBorders(true);
//                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//                    getCpMaster().add(grid);
//                    getCpMaster().layout();
//                }
//            }
//        };
//        timer.schedule(500);
    }
}
