/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.component;

import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.google.gwt.user.client.Element;

/**
 *
 * @author geoleite
 */
public class EasyNumberField extends NumberField {

    private String backGroundColor;
    private String color;

    public void setMaxLength(int m) {
        super.setMaxLength(m);
        if (rendered) {
            getInputEl().setElementAttribute("maxLength", m);
        }
    }

    public void setMessages(String msg) {
        FieldMessages f = new FieldMessages();
        f.setInvalidText(msg);
        setMessages(f);
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        getInputEl().setElementAttribute("maxLength", getMaxLength());
        if (backGroundColor != null) {
            getStyleEl().setStyleAttribute("backgroundColor", backGroundColor);
        }
        if (color != null) {
            getStyleEl().setStyleAttribute("color", color);
        }
    }


    /**
     * @return the backupGroundColor
     */
    public String getBackGroundColor() {
        return backGroundColor;
    }

    /**
     * @param backupGroundColor the backupGroundColor to set
     */
    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
}
