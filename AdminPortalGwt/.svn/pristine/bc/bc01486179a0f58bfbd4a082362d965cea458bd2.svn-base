/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Met_metodoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author topfontes
 */
public interface Met_metodoServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void insert(Met_metodoTGWT met_metodoTGWT, AsyncCallback<java.lang.Boolean> asyncCallback);

    public void update(Met_metodoTGWT met_metodoTGWT, AsyncCallback<java.lang.Boolean> asyncCallback);

    public void delete(Met_metodoTGWT met_metodoTGWT, AsyncCallback<java.lang.Boolean> asyncCallback);

    public void consultByOperacaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT per_perfilT, AsyncCallback<List<Met_metodoTGWT>> asyncCallback);

    public void consultByOperacaoNaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT per_perfilT, AsyncCallback<List<Met_metodoTGWT>> asyncCallback);

    public void consultByOperacao(Met_metodoTGWT met_metodoT, AsyncCallback<List<Met_metodoTGWT>> asyncCallback);

 
}
