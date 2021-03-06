/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.par_parametro;

import br.com.easynet.gwt.client.CPConsultarBaseGWT;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Par_parametroService;
import br.com.i9.portal.client.rpc.Par_parametroServiceAsync;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.Listener;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.core.client.GWT;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.SelectionChangeEvent;
import java.util.TreeMap;

/**
 *
 * @author geoleite
 */
public class Par_parametroConsultGWT extends CPConsultarBaseGWT {

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Par_parametroDAOGWT par_parametroDao = new Par_parametroDAOGWT();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private Grid<Par_parametroTGWT> grid;
    //private TreeMap<Integer, Sis_sistemaTGWT> sisMap = new TreeMap<Integer, Sis_sistemaTGWT>();

    public Par_parametroConsultGWT() {
        setHeaderVisible(false);
        this.setSize("500", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Par_parametroTGWT>> numberRenderer = new NumberCellRenderer<Grid<Par_parametroTGWT>>(currency);
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
        ColumnConfig column = null;

        column = new ColumnConfig();
        column.setId("par_tx_nome");
        column.setHeader("Parametro");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("par_tx_valor");
        column.setHeader("Valor");
        column.setWidth(300);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        getToolBarMaster().add(cbSistema);
        cbSistema.addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<Sis_sistemaTGWT>>() {

            public void handleEvent(SelectionChangedEvent<Sis_sistemaTGWT> be) {
                load();
            }
        });

        cbSistema.setEmptyText("Sel. Sistema");
        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbSistema.setTypeAhead(true);
        cbSistema.setMinChars(1);
        
        loadSistema();
    }

    public void loadSistema() {
        //sisDao.consultarTodos();

        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Erro ao consultar sistemas", null);
            }

            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) { 
                ListStore<Sis_sistemaTGWT> store = new ListStore<Sis_sistemaTGWT>();
                store.add(result);
                cbSistema.setStore(store);
                cbSistema.getView().refresh();
            }
        };
        Sis_sistemaServiceAsync async = EasyAdmPortalRPCFactory.getSis_sistemaService();
        async.consult(callback);
    }

    public void btnAtualizarAction(ButtonEvent be) {
        super.btnAtualizarAction(be);
    }

    public void btnNovoAction(ButtonEvent be) {
        Par_parametroInsertGWT par_parametroInsertGWT = new Par_parametroInsertGWT();
        par_parametroInsertGWT.setPar_parametroConsult(this);
        par_parametroInsertGWT.setModal(true);
        par_parametroInsertGWT.show();
    }

    private GridCellRenderer<Par_parametroTGWT> getEditarRender() {
        return new GridCellRenderer<Par_parametroTGWT>() {

            public Object render(final Par_parametroTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Par_parametroTGWT> store, Grid<Par_parametroTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Par_parametroUpdateDeleteGWT par_parametroUpdateDeleteGWT = new Par_parametroUpdateDeleteGWT();
                        par_parametroUpdateDeleteGWT.setPar_parametroConsult(Par_parametroConsultGWT.this);
                        par_parametroUpdateDeleteGWT.load(model);
                        par_parametroUpdateDeleteGWT.setVisible(true);
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

        AsyncCallback<List<Par_parametroTGWT>> callback = new AsyncCallback<List<Par_parametroTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Falha ao realizar operação", null);
            }

            @Override
            public void onSuccess(List<Par_parametroTGWT> result) {
                List lista = getCpMaster().getItems();
                if (lista.size() > 0 && grid != null) {
                    getCpMaster().remove(grid);
                }

                DebugMessage.message(this.getClass().getName(), "Qnt Parametros " + result.size());
                ColumnModel cm = new ColumnModel(configs);

                PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(result);
                PagingLoader<PagingLoadResult<Par_parametroTGWT>> loader = new BasePagingLoader<PagingLoadResult<Par_parametroTGWT>>(proxy);
                loader.setRemoteSort(true);

                ListStore<Par_parametroTGWT> store = new ListStore<Par_parametroTGWT>(loader);
                store.add(result);

                getToolBarPage().setPageSize(20);
                getToolBarPage().bind(loader);
                loader.load(0, 20);

//                    Grid<Par_parametroTGWT> grid = new Grid<Par_parametroTGWT>(store, cm);
                grid = new Grid<Par_parametroTGWT>(store, cm);
                grid.setId("grid");
                grid.setLoadMask(true);

                grid.setStyleAttribute("borderTop", "none");
                grid.setBorders(true);
                grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                getCpMaster().add(grid);
                getCpMaster().layout();
                DebugMessage.message(this.getClass().getName(), "Fim Grid");
            }
        };
        Par_parametroServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getPar_ParametroService();
        serviceAsync.getParamSistema(cbSistema.getValue(), callback);

//        Par_parametroTGWT parT = new Par_parametroTGWT();
//        parT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
//        par_parametroDao.consultBySistema(parT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                
//                if (!par_parametroDao.isFinalized()) {
//                    DebugMessage.message(this.getClass().getName(), "Esperando parametros");
//                    schedule(500);
//                } else {
//                    DebugMessage.message(this.getClass().getName(), "Parametros chegaram");
//                    List lista = getCpMaster().getItems();
//                    if (lista.size() > 0 && grid != null) {
//                        getCpMaster().remove(grid);
//                    }
//                    ListStore<Par_parametroTGWT> list = par_parametroDao.getList();
//                    DebugMessage.message(this.getClass().getName(), "Qnt Parametros " + list.getCount());
//                    ColumnModel cm = new ColumnModel(configs);
//
//                    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
//                    PagingLoader<PagingLoadResult<Par_parametroTGWT>> loader = new BasePagingLoader<PagingLoadResult<Par_parametroTGWT>>(proxy);
//                    loader.setRemoteSort(true);
//
//                    ListStore<Par_parametroTGWT> store = new ListStore<Par_parametroTGWT>(loader);
//                    store.add(list.getModels());
//
//                    getToolBarPage().setPageSize(20);
//                    getToolBarPage().bind(loader);
//                    loader.load(0, 20);
//
//
////                    Grid<Par_parametroTGWT> grid = new Grid<Par_parametroTGWT>(store, cm);
//                    grid = new Grid<Par_parametroTGWT>(store, cm);
//                    grid.setId("grid");
//                    grid.setLoadMask(true);
//
//                    grid.setStyleAttribute("borderTop", "none");
//                    grid.setBorders(true);
//                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//                    getCpMaster().add(grid);
//                    getCpMaster().layout();
//                    DebugMessage.message(this.getClass().getName(), "Fim Grid");
//                }
//            }
//        };
//        timer.schedule(500);
    }
}
