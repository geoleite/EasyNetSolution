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

/**
 *
 * @author georgejunior
 */
public class EasyHtmlEditor extends HtmlEditor {

    public EasyHtmlEditor() {
        addKeyListener(keyListener);
    }
    private KeyListener keyListener = new KeyListener() {
        @Override
        public void componentKeyDown(ComponentEvent event) {
            Info.display("DEBUG", "Caracter " + event.getKeyCode());
            if (event.getKeyCode() == KeyCodes.KEY_ESCAPE) {
                EasyHtmlEditor.super.setValue("");
                return;
            }

            if (event.getKeyCode() == KeyCodes.KEY_TAB) {
                return;
            }

            int code = event.getKeyCode();
            if (code == KeyCodes.KEY_LEFT || code == KeyCodes.KEY_RIGHT || code == KeyCodes.KEY_UP || code == KeyCodes.KEY_DOWN) {
                //event.stopEvent();
                return;
            }
            //position = getCursorPos();
            if (code == KeyCodes.KEY_CTRL) {
                event.stopEvent();
                return;
            }
        }

        @Override
        public void componentKeyPress(ComponentEvent event) {
            Info.display("DEBUG", "Caracter " + event.getKeyCode());
        }

        @Override
        public void componentKeyUp(ComponentEvent event) {
            Info.display("DEBUG", "Caracter " + event.getKeyCode());
            //super.componentKeyUp(event);
        }
    };
}
