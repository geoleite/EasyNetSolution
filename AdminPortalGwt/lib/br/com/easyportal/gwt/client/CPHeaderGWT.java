/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.CPBaseGWT;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.Image;

/**
 *
 * @author geoleite
 */
public class CPHeaderGWT extends CPBaseGWT{

    public CPHeaderGWT(String urlImageHeader, int alturaBanner) {
        setHeaderVisible(false);
        setFrame(false);
        setBodyBorder(false);
        setBorders(false);
        getCpBotton().setVisible(false);
        getCpLeft().setVisible(false);
        getCpRight().setVisible(false);
        getCpMaster().setLayout(new FitLayout());
        Image img = new Image(urlImageHeader);
        getCpTop().setVisible(true);
        getCpTop().add(img);
        getCpTop().setHeaderVisible(false);
        getCpTop().setFrame(false);
        getCpTop().setBodyBorder(false);

        getDataNORTH().setSize(alturaBanner);
        getDataNORTH().setMinSize(alturaBanner);
        getDataNORTH().setMaxSize(alturaBanner);

    }
}
