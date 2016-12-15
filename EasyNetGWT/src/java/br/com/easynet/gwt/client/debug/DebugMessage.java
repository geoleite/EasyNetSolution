/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.debug;

import br.com.easynet.gwt.client.rpc.EasyNetRPCFactory;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author geoleite
 */
public class DebugMessage {

    public final static int DEBUG = 1;
    public final static int WARN = 2;
    public final static int ERROR = 3;
    public final static int INFO = 4;
    private static boolean debug = false;
    private static boolean client = false;

    public static void message(String clazz, String msg) {
        message(DEBUG, clazz, msg);
    }

    public static void messageInfo(String clazz, String msg) {
        message(INFO, clazz, msg);
    }

    public static void messageWarn(String clazz, String msg) {
        message(WARN, clazz, msg);
    }

    public static void messageError(String clazz, String msg) {
        message(ERROR, clazz, msg);
    }

    public static void message(int type, String clazz, String msg) {

        AsyncCallback<Void> callback = new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
            }

            @Override
            public void onSuccess(Void result) {

            }
        };

        switch (type) {
            case DEBUG:
                if (isDebug()) {
                    if (isClient()) {
                        Window.alert(msg);
                    }
                    EasyNetRPCFactory.getEasyLogger().debug(clazz, msg, callback);
                }
                break;
            case INFO:
                EasyNetRPCFactory.getEasyLogger().info(clazz, msg, callback);
                break;
            case WARN:
                EasyNetRPCFactory.getEasyLogger().warning(clazz, msg, callback);
                break;
            case ERROR:
                EasyNetRPCFactory.getEasyLogger().error(clazz, msg, callback);
                break;
        }
    }

    /**
     * @return the debug
     */
    public static boolean isDebug() {
        return debug;
    }

    /**
     * @param aDebug the debug to set
     */
    public static void setDebug(boolean aDebug) {
        debug = aDebug;
    }

    /**
     * @return the client
     */
    public static boolean isClient() {
        return client;
    }

    /**
     * @param aClient the client to set
     */
    public static void setClient(boolean aClient) {
        client = aClient;
    }

}
