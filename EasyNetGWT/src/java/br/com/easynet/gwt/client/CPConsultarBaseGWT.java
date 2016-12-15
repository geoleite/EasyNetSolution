/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.SplitButton;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class CPConsultarBaseGWT extends CPBaseGWT implements IConsultarGWT {

    public static final String FORMAT_CSV = "CSV";
    public static final String FORMAT_TXT = "TXT";
    public static final String FORMAT_PDF = "PDF";
    private ContentPanel cpFilter = new ContentPanel();
    private List<ColumnConfig> configsSuper = new ArrayList<ColumnConfig>();
    private ColumnModel cmSuper;
    private ToolBar toolBar = new ToolBar();
    private Button btnNovoSuper = new Button("Novo");
    private PagingToolBar toolBarPage = new PagingToolBar(3);
    private Button btnRefersh = new Button("Atualizar", ICONS.refresh());
    private SplitButton btnExportar = new SplitButton("Exportar");

    public CPConsultarBaseGWT() {

        //btnNovoSuper.setIconStyle("icon-add");
        btnNovoSuper.setIcon(ICONS.novo());
        btnNovoSuper.setTitle("Cadastrar");
        btnNovoSuper.setIconAlign(IconAlign.LEFT);

        btnRefersh.setTitle("Atualizar consulta");
        btnRefersh.setIconAlign(IconAlign.LEFT);

        getDataNORTH().setSplit(false);
        getDataSOUTH().setHidden(false);
        getCpBotton().setVisible(false);
        getCpBotton().setHideCollapseTool(false);

        getToolBarMaster().add(btnNovoSuper);
        getToolBarMaster().add(btnRefersh);
        getToolBarMaster().add(btnExportar);
        getToolBarMaster().setHeight(27);

        btnExportar.setToolTip("Selecione o formato para exportação.");
        btnExportar.setMenu(createMenuExportacao());
        btnExportar.setIcon(ICONS.exportation());
        btnExportar.setVisible(false);

        //remove(getCpTop());
        //remove(getCpLeft());
        //remove(getCpRight());
        getCpTop().setVisible(false);
        getCpLeft().setVisible(false);
        getCpRight().setVisible(false);
        getCpMaster().setTopComponent(getToolBarMaster());
        //getCpTop().setHeight(20);
        cpFilter.setHeaderVisible(false);
        cpFilter.setBodyBorder(false);
        getCpTop().add(cpFilter);

        getCpBotton().setVisible(false);
        getCpTop().setHeaderVisible(false);
        getDataSOUTH().setHidden(true);

        // inserir o grid no CpMaster
        getCpMaster().setBottomComponent(toolBarPage);
        btnNovoSuper.addListener(Events.OnClick, new Listener<ButtonEvent>() {
            public void handleEvent(ButtonEvent be) {
                btnNovoAction(be);
            }
        });
        btnRefersh.addListener(Events.OnClick, new Listener<ButtonEvent>() {
            public void handleEvent(ButtonEvent be) {
                btnAtualizarAction(be);
            }
        });

        this.remove(getCpBotton());
        this.layout();
    }

    public void btnAtualizarAction(ButtonEvent be) {
        load();
    }

    /**
     * Opcão para exportar dados
     *
     * @param formato
     */
    public void export(String formato) {

    }

    /**
     * Monta o menu de opções para exportacao
     *
     * @return
     */
    public Menu createMenuExportacao() {
        Menu menu = new Menu();
        MenuItem mi = new MenuItem(FORMAT_CSV);
        mi.setIcon(ICONS.csv());
        mi.addSelectionListener(new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                export(FORMAT_CSV);
            }
        });
        menu.add(mi);
        mi = new MenuItem(FORMAT_TXT);
        mi.setIcon(ICONS.txt());
        mi.addSelectionListener(new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                export(FORMAT_TXT);
            }
        });
        menu.add(mi);
        mi = new MenuItem(FORMAT_PDF);
        mi.setIcon(ICONS.pdf2());
        mi.addSelectionListener(new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                export(FORMAT_PDF);
            }
        });
        menu.add(mi);
        return menu;
    }

    /**
     * Preenche o grid
     * @param result
     * @param configs
     * @return
     * @throws Exception 
     */
    public Grid<BaseModel> fillGrid(List<BaseModel> result, List<ColumnConfig> configs) throws Exception {

        ColumnModel cm = new ColumnModel(configs);
        ListStore<BaseModel> list = new ListStore<BaseModel>();
        list.add(result);
        
        PagingModelMemoryProxy proxy = new PagingModelMemoryProxy(list.getModels());
        PagingLoader<PagingLoadResult<BaseModel>> loader = new BasePagingLoader<PagingLoadResult<BaseModel>>(proxy);
        loader.setRemoteSort(true);

        ListStore<BaseModel> store = new ListStore<BaseModel>(loader);
        store.add(list.getModels());

        getToolBarPage().setPageSize(20);
        getToolBarPage().bind(loader);
        loader.load(0, 20);

        Grid<BaseModel> grid = new Grid<BaseModel>(store, cm);
        grid.setColumnReordering(true);
        grid.setId("grid");
        grid.setLoadMask(true);

        grid.setStyleAttribute("borderTop", "none");
        grid.setBorders(true);
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        getCpMaster().add(grid);
        getCpMaster().layout();
        return grid;
    }

    /**
     * Método que realiza a consulta
     */
    public void load() {

    }

    /**
     * Define a implementação do método Novo
     */
    public void btnNovoAction(ButtonEvent be) {

    }

    public List<ColumnConfig> getConfigsSuper() {
        return configsSuper;
    }

    public void setConfigsSuper(List<ColumnConfig> configsSuper) {
        this.configsSuper = configsSuper;
    }

    public ColumnModel getCmSuper() {
        return cmSuper;
    }

    public void setCmSuper(ColumnModel cmSuper) {
        this.cmSuper = cmSuper;
    }

    public PagingToolBar getToolBarPage() {
        return toolBarPage;
    }

    public void setToolBarPage(PagingToolBar toolBarPage) {
        this.toolBarPage = toolBarPage;
    }

    /**
     * @return the btnNovoSuper
     */
    public Button getBtnNovoSuper() {
        return btnNovoSuper;
    }

    /**
     * @param btnNovoSuper the btnNovoSuper to set
     */
    public void setBtnNovoSuper(Button btnNovoSuper) {
        this.btnNovoSuper = btnNovoSuper;
    }

    /**
     * @return the toolBar
     */
    public ToolBar getToolBar() {
        return toolBar;
    }

    /**
     * @param toolBar the toolBar to set
     */
    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public void setBtnExportar(SplitButton btnExportar) {
        this.btnExportar = btnExportar;
    }

    public SplitButton getBtnExportar() {
        return btnExportar;
    }

    /**
     * @return the btnRefersh
     */
    public Button getBtnRefersh() {
        return btnRefersh;
    }

    /**
     * @param btnRefersh the btnRefersh to set
     */
    public void setBtnRefersh(Button btnRefersh) {
        this.btnRefersh = btnRefersh;
    }

}
