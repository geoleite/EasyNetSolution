/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Pu_per_usuTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author topfontes
 */
public interface Pu_per_usuTServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void insert(Pu_per_usuTGWT pu_per_usuT, AsyncCallback<Void> asyncCallback);

    public void delete(Pu_per_usuTGWT pu_per_usuT, AsyncCallback<Void> asyncCallback);
}
