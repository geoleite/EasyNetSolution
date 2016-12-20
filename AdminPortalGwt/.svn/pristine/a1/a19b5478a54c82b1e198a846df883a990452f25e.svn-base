/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Sis_sistemaTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/sis_sistemaservice")
public interface Sis_sistemaService extends RemoteService {

    public String myMethod(String s);
    
    public void insert(Sis_sistemaTGWT sis_sistemaT) throws Exception;
    public void update(Sis_sistemaTGWT sis_sistemaT) throws Exception;
     public void delete(Sis_sistemaTGWT sis_sistemaT) throws Exception;
    public List<Sis_sistemaTGWT> consult() throws Exception;
}
