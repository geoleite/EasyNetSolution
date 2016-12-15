/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.gwt.client;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

/**
 *
 * @author geoleite
 */
public class EasyContainer extends  LayoutContainer {

    public static String clearAspas(String valor) {
        return valor = valor.replace('"', ' ').trim();
    }

    public native static int getMouseX()/*-{
        return window.event.clientX;
    }-*/;
    public native static int getMouseY()/*-{
        return window.event.clientY;
    }-*/;
}
