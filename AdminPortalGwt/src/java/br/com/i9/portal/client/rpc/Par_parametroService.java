/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Par_parametroTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Sis_sistemaTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/par_parametroservice")
public interface Par_parametroService extends RemoteService {

    public String myMethod(String s);
    public void insert(Par_parametroTGWT par_parametroT)throws Exception;
    public void update(Par_parametroTGWT par_parametroT)throws Exception;
    public void delete(Par_parametroTGWT par_parametroT)throws Exception;
    public List<Par_parametroTGWT> getParamSistema(Sis_sistemaTGWT sis_sistemaTGWT) throws Exception;
    
        
    
}
