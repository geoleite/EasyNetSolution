/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;


import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;

/**
 *
 * @author geoleite
 */
public class MenuEvento implements Command {

    private String actionDisplay;
    public static ContentPanel execucao = Portal2GWT.panelExecucao;
    public static TabPanel tab = Portal2GWT.tab;
    private TabItem ti;

    /**
     * Inicializa a execucao do menu
     */
    public void inicialize() {
        String strOpcao = getActionDisplay();
        TabItem temp = findTabItem(strOpcao);
        if (temp != null) {
            tab.remove(temp);
        }

        setTi(new TabItem(" "));
        getTi().setClosable(true);
        getTi().setId(strOpcao);
        getTi().setText(strOpcao);

    }

    /**
     * Localiza um tabitem
     * @param text
     * @return
     */
    private TabItem findTabItem(String text) {
        return tab.findItem(text, true);
    }

    public void execute() {
        MessageBox.info("Operacao", "Esta operacao nao foi reconhecida!", null);
            
    }

/*
    public void execute() {
        execucao = Portal2GWT.panelExecucao;
        execucao.setFrame(true);
        execucao.setHeaderVisible(true);
        execucao.setHeading("Execucao");
        execucao.setLayout(new RowLayout(Orientation.HORIZONTAL));
        execucao.setSize("100%", "100%");
        execucao.removeAll();
        if (actionDisplay.indexOf("sair") >= 0) {
            try {
                execucao.add(new CadastroClienteGWT());
            } catch (Exception e) {
                Window.alert(e.getMessage());
            }
        } else {
            execucao.add(new Button("teste " + actionDisplay));
        }
    }
 */

    /**
     * Executa algo dinamicamente utilizando a funcao eval do javascript
     * @param json
     * @return
     */
    public native static String getJson(String json)/*-{
    return eval('1+1');
    }-*/;


    /**
     * @return the actionDisplay
     */
    public String getActionDisplay() {
        return actionDisplay;
    }

    /**
     * @param actionDisplay the actionDisplay to set
     */
    public void setActionDisplay(String actionDisplay) {
        this.actionDisplay = actionDisplay;
    }

    /**
     * @return the ti
     */
    public TabItem getTi() {
        return ti;
    }

    /**
     * @param ti the ti to set
     */
    public void setTi(TabItem ti) {
        this.ti = ti;
    }
}
