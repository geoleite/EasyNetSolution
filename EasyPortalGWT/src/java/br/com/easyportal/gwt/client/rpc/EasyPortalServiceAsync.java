/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.rpc;

import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Men_menuTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Par_parametroTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Per_perfilTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Res_recall_senhaTGWT;
import br.com.easyportal.gwt.client.admin.portal.portal.transfer.Usu_usuarioTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author georgejunior
 */
public interface EasyPortalServiceAsync {

    public void authentication(Usu_usuarioTGWT usuT, AsyncCallback<Usu_usuarioTGWT> asyncCallback);

    public void getOut(AsyncCallback<Void> asyncCallback);

    public void getLoggedUser(AsyncCallback<Usu_usuarioTGWT> asyncCallback);

    public void getUserPerfils(AsyncCallback<List<Per_perfilTGWT>> asyncCallback);

    public void getParametersBySystem(String systemName, AsyncCallback<List<Par_parametroTGWT>> asyncCallback);

    public void getUserMenu(String systemName, AsyncCallback<List<Men_menuTGWT>> asyncCallback);

    public void alterPassword(String actualPass, String newPass, String newConfPass, AsyncCallback<Void> asyncCallback);

    public void insertRecallPassword(Res_recall_senhaTGWT recallPassT, AsyncCallback<Void> asyncCallback);

    public void updateRecallPassword(Res_recall_senhaTGWT recallPassT, AsyncCallback<Void> asyncCallback);



}
