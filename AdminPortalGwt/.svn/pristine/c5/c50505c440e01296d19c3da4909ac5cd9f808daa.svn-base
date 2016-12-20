/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 * 
 * @author topfontes
 */
public interface Men_menuServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void insert(Men_menuTGWT men_menuT, AsyncCallback<Void> asyncCallback);

    public void updade(Men_menuTGWT men_menuT, AsyncCallback<Void> asyncCallback);

    public void delete(Men_menuTGWT men_menuT, AsyncCallback<Void> asyncCallback);

    public void consult(AsyncCallback<List<Men_menuTGWT>> asyncCallback);

    public void consultByPerfil(Per_perfilTGWT per_perfilTGWT, AsyncCallback<List<Men_menuTGWT>> asyncCallback);

    public void consultByNotPerfil(Per_perfilTGWT per_perfilTGWT, AsyncCallback<List<Men_menuTGWT>> asyncCallback);






}
