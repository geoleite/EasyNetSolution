/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.gwt.client;
import br.com.easynet.gwt.client.icons.ExampleIcons;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.ListBox;

/**
 *
 * @author geoleite
 */
public class FormBaseGWT extends FormPanel{

    public static final ExampleIcons ICONS = GWT.create(ExampleIcons.class);
    private ToolBar toolBarMaster = new ToolBar();
    public final static int WIDTH_DEFAULT = 600;
    public final static int HEIGHT_DEFAULT = 400;
    public final static int HEIGHT_DEFAULT_TOP = 100;
    private int WIDTH_DEFAULT_WEST = 100;
    private int WIDTH_DEFAULT_EAST = 100;
    private int HEIGHT_DEFAULT_NORTH = 100;
    private int HEIGHT_DEFAULT_SOUTH = 100;
    private BorderLayoutData dataNORTH;
    private BorderLayoutData dataCENTER;
    private BorderLayoutData dataSOUTH;
    private BorderLayoutData dataWEST;
    private BorderLayoutData dataEAST;
    private ContentPanel cpTop = new ContentPanel();
    private ContentPanel cpMaster = new ContentPanel();
    private ContentPanel cpBotton = new ContentPanel();
    private ContentPanel cpLeft = new ContentPanel();
    private ContentPanel cpRight = new ContentPanel();
    private FormBinding formBindings;
    private BorderLayout layoutBase = new BorderLayout();
    private BorderLayout layoutConteiner = new BorderLayout();
    private LayoutContainer container = new LayoutContainer();

    public FormBaseGWT() {
        display();
    }

    public void display() {

        setLayout(layoutBase);

        container.setLayout(layoutConteiner);
        this.setHeaderVisible(true);
        this.setShadow(true);
        this.setSize(WIDTH_DEFAULT + 20, HEIGHT_DEFAULT);

        cpBotton.setSize(WIDTH_DEFAULT, 27);

        cpMaster.setHeaderVisible(false);
        cpMaster.setScrollMode(Scroll.AUTOX);

        cpMaster.setLayout(new FillLayout());

        dataNORTH = new BorderLayoutData(LayoutRegion.NORTH, getHEIGHT_DEFAULT_NORTH());
        dataCENTER = new BorderLayoutData(LayoutRegion.CENTER);
        dataSOUTH = new BorderLayoutData(LayoutRegion.SOUTH, getHEIGHT_DEFAULT_SOUTH());
        dataWEST = new BorderLayoutData(LayoutRegion.WEST, getWIDTH_DEFAULT_WEST());
        dataEAST = new BorderLayoutData(LayoutRegion.EAST, getWIDTH_DEFAULT_EAST());

        dataNORTH.setCollapsible(true);
        dataNORTH.setFloatable(true);
        //dataTop.setHideCollapseTool(true);
        dataNORTH.setSplit(true);
        dataNORTH.setMargins(new Margins(5, 5, 0, 5));
        //dataCENTER.setMargins(new Margins(5, 0, 5, 0));
        dataCENTER.setMargins(new Margins(5, 5, 5, 5));

        dataSOUTH.setSplit(true);
        dataSOUTH.setCollapsible(true);
        dataSOUTH.setFloatable(true);
        dataSOUTH.setMargins(new Margins(0, 5, 5, 5));
        dataWEST.setSplit(true);
        dataWEST.setCollapsible(true);
        dataWEST.setMargins(new Margins(5));

        dataEAST.setSplit(true);
        dataEAST.setCollapsible(true);
        dataEAST.setMargins(new Margins(5));

        add(cpTop, dataNORTH);
        add(cpLeft, dataWEST);
        add(cpMaster, dataCENTER);
        add(cpRight, dataEAST);
        add(cpBotton, dataSOUTH);

        this.layout();
    }

    public ToolBar getToolBarMaster() {
        return toolBarMaster;
    }

    public void setToolBarMaster(ToolBar toolBarMaster) {
        this.toolBarMaster = toolBarMaster;
    }

    public BorderLayoutData getDataNORTH() {
        return dataNORTH;
    }

    public void setDataNORTH(BorderLayoutData dataNORTH) {
        this.dataNORTH = dataNORTH;
    }

    public BorderLayoutData getDataCENTER() {
        return dataCENTER;
    }

    public void setDataCENTER(BorderLayoutData dataCENTER) {
        this.dataCENTER = dataCENTER;
    }

    public BorderLayoutData getDataSOUTH() {
        return dataSOUTH;
    }

    public void setDataSOUTH(BorderLayoutData dataSOUTH) {
        this.dataSOUTH = dataSOUTH;
    }

    public BorderLayoutData getDataWEST() {
        return dataWEST;
    }

    public void setDataWEST(BorderLayoutData dataWEST) {
        this.dataWEST = dataWEST;
    }

    public BorderLayoutData getDataEAST() {
        return dataEAST;
    }

    public void setDataEAST(BorderLayoutData dataEAST) {
        this.dataEAST = dataEAST;
    }

    public ContentPanel getCpTop() {
        return cpTop;
    }

    public void setCpTop(ContentPanel cpTop) {
        this.cpTop = cpTop;
    }

    public ContentPanel getCpMaster() {
        return cpMaster;
    }

    public void setCpMaster(ContentPanel cpMaster) {
        this.cpMaster = cpMaster;
    }

    public ContentPanel getCpBotton() {
        return cpBotton;
    }

    public void setCpBotton(ContentPanel cpBotton) {
        this.cpBotton = cpBotton;
    }

    public ContentPanel getCpLeft() {
        return cpLeft;
    }

    public void setCpLeft(ContentPanel cpLeft) {
        this.cpLeft = cpLeft;
    }

    public ContentPanel getCpRight() {
        return cpRight;
    }

    public void setCpHeight(ContentPanel cpRight) {
        this.cpRight = cpRight;
    }

    public FormBinding getFormBindings() {
        return formBindings;
    }

    public void setFormBindings(FormBinding formBindings) {
        this.formBindings = formBindings;
    }

    public BorderLayout getLayoutBase() {
        return layoutBase;
    }

    public void setLayoutBase(BorderLayout layoutBase) {
        this.layoutBase = layoutBase;
    }

    /**
     * @return the WIDTH_DEFAULT_WEST
     */
    public int getWIDTH_DEFAULT_WEST() {
        return WIDTH_DEFAULT_WEST;
    }

    /**
     * @param WIDTH_DEFAULT_WEST the WIDTH_DEFAULT_WEST to set
     */
    public void setWIDTH_DEFAULT_WEST(int WIDTH_DEFAULT_WEST) {
        this.WIDTH_DEFAULT_WEST = WIDTH_DEFAULT_WEST;
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
     * @return the HEIGHT_DEFAULT_SOUTH
     */
    public int getHEIGHT_DEFAULT_SOUTH() {
        return HEIGHT_DEFAULT_SOUTH;
    }

    /**
     * @param HEIGHT_DEFAULT_SOUTH the HEIGHT_DEFAULT_SOUTH to set
     */
    public void setHEIGHT_DEFAULT_SOUTH(int HEIGHT_DEFAULT_SOUTH) {
        this.HEIGHT_DEFAULT_SOUTH = HEIGHT_DEFAULT_SOUTH;
    }

    /**
     * @return the HEIGHT_DEFAULT_NORTH
     */
    public int getHEIGHT_DEFAULT_NORTH() {
        return HEIGHT_DEFAULT_NORTH;
    }

    /**
     * @param HEIGHT_DEFAULT_NORTH the HEIGHT_DEFAULT_NORTH to set
     */
    public void setHEIGHT_DEFAULT_NORTH(int HEIGHT_DEFAULT_NORTH) {
        this.HEIGHT_DEFAULT_NORTH = HEIGHT_DEFAULT_NORTH;
    }

    public int getkeyValue(ListBox box, String value) {

        for (int i = 0; i < box.getItemCount(); i++) {
            if (box.getValue(i).equalsIgnoreCase(value)) {
                //if (value == Integer.parseInt(box.getValue(i))){
                return i;
            }
        }
        return 0;
    }
}
