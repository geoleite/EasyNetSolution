/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.teste;

import br.com.easyportal.gwt.client.desktopModel.AMenuDesktop;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;

/**
 *
 * @author geoleite
 */
public class MenuHandlerTesteDesktop extends AMenuDesktop{

    @Override
    public Window getWindowAcao(String acao) {        
        Window win = new Window();
        win.setHeading(acao);
        win.setSize(100,100);
        return win;
    }

}
