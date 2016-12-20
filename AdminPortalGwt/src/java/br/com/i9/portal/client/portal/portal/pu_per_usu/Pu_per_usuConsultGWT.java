/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.pu_per_usu;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;


import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;

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
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import java.util.Date;
import com.google.gwt.user.client.Timer;
/**
 *
 * @author geoleite
 */
public class Pu_per_usuConsultGWT extends ConsultarBaseGWT {

    private ContentPanel cp = new ContentPanel();
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Pu_per_usuDAOGWT pu_per_usuDao = new Pu_per_usuDAOGWT();
    public Pu_per_usuConsultGWT() {
        
            this.setSize("500", "400");
            final NumberFormat currency = NumberFormat.getCurrencyFormat();
            final NumberFormat number = NumberFormat.getFormat("0.00");
            final NumberCellRenderer<Grid<Pu_per_usuTGWT>> numberRenderer = new NumberCellRenderer<Grid<Pu_per_usuTGWT>>(currency);
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
            column.setId("per_nr_id");
            column.setHeader("Per_nr_id");
            column.setWidth(200);
            column.setAlignment(HorizontalAlignment.LEFT);
            configs.add(column);

	    column = new ColumnConfig();
            column.setId("usu_nr_id");
            column.setHeader("Usu_nr_id");
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

    public void btnAtualizarAction(ButtonEvent be){
        super.btnAtualizarAction(be);
    }

    public void btnNovoAction(ButtonEvent be) {
        Pu_per_usuInsertGWT pu_per_usuInsertGWT = new Pu_per_usuInsertGWT();
        pu_per_usuInsertGWT.setPu_per_usuConsult(this);
        pu_per_usuInsertGWT.setModal(true);
        pu_per_usuInsertGWT.show();

    }
    private GridCellRenderer<Pu_per_usuTGWT> getEditarRender() {
        return new GridCellRenderer<Pu_per_usuTGWT>() {


            public Object render(final Pu_per_usuTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Pu_per_usuTGWT> store, Grid<Pu_per_usuTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Pu_per_usuUpdateDeleteGWT pu_per_usuUpdateDeleteGWT = new Pu_per_usuUpdateDeleteGWT();
                        pu_per_usuUpdateDeleteGWT.setPu_per_usuConsult(Pu_per_usuConsultGWT.this);
                        pu_per_usuUpdateDeleteGWT.load(model);
                        pu_per_usuUpdateDeleteGWT.setVisible(true);
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
    pu_per_usuDao.consultarTodos();
    Timer timer = new Timer() {
            public void run() {
                ListStore<Pu_per_usuTGWT> list = pu_per_usuDao.getList();
                if (list == null) {
                    schedule(500);
                } else {
                    List lista = getCpMaster().getItems();
                    if (lista.size() > 0) {
                        getCpMaster().removeAll();
                    }

                    ColumnModel cm = new ColumnModel(configs);

        	    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
                    PagingLoader<PagingLoadResult<Pu_per_usuTGWT>> loader = new BasePagingLoader<PagingLoadResult<Pu_per_usuTGWT>>(proxy);
        	    loader.setRemoteSort(true);

        	    getToolBarPage().setPageSize(20);
        	    getToolBarPage().bind(loader);
        	    loader.load(0, 20);
        	    ListStore<Pu_per_usuTGWT> store = new ListStore<Pu_per_usuTGWT>(loader);
        	    store.add(list.getModels());


                    Grid<Pu_per_usuTGWT> grid = new Grid<Pu_per_usuTGWT>(store, cm);
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

