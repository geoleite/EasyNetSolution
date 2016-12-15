/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client.service;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easyportal.gwt.client.Constantes;
import com.google.gwt.user.client.Window;
import java.util.HashMap;

/**
 *
 * @author geoleite
 */
public class ServiceJsonXml {
    private IListenetResponse listener;
    
    public ServiceJsonXml(IListenetResponse listener) {
        setListener(listener);
    }
    public void accessJSONService(String url) {
        HashMap<String, String> param = new HashMap<String, String>();
        url = url.replaceAll("\\?", "|").replaceAll("\\&", ";");
        param.put("url", url);
        String urlReal = Constantes.URL + "portalgwt/jsonService.jsp";
        EasyAccessURL access = new EasyAccessURL(listener) ;
        access.accessJSonMap(urlReal, param);
    }

    /**
     * @return the listener
     */
    public IListenetResponse getListener() {
        return listener;
    }

    /**
     * @param listener the listener to set
     */
    public void setListener(IListenetResponse listener) {
        this.listener = listener;
    }

}
