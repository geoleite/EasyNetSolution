/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.men_menu;

import br.com.easynet.gwt.client.CPConsultarBaseGWT;
import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;


import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
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
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
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
import com.extjs.gxt.ui.client.widget.grid.GridGroupRenderer;
import com.extjs.gxt.ui.client.widget.grid.GroupColumnData;
import com.extjs.gxt.ui.client.widget.grid.GroupSummaryView;
import com.extjs.gxt.ui.client.widget.grid.SummaryColumnConfig;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.core.client.GWT;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import java.util.Date;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.TreeMap;

/**
 *
 * @author geoleite
 */
public class Men_menuConsultGWTori extends CPConsultarBaseGWT {

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Men_menuDAOGWT men_menuDao = new Men_menuDAOGWT();
    private Sis_sistemaDAOGWT sisDao = new Sis_sistemaDAOGWT();
    private ComboBox<Sis_sistemaTGWT> cbSistema = new ComboBox<Sis_sistemaTGWT>();
    private TreeMap<Integer, Men_menuTGWT> menMap = new TreeMap<Integer, Men_menuTGWT>();

    public Men_menuConsultGWTori() {
        setHeaderVisible(false);
        this.setSize("500", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Men_menuTGWT>> numberRenderer = new NumberCellRenderer<Grid<Men_menuTGWT>>(currency);
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

        GridCellRenderer<Men_menuTGWT> gridStatus = new GridCellRenderer<Men_menuTGWT>() {

            public Object render(Men_menuTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Men_menuTGWT> store, Grid<Men_menuTGWT> grid) {
                String txt = "<div style='color:cor'>valor</div>";
                String cor = "green";
                String valor = "Ativo";
                if ("I".equals(model.getMen_tx_status())) {
                    cor = "red";
                    valor = "Inativo";
                }
                txt = txt.replaceAll("cor", cor);
                txt = txt.replaceAll("valor", valor);
                return txt;
            }
        };

//        GridCellRenderer<Men_menuTGWT> gridPai = new GridCellRenderer<Men_menuTGWT>() {
//
//            public Object render(Men_menuTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Men_menuTGWT> store, Grid<Men_menuTGWT> grid) {
//                Men_menuTGWT menPai = menMap.get(model.getSupermenu_nr_id());
//                String txt = "<div style='color:cor'>valor</div>";
//                String cor = "blue";
//                String valor = "";
//                if (menPai != null) {
//                    valor = menPai.getMen_tx_nome();
//                } else {
//                    cor = "gray";
//                    valor = "Raiz";
//                }
//                txt = txt.replaceAll("cor", cor);
//                txt = txt.replaceAll("valor", valor);
//                return txt;
//            }
//        };

        SummaryColumnConfig column = null;

        column = new SummaryColumnConfig();
        column.setId("supermenu_nr_id");
        column.setHeader("Menu Pai");
        column.setWidth(200);
        //column.setRenderer(gridPai);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("men_tx_nome");
        column.setHeader("Nome");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("men_nr_ordem");
        column.setHeader("Ord.");
        column.setWidth(40);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("men_tx_acao");
        column.setHeader("Acao");
        column.setWidth(100);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("men_tx_icone");
        column.setHeader("Icone");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("men_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(gridStatus);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new SummaryColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

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
        Sis_sistemaServiceAsync serviceAsync = GWT.create(Sis_sistemaService.class);
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
        Men_menuInsertGWTori men_menuInsertGWT = new Men_menuInsertGWTori();
        men_menuInsertGWT.setMen_menuConsult(this);
        men_menuInsertGWT.setModal(true);
        men_menuInsertGWT.show();

    }

    private GridCellRenderer<Men_menuTGWT> getEditarRender() {
        return new GridCellRenderer<Men_menuTGWT>() {

            public Object render(final Men_menuTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Men_menuTGWT> store, Grid<Men_menuTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Men_menuUpdateDeleteGWTOri men_menuUpdateDeleteGWT = new Men_menuUpdateDeleteGWTOri();
                        men_menuUpdateDeleteGWT.setMen_menuConsult(Men_menuConsultGWTori.this);
                        men_menuUpdateDeleteGWT.load(model);
                        men_menuUpdateDeleteGWT.setVisible(true);
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
        Men_menuTGWT menT = new Men_menuTGWT();
        menT.setSis_nr_id(cbSistema.getValue().getSis_nr_id());
        men_menuDao.consultBySistema(menT);
        Timer timer = new Timer() {

            public void run() {
                ListStore<Men_menuTGWT> list = men_menuDao.getList();
                if (list == null) {
                    schedule(500);
                } else {

                    List lista = getCpMaster().getItems();
                    if (lista.size() > 0) {
                        getCpMaster().removeAll();
                    }
                    menMap.clear();
                    for (int i = 0; i < list.getCount(); i++) {
                        Men_menuTGWT menT = list.getAt(i);
                        menMap.put(menT.getMen_nr_id(), menT);
                        //store.add(list.getAt(i));
                    }
                    
                    final ColumnModel cm = new ColumnModel(configs);

                    GroupSummaryView view = new GroupSummaryView();
                    view.setShowGroupedColumn(false);
                    view.setForceFit(true);

                    view.setGroupRenderer(new GridGroupRenderer() {

                        public String render(GroupColumnData data) {
                            //Info.display("DEBUHG", "ponto1 " + data.text);
                            Men_menuTGWT menPaiT = menMap.get(Integer.parseInt(data.group));
                            //String servico = serMap.get(Integer.parseInt(data.group)).getSer_tx_nome();
                            String f = cm.getColumnById(data.field).getHeader();
                            String l = data.models.size() == 1 ? "Item" : "Items";
                            return menPaiT.getMen_tx_nome() + "(" + data.models.size() + " " + l + ")";
                        }
                    });

                    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
                    PagingLoader<PagingLoadResult<Men_menuTGWT>> loader = new BasePagingLoader<PagingLoadResult<Men_menuTGWT>>(proxy);
                    loader.setRemoteSort(true);

                    GroupingStore<Men_menuTGWT> store = new GroupingStore<Men_menuTGWT>(loader);
                    //ListStore<Men_menuTGWT> store2 = new ListStore<Men_menuTGWT>(loader);
                    store.add(list.getModels());
                    store.groupBy("supermenu_nr_id");


                    getToolBarPage().setPageSize(20);
                    getToolBarPage().bind(loader);
                    loader.load(0, 20);


                    Grid<Men_menuTGWT> grid = new Grid<Men_menuTGWT>(store, cm);
                    grid.setId("grid");
                    grid.setView(view);
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
