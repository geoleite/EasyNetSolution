/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//br.com.easyportal.gwt.client.CadastrarBaseControleInterfaceGWT
package br.com.easyportal.gwt.client;

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
 
public class CadastrarBaseControleInterfaceGWT extends BaseControleInterfaceGWT implements Listener<ButtonEvent>{
    
    private ToolBar toolBarMaster = new ToolBar();
    Button btnInsert = new Button(" Inserir ");
    Button btnLimpar = new Button(" Limpar ");
    Button btnFechar = new Button(" Fechar ");

    public CadastrarBaseControleInterfaceGWT(){
       btnInsert.setIconStyle("icon-add");
       btnLimpar.setIconStyle("icon-page-white");
       btnFechar.setIconStyle("icon-close");
       toolBarMaster.setId("toolBar");
       btnInsert.setId("btnInserir");
       btnLimpar.setId("btnLimpar");
       btnFechar.setId("btnFechar");
       
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
        if (be.getSource() == btnInsert) {
            btnInsertAction(be);
        } else if (be.getSource() == btnLimpar) {
            btnLimpartAction(be);
        } else if (be.getSource() == btnFechar) {
           btnFecharAction(be);
        }
    }
}

