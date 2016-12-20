/*
 * EasyNet JDragon
 */
package br.com.easyportal.gwt.client.admin.portal.portal.log;

import br.com.easyportal.gwt.client.admin.portal.portal.transfer.*;
import br.com.easyportal.gwt.client.admin.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;

import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.Listener;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.TableLayout;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.i18n.client.DateTimeFormat;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import java.util.Date;

/**
 *
 * @author geoleite
 */
public class LogConsultGWT extends ConsultarBaseGWT {

    private TextField<String> tfUsuario = new TextField<String>();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private TextField<String> tfClasse = new TextField<String>();
    private TextField<String> tfMetodo = new TextField<String>();
    private DateField dfInicio = new DateField();
    private DateField dfFinal = new DateField();
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private LogDAOGWT logDao = new LogDAOGWT();
    private Button btnPesquisar = new Button("Pesquisar");
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();

    public LogConsultGWT() {
        getCpTop().removeAll();
        dfInicio.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
        dfFinal.getPropertyEditor().setFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
        getCpTop().add(new LabelField("Usuário"));
        getCpTop().add(tfUsuario);
        getCpTop().add(new LabelField("Sistemas"));
        getCpTop().add(cbSistema);
        getCpTop().add(new LabelField("Classe"));
        getCpTop().add(tfClasse);
        getCpTop().add(new LabelField("Método"));
        getCpTop().add(tfMetodo);
        getCpTop().add(new LabelField("Dt Inicio"));
        getCpTop().add(dfInicio);
        getCpTop().add(new LabelField("Dt Final"));
        getCpTop().add(dfFinal);
        getCpTop().addButton(btnPesquisar);


        setModal(true);
        cbSistema.setDisplayField("sis_tx_nome");
        cbSistema.setEditable(false);
        cbSistema.setValueField("sis_tx_nome");
        btnPesquisar.setIcon(ICONS.find());

        getCpTop().setLayout(new TableLayout(4));
        add(getCpTop(), getDataNORTH());
        getDataNORTH().setCollapsible(true);

        setHeading("Consultar Log");
        //getToolBarMaster().removeAll();
        getCpTop().setScrollMode(Scroll.AUTO);
        this.setSize("700", "500");
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

        ColumnConfig column = null;

        column = new ColumnConfig();
        column.setId("log_tx_sistema");
        column.setHeader("Sistema");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("log_tx_classe");
        column.setHeader("Classe");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("log_tx_metodo");
        column.setHeader("Metodo");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("log_tx_usuario");
        column.setHeader("Usuario");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("log_dt_datahora");
        column.setHeader("Data/hora");
        column.setWidth(100);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("log_tx_status");
        column.setHeader("Status");
        column.setWidth(300);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("log_tx_ip");
        column.setHeader("IP");
        column.setWidth(100);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);


        column = new ColumnConfig();
        column.setId("imgEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        sisDao.consultarTodos();
        Timer timer = new Timer() {

            @Override
            public void run() {
                ListStore<Sis_sistemaTGWT> list = sisDao.getList();
                if (list == null) {
                    schedule(500);
                } else {
                    if (cbSistema.getStore() != null) {
                        cbSistema.getStore().removeAll();
                    }
                    cbSistema.setStore(list);
                    cbSistema.getListView().setStore(list);
                    cbSistema.getListView().refresh();
                    layout();
                    //setVisible(true);
                }
            }
        };
        timer.schedule(500);

        btnPesquisar.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                load();
            }
        });
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
                        logUpdateDeleteGWT.show();
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
        LogTGWT logT = new LogTGWT();
        logT.setLog_tx_classe(tfClasse.getValue());
        logT.setLog_tx_metodo(tfMetodo.getValue());
        logT.setLog_tx_usuario(tfUsuario.getValue());
        Date dtInicial = dfInicio.getValue();
        Date dtFinal = dfFinal.getValue();

        Sis_sistemaTGWT sisT = cbSistema.getValue();
        if (sisT != null) {
            logT.setLog_tx_sistema(sisT.getSis_tx_nome());
        }
        logDao.consultarCreterio(logT, dtInicial, dtFinal);
        Timer timer = new Timer() {

            public void run() {
                ListStore<LogTGWT> list = logDao.getList();
                if (list == null) {
                    schedule(500);
                } else {
                    List lista = getCpMaster().getItems();
                    if (lista.size() > 0) {
                        getCpMaster().removeAll();
                    }

                    ColumnModel cm = new ColumnModel(configs);

                    Grid<LogTGWT> grid = new Grid<LogTGWT>(list, cm);
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
