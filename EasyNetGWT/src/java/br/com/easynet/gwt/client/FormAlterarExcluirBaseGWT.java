/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.gwt.client;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
/**
 *
 * @author geoleite
 */
public class FormAlterarExcluirBaseGWT extends FormBaseGWT implements Listener<ButtonEvent>{

    private ToolBar toolBarMaster = new ToolBar();
    private Button btnUpdate = new Button(" Atualizar ");
    private Button btnDel = new Button(" Excluir ");
    private Button btnFechar = new Button(" Fechar ");

    public FormAlterarExcluirBaseGWT() {
        btnDel.setIconStyle("icon-delete");
        btnUpdate.setIconStyle("icon-save");
        btnFechar.setIconStyle("icon-close");

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

//       getCpMaster().setHeaderVisible(false);

        getToolBarMaster().add(btnUpdate);
        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(btnDel);
        getToolBarMaster().add(new SeparatorToolItem());
        getToolBarMaster().add(btnFechar);
        getToolBarMaster().add(new SeparatorToolItem());
        //getToolBarMaster().setButtonAlign(HorizontalAlignment.CENTER);
        getCpBotton().add(getToolBarMaster());
        


        btnUpdate.addListener(Events.OnClick, this);

        btnDel.addListener(Events.OnClick, this);

        btnFechar.addListener(Events.OnClick, this);
        this.remove(getCpTop());
        
        btnDel.setIcon(ICONS.del());
        btnFechar.setIcon(ICONS.sair());
        btnUpdate.setIcon(ICONS.update());

        this.layout();
    }

    public void btnUpdateAction(ButtonEvent ce) {
    }

    public void btnDeltAction(ButtonEvent ce) {
    }

    public void btnFecharAction(ButtonEvent ce) {
        //hide();
        setVisible(false);
    }

    public void handleEvent(ButtonEvent be) {
        if (be.getButton() == getBtnUpdate()) {
            btnUpdateAction(be);
        } else if (be.getButton() == getBtnDel()) {
            btnDeltAction(be);
        } else if (be.getButton() == getBtnFechar()) {
            btnFecharAction(be);
        }
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
    public Button getBtnDel() {
        return btnDel;
    }

    /**
     * @param btnDel the btnDel to set
     */
    public void setBtnDel(Button btnDel) {
        this.btnDel = btnDel;
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
