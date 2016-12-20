/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.ope_operacao;

import br.com.easynet.gwt.client.CPConsultarBaseGWT;
import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
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
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
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
public class Ope_operacaoConsultGWT extends CPConsultarBaseGWT {

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Ope_operacaoDAOGWT ope_operacaoDao = new Ope_operacaoDAOGWT();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();

    public Ope_operacaoConsultGWT() {
        setHeaderVisible(false);
        this.setSize("500", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Ope_operacaoTGWT>> numberRenderer = new NumberCellRenderer<Grid<Ope_operacaoTGWT>>(currency);
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
        GridCellRenderer<Ope_operacaoTGWT> gridStatus = new GridCellRenderer<Ope_operacaoTGWT>() {

            public Object render(Ope_operacaoTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Ope_operacaoTGWT> store, Grid<Ope_operacaoTGWT> grid) {
                String txt = "<div style='color:cor'>valor</div>";
                String cor = "green";
                String valor = "Ativo";
                if ("I".equals(model.getOpe_tx_status())) {
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
        column.setId("ope_tx_nome");
        column.setHeader("Nome");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("ope_tx_descricao");
        column.setHeader("Descricao");
        column.setWidth(300);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("ope_tx_classe");
        column.setHeader("Classe");
        column.setWidth(400);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("ope_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(gridStatus);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(cbSistema);

        cbSistema.setEmptyText("Sel. Sistema");
        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setMinChars(1);
        cbSistema.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbSistema.setTypeAhead(true);

        cbSistema.addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<Sis_sistemaTGWT>>() {

            public void handleEvent(SelectionChangedEvent<Sis_sistemaTGWT> be) {
                load();
            }
        });

        loadSistemas();
    }

    private void loadSistemas() {

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
        Ope_operacaoInsertGWT ope_operacaoInsertGWT = new Ope_operacaoInsertGWT();

        ope_operacaoInsertGWT.setSisNrId(cbSistema.getValue().getSis_nr_id());

        ope_operacaoInsertGWT.loadSistemas();

        ope_operacaoInsertGWT.setOpe_operacaoConsult(this);
        ope_operacaoInsertGWT.setModal(true);
        ope_operacaoInsertGWT.show();
    }

    private GridCellRenderer<Ope_operacaoTGWT> getEditarRender() {
        return new GridCellRenderer<Ope_operacaoTGWT>() {

            public Object render(final Ope_operacaoTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Ope_operacaoTGWT> store, Grid<Ope_operacaoTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Ope_operacaoUpdateDeleteGWT ope_operacaoUpdateDeleteGWT = new Ope_operacaoUpdateDeleteGWT();
                        ope_operacaoUpdateDeleteGWT.setOpe_operacaoConsult(Ope_operacaoConsultGWT.this);
                        ope_operacaoUpdateDeleteGWT.load(model);
                        ope_operacaoUpdateDeleteGWT.setVisible(true);
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
        if (cbSistema.getValue() == null) {
            MessageBox.alert("Falha", "É necessário selecionar um sistema.", null);
            return;
        }
        Ope_operacaoTGWT opeT = new Ope_operacaoTGWT();
        opeT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());

        AsyncCallback<List<Ope_operacaoTGWT>> callback = new AsyncCallback<List<Ope_operacaoTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Erro ao execultar a operação", null);
            }

            @Override
            public void onSuccess(List<Ope_operacaoTGWT> result) {
                List lista = getCpMaster().getItems();
                if (lista.size() > 0) {
                    getCpMaster().removeAll();
                }

                ColumnModel cm = new ColumnModel(configs);

                PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(result);
                PagingLoader<PagingLoadResult<Ope_operacaoTGWT>> loader = new BasePagingLoader<PagingLoadResult<Ope_operacaoTGWT>>(proxy);
                loader.setRemoteSort(true);

                ListStore<Ope_operacaoTGWT> store = new ListStore<Ope_operacaoTGWT>(loader);
                store.add(result);

                getToolBarPage().setPageSize(20);
                getToolBarPage().bind(loader);
                loader.load(0, 20);

                Grid<Ope_operacaoTGWT> grid = new Grid<Ope_operacaoTGWT>(store, cm);
                grid.setId("grid");
                grid.setLoadMask(true);

                grid.setStyleAttribute("borderTop", "none");
                grid.setBorders(true);
                grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                getCpMaster().add(grid);
                getCpMaster().layout();
            }
        };
        Ope_operacaoServiceAsync async = EasyAdmPortalRPCFactory.getOpe_operacaoService();
        async.consultBySistema(opeT, callback);

//        ope_operacaoDao.consultBySistema(opeT);
//        Timer timer = new Timer() {
//
//            public void run() {
//                ListStore<Ope_operacaoTGWT> list = ope_operacaoDao.getList();
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
//                    PagingLoader<PagingLoadResult<Ope_operacaoTGWT>> loader = new BasePagingLoader<PagingLoadResult<Ope_operacaoTGWT>>(proxy);
//                    loader.setRemoteSort(true);
//
//                    ListStore<Ope_operacaoTGWT> store = new ListStore<Ope_operacaoTGWT>(loader);
//                    store.add(list.getModels());
//
//                    getToolBarPage().setPageSize(20);
//                    getToolBarPage().bind(loader);
//                    loader.load(0, 20);
//
//                    Grid<Ope_operacaoTGWT> grid = new Grid<Ope_operacaoTGWT>(store, cm);
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
