/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.component;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.user.client.Element;

/**
 *
 * @author geoleite
 */
public class EasyTextField<D> extends TextFieldMask<D> {

    private String backGroundColor;
    private String color;

    public EasyTextField() {
        super();
    }

    public EasyTextField(String maskFormat) {
        super(maskFormat);
    }

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