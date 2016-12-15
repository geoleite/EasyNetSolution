/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.report;

import br.com.easynet.gwt.client.i18n.EasyLabels;
import br.com.easynet.gwt.client.icons.Icones;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.menu.SeparatorMenuItem;
import com.extjs.gxt.ui.client.widget.toolbar.LabelToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;

/**
 *
 * @author marcos
 */
public class CPRelatorioBaseGWT extends ContentPanel {
    public EasyLabels easyLabels = GWT.create(EasyLabels.class);
    private ContentPanel cpMaster = new ContentPanel();
    private ToolBar toolBarMaster = new ToolBar();
    protected Button btnFiltrar = new Button(easyLabels.filter());
    private Button btnExportar = new Button(easyLabels.export());
    private RadioGroup radioGroup = new RadioGroup();
    private DateTimeFormat dtfDate = DateTimeFormat.getFormat("dd/MM/yyyy");
    private DateField dtInicio = new DateField();
    private DateField dtFim = new DateField();
    private Menu menu = new Menu();
    private String paramBase;
    public final String TIPO_HTML = "$tipo=HTML";
    public final String TIPO_XLS = "&tipo=XLS";
    public final String TIPO_PDF = "&tipo=PDF";
    public final String JSP_VIEW = "viewer.jsp?url=";
    private MessageBox mb = new MessageBox();
    private ContentPanel cpREL = new ContentPanel(new FillLayout());

    public CPRelatorioBaseGWT() {
        cpREL.setHeaderVisible(false);
        dtInicio.setWidth(80);
        dtFim.setWidth(80);
        dtInicio.getPropertyEditor().setFormat(dtfDate);
        dtFim.getPropertyEditor().setFormat(dtfDate);

        setLayout(new FillLayout());

        cpMaster.setHeaderVisible(false);
        FillLayout layout = new FillLayout();
        cpMaster.setLayout(layout);
        

        btnFiltrar.setIcon(Icones.ICONS.filter());
        btnExportar.setIcon(Icones.ICONS.download());
        btnFiltrar.addSelectionListener(new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                filtrar();
            }
        });

        cpMaster.setTopComponent(toolBarMaster);
        getCpMaster().add(cpREL);

        montarMenu();
        btnExportar.setMenu(menu);
        add(cpMaster);
        layout();
    }

//    @Override
//    protected void onShow() {
//        super.onShow();
//        maximize();
//    }

    public void addStatus() {
        toolBarMaster.add(new LabelToolItem(easyLabels.status()));
        toolBarMaster.add(getRadioGroup());
        toolBarMaster.add(new SeparatorToolItem());
        toolBarMaster.add(new SeparatorToolItem());
    }

    public void addPeriodo() {
        toolBarMaster.add(new LabelToolItem(easyLabels.from() + ":"));
        toolBarMaster.add(getDtInicio());
        toolBarMaster.add(new LabelToolItem(" a "));
        toolBarMaster.add(getDtFim());
        toolBarMaster.add(new SeparatorToolItem());
        toolBarMaster.add(new SeparatorToolItem());
    }

    public void addBtnFiltrar() {
        toolBarMaster.add(btnFiltrar);
        toolBarMaster.add(new SeparatorToolItem());
        toolBarMaster.add(new SeparatorToolItem());
    }

    public void addBtnExportar() {
        toolBarMaster.add(getBtnExportar());
        toolBarMaster.add(new SeparatorToolItem());
        toolBarMaster.add(new SeparatorToolItem());
    }

    public void montarMenu() {
        MenuItem itemPDF = new MenuItem("PDF");
        itemPDF.addSelectionListener(new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                exportarPDF();
            }
        });

        MenuItem itemXLS = new MenuItem("XLS");
        itemXLS.addSelectionListener(new SelectionListener<MenuEvent>() {

            @Override
            public void componentSelected(MenuEvent ce) {
                exportarXLS();
            }
        });
        getMenu().add(itemXLS);
        getMenu().add(itemPDF);
    }

    public void filtrar() {
    }

    public void exportarPDF() {
    }

    public void exportarXLS() {
    }

    public void download(final String url_download) {
        mb = MessageBox.wait(easyLabels.process(), easyLabels.generateFile(), easyLabels.waitRequest() + ".......!!!");
        //com.google.gwt.user.client.Window.alert("Chegou");
        Timer timer = new Timer() {

            @Override
            public void run() {
                final com.extjs.gxt.ui.client.widget.Window winDownload = new com.extjs.gxt.ui.client.widget.Window();
                winDownload.setUrl(url_download);
                winDownload.setSize(1, 1);
                winDownload.setResizable(false);
                winDownload.show();
                Timer t = new Timer() {

                    @Override
                    public void run() {
                        winDownload.setVisible(false);
                        mb.close();
                    }
                };
                t.schedule(5000);
            }
        };
        timer.schedule(10000);// espera 10 segundos para poder exibir a janela para download do arquivo
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
     * @return the toolBarMaster
     */
    public ToolBar getToolBarMaster() {
        return toolBarMaster;
    }

    /**
     * @param toolBarMaster the toolBarMaster to set
     */
    public void setToolBarMaster(ToolBar toolBarMaster) {
        this.toolBarMaster = toolBarMaster;
    }

    /**
     * @return the menu
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String convertCaracterURL(String url, String tipo){
        String newUrl ="";
        if (tipo.equalsIgnoreCase(TIPO_HTML)){
            newUrl = url.replace("?", "|");
            newUrl = newUrl.replace("&", "$");
        }
        return newUrl;
    }
    
    /**
     * @param paramBase the paramBase to set
     */
    public void setParamBase(String paramBase) {
        this.paramBase = paramBase;
    }

    /**
     * @return the btnExportar
     */
    public Button getBtnExportar() {
        return btnExportar;
    }

    /**
     * @param btnExportar the btnExportar to set
     */
    public void setBtnExportar(Button btnExportar) {
        this.btnExportar = btnExportar;
    }

    /**
     * @return the radioGroup
     */
    public RadioGroup getRadioGroup() {
        return radioGroup;
    }

    /**
     * @param radioGroup the radioGroup to set
     */
    public void setRadioGroup(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    /**
     * @param dtfDate the dtfDate to set
     */
    public void setDtfDate(DateTimeFormat dtfDate) {
        this.dtfDate = dtfDate;
    }

    /**
     * @return the dtInicio
     */
    public DateField getDtInicio() {
        return dtInicio;
    }

    /**
     * @param dtInicio the dtInicio to set
     */
    public void setDtInicio(DateField dtInicio) {
        this.dtInicio = dtInicio;
    }

    /**
     * @return the dtFim
     */
    public DateField getDtFim() {
        return dtFim;
    }

    /**
     * @param dtFim the dtFim to set
     */
    public void setDtFim(DateField dtFim) {
        this.dtFim = dtFim;
    }

    /**
     * @return the dtfDate
     */
    public DateTimeFormat getDtfDate() {
        return dtfDate;
    }

    /**
     * @return the cpREL
     */
    public ContentPanel getCpREL() {
        return cpREL;
    }

    /**
     * @param cpREL the cpREL to set
     */
    public void setCpREL(ContentPanel cpREL) {
        this.cpREL = cpREL;
    }
}