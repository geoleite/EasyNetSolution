/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.Mep_men_perTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/mep_men_perservice")
public interface Mep_men_perService extends RemoteService {

    public String myMethod(String s);
    public boolean insert(Mep_men_perTGWT mep_men_perTGWT)throws Exception;
    public boolean delete(Mep_men_perTGWT mep_men_perTGWT)throws Exception;
    
}
