/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/men_menuservice")
public interface Men_menuService extends RemoteService {
 
    public String myMethod(String s);

    public void insert(Men_menuTGWT men_menuT) throws Exception;

    public void updade(Men_menuTGWT men_menuT) throws Exception;

    public void delete(Men_menuTGWT men_menuT) throws Exception;

    public List<Men_menuTGWT> consult() throws Exception;
    
    public List<Men_menuTGWT> consultByPerfil(Per_perfilTGWT per_perfilTGWT)throws Exception;
    public List<Men_menuTGWT> consultByNotPerfil(Per_perfilTGWT per_perfilTGWT)throws Exception;
    


}
