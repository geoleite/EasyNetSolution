/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author georgejunior
 */
@RemoteServiceRelativePath("rpc/easyloggerservice")
public interface EasyLoggerService extends RemoteService {

    public void debug(String clazz, String s);
    public void info(String clazz, String s);
    public void error(String clazz, String s);
    public void warning(String clazz, String s);
}
