/*
 * EasyNet JDragon
 */
package br.com.easyportal.gwt.client.admin.portal.portal.per_perfil;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;


import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;

import com.google.gwt.json.client.JSONValue;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.event.SelectionEvent;
import com.extjs.gxt.ui.client.event.Listener;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
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

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import java.util.Date;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author geoleite
 */
public class Per_perfilConsultGWT extends ConsultarBaseGWT {

    private ContentPanel cp = new ContentPanel();
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Per_perfilDAOGWT per_perfilDao = new Per_perfilDAOGWT();
    private boolean exibir = false;

    public Per_perfilConsultGWT() {
        setHeading("Consultar Perfis");
        setModal(true);
        this.setSize("600", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Per_perfilTGWT>> numberRenderer = new NumberCellRenderer<Grid<Per_perfilTGWT>>(currency);
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
        GridCellRenderer<Per_perfilTGWT> statusRender = new GridCellRenderer<Per_perfilTGWT>() {

            public Object render(Per_perfilTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {
                return "A".equalsIgnoreCase(model.getPer_tx_status()) ? "Ativo" : "Inativo";
            }
        };

        ColumnConfig column = null;


        column = new ColumnConfig();
        column.setId("per_tx_nome");
        column.setHeader("Nome");
        column.setWidth(120);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("per_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(statusRender);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("per_tx_class");
        column.setHeader("Class Java");
        column.setWidth(300);
        column.setAlignment(HorizontalAlignment.LEFT);
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
        Per_perfilInsertGWT per_perfilInsertGWT = new Per_perfilInsertGWT();
        per_perfilInsertGWT.setPer_perfilConsult(this);
        per_perfilInsertGWT.setModal(true);
        per_perfilInsertGWT.show();

    }

    private GridCellRenderer<Per_perfilTGWT> getEditarRender() {
        return new GridCellRenderer<Per_perfilTGWT>() {

            private boolean init;

            public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {
                if (!init) {
                    init = true;
                    grid.addListener(Events.ColumnResize, new Listener<GridEvent<Per_perfilTGWT>>() {

                        public void handleEvent(GridEvent<Per_perfilTGWT> be) {
                            for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
                                if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
                                        && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
                                    ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);
                                }
                            }
                        }
                    });
                }

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Per_perfilUpdateDeleteGWT per_perfilUpdateDeleteGWT = new Per_perfilUpdateDeleteGWT();
                        per_perfilUpdateDeleteGWT.setPer_perfilConsult(Per_perfilConsultGWT.this);
                        per_perfilUpdateDeleteGWT.load(model);
                        per_perfilUpdateDeleteGWT.show();
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Alterar dados.");
                b.setIcon(ICONS.edit());

                return b;
            }
        };
    }

    public void load() {
        per_perfilDao.consultarTodos();
        Timer timer = new Timer() {

            public void run() {
                ListStore<Per_perfilTGWT> list = per_perfilDao.getList();
                if (list == null) {
                    schedule(500);
                } else {
                    List lista = getCpMaster().getItems();
                    if (lista.size() > 0) {
                        getCpMaster().removeAll();
                    }

                    ColumnModel cm = new ColumnModel(configs);

                    Grid<Per_perfilTGWT> grid = new Grid<Per_perfilTGWT>(list, cm);
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
