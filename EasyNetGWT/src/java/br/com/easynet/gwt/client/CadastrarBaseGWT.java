/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.gwt.client;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.ButtonEvent;
//import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
/**
 *
 * @author marcos
 */
 
public class CadastrarBaseGWT extends BaseGWT implements Listener<ButtonEvent>{
    
    private ToolBar toolBarMaster = new ToolBar();
    private Button btnInsert = new Button(EASY_LABELS.btnInsert());
    private Button btnLimpar = new Button(EASY_LABELS.btnClear());
    private Button btnFechar = new Button(EASY_LABELS.btnClose());

    public CadastrarBaseGWT(){
       btnInsert.setIconStyle("icon-add");
       btnLimpar.setIconStyle("icon-page-white");
       btnFechar.setIconStyle("icon-close");
       

       //desabilitar o ContentPanel

       getDataNORTH().setHidden(true);
       getCpTop().setVisible(false);

       getDataEAST().setHidden(true);
       getCpLeft().setVisible(false);

       getDataWEST().setHidden(true);
       getCpRight().setVisible(false);
       
       getDataSOUTH().setHidden(false);
       getDataSOUTH().setSize(27);
       getCpBotton().setHeaderVisible(false);
       getCpMaster().setHeaderVisible(false);
       
       setModal(true);

       getToolBarMaster().add(btnInsert);
       getToolBarMaster().add(new SeparatorToolItem());
       getToolBarMaster().add(btnLimpar);
       getToolBarMaster().add(new SeparatorToolItem());
       getToolBarMaster().add(btnFechar);
       getToolBarMaster().add(new SeparatorToolItem());
       //getToolBarMaster().setButtonAlign(HorizontalAlignment.CENTER);
       getCpBotton().add(getToolBarMaster());

       this.layout();
       
       btnInsert.addListener(Events.OnClick, this);
       btnLimpar.addListener(Events.OnClick, this);
       btnFechar.addListener(Events.OnClick, this);

       btnInsert.setIcon(ICONS.aplicar());
       btnFechar.setIcon(ICONS.sair());
       btnLimpar.setIcon(ICONS.limpar());

    }

    public void btnInsertAction(ButtonEvent ce){
    }
    public void btnLimpartAction(ButtonEvent ce){
    }
    public void btnFecharAction(ButtonEvent ce){
        setVisible(false);
    }

    public void handleEvent(ButtonEvent be) {
        if (be.getSource() == getBtnInsert()) {
            btnInsertAction(be);
        } else if (be.getSource() == getBtnLimpar()) {
            btnLimpartAction(be);
        } else if (be.getSource() == getBtnFechar()) {
           btnFecharAction(be);
        }
    }

    /**
     * @return the toolBarMaster
     */
    public ToolBar getToolBarMaster() {
        return toolBarMaster;
    }

    /**
     * @param toolBarMaster the toolBarMaster to set
     */
    public void setToolBarMaster(ToolBar toolBarMaster) {
        this.toolBarMaster = toolBarMaster;
    }

    /**
     * @return the btnInsert
     */
    public Button getBtnInsert() {
        return btnInsert;
    }

    /**
     * @param btnInsert the btnInsert to set
     */
    public void setBtnInsert(Button btnInsert) {
        this.btnInsert = btnInsert;
    }

    /**
     * @return the btnLimpar
     */
    public Button getBtnLimpar() {
        return btnLimpar;
    }

    /**
     * @param btnLimpar the btnLimpar to set
     */
    public void setBtnLimpar(Button btnLimpar) {
        this.btnLimpar = btnLimpar;
    }

    /**
     * @return the btnFechar
     */
    public Button getBtnFechar() {
        return btnFechar;
    }

    /**
     * @param btnFechar the btnFechar to set
     */
    public void setBtnFechar(Button btnFechar) {
        this.btnFechar = btnFechar;
    }
}