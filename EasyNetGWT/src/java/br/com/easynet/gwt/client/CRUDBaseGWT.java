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
 * Classe Para integrar o cadastro e a tela de alteração
 * @author George Leite
 */
public class CRUDBaseGWT extends BaseGWT implements Listener<ButtonEvent> {

    private ToolBar toolBarMaster = new ToolBar();
    private Button btnInsert = new Button(EASY_LABELS.btnInsert(), ICONS.applic());
    private Button btnClear = new Button(EASY_LABELS.btnClear(), ICONS.clear());
    private Button btnClose = new Button(EASY_LABELS.btnClose(), ICONS.close());
    private Button btnUpdate = new Button(EASY_LABELS.btnUpdate(), ICONS.editUpdate());
    private Button btnDelete = new Button(EASY_LABELS.btnDelete(), ICONS.remove());

    public CRUDBaseGWT() {
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

        getToolBarMaster().add(getBtnInsert());
        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(btnInsert);
        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(btnUpdate);
        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(btnClear);
        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(btnDelete);
        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(btnClose);
        getToolBarMaster().add(new SeparatorToolItem());
        //getToolBarMaster().setButtonAlign(HorizontalAlignment.CENTER);
        getCpBotton().add(getToolBarMaster());

        this.layout();

        btnInsert.addListener(Events.OnClick, this);
        btnClear.addListener(Events.OnClick, this);
        btnClose.addListener(Events.OnClick, this);
        btnUpdate.addListener(Events.OnClick, this);
        btnDelete.addListener(Events.OnClick, this);

    }

    public void handleEvent(ButtonEvent be) {
        if (be.getSource() == btnInsert) {
            btnInsertAction(be);
        } else if (be.getSource() == btnUpdate) {
            btnUpdateAction(be);
        } else if (be.getSource() == btnDelete) {
            btnDeleteAction(be);
        } else if (be.getSource() == btnClear) {
            btnClearAction(be);
        } else if (be.getSource() == btnClose) {
           btnCloseAction(be);
        }
    }

    public boolean valide() {
        return true;
    }

    public void btnInsertAction(ButtonEvent ce){
    }
    public void btnUpdateAction(ButtonEvent ce){
    }
    public void btnDeleteAction(ButtonEvent ce){
    }
    public void btnClearAction(ButtonEvent ce){
    }
    public void btnCloseAction(ButtonEvent ce){
        setVisible(false);
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
     * @return the btnClear
     */
    public Button getBtnClear() {
        return btnClear;
    }

    /**
     * @param btnClear the btnClear to set
     */
    public void setBtnClear(Button btnClear) {
        this.btnClear = btnClear;
    }

    /**
     * @return the btnClose
     */
    public Button getBtnClose() {
        return btnClose;
    }

    /**
     * @param btnClose the btnClose to set
     */
    public void setBtnClose(Button btnClose) {
        this.btnClose = btnClose;
    }

    /**
     * @return the btnUpdate
     */
    public Button getBtnUpdate() {
        return btnUpdate;
    }

    /**
     * @param btnUpdate the btnUpdate to set
     */
    public void setBtnUpdate(Button btnUpdate) {
        this.btnUpdate = btnUpdate;
    }

    /**
     * @return the btnDel
     */
    public Button getBtnDelete() {
        return btnDelete;
    }

    /**
     * @param btnDel the btnDel to set
     */
    public void setBtnDel(Button btnDel) {
        this.btnDelete = btnDel;
    }
}
