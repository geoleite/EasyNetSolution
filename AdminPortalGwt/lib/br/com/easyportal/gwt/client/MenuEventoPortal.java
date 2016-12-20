/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author geoleite 
 */
public class MenuEventoPortal extends MenuEvento {

    public void execute() {
        super.inicialize();
        String strOpcao = getActionDisplay();

        TabItem ti = getTi();
        if ("Limpar Execucoes".equals(strOpcao)) {
            tab.removeAll();
            ti = null;
        } else if ("Alterar Senha".equals(strOpcao)) {
            ti.setLayout(new CenterLayout());
            ti.add(new AlterarSenhaGWT());
        } else if ("Sair".equals(strOpcao)) {
           SairGWT sair = new SairGWT();
        //    ti.add(new SairGWT());
            ti = null;
        } else {
            // Operacao nao identificada            
            super.execute();
            ti = null;
        }

        // Se o tabitem diferente de null insere no tab
        if (ti != null) {
            tab.add(ti);
            tab.setSelection(ti);
        }
        


    }


}
