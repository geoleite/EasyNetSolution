/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.component;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.PropertyEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;

/**
 *
 * @author geoleite
 */
public class TextFieldMaskOld extends TextField<String> {

    private String mask;
    private KeyMaskListener maskListener = new KeyMaskListener();

    public TextFieldMaskOld() {
        super.addKeyListener(maskListener);
        
    }

    class KeyMaskListener extends KeyListener {

        private boolean returnValue = false;

        public void componentKeyDown(ComponentEvent event) {
        }

        private boolean isString(int code) {
            return (code >= 65 && code <= 122);
        }

        private boolean isDigit(int code) {
            return (code >= 48 && code <= 57);
        }

        /**
         * Obtém o tipo da informacao em uma determinada posição da máscara
         * @param position
         * @return
         */
        private char getTypePositionMask(int position) {
            return mask.toCharArray()[position];
        }

        /**
         * Fires on key press.
         *
         * @param event the component event
         */
        public void componentKeyPress(ComponentEvent event) {
            //Info.display("DEBUG", event. + "");
            returnValue = false;
            if (mask != null && mask.trim().length() > 0) {
                int position = getCursorPos();

                if (mask.length() > position) {

                    char type = getTypePositionMask(position);
                    int code = event.getKeyCode();
                    if ('S' == type) {
                        if (!isString(code)) {
                            returnValue = true;
                        }
                    } else if ('D' == type) {
                        if (!isDigit(code)) {
                            returnValue = true;
                        }
                    } else if ('W' == type) {
                    } else {

                        setValue(getValue() + type);
                        componentKeyPress(event);
                    }
                } else {
                    returnValue = true;
                }
            }
        }

        /**
         * Fires on key up.
         *
         * @param event the component event
         */
        public void componentKeyUp(ComponentEvent event) {
            if (returnValue) {
                if (getValue().length() > 1) {
                    setValue(getValue().substring(0, getValue().length() - 1));
                } else {
                    setValue("");
                }
            }
        }
    }

    /**
     * @return the mask
     */
    public String getMask() {
        return mask;
    }

    /**
     * @param mask the mask to set
     */
    public void setMask(String mask) {
        this.mask = mask;
        if (mask != null) {
            setMaxLength(mask.length());
        }
    }
}
