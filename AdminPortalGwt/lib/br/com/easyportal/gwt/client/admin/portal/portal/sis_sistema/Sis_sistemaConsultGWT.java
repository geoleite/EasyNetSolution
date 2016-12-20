/*
 * EasyNet JDragon
 */
package br.com.easyportal.gwt.client.admin.portal.portal.sis_sistema;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;


import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.ope_operacao.Ope_operacaoConsultGWT;

import com.google.gwt.json.client.JSONValue;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.event.SelectionEvent;
import com.extjs.gxt.ui.client.event.Listener;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import java.util.Date;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Sis_sistemaConsultGWT extends ConsultarBaseGWT {

    private ContentPanel cp = new ContentPanel();
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Sis_sistemaDAOGWT sis_sistemaDao = new Sis_sistemaDAOGWT();
    private boolean exibir = false;

    public Sis_sistemaConsultGWT() {
        setHeading("Sistemas");
        setModal(true);
        this.setSize("550", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Sis_sistemaTGWT>> numberRenderer = new NumberCellRenderer<Grid<Sis_sistemaTGWT>>(currency);

        GridCellRenderer<Sis_sistemaTGWT> statusRender = new GridCellRenderer<Sis_sistemaTGWT>() {

            public Object render(Sis_sistemaTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Sis_sistemaTGWT> store, Grid<Sis_sistemaTGWT> grid) {
                return "A".equalsIgnoreCase(model.getSis_tx_status()) ? "Ativo" : "Inativo";
            }
        };
        /*
        GridCellRenderer<Stock> gridNumber = new GridCellRenderer<Stock>() {
        public String render(Stock model, String property, ColumnData config, int rowIndex,
        int colIndex, ListStore<Stock> store) {
        return numberRenderer.render(null, property, model.get(property));
        }
        };
         */

        ColumnConfig column = null;


        column = new ColumnConfig();
        column.setId("sis_tx_nome");
        column.setHeader("Nome");
        column.setWidth(150);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("sis_tx_descricao");
        column.setHeader("Descricao");
        column.setWidth(250);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("sis_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(statusRender);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("imgOperacoes");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getOperacoesRender());
        configs.add(column);


        column = new ColumnConfig();
        column.setId("imgEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        load();
    }

    public void btnNovoAction(ButtonEvent be) {
        Sis_sistemaInsertGWT sis_sistemaInsertGWT = new Sis_sistemaInsertGWT();
        sis_sistemaInsertGWT.setSis_sistemaConsult(this);
        sis_sistemaInsertGWT.setModal(true);
        sis_sistemaInsertGWT.show();

    }

    private GridCellRenderer<Sis_sistemaTGWT> getEditarRender() {
        return new GridCellRenderer<Sis_sistemaTGWT>() {

            public Object render(final Sis_sistemaTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Sis_sistemaTGWT> store, Grid<Sis_sistemaTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Sis_sistemaUpdateDeleteGWT sis_sistemaUpdateDeleteGWT = new Sis_sistemaUpdateDeleteGWT();
                        sis_sistemaUpdateDeleteGWT.setSis_sistemaConsult(Sis_sistemaConsultGWT.this);
                        sis_sistemaUpdateDeleteGWT.load(model);
                        sis_sistemaUpdateDeleteGWT.show();
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Alterar dados.");
                b.setIcon(ICONS.edit());

                return b;
            }
        };
    }

    private GridCellRenderer<Sis_sistemaTGWT> getOperacoesRender() {
        return new GridCellRenderer<Sis_sistemaTGWT>() {

            public Object render(final Sis_sistemaTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Sis_sistemaTGWT> store, Grid<Sis_sistemaTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Ope_operacaoConsultGWT ope_operacaoConsultGWT = new Ope_operacaoConsultGWT();
                        ope_operacaoConsultGWT.setSisT(model);
                        ope_operacaoConsultGWT.load();
                        ope_operacaoConsultGWT.show();
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Operações do Sistema.");
                b.setIcon(ICONS.album());
                return b;
            }
        };
    }

    public void load() {
        exibir = false;
        sis_sistemaDao.consultarTodos();
        Timer timer = new Timer() {

            public void run() {
                ListStore<Sis_sistemaTGWT> list = sis_sistemaDao.getList();
                if (list == null) {
                    schedule(500);
                } else {
                    List lista = getCpMaster().getItems();
                    if (lista.size() > 0) {
                        getCpMaster().removeAll();
                    }
                    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
                    PagingLoader<PagingLoadResult<Sis_sistemaTGWT>> loader = new BasePagingLoader<PagingLoadResult<Sis_sistemaTGWT>>(proxy);
                    loader.setRemoteSort(true);

                    getToolBarPage().setPageSize(20);
                    getToolBarPage().bind(loader);
                    loader.load(0, 20);

                    ListStore<Sis_sistemaTGWT> store = new ListStore<Sis_sistemaTGWT>(loader);
                    store.add(list.getModels());

                    ColumnModel cm = new ColumnModel(configs);

                    Grid<Sis_sistemaTGWT> grid = new Grid<Sis_sistemaTGWT>(store, cm);
                    grid.setLoadMask(true);

                    grid.setStyleAttribute("borderTop", "none");
                    grid.setBorders(true);
                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                    getCpMaster().add(grid);
                    getCpMaster().layout();
                    exibir = true;
                }
            }
        };
        timer.schedule(500);
    }

    /**
     * @return the exibir
     */
    public boolean isExibir() {
        return exibir;
    }

    /**
     * @param exibir the exibir to set
     */
    public void setExibir(boolean exibir) {
        this.exibir = exibir;
    }
}