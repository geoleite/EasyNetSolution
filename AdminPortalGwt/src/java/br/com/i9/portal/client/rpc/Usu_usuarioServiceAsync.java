/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Usu_usuarioTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author topfontes
 */
public interface Usu_usuarioServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void insert(Usu_usuarioTGWT usu_usuarioTGWT, AsyncCallback<Void> asyncCallback);

    public void findById(Usu_usuarioTGWT usu_usuarioTGWT, AsyncCallback<Void> asyncCallback);

    public void update(Usu_usuarioTGWT usu_usuarioTGWT, AsyncCallback<Void> asyncCallback);

    public void getAll(AsyncCallback<List<Usu_usuarioTGWT>> asyncCallback);

    public void consultByPerfil(Per_perfilTGWT per_perfilTGWT, AsyncCallback<List<Usu_usuarioTGWT>> asyncCallback);

    public void consultByNaoPerfil(Per_perfilTGWT per_perfilTGWT, AsyncCallback<List<Usu_usuarioTGWT>> asyncCallback);

    public void gerarNovaSenha(Usu_usuarioTGWT usu_usuarioT, AsyncCallback<Void> asyncCallback);

    public void consultByNomeLogin(String login, AsyncCallback<List<Usu_usuarioTGWT>> asyncCallback);

    public void delete(Usu_usuarioTGWT usu_usuarioTGWT, AsyncCallback<Void> asyncCallback);

    public void updatePassword(Usu_usuarioTGWT usu_usuarioTGWT, String novaSenha, AsyncCallback<Void> asyncCallback);



}
