/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Ope_operacaoTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usu_usuarioTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author topfontes
 */
public interface Ope_operacaoServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void insert(Ope_operacaoTGWT ope_operacaoT, AsyncCallback<Void> asyncCallback);

    public void update(Ope_operacaoTGWT ope_operacaoT, AsyncCallback<Void> asyncCallback);

    public void getAll(AsyncCallback<List<Usu_usuarioTGWT>> asyncCallback);

    public void consultByPerfil(Per_perfilTGWT per_perfilTGWT, AsyncCallback<List<Ope_operacaoTGWT>> asyncCallback);

    public void consultByNaoPerfil(Per_perfilTGWT per_perfilTGWT, AsyncCallback<List<Ope_operacaoTGWT>> asyncCallback);

    public void consultBySistema(Ope_operacaoTGWT opeT, AsyncCallback<List<Ope_operacaoTGWT>> asyncCallback);

    public void delete(Ope_operacaoTGWT ope_operacaoT, AsyncCallback<Void> asyncCallback);




}
