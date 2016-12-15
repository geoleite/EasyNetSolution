/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.per_perfil;

import br.com.easynet.gwt.client.BaseGWT;
import br.com.i9.portal.client.ConsultaGWTHeight;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.i9.portal.client.portal.portal.men_menu.ListaMenuGWT;
import br.com.i9.portal.client.portal.portal.men_menu.MenuPerfilGWT;
import br.com.i9.portal.client.portal.portal.men_menu.MenuPerfilPermissao;
import br.com.i9.portal.client.portal.portal.met_metodo.ListMetodoGWT;
import br.com.i9.portal.client.portal.portal.ope_operacao.Ope_operacaoConsultGWT1;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Men_menuService;
import br.com.i9.portal.client.rpc.Men_menuServiceAsync;
import br.com.i9.portal.client.rpc.Ope_operacaoService;
import br.com.i9.portal.client.rpc.Ope_operacaoServiceAsync;
import br.com.i9.portal.client.rpc.Per_perfilService;
import br.com.i9.portal.client.rpc.Per_perfilServiceAsync;
import br.com.i9.portal.client.rpc.Usu_usuarioService;
import br.com.i9.portal.client.rpc.Usu_usuarioServiceAsync;

import com.extjs.gxt.ui.client.Style.SelectionMode;
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
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.google.gwt.core.client.GWT;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Vector;

/**
 *
 * @author geoleite
 */
public class Per_perfilConsultGWT extends ConsultaGWTHeight {

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Per_perfilDAOGWT per_perfilDao = new Per_perfilDAOGWT();
    private ContentPanel pnlAccordion = new ContentPanel(new AccordionLayout());
    final private ContentPanel pnlUsu = new ContentPanel(new FillLayout());
    final private ContentPanel pnlOpera = new ContentPanel(new FillLayout());
    final private ContentPanel pnlMenu = new ContentPanel(new FillLayout());
    final ListaMenuGWT listMenuVinc = new ListaMenuGWT();
    private ListMetodoGWT listmetodosVinc = new ListMetodoGWT();
    private Usu_usuarioDAOGWT usuDao = new Usu_usuarioDAOGWT();
    private Men_menuDAOGWT menDao = new Men_menuDAOGWT();
    private Ope_operacaoDAOGWT opeDao = new Ope_operacaoDAOGWT();
    private Button btnRefresh = new Button("Atualizar", BaseGWT.ICONS.refresh());

    public Per_perfilConsultGWT() {
        this.setHeaderVisible(false);
        getDataNORTH().setSize(27);
        getDataEAST().setHidden(false);
        getDataEAST().setSize(250);
        getCpRight().add(pnlAccordion);
        getCpRight().setHeaderVisible(false);
        getCpRight().setLayout(new FillLayout());
        pnlAccordion.setHeaderVisible(false);
        pnlAccordion.setBodyBorder(false);
        getToolBarMaster().add(btnRefresh);
        btnRefresh.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                load();
            }
        });

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

        setHeaderVisible(false);
        this.setSize("500", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Per_perfilTGWT>> numberRenderer = new NumberCellRenderer<Grid<Per_perfilTGWT>>(currency);

        GridCellRenderer<Per_perfilTGWT> gridStatus = new GridCellRenderer<Per_perfilTGWT>() {

            public Object render(Per_perfilTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {
                String txt = "<div style='color:cor'>valor</div>";
                String cor = "green";
                String valor = "Ativo";
                if ("I".equals(model.getPer_tx_status())) {
                    cor = "red";
                    valor = "Inativo";
                }
                txt = txt.replaceAll("cor", cor);
                txt = txt.replaceAll("valor", valor);
                return txt;

            }
        };

        ColumnConfig column = null;

        column = new ColumnConfig();
        column.setId("per_tx_nome");
        column.setHeader("Nome");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("per_tx_class");
        column.setHeader("Classe");
        column.setWidth(300);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("per_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(gridStatus);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        column = new ColumnConfig();
        column.setWidth(30);
        column.setRenderer(getUsuariosRender());
        column.setAlignment(HorizontalAlignment.CENTER);
        configs.add(column);

        column = new ColumnConfig();
        column.setWidth(30);
        column.setRenderer(getOperacoesRender());
        configs.add(column);

        column = new ColumnConfig();
        column.setWidth(30);
        column.setRenderer(getMenuRender());
        configs.add(column);

        column = new ColumnConfig();
        column.setWidth(30);
        column.setRenderer(getExcluirRender());
        configs.add(column);

        load();
    }

    public void montarAcordion(Per_perfilTGWT per_perfilT) {
        try {
            montarPnl_usu(per_perfilT);
            montarPnl_opera(per_perfilT);
            montarPnl_menu(per_perfilT);
        } catch (Exception e) {
        }

    }

    public void montarPnl_usu(final Per_perfilTGWT per_perfilT) throws Exception {
        pnlUsu.removeAll();
        AsyncCallback<List<Usu_usuarioTGWT>> callback = new AsyncCallback<List<Usu_usuarioTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Usu_usuarioTGWT> result) {
                ListStore<Usu_usuarioTGWT> list = new ListStore<Usu_usuarioTGWT>();
                list.add(result);
                List<ColumnConfig> config = new Vector<ColumnConfig>();
                ColumnConfig column = new ColumnConfig("usu_nr_id", "Usuário", 20);
                column.setHidden(true);
                config.add(column);
                column = new ColumnConfig("usu_tx_nome", "nome", 200);
                config.add(column);
                ColumnModel cm = new ColumnModel(config);
                Grid<Usu_usuarioTGWT> grid = new Grid<Usu_usuarioTGWT>(list, cm);
                grid.setStripeRows(true);
                pnlUsu.add(grid);
                layout();
            }
        };
        Usu_usuarioServiceAsync service = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
        service.consultByPerfil(per_perfilT, callback);

//        usuDao.consultByPerfil(per_perfilT);
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                ListStore<Usu_usuarioTGWT> list = usuDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//                    
//                    List<ColumnConfig> config = new Vector<ColumnConfig>();
//                    ColumnConfig column = new ColumnConfig("usu_nr_id", "Usuário", 20);
//                    column.setHidden(true);
//                    config.add(column);
//                    column = new ColumnConfig("usu_tx_nome", "nome", 200);
//                    config.add(column);
//                    ColumnModel cm = new ColumnModel(config);
//                    Grid<Usu_usuarioTGWT> grid = new Grid<Usu_usuarioTGWT>(list, cm);
//                    grid.setStripeRows(true);
//                    pnlUsu.add(grid);
//                    layout();
//                    
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void montarPnl_menu(Per_perfilTGWT per_perfilTGWT) {
        pnlMenu.removeAll();

        
        AsyncCallback<List<Men_menuTGWT>> callback = new AsyncCallback<List<Men_menuTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("Atenção", "Erro ao execultar a consulta", null);
            }

            @Override
            public void onSuccess(List<Men_menuTGWT> result) {
                
                ListStore<Men_menuTGWT> list = new ListStore<Men_menuTGWT>();
                list.add(result);
                MenuPerfilGWT menuPerfilGWT = new MenuPerfilGWT();
                menuPerfilGWT.load(list);
                pnlMenu.add(menuPerfilGWT);
                pnlMenu.layout();
                
            }
        };
        
        Men_menuServiceAsync serviceAsync = EasyAdmPortalRPCFactory.getMen_MenuService();
        serviceAsync.consultByPerfil(per_perfilTGWT, callback);

//        menDao.consultByPerfil(per_perfilTGWT);
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                ListStore<Men_menuTGWT> list = menDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//                    MenuPerfilGWT menuPerfilGWT = new MenuPerfilGWT();
//                    menuPerfilGWT.load(list);
//                    pnlMenu.add(menuPerfilGWT);
//                    pnlMenu.layout();
//
//                }
//            }
//        };
//        timer.schedule(500);
    }

    public void montarPnl_opera(Per_perfilTGWT per_perfilTGWT) throws Exception {
        pnlOpera.removeAll();

        AsyncCallback<List<Ope_operacaoTGWT>> callback = new AsyncCallback<List<Ope_operacaoTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Ope_operacaoTGWT> result) {
                ListStore<Ope_operacaoTGWT> list = new ListStore<Ope_operacaoTGWT>();
                list.add(result);
                List<ColumnConfig> configMetodos = new Vector<ColumnConfig>();
                ColumnConfig column = new ColumnConfig();
                column.setId("ope_tx_nome");
                column.setHeader("Nome");
                column.setWidth(200);
                configMetodos.add(column);
                ColumnModel cm = new ColumnModel(configMetodos);
                Grid<Ope_operacaoTGWT> grid = new Grid<Ope_operacaoTGWT>(list, cm);
                grid.setStripeRows(true);
                pnlOpera.add(grid);
                layout();
                doLayout();
                
            }
        };
        Ope_operacaoServiceAsync service = EasyAdmPortalRPCFactory.getOpe_operacaoService();
        service.consultByPerfil(per_perfilTGWT, callback);

//        opeDao.consultByPerfil(per_perfilTGWT);
//
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                ListStore<Ope_operacaoTGWT> list = opeDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//                    List<ColumnConfig> configMetodos = new Vector<ColumnConfig>();
//                    ColumnConfig column = new ColumnConfig();
//                    column.setId("ope_tx_nome");
//                    column.setHeader("Nome");
//                    column.setWidth(200);
//                    configMetodos.add(column);
//                    ColumnModel cm = new ColumnModel(configMetodos);
//                    Grid<Ope_operacaoTGWT> grid = new Grid<Ope_operacaoTGWT>(list, cm);
//                    grid.setStripeRows(true);
//                    pnlOpera.add(grid);
//                    layout();
//                    doLayout();
//                }
//            }
//        };
//        timer.schedule(500);
//        layout();
        doLayout();
    }

    public void btnAtualizarAction(ButtonEvent be) {
        //super.btnAtualizarAction(be);
    }

    public void btnNovoAction(ButtonEvent be) {
        //br.com.easyportal.gwt.client.admin.portal.portal.per_perfil.Per_perfilInsertGWT per_perfilInsertGWT = new br.com.easyportal.gwt.client.admin.portal.portal.per_perfil.Per_perfilInsertGWT
        Per_perfilInsertGWT per_perfilInsertGWT = new Per_perfilInsertGWT();
        per_perfilInsertGWT.setPer_perfilConsult(Per_perfilConsultGWT.this);
        per_perfilInsertGWT.setModal(true);
        per_perfilInsertGWT.show();
    }

    private GridCellRenderer<Per_perfilTGWT> getMenuRender() {
        return new GridCellRenderer<Per_perfilTGWT>() {

            public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {

                Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {
                    @Override
                    public void componentSelected(ButtonEvent ce) {

                        MenuPerfilPermissao menu = new MenuPerfilPermissao();
                        menu.load(model);
                        menu.setVisible(true);
                    }
                });
                btnCol.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                btnCol.setToolTip("Menu");

                btnCol.setIcon(ICONS.menu_show());
                btnCol.setBorders(false);

                return btnCol;
            }
        };
    }

    private GridCellRenderer<Per_perfilTGWT> getExcluirRender() {
        return new GridCellRenderer<Per_perfilTGWT>() {

            public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {

                Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        MessageBox.confirm("Aviso", "Confirma o exclusao do perfil?", new Listener<MessageBoxEvent>() {

                            public void handleEvent(MessageBoxEvent be) {

                                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
                                    per_perfilDao.excluir(model);
                                    Timer timer = new Timer() {

                                        public void run() {
                                            String msg = per_perfilDao.getMsg();
                                            if (msg == null) {
                                                schedule(500);
                                            } else {
                                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
                                                    MessageBox.alert("Problemas", msg, null);
                                                } else {
                                                    Info.display("Resultado", msg);
                                                    load();
                                                }
                                            }
                                        }
                                    };
                                    timer.schedule(500);

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
    }

    private GridCellRenderer<Per_perfilTGWT> getOperacoesRender() {
        return new GridCellRenderer<Per_perfilTGWT>() {

            public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {

                Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {

                    //@Override
                    public void componentSelected(ButtonEvent ce) {

                        Ope_operacaoConsultGWT1 ope_operacaoConsultGWT = new Ope_operacaoConsultGWT1();
                        Window.alert("getOperacoesRender");
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
    }

    private GridCellRenderer<Per_perfilTGWT> getUsuariosRender() {
        return new GridCellRenderer<Per_perfilTGWT>() {

            public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {

                Button btnCol = new Button("", new SelectionListener<ButtonEvent>() {

                    //@Override
                    public void componentSelected(ButtonEvent ce) {

                        Perfil_usuarioGWT perfilUsuario = new Perfil_usuarioGWT();
                        //perfilUsuario.setConsultGWT(consultGWT);
                        perfilUsuario.load(model);
                        perfilUsuario.setVisible(true);

                    }
                });
                btnCol.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                btnCol.setToolTip("Usuarios");

                btnCol.setIcon(ICONS.user());
                btnCol.setBorders(false);

                return btnCol;
            }
        };
    }

    private GridCellRenderer<Per_perfilTGWT> getEditarRender() {
        return new GridCellRenderer<Per_perfilTGWT>() {

            public Object render(final Per_perfilTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Per_perfilTGWT> store, Grid<Per_perfilTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        Per_perfilUpdateDeleteGWT per_perfilUpdateDeleteGWT = new Per_perfilUpdateDeleteGWT();
                        per_perfilUpdateDeleteGWT.setPer_perfilConsult(Per_perfilConsultGWT.this);
                        per_perfilUpdateDeleteGWT.load(model);
                        per_perfilUpdateDeleteGWT.setVisible(true);
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
        AsyncCallback<List<Per_perfilTGWT>> asyncCallback = new AsyncCallback<List<Per_perfilTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("Atenção", "Erro ao consultar perfis", null);
            }

            @Override
            public void onSuccess(List<Per_perfilTGWT> result) {
                
                List lista = getCpMaster().getItems();
                ListStore<Per_perfilTGWT> list = new ListStore<Per_perfilTGWT>();
                list.add(result);
                if (lista.size() > 0) {
                    getCpMaster().removeAll();
                }

                ColumnModel cm = new ColumnModel(configs);

                PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
                PagingLoader<PagingLoadResult<Per_perfilTGWT>> loader = new BasePagingLoader<PagingLoadResult<Per_perfilTGWT>>(proxy);
                loader.setRemoteSort(true);

                getToolBarPage().setPageSize(20);
                getToolBarPage().bind(loader);
                loader.load(0, 20);
                ListStore<Per_perfilTGWT> store = new ListStore<Per_perfilTGWT>(loader);
                store.add(list.getModels());

                Grid<Per_perfilTGWT> grid = new Grid<Per_perfilTGWT>(store, cm);
                grid.setId("grid");
                grid.setStripeRows(true);
                grid.setLoadMask(true);

                grid.setStyleAttribute("borderTop", "none");
                grid.setBorders(true);
                grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                grid.addListener(Events.OnClick, new Listener<GridEvent<Per_perfilTGWT>>() {

                    public void handleEvent(GridEvent<Per_perfilTGWT> be) {
                        montarAcordion(be.getModel());
                    }
                });

                getCpMaster().add(grid);
                getCpMaster().layout();

            }
        };
        Per_perfilServiceAsync async = EasyAdmPortalRPCFactory.getPer_PerfilService();
        async.getAll(asyncCallback);
    }

//    public void load() {
//        per_perfilDao.consultarTodos();
//        Timer timer = new Timer() {
//
//            public void run() {
//                ListStore<Per_perfilTGWT> list = per_perfilDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//                    List lista = getCpMaster().getItems();
//                    if (lista.size() > 0) {
//                        getCpMaster().removeAll();
//                    }
//
//                    ColumnModel cm = new ColumnModel(configs);
//
//                    PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
//                    PagingLoader<PagingLoadResult<Per_perfilTGWT>> loader = new BasePagingLoader<PagingLoadResult<Per_perfilTGWT>>(proxy);
//                    loader.setRemoteSort(true);
//
//                    getToolBarPage().setPageSize(20);
//                    getToolBarPage().bind(loader);
//                    loader.load(0, 20);
//                    ListStore<Per_perfilTGWT> store = new ListStore<Per_perfilTGWT>(loader);
//                    store.add(list.getModels());
//
//
//                    Grid<Per_perfilTGWT> grid = new Grid<Per_perfilTGWT>(store, cm);
//                    grid.setId("grid");
//                    grid.setStripeRows(true);
//                    grid.setLoadMask(true);
//
//                    grid.setStyleAttribute("borderTop", "none");
//                    grid.setBorders(true);
//                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//                    grid.addListener(Events.OnClick, new Listener<GridEvent<Per_perfilTGWT>>() {
//
//                        public void handleEvent(GridEvent<Per_perfilTGWT> be) {
//                            montarAcordion(be.getModel());
//                        }
//                    });
//
//                    getCpMaster().add(grid);
//                    getCpMaster().layout();
//                }
//            }
//        };
//        timer.schedule(500);
//    }
}
