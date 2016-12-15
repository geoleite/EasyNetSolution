/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.desktopModel;

import br.com.easyportal.gwt.client.IMenuHandler;
import com.extjs.gxt.ui.client.widget.Window;
import java.util.HashMap;

/**
 *
 * @author geoleite
 */
public abstract class AMenuDesktop implements IMenuHandler {
    protected static final HashMap<String, Window> janelas = new HashMap<String, Window>();
    public abstract Window getWindowAcao(String acao);
    public void getWindowAcao(String acao, String url) {

    }

}
