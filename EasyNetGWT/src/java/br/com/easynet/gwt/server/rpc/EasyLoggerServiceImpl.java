/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.server.rpc;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import br.com.easynet.gwt.client.rpc.EasyLoggerService;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author georgejunior
 */
@WebServlet(name = "EasyLoggerService", urlPatterns = {"/br.com.easynet.gwt.EasyNetGWT/rpc/easyloggerservice"})
public class EasyLoggerServiceImpl extends RPCBase implements EasyLoggerService {
 
    @Override
    public void debug(String clazz, String s) {
        easyLogger.debug("class GWT: "+ clazz +":" + s);
    }

    @Override
    public void error(String clazz, String s) {
        easyLogger.error("class GWT: "+ clazz +":" + s);
    }

    @Override
    public void warning(String clazz, String s) {
        easyLogger.warn("class GWT: "+ clazz +":" + s);
    }

    @Override
    public void info(String clazz, String s) {
        easyLogger.info("class GWT: "+ clazz +":" + s);
    }
}
