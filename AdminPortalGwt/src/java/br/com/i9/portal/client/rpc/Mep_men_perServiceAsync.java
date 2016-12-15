/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Mep_men_perTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author topfontes
 */
public interface Mep_men_perServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void insert(Mep_men_perTGWT mep_men_perTGWT, AsyncCallback<java.lang.Boolean> asyncCallback);

    public void delete(Mep_men_perTGWT mep_men_perTGWT, AsyncCallback<java.lang.Boolean> asyncCallback);

}
