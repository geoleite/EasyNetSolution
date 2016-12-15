/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.gwt.client.theme;

import com.extjs.gxt.ui.client.util.Theme;

/**
 *
 * @author geoleite
 */
public class I9ThemeDarkGray extends Theme {

  public static Theme I9THEMEDARKGRAY = new I9ThemeDarkGray();
  
  public I9ThemeDarkGray() {
    super("i9themedarkgray", "I9ThemeDarkGray", "gxt/themes/darkgray/css/xtheme-darkgray.css");
  }

  public I9ThemeDarkGray(String name) {
    super("i9themedarkgray", name, "gxt/themes/darkgray/css/xtheme-darkgray.css");
  }
}
