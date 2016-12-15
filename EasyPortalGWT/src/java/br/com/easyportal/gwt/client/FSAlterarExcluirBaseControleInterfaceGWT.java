/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client;

import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

/**
 *
 * @author geoleite
 */
public class FSAlterarExcluirBaseControleInterfaceGWT extends FSBaseControleInterfaceGWT  implements Listener<ButtonEvent> {

    private ToolBar toolBarMaster = new ToolBar();
    private Button btnUpdate = new Button(" Atualizar ");
    private Button btnDel = new Button(" Excluir ");
    private Button btnFechar = new Button(" Fechar ");

    public FSAlterarExcluirBaseControleInterfaceGWT() {
        btnDel.setIconStyle("icon-delete");
        btnUpdate.setIconStyle("icon-save");
        btnFechar.setIconStyle("icon-close");
        btnDel.setId("btnExcluir");
        btnUpdate.setId("btnAlterar");
        btnFechar.setId("btnFechar");
        toolBarMaster.setId("toolBarMaster");

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
        if (be.getButton() == btnUpdate) {
            btnUpdateAction(be);
        } else if (be.getButton() == btnDel) {
            btnDeltAction(be);
        } else if (be.getButton() == btnFechar) {
            btnFecharAction(be);
        }
    }
}
