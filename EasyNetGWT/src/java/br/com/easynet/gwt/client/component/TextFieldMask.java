/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.component;

import br.com.easynet.gwt.client.debug.DebugMessage;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Container;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.form.TextField;

/**
 *
 * @author geoleite
 */
public class TextFieldMask<D> extends TextField<D> {

    //0 para numeros obrigatorios
    // 9 para numeros opcionais
    //A-Z
    private String maskFormat = null;
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
    private int position = 0;
    private boolean upperCase = true;
    private boolean allowCharSpecial = false;
    private boolean allowCtrl = false;
    private KeyListener keyListenerUpperCase = new KeyListener() {

        public boolean checkCharSpecial(int code, boolean isShift) {
            //Info.display("DEBUG", isShift + " " + code);
            if (isShift) {
                return (code >= 48 && code <= 58);
            } else if (code > 105) {
                return true;
            } else {
                //String listChar = "([-_,_._>_`_´_<_~_^_/_?_°_\\_:_;_§_|_!_¹_²_³_£_¢_¬_§_º_@_#_%_¨_&_*_+_{_}_*_])";
                return false;
            }
        }

        public void componentKeyDown(ComponentEvent event) {
            if (event.getKeyCode() == ESC_CODE) {
                TextFieldMask.super.setValue((D) "");
                return;
            }

            if (event.getKeyCode() == TAB_CODE) {
                return;
            }

            if (!allowCtrl && event.isControlKey()) {
                event.stopEvent();
                return;
            }
            if (!allowCharSpecial) {
                if (checkCharSpecial(event.getKeyCode(), event.isShiftKey())) {
                    event.stopEvent();
                    return;
                }
            }

            if (!(event.getKeyCode() == LEFT_CODE || event.getKeyCode() == RIGHT_CODE
                    || event.getKeyCode() == TOP_CODE || event.getKeyCode() == DOWN_CODE)) {

                int positionCursor = TextFieldMask.this.getCursorPos();
                if (TextFieldMask.this.upperCase) {
                    String text = (String) TextFieldMask.this.getValueWithMask();
                    text = text.toUpperCase();
                    TextFieldMask.this.setValue((D) text);
                    TextFieldMask.this.setCursorPos(positionCursor);
                }
            }
        }

        public void componentKeyUp(ComponentEvent event) {
            if (upperCase) {
                String text = (String) TextFieldMask.this.getValueWithMask();
                text = text.toUpperCase();
                TextFieldMask.this.setValue((D) text);
            }
        }
    };
    private KeyListener keyListener = new KeyListener() {

        public void componentKeyDown(ComponentEvent event) {
            if (event.getKeyCode() == ESC_CODE) {
                TextFieldMask.super.setValue((D) "");
                return;
            }

            if (event.getKeyCode() == TAB_CODE) {
                return;
            }

            code = event.getKeyCode();
            if (code == LEFT_CODE || code == RIGHT_CODE || code == TOP_CODE || code == DOWN_CODE) {
                event.stopEvent();
                return;
            }
            position = getCursorPos();
            if (code == BACK_CODE) {
            } else if (code != CTRL_CODE) {
                //verifica se o campo já foi todo preenchido de acordo com a definição da máscara
                String text = (String) TextFieldMask.this.getValueWithMask();
                if (TextFieldMask.this.getValueWithMask() != null && text.length() == maskFormat.length()) {
                    event.stopEvent();
                    return;
                }
                int codeCharMask = getCharMask(position);
                if (codeCharMask > -1) {
                    char charMask = (char) codeCharMask;
                    boolean result = prepareMaskCharacter(charMask, code);
                    if (!result) {
                        String str = (String) TextFieldMask.this.getValueWithMask() + charMask;
                        TextFieldMask.this.setValue((D) str);
                        position++;
                        // já verifica o proximo caracter
                        codeCharMask = getCharMask(position);
                        charMask = (char) codeCharMask;
                        if (!prepareMaskCharacter(charMask, code)) {
                            event.stopEvent();
                        }
                    }
                    if (!isValidCode) {
                        event.stopEvent();
                    }
                }
            }
        }

        /**
         * Fires on key press.
         *
         * @param event the component event
         */
        public void componentKeyPress(ComponentEvent event) {
        }

        /**
         * Fires on key up.
         *
         * @param event the component event
         */
        public void componentKeyUp(ComponentEvent event) {
            if (TextFieldMask.this.upperCase) {
                String text = (String) TextFieldMask.this.getValueWithMask();
                text = text.toUpperCase();
                TextFieldMask.this.setValue((D) text);
            }
        }
    };

    public boolean prepareMaskCharacter(char charMask, int code) {
        boolean result = false;
        if (MASK_TYPE_A == charMask) {
            isValidCode = isAlpha(code);
            result = true;
        } else if (MASK_TYPE_0 == charMask) {
            isValidCode = isNumber(code);
            result = true;
        } else if (MASK_TYPE_9 == charMask) {
            isValidCode = isNumber(code);
            result = true;
        } else if (MASK_TYPE_A9 == charMask) {
            isValidCode = isAlphanumeric(code);
            result = true;
        }
        return result;
    }

    public boolean isNumber(int keyCode) {
        return ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 96 && keyCode <= 105));
    }

    public boolean isAlpha(int keyCode) {
        return (keyCode >= 65 && keyCode <= 90);
    }

    public boolean isAlphanumeric(int keyCode) {
        return isAlpha(keyCode) || isNumber(keyCode);
    }

    public int getCharMask(int position) {
        if (position < maskFormat.length()) {
            return maskFormat.toCharArray()[position];
        }
        return -1;
    }

    public TextFieldMask() {
        this(null);
    }

    public TextFieldMask(String maskFormat) {
        this.setMaskFormat(maskFormat);
    }

    /**
     * @return the maskFormat
     */
    public String getMaskFormat() {
        return maskFormat;
    }

    /**
     * @param maskFormat the maskFormat to set
     */
    public void setMaskFormat(String maskFormat) {
        removeAllListeners();
        this.maskFormat = maskFormat;
        if (maskFormat == null) {
            addKeyListener(keyListenerUpperCase);
        } else {
            addKeyListener(keyListener);
            addListener(Events.OnClick, new Listener<BaseEvent>() {

                public void handleEvent(BaseEvent be) {
                    setCursorPos(((String) getValueWithMask()).length());
                }
            });
        }

    }

    protected void setValueNew(String vl) {

        TextFieldMask.super.setValue((D)vl);
        //super.setValue((D)vl);
    }
    public void setValue(D value) {
        try {
            if (value != null) {
                if (upperCase) {
                    String vl = (String) value;
                    vl = vl.toUpperCase();
                    value = (D) vl;
                }
                super.setValue(value);
            }
        } catch (Exception e) {
            DebugMessage.message(this.getClass().getName(), "erro " + e.getMessage());
        }
    }

    /**
     * Formata o valor utilizando a expressao regular e os parametros
     * @param value 12345678921
     * @param maskRegex "(\\d{3})(\\d{3})(\\d{3})(\\d{2})$"
     * @param paramRegex "$1.$2.$3-$4"
     */
    public void setValue(String value, String maskRegex, String paramRegex) {
        try {
            if (maskRegex != null && value != null) {
                value = value.replaceAll(maskRegex, paramRegex);
                super.setValue((D) value);
            }
        } catch (Exception e) {
        }

    }

    public boolean isEmpty() {
        try {
            String text = (String) super.getValue();
            return text == null || text.trim().isEmpty();
        } catch (Exception e) {
        }
        return false;
    }

    public void clear() {
        super.setValue((D) "");
    }

    public D getValue() {
        String result = "";
        if (maskFormat != null) {
            int sizeMask = maskFormat.length();
            String strOriginal = (String) super.getValue();
            if (strOriginal != null) {
                for (int i = 0; i < sizeMask; i++) {
                    int codeCharMask = getCharMask(i);
                    if (codeCharMask > -1) {
                        char charMask = (char) codeCharMask;
                        if (MASK_TYPE_A == charMask || MASK_TYPE_0 == charMask || MASK_TYPE_9 == charMask || MASK_TYPE_A9 == charMask) {
                            if (i < strOriginal.length()) {
                                result += strOriginal.toCharArray()[i];
                            }
                        }
                    }
                }
            }
        } else {
            result = (String) super.getValue();
        }
        return (D) result;
    }

    public D getValueWithMask() {
        if (super.getValue() == null) {
            return (D) "";
        }
        return super.getValue();
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

    /**
     * @return the allowCharSpacial
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
}
