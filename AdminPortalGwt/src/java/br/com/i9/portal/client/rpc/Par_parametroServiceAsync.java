/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Par_parametroTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Sis_sistemaTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author topfontes
 */
public interface Par_parametroServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void insert(Par_parametroTGWT par_parametroT, AsyncCallback<Void> asyncCallback);

    public void update(Par_parametroTGWT par_parametroT, AsyncCallback<Void> asyncCallback);

    public void getParamSistema(Sis_sistemaTGWT sis_sistemaTGWT, AsyncCallback<List<Par_parametroTGWT>> asyncCallback);

    public void delete(Par_parametroTGWT par_parametroT, AsyncCallback<Void> asyncCallback);
}
