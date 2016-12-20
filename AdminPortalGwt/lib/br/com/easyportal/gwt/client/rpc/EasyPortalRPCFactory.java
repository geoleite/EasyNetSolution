/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client.rpc;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author georgejunior
 */
public class EasyPortalRPCFactory {
    public static EasyPortalServiceAsync getEasyPortalService() {
        EasyPortalServiceAsync easyLoggerAsync = GWT.create(EasyPortalService.class);
        ServiceDefTarget serviceDef = (ServiceDefTarget)easyLoggerAsync;
        String serviceUrl = GWT.getHostPageBaseURL() + "br.com.easyportal.gwt.EasyPortalGWT/rpc/easyportalservice";
        //Window.alert(serviceDef.getServiceEntryPoint());
        //Window.alert(serviceUrl);
        serviceDef.setServiceEntryPoint(serviceUrl);
        return easyLoggerAsync;
    }
    
}
