/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.sis_sistema;

import br.com.i9.portal.client.Constantes;
import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.component.EasyTextField;

import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.i9.portal.client.portal.portal.dao.*;
import br.com.easynet.gwt.client.ConsultarBaseGWT;
import br.com.easynet.gwt.client.debug.DebugMessage;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Sis_sistemaService;
import br.com.i9.portal.client.rpc.Sis_sistemaServiceAsync;
import br.com.i9.portal.server.rpc.Sis_sistemaServiceImpl;

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
import com.google.gwt.core.client.GWT;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import java.util.Date;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geoleite
 */
public class Sis_sistemaConsultGWT extends ConsultarBaseGWT {
    
    private ContentPanel cp = new ContentPanel();
    private List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
    private Sis_sistemaDAOGWT sis_sistemaDao = new Sis_sistemaDAOGWT();
    
    public Sis_sistemaConsultGWT() {
        setHeaderVisible(false);
        this.setSize("500", "400");
        final NumberFormat currency = NumberFormat.getCurrencyFormat();
        final NumberFormat number = NumberFormat.getFormat("0.00");
        final NumberCellRenderer<Grid<Sis_sistemaTGWT>> numberRenderer = new NumberCellRenderer<Grid<Sis_sistemaTGWT>>(currency);
        GridCellRenderer<Sis_sistemaTGWT> gridStatus = new GridCellRenderer<Sis_sistemaTGWT>() {
            
            public Object render(Sis_sistemaTGWT model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<Sis_sistemaTGWT> store, Grid<Sis_sistemaTGWT> grid) {
                String txt = "<div style='color:cor'>valor</div>";
                String cor = "green";
                String valor = "Ativo";
                if ("I".equals(model.getSis_tx_status())) {
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
        column.setId("sis_tx_nome");
        column.setHeader("Nome");
        column.setWidth(200);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);
        
        column = new ColumnConfig();
        column.setId("sis_tx_descricao");
        column.setHeader("Descricao");
        column.setWidth(350);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);
        
        column = new ColumnConfig();
        column.setId("sis_tx_status");
        column.setHeader("Status");
        column.setWidth(100);
        column.setRenderer(gridStatus);
        column.setAlignment(HorizontalAlignment.LEFT);
        configs.add(column);
        
        column = new ColumnConfig();
        column.setId("colEditar");
        column.setWidth(30);
        column.setAlignment(HorizontalAlignment.CENTER);
        column.setRenderer(getEditarRender());
        configs.add(column);
        
        DebugMessage.message(this.getClass().getName(), "Abrindo tela de sistema");
        load();
    }
    
    public void btnAtualizarAction(ButtonEvent be) {
        super.btnAtualizarAction(be);
    }
    
    public void btnNovoAction(ButtonEvent be) {
        Sis_sistemaInsertGWT sis_sistemaInsertGWT = new Sis_sistemaInsertGWT();
        sis_sistemaInsertGWT.setSis_sistemaConsult(this);
        sis_sistemaInsertGWT.setModal(true);
        sis_sistemaInsertGWT.show();
        
    }
    
    private GridCellRenderer<Sis_sistemaTGWT> getEditarRender() {
        return new GridCellRenderer<Sis_sistemaTGWT>() {
            
            public Object render(final Sis_sistemaTGWT model, String property, ColumnData config, final int rowIndex,
                    final int colIndex, ListStore<Sis_sistemaTGWT> store, Grid<Sis_sistemaTGWT> grid) {
                
                Button b = new Button();
                b.addListener(Events.OnClick, new Listener<ButtonEvent>() {
                    
                    public void handleEvent(ButtonEvent be) {
                        Sis_sistemaUpdateDeleteGWT sis_sistemaUpdateDeleteGWT = new Sis_sistemaUpdateDeleteGWT();
                        sis_sistemaUpdateDeleteGWT.setSis_sistemaConsult(Sis_sistemaConsultGWT.this);
                        sis_sistemaUpdateDeleteGWT.load(model);
                        sis_sistemaUpdateDeleteGWT.setVisible(true);
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
        AsyncCallback<List<Sis_sistemaTGWT>> callback = new AsyncCallback<List<Sis_sistemaTGWT>>() {
            
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Erro: " + caught.getCause().getMessage());
            }
            
            @Override
            public void onSuccess(List<Sis_sistemaTGWT> result) {
                List lista = getCpMaster().getItems();
                if (lista.size() > 0) {
                    getCpMaster().removeAll();
                }
                ColumnModel cm = new ColumnModel(configs);
                
                PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(result);
                PagingLoader<PagingLoadResult<Sis_sistemaTGWT>> loader = new BasePagingLoader<PagingLoadResult<Sis_sistemaTGWT>>(proxy);
                loader.setRemoteSort(true);
                
                ListStore<Sis_sistemaTGWT> store = new ListStore<Sis_sistemaTGWT>(loader);
                store.add(result);
                
                getToolBarPage().setPageSize(20);
                getToolBarPage().bind(loader);
                loader.load(0, 20);
                
                Grid<Sis_sistemaTGWT> grid = new Grid<Sis_sistemaTGWT>(store, cm);
                grid.setId("grid");
                grid.setLoadMask(true);
                
                grid.setStyleAttribute("borderTop", "none");
                grid.setBorders(true);
                grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                
                getCpMaster().add(grid);
                getCpMaster().layout();
                
            }
        };
        Sis_sistemaServiceAsync service = EasyAdmPortalRPCFactory.getSis_sistemaService();
        service.consult(callback);
        
//        sis_sistemaDao.consultarTodos();
//        DebugMessage.message(this.getClass().getName(), "Consultando todos os sistemas");
//        Timer timer = new Timer() {
//            
//            public void run() {
//                ListStore<Sis_sistemaTGWT> list = sis_sistemaDao.getList();
//                DebugMessage.message(this.getClass().getName(), "Lista " + list);
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
//                    PagingLoader<PagingLoadResult<Sis_sistemaTGWT>> loader = new BasePagingLoader<PagingLoadResult<Sis_sistemaTGWT>>(proxy);
//                    loader.setRemoteSort(true);
//                    
//                    ListStore<Sis_sistemaTGWT> store = new ListStore<Sis_sistemaTGWT>(loader);
//                    store.add(list.getModels());
//                    
//                    getToolBarPage().setPageSize(20);
//                    getToolBarPage().bind(loader);
//                    loader.load(0, 20);
//                    
//                    Grid<Sis_sistemaTGWT> grid = new Grid<Sis_sistemaTGWT>(store, cm);
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
