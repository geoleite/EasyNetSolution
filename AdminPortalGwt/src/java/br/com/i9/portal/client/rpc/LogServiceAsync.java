/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.rpc;

import br.com.i9.portal.client.portal.portal.transfer.LogTGWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.List;

/**
 *
 * @author topfontes
 */
public interface LogServiceAsync {

    public void myMethod(String s, AsyncCallback<String> callback);

    public void consult(LogTGWT logT, String dtinicio, String dtfinal, AsyncCallback<List<LogTGWT>> asyncCallback);
    
}
