/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author topfontes
 */
public interface Per_perfilServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void getAll(AsyncCallback<List<Per_perfilTGWT>> asyncCallback);

    public void insert(Per_perfilTGWT per_perfilTGWT, AsyncCallback<java.lang.Boolean> asyncCallback);

    public void update(Per_perfilTGWT per_perfilTGWT, AsyncCallback<java.lang.Boolean> asyncCallback);

    public void delete(Per_perfilTGWT per_perfilTGWT, AsyncCallback<java.lang.Boolean> asyncCallback);

}
