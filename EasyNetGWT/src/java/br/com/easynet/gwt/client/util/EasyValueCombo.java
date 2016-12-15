/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.util;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 *
 * @author geoleite
 */
public class EasyValueCombo extends BaseModel {

    public EasyValueCombo() {
        
    }
    public EasyValueCombo(String value, String text) {
        setValue(value);
        setText(text);
    }

    public void setText(String value) {
        set("text", value);
    }

    public String getText() {
        return get("text");
    }

    public void setValue(String value) {
        set("value", value);
    }

    public String getValue() {
        return get("value");
    }
}
