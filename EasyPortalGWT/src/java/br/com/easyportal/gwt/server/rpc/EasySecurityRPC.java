/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.server.rpc;

import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.easynet.gwt.server.rpc.RPCBase;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 *
 * @author georgejunior
 */
public class EasySecurityRPC extends RPCBase {


    /**
     * @return the usuarioLogado
     */
    public Usu_usuarioT getUsuarioLogado() {
        return (Usu_usuarioT) getThreadLocalRequest().getSession().getAttribute(EasySecurityJB.USER_PRINCIPAL);
    }

    /**
     * @param usuarioLogado the usuarioLogado to set
     */
    public void setUsuarioLogado(Usu_usuarioT usuarioLogado) {
        getThreadLocalRequest().getSession().setAttribute(EasySecurityJB.USER_PRINCIPAL, usuarioLogado);
    }

}
