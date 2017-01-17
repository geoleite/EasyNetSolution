/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.usu_usuario;

import br.com.easynet.gwt.client.CPConsultarBaseGWT;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Res_recall_senhaService;
import br.com.i9.portal.client.rpc.Res_recall_senhaServiceAsync;
import br.com.i9.portal.client.rpc.Usu_usuarioService;
import br.com.i9.portal.client.rpc.Usu_usuarioServiceAsync;

import com.extjs.gxt.ui.client.widget.MessageBox;
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
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.table.NumberCellRenderer;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.gwt.core.client.GWT;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class Usu_usuarioConsultGWT extends CPConsultarBaseGWT {

    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Usu_usuarioDAOGWT usu_usuarioDao = new Usu_usuarioDAOGWT();
    private Res_recall_senhaDAOGWT res_recall_senhaDAOGWT = new Res_recall_senhaDAOGWT();
    private EasyTextField<String> etfFind = new EasyTextField<String>();
    private Button btnFind = new Button("", ICONS.find());

    public Usu_usuarioConsultGWT() {
        setHeaderVisible(false);
        this.setSize("500", "400");
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

        etfFind.setEmptyText("Nome ou login.");
        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(etfFind);
        getToolBarMaster().add(btnFind);
        btnFind.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                load();
            }
        });
        GridCellRenderer<Usu_usuarioTGWT> gridStatus = new GridCellRenderer<Usu_usuarioTGWT>() {

            public Object render(Usu_usuarioTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Usu_usuarioTGWT> store, Grid<Usu_usuarioTGWT> grid) {
                String txt = "<div style='color:cor'>valor</div>";
                String cor = "green";
                String valor = "Ativo";
                if ("I".equals(model.getUsu_tx_status())) {
                    cor = "red";
                    valor = "Inativo";
                } else if ("B".equalsIgnoreCase(model.getUsu_tx_status())) {
                    cor = "orange";
                    valor = "Bloqueado";
                }
                txt = txt.replaceAll("cor", cor);
                txt = txt.replaceAll("valor", valor);
                return txt;
            }
        };
        ColumnConfig column = null;

        column = new ColumnConfig();
        column.setId("usu_tx_nome");
        column.setHeader("Nome");
        column.setWidth(250);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("usu_tx_login");
        column.setHeader("Login");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("usu_tx_email");
        column.setHeader("Email");
        column.setWidth(300);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("usu_tx_status");
        column.setHeader("Status");
        column.setWidth(60);
        column.setRenderer(gridStatus);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);

        column = new ColumnConfig();
        column.setId("colPass");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getSenhaRender());
        configs.add(column);

        column = new ColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);

        column = new ColumnConfig();
        column.setId("colContador");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getZerarContadorTentativas());
        configs.add(column);

    }

    public void btnAtualizarAction(ButtonEvent be) {
        super.btnAtualizarAction(be);
    }

    public void btnNovoAction(ButtonEvent be) {
        Usu_usuarioInsertGWT usu_usuarioInsertGWT = new Usu_usuarioInsertGWT();
        usu_usuarioInsertGWT.setUsu_usuarioConsult(this);
        usu_usuarioInsertGWT.setModal(true);
        usu_usuarioInsertGWT.show();
    }

    private GridCellRenderer<Usu_usuarioTGWT> getSenhaRender() {
        return new GridCellRenderer<Usu_usuarioTGWT>() {

            public Object render(final Usu_usuarioTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Usu_usuarioTGWT> store, Grid<Usu_usuarioTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
                        AlterarSenhaGWT alterarSenhaGWT = new AlterarSenhaGWT();
                        alterarSenhaGWT.setUsu_usuarioConsult(Usu_usuarioConsultGWT.this);
                        alterarSenhaGWT.load(model);
                        alterarSenhaGWT.setVisible(true);
//                        MessageBox.confirm("Aviso", "Tem certeza que deseja gerar nova senha para o usuario?", new Listener<MessageBoxEvent>() {
//
//                            public void handleEvent(MessageBoxEvent be) {
//
//                                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
//
//                                    AsyncCallback<Void> callback = new AsyncCallback<Void>() {
//
//                                        @Override
//                                        public void onFailure(Throwable caught) {
//                                            MessageBox.info("IMPORTANTE", "Falha ao realizar a operação", null);
//                                        }
//
//                                        @Override
//                                        public void onSuccess(Void result) {
//                                            Info.display("Resultado", "sucesso!");
//                                        }
//                                    };
//                                    Usu_usuarioServiceAsync async = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
//                                    async.gerarNovaSenha(model, callback);
//                                }
//                            }
//                        });
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Alterar Senha.");
                b.setIcon(ICONS.chave());
                b.setId("btnPass");

                return b;
            }
        };
    }

    private GridCellRenderer<Usu_usuarioTGWT> getZerarContadorTentativas() {
        return new GridCellRenderer<Usu_usuarioTGWT>() {

            public Object render(final Usu_usuarioTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Usu_usuarioTGWT> store, Grid<Usu_usuarioTGWT> grid) {

                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {

                    public void handleEvent(ButtonEvent be) {
//                        AlterarSenhaGWT alterarSenhaGWT = new AlterarSenhaGWT();
//                        alterarSenhaGWT.setUsu_usuarioConsult(Usu_usuarioConsultGWT.this);
//                        alterarSenhaGWT.load(model);
//                        alterarSenhaGWT.setVisible(true);
                        MessageBox.confirm("Aviso", "Tem certeza que deseja limpar o contador de tentativas do usuario?", new Listener<MessageBoxEvent>() {

                            public void handleEvent(MessageBoxEvent be) {

                                if (new Dialog().yesText.equalsIgnoreCase(be.getButtonClicked().getText())) {
                                    //usu_usuarioDao.gerarNovaSenha(model);
                                    Res_recall_senhaTGWT resT = new Res_recall_senhaTGWT();
                                    resT.setUsu_nr_id(model.getUsu_nr_id());

                                    AsyncCallback<Void> callback = new AsyncCallback<Void>() {

                                        @Override
                                        public void onFailure(Throwable caught) {
                                            MessageBox.info("IMPORTANTE", "Falha ao execultar a operação", null);
                                        }

                                        @Override
                                        public void onSuccess(Void result) {
                                            Info.display("Resposta", "Sucesso!");
                                        }
                                    };

                                    Res_recall_senhaServiceAsync async = EasyAdmPortalRPCFactory.getRes_Recall_SenhaService();
                                    async.zeraContadorTentativas(resT, callback);

//                                    res_recall_senhaDAOGWT.zerarContadorTentativas(resT);
//                                    Timer timer = new Timer() {
//
//                                        public void run() {
//                                            String msg = res_recall_senhaDAOGWT.getMsg();
//                                            if (msg == null) {
//                                                schedule(500);
//                                            } else {
//                                                if (msg.toUpperCase().indexOf("FALHA") >= 0) {
//                                                    MessageBox.alert("ATENÇÃO", msg, null);
//                                                } else {
//                                                    Info.display("Resultado", msg);
//                                                }
//                                            }
//                                        }
//                                    };
//                                    timer.schedule(500);
                                }
                            }
                        });
                    }
                });

                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
                b.setToolTip("Limpar Contador de Tentativas Senha.");
                b.setIcon(ICONS.clear());
                b.setId("btnConTentativas");

                return b;
            }
        };
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
                        usu_usuarioUpdateDeleteGWT.setVisible(true);
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

        AsyncCallback<List<Usu_usuarioTGWT>> callback = new AsyncCallback<List<Usu_usuarioTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(List<Usu_usuarioTGWT> result) {
                List lista = getCpMaster().getItems();
                if (lista.size() > 0) {
                    getCpMaster().removeAll();
                }

                ColumnModel cm = new ColumnModel(configs);

                PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(result);
                PagingLoader<PagingLoadResult<Usu_usuarioTGWT>> loader = new BasePagingLoader<PagingLoadResult<Usu_usuarioTGWT>>(proxy);
                loader.setRemoteSort(true);

                ListStore<Usu_usuarioTGWT> store = new ListStore<Usu_usuarioTGWT>(loader);
                store.add(result);
                getToolBarPage().setPageSize(20);
                getToolBarPage().bind(loader);
                loader.load(0, 20);

                Grid<Usu_usuarioTGWT> grid = new Grid<Usu_usuarioTGWT>(store, cm);
                grid.setId("grid");
                grid.setLoadMask(true);

                grid.setStyleAttribute("borderTop", "none");
                grid.setBorders(true);
                grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

                getCpMaster().add(grid);
                getCpMaster().layout();
            }
        };
        Usu_usuarioServiceAsync async = EasyAdmPortalRPCFactory.getUsu_UsuarioService();
        if (etfFind.getValue() == null || etfFind.getValue().trim().length() == 0) {
            //MessageBox.alert("Falha", "É necessário infomar parte do nome ou do login.", null);
            //return;
            //usu_usuarioDao.consultarTodos();
            async.getAll(callback);
        } else {
            async.consultByNomeLogin(etfFind.getValue(), callback);
            //usu_usuarioDao.consultByNomeLogin(etfFind.getValue());
        }

        
//
//        Timer timer = new Timer() {
//
//            public void run() {
//                ListStore<Usu_usuarioTGWT> list = usu_usuarioDao.getList();
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
//                    PagingLoader<PagingLoadResult<Usu_usuarioTGWT>> loader = new BasePagingLoader<PagingLoadResult<Usu_usuarioTGWT>>(proxy);
//                    loader.setRemoteSort(true);
//
//                    ListStore<Usu_usuarioTGWT> store = new ListStore<Usu_usuarioTGWT>(loader);
//                    store.add(list.getModels());
//                    getToolBarPage().setPageSize(20);
//                    getToolBarPage().bind(loader);
//                    loader.load(0, 20);
//
//                    Grid<Usu_usuarioTGWT> grid = new Grid<Usu_usuarioTGWT>(store, cm);
//                    grid.setId("grid");
//                    grid.setLoadMask(true);
//
//                    grid.setStyleAttribute("borderTop", "none");
//                    grid.setBorders(true);
//                    grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//
//                    getCpMaster().add(grid);
//                    getCpMaster().layout();
//                }
//            }
//        };
//        timer.schedule(500);
    }
}
