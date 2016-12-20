/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.CPBaseGWT;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;

/**
 *
 * @author geoleite
 */
public class TesteGWT extends CPBaseGWT {
    public TesteGWT() {
        remove(getCpBotton());
        //remove(getCpTop());
        remove(getCpRight());
        getDataWEST().setSize(200);
        getCpLeft().setLayout(new AccordionLayout());
        Window.setMargin("0");
        setHeaderVisible(false);
        //setWidth("100%");
        //setBodyStyle("heigth: 100%");// height:100%;

        //setHeight(Window.getClientHeight() - alturaBanner);
        setTopComponent(new ToolBar());
        setScrollMode(Scroll.AUTO);
        getCpMaster().setLayout(new FitLayout());
        getCpTop().setLayout(new FillLayout());
        Image img = new Image("images/topo.jpg");
        getCpTop().add(img); 
        getCpTop().setHeaderVisible(false);
        getCpTop().setFrame(false);
        getCpTop().setBodyBorder(false);

        getDataNORTH().setSize(30);
        getDataNORTH().setMinSize(30);
        getDataNORTH().setMaxSize(30);

        setHeaderVisible(false);
        setFrame(false);
        setBodyBorder(false);

        ContentPanel cp = new ContentPanel();
        cp.setLayout( new FillLayout());
        cp.setTopComponent(new Button("img"));
        ToolBar tb = new ToolBar();
        tb.add(new LabelField("teste"));
        cp.setBottomComponent(tb);
        cp.setHeight(57);

        setTopComponent(cp);

    }
}
