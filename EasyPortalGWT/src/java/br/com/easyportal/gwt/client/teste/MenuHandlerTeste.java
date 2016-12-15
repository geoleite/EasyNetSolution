/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.teste;

import br.com.easyportal.gwt.client.webModel.AMenuHandler;
import br.com.easyportal.gwt.client.IMenuHandler;
import com.extjs.gxt.ui.client.widget.Info;

/**
 *
 * @author geoleite
 */
public class MenuHandlerTeste extends AMenuHandler {

    public MenuHandlerTeste() {
        setSystem("I9IM");
    }
    @Override
    public void actionEventMenu(Object me, String acao) {
        
        Info.display("Acao:" + acao, me + "");
    }

}
