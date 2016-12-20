/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Res_recall_senhaTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author topfontes
 */
public interface Res_recall_senhaServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void zeraContadorTentativas(Res_recall_senhaTGWT res_recall_senhaT, AsyncCallback<Void> asyncCallback);
}
