/*
 * EasyNet JDragon
 */
package br.com.easyportal.gwt.client.admin.portal.portal.usu_usuario;

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
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
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
public class Usu_usuarioConsultGWT extends ConsultarBaseGWT {

    private ContentPanel cp = new ContentPanel();
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Usu_usuarioDAOGWT usu_usuarioDao = new Usu_usuarioDAOGWT();
    private boolean exibir = false;
    private TextField<String> tfNome = new TextField<String>();
    private Button btnFind = new Button("");

    public Usu_usuarioConsultGWT() {
        btnFind.setIcon(ICONS.find());
        btnFind.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                load();
            }
        });
        tfNome.setTitle("Pesquisar por Nome ou Login");
        getToolBarMaster().add(new LabelField("Pesquisar:"));
        getToolBarMaster().add(tfNome);
        getToolBarMaster().add(btnFind);
        setHeading("Usuários Cadastrados");
        setModal(true);
        this.setSize("600", "300");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Usu_usuarioTGWT>> numberRenderer = new NumberCellRenderer<Grid<Usu_usuarioTGWT>>(currency);
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

        GridCellRenderer<Usu_usuarioTGWT> statusRender = new GridCellRenderer<Usu_usuarioTGWT>() {

            public Object render(Usu_usuarioTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Usu_usuarioTGWT> store, Grid<Usu_usuarioTGWT> grid) {
                return "A".equalsIgnoreCase(model.getUsu_tx_status()) ? "Ativo" : "Inativo";
            }
        };


        ColumnConfig column = null;


        column = new ColumnConfig();
        column.setId("usu_tx_nome");
        column.setHeader("Nome");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("usu_tx_login");
        column.setHeader("Login");
        column.setWidth(130);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);


        column = new ColumnConfig();
        column.setId("usu_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(statusRender);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("usu_tx_email");
        column.setHeader("Email");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);


        column = new ColumnConfig();
        column.setId("imgAlterarSenha");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getAlterarSenhaRender());
        configs.add(column);

        column = new ColumnConfig();
        column.setId("imgEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        

    }

    public void btnNovoAction(ButtonEvent be) {
        Usu_usuarioInsertGWT usu_usuarioInsertGWT = new Usu_usuarioInsertGWT();
        usu_usuarioInsertGWT.setUsu_usuarioConsult(this);
        usu_usuarioInsertGWT.setModal(true);
        usu_usuarioInsertGWT.show();

    }

    private GridCellRenderer<Usu_usuarioTGWT> getEditarRender() {
        return new GridCellRenderer<Usu_usuarioTGWT>() {

            public Object render(final Usu_usuarioTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Usu_usuarioTGWT> store, Grid<Usu_usuarioTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Usu_usuarioUpdateDeleteGWT usu_usuarioUpdateDeleteGWT = new Usu_usuarioUpdateDeleteGWT();
                        usu_usuarioUpdateDeleteGWT.setUsu_usuarioConsult(Usu_usuarioConsultGWT.this);
                        usu_usuarioUpdateDeleteGWT.load(model);
                        usu_usuarioUpdateDeleteGWT.show();
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Alterar dados.");
                b.setIcon(ICONS.edit());

                return b;
            }
        };
    }
    private GridCellRenderer<Usu_usuarioTGWT> getAlterarSenhaRender() {
        return new GridCellRenderer<Usu_usuarioTGWT>() {

            public Object render(final Usu_usuarioTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Usu_usuarioTGWT> store, Grid<Usu_usuarioTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Usu_usuarioAlterarSenhaGWT usu_usuarioAlterarSenhaGWT = new Usu_usuarioAlterarSenhaGWT();
                        usu_usuarioAlterarSenhaGWT.setUsu_usuarioTGWT(model);
                        usu_usuarioAlterarSenhaGWT.show();
//                        Usu_usuarioUpdateDeleteGWT usu_usuarioUpdateDeleteGWT = new Usu_usuarioUpdateDeleteGWT();
//                        usu_usuarioUpdateDeleteGWT.setUsu_usuarioConsult(Usu_usuarioConsultGWT.this);
//                        usu_usuarioUpdateDeleteGWT.load(model);
//                        usu_usuarioUpdateDeleteGWT.show();
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Alterar Senha do Usuário.");
                b.setIcon(ICONS.chave());
                return b;
            }
        };
    }

    public void load() {
        setExibir(false);
        usu_usuarioDao.consultarUsuarios("N", tfNome.getValue());
        Timer timer = new Timer() {

            public void run() {
                ListStore<Usu_usuarioTGWT> list = usu_usuarioDao.getList();
                if (list == null) {
                    schedule(500);
                } else {
                    List lista = getCpMaster().getItems();
                    if (lista.size() > 0) {
                        getCpMaster().removeAll();
                    }

                    ColumnModel cm = new ColumnModel(configs);

                    Grid<Usu_usuarioTGWT> grid = new Grid<Usu_usuarioTGWT>(list, cm);
                    grid.setLoadMask(true);

                    grid.setStyleAttribute("borderTop", "none");
                    grid.setBorders(true);
                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                    getCpMaster().add(grid);
                    getCpMaster().layout();
                    setExibir(true);
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
