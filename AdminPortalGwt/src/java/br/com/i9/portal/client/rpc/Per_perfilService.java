/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/per_perfilservice")
public interface Per_perfilService extends RemoteService {

    public String myMethod(String s);

    public List<Per_perfilTGWT> getAll() throws Exception;

    public boolean insert(Per_perfilTGWT per_perfilTGWT) throws Exception;

    public boolean update(Per_perfilTGWT per_perfilTGWT) throws Exception;
   
    public boolean delete(Per_perfilTGWT per_perfilTGWT) throws Exception;

}
