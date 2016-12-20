/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.res_recall_senha;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;


import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
import br.com.easynet.gwt.client.CPConsultarBaseGWT;

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
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import java.util.Date;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Res_recall_senhaConsultGWT extends CPConsultarBaseGWT {

    private Grid<Res_recall_senhaTGWT> grid = null;
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Res_recall_senhaDAOGWT res_recall_senhaDao = new Res_recall_senhaDAOGWT();

    public Res_recall_senhaConsultGWT() {
        setHeaderVisible(false);
        this.setSize("500", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Res_recall_senhaTGWT>> numberRenderer = new NumberCellRenderer<Grid<Res_recall_senhaTGWT>>(currency);
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
        RowNumberer numero = new RowNumberer();
        column = new ColumnConfig();
        configs.add(numero);


        column = new ColumnConfig();
        column.setId("usu_nr_id");
        column.setHeader("Usu_nr_id");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("res_tx_pergunta");
        column.setHeader("Res_tx_pergunta");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("res_tx_resposta");
        column.setHeader("Res_tx_resposta");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("res_dt_ultimoacesso");
        column.setHeader("Res_dt_ultimoacesso");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("res_nr_tentativas");
        column.setHeader("Res_nr_tentativas");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);



        column = new ColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        load();
    }

    public void btnAtualizarAction(ButtonEvent be) {
        super.btnAtualizarAction(be);
    }

    public void btnNovoAction(ButtonEvent be) {
        Res_recall_senhaInsertGWT res_recall_senhaInsertGWT = new Res_recall_senhaInsertGWT();
        res_recall_senhaInsertGWT.setRes_recall_senhaConsult(this);
        res_recall_senhaInsertGWT.setModal(true);
        res_recall_senhaInsertGWT.show();
    }

    private GridCellRenderer<Res_recall_senhaTGWT> getEditarRender() {
        return new GridCellRenderer<Res_recall_senhaTGWT>() {

            public Object render(final Res_recall_senhaTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Res_recall_senhaTGWT> store, Grid<Res_recall_senhaTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Res_recall_senhaUpdateDeleteGWT res_recall_senhaUpdateDeleteGWT = new Res_recall_senhaUpdateDeleteGWT();
                        res_recall_senhaUpdateDeleteGWT.setRes_recall_senhaConsult(Res_recall_senhaConsultGWT.this);
                        res_recall_senhaUpdateDeleteGWT.load(model);
                        res_recall_senhaUpdateDeleteGWT.setVisible(true);
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

    /**
     * Opc o para exportar dados
     * @param formato
     */
    public void export(String formato) {
        //FORMAT_CSV
        //FORMAT_PDF
        //FORMAT_TXT
        String url = Res_recall_senhaDAOGWT.PAGE_REPORT;
	if (FORMAT_CSV.equals(formato)) {
            url += "?op=imprimir&tipo=PDF&download=true";
        } else if (FORMAT_PDF.equals(formato)) {
            url += "?op=imprimir&tipo=CSV&download=true";
        } else {
            url += "?op=imprimir&tipo=TXT&download=true";
        }
    }

    public void load() {
        res_recall_senhaDao.getAll();
        Timer timer = new Timer() {

            public void run() {
                ListStore<Res_recall_senhaTGWT> list = res_recall_senhaDao.getList();
                if (list == null) {
                    schedule(500);
                } else {

                    if (grid != null) {
                        grid.removeFromParent();
                    }

                    ColumnModel cm = new ColumnModel(configs);

                    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
                    PagingLoader<PagingLoadResult<Res_recall_senhaTGWT>> loader = new BasePagingLoader<PagingLoadResult<Res_recall_senhaTGWT>>(proxy);
                    loader.setRemoteSort(true);

                    ListStore<Res_recall_senhaTGWT> store = new ListStore<Res_recall_senhaTGWT>(loader);
                    store.add(list.getModels());

                    getToolBarPage().setPageSize(20);
                    getToolBarPage().bind(loader);
                    loader.load(0, 20);

                    grid = new Grid<Res_recall_senhaTGWT>(store, cm);
                    grid.setColumnReordering(true);
                    grid.setId("grid");
                    grid.setLoadMask(true);

                    grid.setStyleAttribute("borderTop", "none");
                    grid.setBorders(true);
                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                    getCpMaster().add(grid);
                    getCpMaster().layout();
                }
            }
        };
        timer.schedule(500);
    }
}
