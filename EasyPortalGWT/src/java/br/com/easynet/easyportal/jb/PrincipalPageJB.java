/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.easyportal.jb;

import br.com.easynet.jb.BeanBase;

/**
 *
 * @author geoleite
 */
public class PrincipalPageJB extends EasySecurityJB implements INotSecurity {
    private boolean gwt=false;
    public void sair() throws Exception {
        getSession().invalidate();
        if (!gwt)
            getResponse().sendRedirect(PAGE_INDEX);
    }

    /**
     * @return the gwt
     */
    public boolean getGwt() {
        return gwt;
    }

    /**
     * @param gwt the gwt to set
     */
    public void setGwt(boolean gwt) {
        this.gwt = gwt;
    }
}
