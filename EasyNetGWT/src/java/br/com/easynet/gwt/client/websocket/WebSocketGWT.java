/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.websocket;

import com.extjs.gxt.ui.client.widget.Info;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;

/**
 * WebSocket GWT
 *
 * @author georgejunior
 */
public class WebSocketGWT {

    //String webSocketURL = GWT.getModuleBaseURL().replace("http", "ws") + "webSocket";
    private Object webSocket;
    private String url;
    private boolean running = true;

    public WebSocketGWT(String url) {
        //ws://localhost:8080/I9TaxiGwt/wst?session=" + id
        setUrl(url);
    }

    public void startConnect() {
        webSocket = connect(this, getUrl());
        running = true;
        //Info.display("Teste WS", getUrl());

    }

    protected void onOpen() {
        //Info.display("WebSocketGWT", "OnOpen");
    }

    protected void onMessage(String message) {
        //Info.display("WebSocketGWT", "OnMessage " + message);
    }

    protected void onClosed() {
        //Info.display("WebSocketGWT", "OnClosed");
        if (running) {
            new Timer() {

                @Override
                public void run() {
                    Info.display("WebSocket", "Tentando reconectar.");
                    startConnect();
                }
            }.schedule(60000);
        }
    }
    
    public void close() {
        running=false;
        closeWS(webSocket);
    }

    public void sendMessage(String message) {
        sendMessageWS(webSocket, message);
    }

    private static native void closeWS(Object ws)/*-{
      var websocket = ws;
      websocket.close();
    }-*/;
    
    private static native String sendMessageWS(Object ws, String message)/*-{
     var websocket = ws;
        websocket.send(message);
     }-*/;

    private static native Object connect(WebSocketGWT wsGWT, String url) /*-{
     //var wsUri = "ws://localhost:8080/I9TaxiGwt/wst?session=" + id;
     var wsUri = url;
     var websocket = new WebSocket(wsUri);
     websocket.onerror = function (evt) {
       wsGWT.@br.com.easynet.gwt.client.websocket.WebSocketGWT::onClosed()();
     };
                
     websocket.onopen = function (evt) {
        wsGWT.@br.com.easynet.gwt.client.websocket.WebSocketGWT::onOpen()();
     };
     websocket.onmessage = function (evt) {
        wsGWT.@br.com.easynet.gwt.client.websocket.WebSocketGWT::onMessage(Ljava/lang/String;)(evt.data);
     };
     
     websocket.onclose = function(ev) {
        wsGWT.@br.com.easynet.gwt.client.websocket.WebSocketGWT::onClosed()();
     }; 
        
     return websocket;
     }-*/;

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
