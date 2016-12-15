/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author georgejunior
 */
public interface EasyLoggerServiceAsync {

    public void debug(String clazz, String s, AsyncCallback<Void> asyncCallback);

    public void info(String clazz, String s, AsyncCallback<Void> asyncCallback);

    public void error(String clazz, String s, AsyncCallback<Void> asyncCallback);

    public void warning(String clazz, String s, AsyncCallback<Void> asyncCallback);
}
