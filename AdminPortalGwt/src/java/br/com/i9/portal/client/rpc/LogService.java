/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.log.LogInsertGWT;
import br.com.i9.portal.client.portal.portal.transfer.LogTGWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.Date;
import java.util.List;

/**
 *
 * @author topfontes
 */
@RemoteServiceRelativePath("rpc/logservice")
public interface LogService extends RemoteService {

    public String myMethod(String s);
    public List<LogTGWT> consult(LogTGWT logT, String dtinicio, String dtfinal)throws Exception;
}
