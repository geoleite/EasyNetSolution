/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client;

import com.extjs.gxt.ui.client.Style.IconAlign;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import java.util.ArrayList;
import java.util.List;
       
/**
 * 
 * @author marcos 
 */
public class ConsultarBaseControleInterfaceGWT extends BaseControleInterfaceGWT {

    private ContentPanel cpFilter = new ContentPanel();
    private List<ColumnConfig> configsSuper = new ArrayList<ColumnConfig>();
    private ColumnModel cmSuper;
    private ToolBar toolBar = new ToolBar();
    private Button btnNovoSuper = new Button("Novo");
    private PagingToolBar toolBarPage = new PagingToolBar(3);

    public ConsultarBaseControleInterfaceGWT(){
        
        btnNovoSuper.setId("btnNovo");
        cpFilter.setId("filter");
        toolBar.setId("toolBar");
        toolBarPage.setId("toolBarPage");
        btnNovoSuper.setIcon(ICONS.novo());
        btnNovoSuper.setTitle("Cadastrar");
        btnNovoSuper.setIconAlign(IconAlign.LEFT );
        getDataNORTH().setSplit(false);
        getDataSOUTH().setHidden(false);
        getCpBotton().setVisible(false);
        getCpBotton().setHideCollapseTool(false);

        getToolBarMaster().add(btnNovoSuper);
        getToolBarMaster().setHeight(27);
        remove(getCpTop());
        remove(getCpLeft());
        remove(getCpRight());
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
        this.remove(getCpBotton());
        this.layout();

    }
    
    /**
     * Método que realiza a consulta
     */
    public void load() {

    }
    /**
     * Define a implementação do método Novo
     */
    public void btnNovoAction(ButtonEvent be){
        
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

}
