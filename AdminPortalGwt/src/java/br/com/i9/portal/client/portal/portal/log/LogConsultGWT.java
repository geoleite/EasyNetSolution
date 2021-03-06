/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.log;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;


import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
import br.com.i9.portal.client.portal.portal.icons.Icones;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.LogService;
import br.com.i9.portal.client.rpc.LogServiceAsync;
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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
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
public class LogConsultGWT extends ConsultarBaseGWT {
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateTimeFormat dtfDateTime = DateTimeFormat.getFormat("dd/MM/yyyy HH:mm:ss");

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private LogDAOGWT logDao = new LogDAOGWT();
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private EasyTextField<String> etfClasse = new EasyTextField<String>();
    private EasyTextField<String> etfMetodo = new EasyTextField<String>();
    private EasyTextField<String> etfUsuario = new EasyTextField<String>();
    private DateField dfInicio = new DateField();
    private DateField dfFinal = new DateField();
    private Button btnPesquisar = new Button("", ICONS.find());

    public LogConsultGWT() {
        setHeaderVisible(false);

        this.setSize("500", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<LogTGWT>> numberRenderer = new NumberCellRenderer<Grid<LogTGWT>>(currency);
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

        cbSistema.setEmptyText("Sel. Sistema");
        etfUsuario.setEmptyText("Nome Usuario");
        etfClasse.setEmptyText("Classe");
        etfMetodo.setEmptyText("Método");

        dfInicio.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
        Date dtAtual = new Date();
        dfInicio.setValue(dtAtual);

        long timeUmDia = 86400000; //tempo em milisegundos de 24 horas
        dfFinal.setValue(new Date(dtAtual.getTime() + timeUmDia));
        dfFinal.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));

        getBtnNovoSuper().setVisible(false);
        getBtnRefersh().setVisible(false);
        getToolBarMaster().add(cbSistema);
        getToolBarMaster().add(etfUsuario);
        getToolBarMaster().add(etfClasse);
        getToolBarMaster().add(etfMetodo);
        getToolBarMaster().add(dfInicio);
        getToolBarMaster().add(dfFinal);
        getToolBarMaster().add(btnPesquisar);

        btnPesquisar.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                load();
            }
        });

        GridCellRenderer<LogTGWT> gridStatus = new GridCellRenderer<LogTGWT>() {

            public Object render(LogTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<LogTGWT> store, Grid<LogTGWT> grid) {
                String txt = "<div style='color:cor'>valor</div>";
                String cor = "green";
                String valor = "Ativo";
                if ("I".equals(model.getLog_tx_status())) {
                    cor = "red";
                    valor = "Inativo";
                }
                txt = txt.replaceAll("cor", cor);
                txt = txt.replaceAll("valor", valor);
                return txt;
            }
        };

        SummaryColumnConfig column = null;

        column = new SummaryColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("log_tx_sistema");
        column.setHeader("Sistema");
        column.setWidth(150);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("log_tx_usuario");
        column.setHeader("Usuário");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("log_tx_classe");
        column.setHeader("Classe");
        column.setWidth(150);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("log_tx_metodo");
        column.setHeader("Método");
        column.setWidth(120);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("log_dt_datahora");
        column.setHeader("Data/Hora");
        column.setWidth(100);
        column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy HH:mm"));
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("log_tx_ip");
        column.setHeader("IP");
        column.setWidth(100);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("log_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(gridStatus);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        cbSistema.setAllowBlank(true);
        cbSistema.setEmptyText("Sel. Sistema");
        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setMinChars(1);
        cbSistema.setTriggerAction(ComboBox.TriggerAction.ALL);
        cbSistema.setTypeAhead(true);

        loadSistemas();

    }

    private void loadSistemas() {
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
    }

    private GridCellRenderer<LogTGWT> getEditarRender() {
        return new GridCellRenderer<LogTGWT>() {

            public Object render(final LogTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<LogTGWT> store, Grid<LogTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        LogUpdateDeleteGWT logUpdateDeleteGWT = new LogUpdateDeleteGWT();
                        logUpdateDeleteGWT.setLogConsult(LogConsultGWT.this);
                        logUpdateDeleteGWT.load(model);
                        logUpdateDeleteGWT.setVisible(true);
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Visualizar dados.");
                b.setIcon(Icones.ICONS.visualizar());
                b.setId("btnEditar");

                return b;
            }
        };
    }

    public void load() {
        if (dfInicio.getValue() == null || dfFinal.getValue() == null) {
            MessageBox.alert("ATENÇÃO", "É necessário informar um intervalo correto.", null);
            return;
        }
        if (dfFinal.getValue().before(dfInicio.getValue()) ) {
            MessageBox.alert("ATENÇÃO", "Data Início não pode ser maior que Data Final.", null);
            return;
        }
        String dtInicio = dtfDate.format(dfInicio.getValue());
        String dtFinal = dtfDate.format(dfFinal.getValue());
        LogTGWT logT = new LogTGWT();
        if (cbSistema.getValue() != null) {
            logT.setLog_tx_sistema(cbSistema.getValue().getSis_tx_nome());
        }
        logT.setLog_tx_usuario(etfUsuario.getValue());
        logT.setLog_tx_classe(etfClasse.getValue());
        logT.setLog_tx_metodo(etfMetodo.getValue());
        
        
        AsyncCallback<List<LogTGWT>> callback = new AsyncCallback<List<LogTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("ATENÇÃO", "Falha ao realizar a operação", null);
            }

            @Override
            public void onSuccess(List<LogTGWT> result) {
                List lista = getCpMaster().getItems();
                    if (lista.size() > 0) {
                        getCpMaster().removeAll();
                    }

                    ColumnModel cm = new ColumnModel(configs);

                    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(result);
                    PagingLoader<PagingLoadResult<LogTGWT>> loader = new BasePagingLoader<PagingLoadResult<LogTGWT>>(proxy);
                    loader.setRemoteSort(true);

                    ListStore<LogTGWT> store = new ListStore<LogTGWT>(loader);
                    store.add(result);
                    getToolBarPage().setPageSize(20);
                    getToolBarPage().bind(loader);
                    loader.load(0, 20);


                    Grid<LogTGWT> grid = new Grid<LogTGWT>(store, cm);
                    grid.setId("grid");
                    grid.setLoadMask(true);
                    grid.getView().setEmptyText("Nenhum Registro");

                    grid.setStyleAttribute("borderTop", "none");
                    grid.setBorders(true);
                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                    getCpMaster().add(grid);
                    getCpMaster().layout();
            }
        };
        LogServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getLogService();
        serviceAsync.consult(logT, dtInicio, dtFinal, callback);
        
//        
//        logDao.consultByFiltroGenerico(logT, dtInicio, dtFinal);
//        Timer timer = new Timer() {
//
//            public void run() {
//                ListStore<LogTGWT> list = logDao.getList();
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
//                    PagingLoader<PagingLoadResult<LogTGWT>> loader = new BasePagingLoader<PagingLoadResult<LogTGWT>>(proxy);
//                    loader.setRemoteSort(true);
//
//                    ListStore<LogTGWT> store = new ListStore<LogTGWT>(loader);
//                    store.add(list.getModels());
//                    getToolBarPage().setPageSize(20);
//                    getToolBarPage().bind(loader);
//                    loader.load(0, 20);
//
//
//                    Grid<LogTGWT> grid = new Grid<LogTGWT>(store, cm);
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
