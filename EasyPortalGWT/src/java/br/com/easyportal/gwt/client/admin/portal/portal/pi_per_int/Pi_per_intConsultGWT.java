/*
 * EasyNet JDragon
 */
package br.com.easyportal.gwt.client.admin.portal.portal.pi_per_int;

import br.com.easyportal.gwt.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;


import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;

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
public class Pi_per_intConsultGWT extends ConsultarBaseGWT {

    private ContentPanel cp = new ContentPanel();
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Pi_per_intDAOGWT pi_per_intDao = new Pi_per_intDAOGWT();
    public Pi_per_intConsultGWT() {
        
            this.setSize("500", "400");
            final NumberFormat currency = NumberFormat.getCurrencyFormat();
            final NumberFormat number = NumberFormat.getFormat("0.00");
            final NumberCellRenderer<Grid<Pi_per_intTGWT>> numberRenderer = new NumberCellRenderer<Grid<Pi_per_intTGWT>>(currency);
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
            column.setId("int_nr_id");
            column.setHeader("Int_nr_id");
            column.setWidth(200);
            column.setAlignment(HorizontalAlignment.LEFT);
            configs.add(column);

	    column = new ColumnConfig();
            column.setId("per_nr_id");
            column.setHeader("Per_nr_id");
            column.setWidth(200);
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
        Pi_per_intInsertGWT pi_per_intInsertGWT = new Pi_per_intInsertGWT();
        pi_per_intInsertGWT.setPi_per_intConsult(this);
        pi_per_intInsertGWT.setModal(true);
        pi_per_intInsertGWT.show();

    }
    private GridCellRenderer<Pi_per_intTGWT> getEditarRender() {
        return new GridCellRenderer<Pi_per_intTGWT>() {

            private boolean init;

            public Object render(final Pi_per_intTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Pi_per_intTGWT> store, Grid<Pi_per_intTGWT> grid) {
                if (!init) {
                    init = true;
                    grid.addListener(Events.ColumnResize, new Listener<GridEvent<Pi_per_intTGWT>>() {

                        public void handleEvent(GridEvent<Pi_per_intTGWT> be) {
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
                        Pi_per_intUpdateDeleteGWT pi_per_intUpdateDeleteGWT = new Pi_per_intUpdateDeleteGWT();
                        pi_per_intUpdateDeleteGWT.setPi_per_intConsult(Pi_per_intConsultGWT.this);
                        pi_per_intUpdateDeleteGWT.load(model);
                        pi_per_intUpdateDeleteGWT.show();
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
    pi_per_intDao.consultarTodos();
    Timer timer = new Timer() {
            public void run() {
                ListStore<Pi_per_intTGWT> list = pi_per_intDao.getList();
                if (list == null) {
                    schedule(500);
                } else {
                    List lista = getCpMaster().getItems();
                    if (lista.size() > 0) {
                        getCpMaster().removeAll();
                    }

                    ColumnModel cm = new ColumnModel(configs);

                    Grid<Pi_per_intTGWT> grid = new Grid<Pi_per_intTGWT>(list, cm);
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

