/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.IListenetResponse;
import br.com.easynet.gwt.client.debug.DebugMessage;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

public class SairIListenerUrl implements IListenetResponse {
    private MessageBox mb;
    public void read(JSONValue jsonValue) {
        if (mb != null) {
            mb.close();
        }
        DebugMessage.message(this.getClass().getName(), "Reload Page");
        //Window.Location.reload();
        //execute("document.location.reload();");
        Timer timer = new Timer() {
            @Override
            public void run() {               
                com.google.gwt.user.client.Window.Location.reload();
            }
        };
        timer.schedule(2000);
    }

    public native static void execute(String command)/*-{
    eval(command);
    }-*/;

    /**
     * @return the mb
     */
    public MessageBox getMb() {
        return mb;
    }

    /**
     * @param mb the mb to set
     */
    public void setMb(MessageBox mb) {
        this.mb = mb;
    }
}
