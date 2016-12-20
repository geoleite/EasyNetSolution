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
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author georgejunior
 */
@RemoteServiceRelativePath("rpc/easyportalservice")
public interface EasyPortalService extends RemoteService {

    public Usu_usuarioTGWT authentication(Usu_usuarioTGWT usuT) throws Exception;
    public Usu_usuarioTGWT getLoggedUser() throws Exception;
    public List<Per_perfilTGWT> getUserPerfils() throws Exception;
    public List<Par_parametroTGWT> getParametersBySystem(String systemName) throws Exception;
    public List<Men_menuTGWT> getUserMenu(String systemName)throws Exception;
    public void alterPassword(String actualPass, String newPass, String newConfPass) throws Exception;
    public void insertRecallPassword(Res_recall_senhaTGWT recallPassT) throws Exception;
    public void updateRecallPassword(Res_recall_senhaTGWT recallPassT) throws Exception;
    public void getOut();
}
