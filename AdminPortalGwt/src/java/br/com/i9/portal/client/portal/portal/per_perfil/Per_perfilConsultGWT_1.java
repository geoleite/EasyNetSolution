package br.com.i9.portal.client.portal.portal.per_perfil;

/*
 * EasyNet JDragon
 */
import br.com.easynet.gwt.client.CPConsultarBaseGWT;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.BaseTreeGWT;
import br.com.i9.portal.client.Constantes;
import br.com.i9.portal.client.ConsultaGWTHeight;
import br.com.i9.portal.client.MontarTreeGWT;
import br.com.i9.portal.client.portal.portal.men_menu.ListaMenuGWT;
import br.com.i9.portal.client.portal.portal.men_menu.MenuPerfilPermissao;
import br.com.i9.portal.client.portal.portal.met_metodo.ListMetodoGWT;
import br.com.i9.portal.client.portal.portal.ope_operacao.Ope_operacaoConsultGWT;
import br.com.i9.portal.client.portal.portal.ope_operacao.Ope_operacaoConsultGWT1;
import br.com.i9.portal.client.portal.portal.transfer.Met_metodoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usuario_sistemaTGWT;
import br.com.i9.portal.client.portal.portal.usuario_sistema.ListUsuarioSistemaGWT;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.google.gwt.json.client.JSONValue;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.event.Listener;

import java.util.ArrayList;
import java.util.List;



import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.data.BaseTreeModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.event.ToolBarEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.BoxComponent;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGrid;
import com.extjs.gxt.ui.client.widget.treegrid.TreeGridCellRenderer;
import com.google.gwt.user.client.Timer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author geoleite
 */
public class Per_perfilConsultGWT_1 extends ConsultaGWTHeight implements IListenetResponse {

    public static final String PAGE = "portal/portal/per_perfil/per_perfilConsultGWT.jsp";
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    final Per_perfilConsultGWT_1 consultGWT = this;
    private ContentPanel pnlAccordion = new ContentPanel(new AccordionLayout());
    final private ContentPanel pnlUsu = new ContentPanel(new FillLayout());
    final private ContentPanel pnlOpera = new ContentPanel(new FillLayout());
    final private ContentPanel pnlMenu = new ContentPanel(new FillLayout());
    final ListaMenuGWT listMenuVinc = new ListaMenuGWT();
    private ListMetodoGWT listmetodosVinc = new ListMetodoGWT();

    public Per_perfilConsultGWT_1() {
        try {
            this.setHeaderVisible(false);
            getDataNORTH().setSize(27);
            getDataEAST().setHidden(false);
            getDataEAST().setSize(250);
            getCpRight().add(pnlAccordion);
            getCpRight().setHeaderVisible(false);
            getCpRight().setLayout(new FillLayout());
            pnlAccordion.setHeaderVisible(false);
            pnlAccordion.setBodyBorder(false);

            pnlAccordion.add(pnlUsu);
            pnlAccordion.add(pnlOpera);
            pnlAccordion.add(pnlMenu);
            pnlAccordion.setTabIndex(2);

            pnlUsu.setHeading("Usuários");
            pnlUsu.setIcon(ICONS.addUser());

            pnlOpera.setHeading("Operações");
            pnlOpera.setIcon(ICONS.plugin());

            pnlMenu.setHeading("Menus");
            pnlMenu.setIcon(ICONS.menu_show());

            getDataWEST().setHidden(true);
            this.setSize("650", "400");
            final NumberFormat currency = NumberFormat.getCurrencyFormat();
            final NumberFormat number = NumberFormat.getFormat("0.00");
            final NumberCellRenderer<Grid<Per_perfilTGWT>> numberRenderer = new NumberCellRenderer<Grid<Per_perfilTGWT>>(currency);

            ColumnConfig column = null;

            column = new ColumnConfig();
            column.setId("per_tx_nome");
            column.setHeader("Nome");
            column.setWidth(210);
            column.setAlignment(HorizontalAlignment.LEFT);
            configs.add(column);

            GridCellRenderer<Per_perfilTGWT> btnDel = new GridCellRenderer<Per_perfilTGWT>() {

                public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                        final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {

                    Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {

                        @Override
                        public void componentSelected(ButtonEvent ce) {
                            MessageBox.confirm("Aviso", "Confirma o exclusao do perfil?", new Listener<MessageBoxEvent>() {

                                public void handleEvent(MessageBoxEvent be) {

                                    if ("Yes".equalsIgnoreCase(be.getButtonClicked().getText())) {
                                        EasyAccessURL eaurl = new EasyAccessURL(consultGWT);
                                        HashMap<String, String> param = new HashMap<String, String>();
                                        param.put("op", "delete");
                                        param.put("per_perfilT.per_nr_id", model.getPer_nr_id() + "");
                                        eaurl.accessJSonMap(Constantes.URL + PAGE, param);
                                        load();
                                    }
                                }
                            });
                        }
                    });

                    btnCol.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                    btnCol.setToolTip("Excluir perfil");
                    btnCol.setBorders(false);
                    btnCol.setBounds(0, 0, 0, 0);
                    btnCol.setIcon(ICONS.del());

                    return btnCol;
                }
            };
            GridCellRenderer<Per_perfilTGWT> btnUsuarios = new GridCellRenderer<Per_perfilTGWT>() {

                public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                        final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {


                    Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {

                        //@Override
                        public void componentSelected(ButtonEvent ce) {

                            Perfil_usuarioGWT perfilUsuario = new Perfil_usuarioGWT();
                            //perfilUsuario.setConsultGWT(consultGWT);
                            perfilUsuario.load(model);
                            perfilUsuario.show();

                        }
                    });
                    btnCol.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                    btnCol.setToolTip("Usuarios");

                    btnCol.setIcon(ICONS.user());
                    btnCol.setBorders(false);

                    return btnCol;
                }
            };





            GridCellRenderer<Per_perfilTGWT> btnOperacoes = new GridCellRenderer<Per_perfilTGWT>() {

                public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                        final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {

                    Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {

                        //@Override
                        public void componentSelected(ButtonEvent ce) {

                            Ope_operacaoConsultGWT1 ope_operacaoConsultGWT = new Ope_operacaoConsultGWT1();
                            ope_operacaoConsultGWT.load(model);
                            ope_operacaoConsultGWT.setVisible(true);

                        }
                    });
                    btnCol.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                    btnCol.setToolTip("Operações");

                    btnCol.setIcon(ICONS.cog());
                    btnCol.setBorders(false);

                    return btnCol;
                }
            };


            GridCellRenderer<Per_perfilTGWT> btnMenu = new GridCellRenderer<Per_perfilTGWT>() {

                public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                        final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {

                    Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {
                        //@Override

                        public void componentSelected(ButtonEvent ce) {

                            MenuPerfilPermissao menu = new MenuPerfilPermissao();
                            menu.load(model);
                            menu.show();

                        }
                    });
                    btnCol.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                    btnCol.setToolTip("Menu");

                    btnCol.setIcon(ICONS.menu_show());
                    btnCol.setBorders(false);

                    return btnCol;
                }
            };



            column = new ColumnConfig();
            column.setWidth(30);
            column.setRenderer(btnUsuarios);
            configs.add(column);


            column = new ColumnConfig();
            column.setWidth(30);
            column.setRenderer(btnOperacoes);
            configs.add(column);

            column = new ColumnConfig();
            column.setWidth(30);
            column.setRenderer(btnMenu);
            configs.add(column);

            column = new ColumnConfig();
            column.setWidth(30);
            column.setRenderer(btnDel);
            configs.add(column);


            load();
        } catch (Exception ex) {
            Window.alert(ex.getMessage());
        }
    }

    public void montarAcordion(Per_perfilTGWT per_perfilT) {
        montarPnl_usu(per_perfilT);
    }

    public void montarPnl_usu(Per_perfilTGWT per_perfilT) {
        pnlUsu.removeAll();
        final ListUsuarioSistemaGWT usuarioSistemaGWT = new ListUsuarioSistemaGWT();
        usuarioSistemaGWT.povoaUsuarioVinculado(per_perfilT);
        final Per_perfilTGWT perT = per_perfilT;
        Timer timer = new Timer() {

            @Override
            public void run() {
                if (!usuarioSistemaGWT.preechido) {
                    schedule(500);
                } else {
                    List<ColumnConfig> config = new Vector<ColumnConfig>();
                    ColumnConfig column = new ColumnConfig("usu_nr_id", "Usuário", 20);
                    column.setHidden(true);
                    config.add(column);
                    column = new ColumnConfig("usu_tx_nome", "nome", 200);
                    config.add(column);
                    ColumnModel cm = new ColumnModel(config);
                    ListStore<Usuario_sistemaTGWT> store = new ListStore<Usuario_sistemaTGWT>();
                    store.add(usuarioSistemaGWT.getList());
                    Grid<Usuario_sistemaTGWT> grid = new EditorGrid<Usuario_sistemaTGWT>(store, cm);
                    grid.setStripeRows(true);
                    pnlUsu.add(grid);
                    //layout();
                    montarPnl_menu(perT);
                }
            }
        };
        timer.schedule(500);
    }

    public void montarPnl_menu(Per_perfilTGWT per_perfilTGWT) {
        pnlMenu.removeAll();
        listMenuVinc.povoaMenuVinculado(per_perfilTGWT);
        final Per_perfilTGWT perT = per_perfilTGWT;
        Timer timer = new Timer() {

            @Override
            public void run() {
                if (!listMenuVinc.preechido) {
                    schedule(500);
                } else {
                    List<ColumnConfig> config = new Vector();
                    ColumnConfig column = null;
                    RowNumberer numberer = new RowNumberer();
                    config.add(numberer);
                    ColumnConfig nome = new ColumnConfig("name", "Menu", 200);
                    nome.setRenderer(new TreeGridCellRenderer<BaseTreeGWT>());
                    config.add(column);

                    ColumnModel cm = new ColumnModel(Arrays.asList(numberer, nome));

                    TreeStore<ModelData> store = new TreeStore<ModelData>();
                    MontarTreeGWT montarTrreGWT = new MontarTreeGWT(listMenuVinc.getList(), "supermenu_nr_id", "men_nr_id", "men_tx_nome");
                    BaseTreeGWT baseTreeGWT = montarTrreGWT.getListModelData();
                    store.add(baseTreeGWT.getChildren(), true);

                    TreeGrid<ModelData> grid = new TreeGrid<ModelData>(store, cm);
                    grid.addPlugin(numberer);
                    grid.setStripeRows(true);
                    grid.getStyle().setLeafIcon(ICONS.menu_show());
                    grid.setTrackMouseOver(false);
                    grid.setAutoExpandColumn("name");
                    grid.setAutoExpand(true);
                    pnlMenu.add(grid);
                    montarPnl_opera(perT);
                }
            }
        };
        timer.schedule(500);
    }

    public void montarPnl_opera(Per_perfilTGWT per_perfilTGWT) {
        pnlOpera.removeAll();
        listmetodosVinc.povoaMetodosVinculadoPerfilSistema(per_perfilTGWT);
        Timer timer = new Timer() {

            @Override
            public void run() {
                if (!listmetodosVinc.preechido) {
                    schedule(500);
                } else {
                    List<ColumnConfig> configMetodos = new Vector();
                    ColumnConfig column = new ColumnConfig();
                    column.setId("met_tx_metodo");
                    column.setHeader("Descrição");
                    column.setWidth(200);
                    configMetodos.add(column);

                    column = new ColumnConfig();
                    column.setHidden(true);
                    column.setId("sis_nr_id");
                    column.setHeader("sis");
                    column.setWidth(200);
                    configMetodos.add(column);

                    ColumnModel cm = new ColumnModel(configMetodos);
                    ListStore<Met_metodoTGWT> store = new ListStore<Met_metodoTGWT>();
                    store.add(listmetodosVinc.getList());
                    Grid<Met_metodoTGWT> grid = new Grid<Met_metodoTGWT>(store, cm);
                    grid.setStripeRows(true);
                    pnlOpera.add(grid);
                    layout();
                    doLayout();
                }
            }
        };
        timer.schedule(500);
        layout();
        doLayout();
    }

    public void read(JSONValue jsonValue) {
        JSONObject jsonObject;
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            //Set<String> keys = jsonObject.keySet();
            //Window.alert(usuario);
            JSONArray resultado = jsonObject.get("resultado").isArray();

            ListStore<Per_perfilTGWT> store = new ListStore<Per_perfilTGWT>();
            for (int i = 1; i < resultado.size(); i++) {
                Per_perfilTGWT per_perfilTGWT = new Per_perfilTGWT();
                JSONObject registro = resultado.get(i).isObject();
                Integer per_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("per_nr_id").toString()));
                per_perfilTGWT.setPer_nr_id(per_nr_id);

                String per_tx_nome = EasyContainer.clearAspas(registro.get("per_tx_nome").toString());
                per_perfilTGWT.setPer_tx_nome(per_tx_nome);

                String per_tx_status = EasyContainer.clearAspas(registro.get("per_tx_status").toString());
                per_perfilTGWT.setPer_tx_status(per_tx_status);

                String per_tx_class = EasyContainer.clearAspas(registro.get("per_tx_class").toString());
                per_perfilTGWT.setPer_tx_class(per_tx_class);

                store.add(per_perfilTGWT);
            }


            ColumnModel cm = new ColumnModel(configs);

            Grid<Per_perfilTGWT> grid = new Grid<Per_perfilTGWT>(store, cm);
            grid.setLoadMask(true);

            grid.setStyleAttribute("borderTop", "none");
//            grid.setAutoExpandColumn("name");
            grid.setStripeRows(true);

            grid.addListener(Events.OnDoubleClick, new Listener<GridEvent<Per_perfilTGWT>>() {

                public void handleEvent(GridEvent<Per_perfilTGWT> be) {
                    Per_perfilUpdateDeleteGWT per_perfilUpdateDeleteGWT = new Per_perfilUpdateDeleteGWT();
                    per_perfilUpdateDeleteGWT.load(be.getModel());
//                    per_perfilUpdateDeleteGWT.setPer_perfilConsult(consultGWT);
                    per_perfilUpdateDeleteGWT.show();
                }
            });
            grid.addListener(Events.OnClick, new Listener<GridEvent<Per_perfilTGWT>>() {

                public void handleEvent(GridEvent<Per_perfilTGWT> be) {
                    montarAcordion(be.getModel());
                }
            });


            getCpMaster().removeAll();
            getCpMaster().add(grid);
            this.layout();
            doLayout();
        }
    }

    @Override
    public void btnNovoAction(ButtonEvent be) {
        Per_perfilInsertGWT insert;
        try {
            insert = new Per_perfilInsertGWT();
            //insert.setPer_perfilConsult(consultGWT);
            insert.setModal(true);
            insert.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        EasyAccessURL eaurl = new EasyAccessURL(this);
        eaurl.accessJSon(Constantes.URL + PAGE);//"portalgwt/exemplos/gridexemplo.jsp");
        List list = getCpMaster().getItems();
        getCpMaster().removeAll();
    }
}
