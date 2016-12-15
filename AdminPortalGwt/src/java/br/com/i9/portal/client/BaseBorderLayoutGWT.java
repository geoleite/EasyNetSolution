/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client;

import br.com.easynet.gwt.client.icons.ExampleIcons;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author marcos
 */
public class BaseBorderLayoutGWT extends Window {

    private int WIDTH_DEFAULT_CENTER = 100;
    private int WIDTH_DEFAULT_EAST = 100;
    private BorderLayoutData dataCENTER;
    private BorderLayoutData dataEAST;
    private ContentPanel cpMaster = new ContentPanel();
    private ContentPanel cpRight = new ContentPanel();
    private BorderLayout layoutBase = new BorderLayout();
    public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);

    public BaseBorderLayoutGWT() {
        display();
    }

    public void display() {

        setLayout(getLayoutBase());

        this.setHeaderVisible(true);
        this.setMinimizable(true);
        this.setMaximizable(true);
        this.setShadow(true);
        this.setSize(500, 400);
        getCpMaster().setHeaderVisible(false);
        getCpMaster().setScrollMode(Scroll.AUTOX);
        getCpMaster().setLayout(new FillLayout());

        getCpRight().setHeaderVisible(false);
        getCpRight().setScrollMode(Scroll.AUTOX);
        getCpRight().setLayout(new FillLayout());

        setDataCENTER(new BorderLayoutData(LayoutRegion.CENTER));
        getDataCENTER().setMargins(new Margins(5, 5, 5, 5));
        setDataEAST(new BorderLayoutData(LayoutRegion.EAST, getWIDTH_DEFAULT_EAST()));
        getDataEAST().setSplit(true);
        getDataEAST().setCollapsible(true);
        getDataEAST().setMargins(new Margins(5));

        add(getCpMaster(), getDataCENTER());
        add(getCpRight(), getDataEAST());
        this.layout();
    }

    /**
     * @return the WIDTH_DEFAULT_CENTER
     */
    public int getWIDTH_DEFAULT_CENTER() {
        return WIDTH_DEFAULT_CENTER;
    }

    /**
     * @param WIDTH_DEFAULT_CENTER the WIDTH_DEFAULT_CENTER to set
     */
    public void setWIDTH_DEFAULT_CENTER(int WIDTH_DEFAULT_CENTER) {
        this.WIDTH_DEFAULT_CENTER = WIDTH_DEFAULT_CENTER;
    }

    /**
     * @return the WIDTH_DEFAULT_EAST
     */
    public int getWIDTH_DEFAULT_EAST() {
        return WIDTH_DEFAULT_EAST;
    }

    /**
     * @param WIDTH_DEFAULT_EAST the WIDTH_DEFAULT_EAST to set
     */
    public void setWIDTH_DEFAULT_EAST(int WIDTH_DEFAULT_EAST) {
        this.WIDTH_DEFAULT_EAST = WIDTH_DEFAULT_EAST;
    }

    /**
     * @return the dataCENTER
     */
    public BorderLayoutData getDataCENTER() {
        return dataCENTER;
    }

    /**
     * @param dataCENTER the dataCENTER to set
     */
    public void setDataCENTER(BorderLayoutData dataCENTER) {
        this.dataCENTER = dataCENTER;
    }

    /**
     * @return the dataEAST
     */
    public BorderLayoutData getDataEAST() {
        return dataEAST;
    }

    /**
     * @param dataEAST the dataEAST to set
     */
    public void setDataEAST(BorderLayoutData dataEAST) {
        this.dataEAST = dataEAST;
    }

    /**
     * @return the cpMaster
     */
    public ContentPanel getCpMaster() {
        return cpMaster;
    }

    /**
     * @param cpMaster the cpMaster to set
     */
    public void setCpMaster(ContentPanel cpMaster) {
        this.cpMaster = cpMaster;
    }

    /**
     * @return the cpRight
     */
    public ContentPanel getCpRight() {
        return cpRight;
    }

    /**
     * @param cpRight the cpRight to set
     */
    public void setCpRight(ContentPanel cpRight) {
        this.cpRight = cpRight;
    }

    /**
     * @return the layoutBase
     */
    public BorderLayout getLayoutBase() {
        return layoutBase;
    }

    /**
     * @param layoutBase the layoutBase to set
     */
    public void setLayoutBase(BorderLayout layoutBase) {
        this.layoutBase = layoutBase;
    }
}
