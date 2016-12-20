/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.i9.portal.client.portal.portal.usu_usuario;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;

/**
 *
 * @author geoleite
 */

public class ComboStatus extends BaseModel {
    public ComboStatus(String value) {
        setValue(value);
    }
    public void setValue(String value) {
        set("value", value);
    }
    public String getValue() {
        return get("value");
    }
}
