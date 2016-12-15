/*
 * EasyNet JDragon
 */
package br.com.easyportal.gwt.client.admin.portal.portal.ope_operacao;

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
import com.extjs.gxt.ui.client.widget.form.TextField;
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
public class Ope_operacaoConsultGWT extends ConsultarBaseGWT {

    private ContentPanel cp = new ContentPanel();
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Ope_operacaoDAOGWT ope_operacaoDao = new Ope_operacaoDAOGWT();
    private Sis_sistemaTGWT sisT;
    private TextField<String> tfNome = new TextField<String>();

    public Ope_operacaoConsultGWT() {
        setHeading("Consultar Operações");
        setModal(true);
        this.setSize("700", "400");
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
        GridCellRenderer<Ope_operacaoTGWT> statusRender = new GridCellRenderer<Ope_operacaoTGWT>() {

            public Object render(Ope_operacaoTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Ope_operacaoTGWT> store, Grid<Ope_operacaoTGWT> grid) {
                return "A".equalsIgnoreCase(model.getOpe_tx_status()) ? "Ativo" : "Inativo";
            }
        };

        ColumnConfig column = null;


        column = new ColumnConfig();
        column.setId("ope_tx_nome");
        column.setHeader("Nome");
        column.setWidth(130);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("ope_tx_descricao");
        column.setHeader("Descricao");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("ope_tx_classe");
        column.setHeader("Class Java");
        column.setWidth(300);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("ope_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(statusRender);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);


        column = new ColumnConfig();
        column.setId("imgEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

    }

    public void btnNovoAction(ButtonEvent be) {
        Ope_operacaoInsertGWT ope_operacaoInsertGWT = new Ope_operacaoInsertGWT();
        ope_operacaoInsertGWT.setSisT(sisT);
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
                        ope_operacaoUpdateDeleteGWT.show();
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
        if (sisT != null) {
            ope_operacaoDao.consultarPorSistema(sisT);
            Timer timer = new Timer() {

                public void run() {
                    ListStore<Ope_operacaoTGWT> list = ope_operacaoDao.getList();
                    if (list == null) {
                        schedule(500);
                    } else {
                        List lista = getCpMaster().getItems();
                        if (lista.size() > 0) {
                            getCpMaster().removeAll();
                        }

                        ColumnModel cm = new ColumnModel(configs);

                        Grid<Ope_operacaoTGWT> grid = new Grid<Ope_operacaoTGWT>(list, cm);
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

    /**
     * @return the sisT
     */
    public Sis_sistemaTGWT getSisT() {
        return sisT;
    }

    /**
     * @param sisT the sisT to set
     */
    public void setSisT(Sis_sistemaTGWT sisT) {
        this.sisT = sisT;
    }
}
