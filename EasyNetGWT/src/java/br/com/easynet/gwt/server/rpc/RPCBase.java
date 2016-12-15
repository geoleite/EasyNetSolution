/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.server.rpc;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.log4j.Logger;
/**
 *
 * @author georgejunior
 */
public class RPCBase extends RemoteServiceServlet {
    protected Logger easyLogger = Logger.getLogger(this.getClass());
}
