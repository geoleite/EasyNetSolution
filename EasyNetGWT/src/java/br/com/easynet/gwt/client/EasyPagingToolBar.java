/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.gwt.client;

import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

/**
 *
 * @author geoleite
 */
public class EasyPagingToolBar extends PagingToolBar {
    private IConsultarGWT consultarBase;
    public EasyPagingToolBar(int pageSize) {
        super(pageSize);
    }

  public void refresh() {
    super.refresh();
    if (getConsultarBase() != null) {
        if (getConsultarBase() instanceof CPConsultarBaseGWT)
            getConsultarBase().btnAtualizarAction(null);
    }
  }

    /**
     * @return the consultarBase
     */
    public IConsultarGWT getConsultarBase() {
        return consultarBase;
    }

    /**
     * @param consultarBase the consultarBase to set
     */
    public void setConsultarBase(IConsultarGWT consultarBase) {
        this.consultarBase = consultarBase;
    }

}
