/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.component;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.HtmlEditor;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RichTextArea;

/**
 *
 * @author georgejunior
 */
public class EasyRichText extends RichTextArea {

    private final static char MASK_TYPE_A9 = '#';
    private final static char MASK_TYPE_A = 'A';
    private final static char MASK_TYPE_0 = '0';
    private final static char MASK_TYPE_9 = '9';
    private int code;
    private boolean isValidCode = false;
    private final static int CTRL_CODE = 17;
    private final static int SHIFT_CODE = 16;
    private final static int BACK_CODE = 8;
    private final static int LEFT_CODE = 37;
    private final static int TOP_CODE = 37;
    private final static int RIGHT_CODE = 38;
    private final static int DOWN_CODE = 40;
    private final static int ESC_CODE = 27;
    private final static int TAB_CODE = 9;
    private final static int ENTER_CODE = 13;
    private boolean upperCase = true;
    private boolean allowCharSpecial = false;
    private boolean allowCtrl = false;

    public EasyRichText() {
//        addKeyDownHandler(new KeyDownHandler() {
//
//            public void onKeyDown(KeyDownEvent event) {
//                event.stopPropagation();
//            }
//        });
        addKeyPressHandler(new KeyPressHandler() {
            public boolean checkCharSpecial(int code, boolean isShift) {
                Info.display("DEBUG", isShift + " " + code);
                if (isShift) {
                    return (code >= 65 && code <= 122);
                } else if (code > 122) {
                    return true;
                } else {
                    //String listChar = "([-_,_._>_`_´_<_~_^_/_?_°_\\_:_;_§_|_!_¹_²_³_£_¢_¬_§_º_@_#_%_¨_&_*_+_{_}_*_])";
                    return false;
                }
            }

            public void onKeyPress(KeyPressEvent event) {
                
                //Info.display("DEBUG", event.getUnicodeCharCode() + " - " + event.getCharCode());
//                if (event.getUnicodeCharCode()
//                        == ESC_CODE) {
//                    EasyRichText.super.setText("");
//                    return;
//                }
//
//                if (event.getUnicodeCharCode()
//                        == TAB_CODE) {
//                    return;
//                }
                if (event.isControlKeyDown() || event.getCharCode() == KeyCodes.KEY_CTRL || event.getNativeEvent().getCtrlKey()) {
                    //event.stopEvent();
                    event.stopPropagation();
                    //return;
                }
//                if (!allowCharSpecial) {
//                    if (checkCharSpecial(event.getUnicodeCharCode(), event.isShiftKeyDown())) {
//                        //event.stopEvent();
//                        Window.alert("nao permite caracter especial");
//                        return;
//                    }
//                }
//
//                if (!(event.getUnicodeCharCode()
//                        == LEFT_CODE || event.getUnicodeCharCode() == RIGHT_CODE
//                        || event.getUnicodeCharCode() == TOP_CODE || event.getUnicodeCharCode() == DOWN_CODE)) {
//
////                int positionCursor = EasyRichText.this.getCursorPos();
//                    int positionCursor = EasyRichText.this.getText().length();
//                    if (EasyRichText.this.upperCase) {
//                        String text = (String) EasyRichText.this.getText();
//                        text = text.toUpperCase();
//                        EasyRichText.this.setText(text);
//                        //EasyRichText.this.setCursorPos(positionCursor);
//                    }
//                }
            }
        });
        //addKeyListener(keyListenerUpperCase);
        
    }
    
    private KeyListener keyListenerUpperCase = new KeyListener() {
        public void componentKeyDown(ComponentEvent event) {
        }

        public void componentKeyUp(ComponentEvent event) {
            if (isUpperCase()) {
                String text = (String) EasyRichText.this.getText();
                text = text.toUpperCase();
                EasyRichText.this.setText(text);
            }
        }
    };

    /**
     * @return the allowCharSpecial
     */
    public boolean isAllowCharSpecial() {
        return allowCharSpecial;
    }

    /**
     * @param allowCharSpecial the allowCharSpecial to set
     */
    public void setAllowCharSpecial(boolean allowCharSpecial) {
        this.allowCharSpecial = allowCharSpecial;
    }

    /**
     * @return the allowCtrl
     */
    public boolean isAllowCtrl() {
        return allowCtrl;
    }

    /**
     * @param allowCtrl the allowCtrl to set
     */
    public void setAllowCtrl(boolean allowCtrl) {
        this.allowCtrl = allowCtrl;
    }

    /**
     * @return the upperCase
     */
    public boolean isUpperCase() {
        return upperCase;
    }

    /**
     * @param upperCase the upperCase to set
     */
    public void setUpperCase(boolean upperCase) {
        this.upperCase = upperCase;
    }
}
