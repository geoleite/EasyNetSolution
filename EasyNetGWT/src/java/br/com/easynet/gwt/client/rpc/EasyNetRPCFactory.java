/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dev.js.rhino.TokenStream;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author georgejunior
 */
public class EasyNetRPCFactory {
    
    public static EasyLoggerServiceAsync getEasyLogger() {
        EasyLoggerServiceAsync easyLoggerAsync = GWT.create(EasyLoggerService.class);
        ServiceDefTarget serviceDef = (ServiceDefTarget)easyLoggerAsync;
        String serviceUrl = GWT.getHostPageBaseURL() + "br.com.easynet.gwt.EasyNetGWT/rpc/easyloggerservice";
        serviceDef.setServiceEntryPoint(serviceUrl);
        return easyLoggerAsync;
    }
}
