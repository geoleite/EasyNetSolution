/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.EasyContainer;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.GXT;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.fx.Resizable;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Params;
import com.extjs.gxt.ui.client.util.Theme;
import com.extjs.gxt.ui.client.util.ThemeManager;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
 import com.extjs.gxt.ui.client.widget.form.LabelField; 
import com.google.gwt.user.client.ui.Widget;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Portal2GWT extends EasyContainer {

    private String nomeUsuario;
    public static ContentPanel panelBase = new ContentPanel();
    public static ContentPanel panelExecucao = new ContentPanel();
    public static TabPanel tab = new TabPanel();
    //private MenuItem miSair;
    private MenuGWT menu;

    public Portal2GWT() {

        panelBase.setCollapsible(true);
        panelExecucao.setId("123456789");
        panelBase.setHeading("EasyPortal 3.0");
        panelBase.setId("panelbase");
        panelBase.setLayout(new RowLayout(Orientation.VERTICAL));
        panelBase.setSize(Window.getClientWidth(), Window.getClientHeight());
        panelBase.add(getBanner());
        panelBase.add(getPrincipal());
        this.add(panelBase);

    }

    private ContentPanel getPrincipal() {

        ContentPanel panel = new ContentPanel();
        panel.setCollapsible(false);
        //panel.setFrame(true);
        panel.setHeading("Execucao");
        panel.setHeaderVisible(false);
        panel.setLayout(new RowLayout(Orientation.HORIZONTAL));
        panel.setSize("100%", "100%");
        panel.add(getAreaExecucao());

        return panel;
    }

    /**
     * Area de execucao das operacoes
     * @return
     */
    private ContentPanel getAreaExecucao() {
        ContentPanel panel = new ContentPanel();
        panel.setCollapsible(false);
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        panel.setHeading("Execucao");
        panel.setLayout(new RowLayout(Orientation.VERTICAL));
        panel.setSize("100%", "100%");


        menu = new MenuGWT();
        // Carrega o menu do usuario
        menu.loadMenu();
        menu.setId("menu");
        panel.add(menu);

        panel.add(getExecucao());

        return panel;
    }

    private ContentPanel getExecucao() {
        ContentPanel panel = new ContentPanel();
        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.setCollapsible(false);
        panel.setFrame(false);
        panel.setHeaderVisible(false);
        panel.setSize("100%", "100%");
        ContentPanel panel1 = new ContentPanel();
        panel1.setHeading("Ferramentas");
        BorderLayoutData data = new BorderLayoutData(LayoutRegion.WEST, 230, 200, 250);
        data.setMargins(new Margins(0, 5, 0, 0));
        data.setSplit(true);
        data.setCollapsible(true);
        data.setFloatable(true);

        panel1.add(getBarraFerramentas());

        panel.add(panel1, data);

        panel1 = new ContentPanel();
        panel1.setHeading("Ambiente de Execucao");
        data = new BorderLayoutData(LayoutRegion.CENTER);
        tab.setAnimScroll(true);
        tab.setAutoSelect(true);
        tab.setSize("100%", "100%");
        panel1.add(tab);

        panel.add(panel1, data);

        return panel;
    }

    /**
     * Obtem a barra de ferramentas
     * @return
     */
    private ContentPanel getBarraFerramentas() {
        ContentPanel panel = new ContentPanel();
        panel.setCollapsible(false);
        panel.setFrame(false);
        panel.setHeaderVisible(false);
        panel.setHeading("Utilitarios");
        panel.setLayout(new RowLayout(Orientation.VERTICAL));
        panel.setSize("200", "100%");
        panel.setScrollMode(Scroll.AUTO);
        new Resizable(panel);
        // Calendario

        ContentPanel panelCalendario = new ContentPanel();
        panelCalendario.setCollapsible(true);
        panelCalendario.setFrame(true);
        panelCalendario.setHeaderVisible(true);
        panelCalendario.setHeading("Calendario");
        panelCalendario.setLayout(new RowLayout(Orientation.VERTICAL));
        panelCalendario.setSize("100%", "240");
        panelCalendario.setScrollMode(Scroll.AUTO);
        panelCalendario.setLayout(new CenterLayout());

        final DatePicker picker = new DatePicker();

        picker.addListener(Events.Select, new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent be) {
                String d = DateTimeFormat.getShortDateFormat().format(picker.getValue());
                Info.display("Data Selecionada", "Voce selecionou {0}.", new Params(d));
            }
        });
        panelCalendario.add(picker);
        panel.add(panelCalendario);


        // EasyMsn
        ContentPanel panelMsn = new ContentPanel();
        panelMsn.setCollapsible(true);
        panelMsn.setFrame(true);
        panelMsn.setHeaderVisible(true);
        panelMsn.setHeading("EasyMsn");
        panelMsn.setLayout(new RowLayout(Orientation.VERTICAL));
        panelMsn.setSize("100%", "50");
        LabelField LabelField = new LabelField("EasyMessenger e um sitema para comunicacao textual utilizando o protocolo do Messenger da Microsoft.");
        Text hl = new Text();

        hl.setText("EasyMsn o seu Messenger Corporativo.");
        panelMsn.add(hl);

        panel.add(panelMsn);

        // EasyThema
        ContentPanel panelThema = new ContentPanel();
        panelThema.setCollapsible(true);
        panelThema.setFrame(true);
        panelThema.setHeaderVisible(true);
        panelThema.setHeading("Temas Disponiveis");
        panelThema.setLayout(new CenterLayout());
        panelThema.setSize("100%", "80");
        panelThema.setScrollMode(Scroll.AUTO);
        com.google.gwt.user.client.ui.Button btnBlue = new com.google.gwt.user.client.ui.Button("Azul");
        btnBlue.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                mudarTemaAction("Azul");
            }
        });
        com.google.gwt.user.client.ui.Button btnCinza = new com.google.gwt.user.client.ui.Button("Cinza");
        btnCinza.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {

                mudarTemaAction("Cinza");
            }
        });
        com.google.gwt.user.client.ui.Button btnSlate = new com.google.gwt.user.client.ui.Button("Slate");
        btnSlate.addClickListener(new ClickListener() {

            public void onClick(Widget sender) {
                mudarTemaAction("Slate");
            }
        });
        FlowPanel fp = new FlowPanel();
        fp.add(btnBlue);
        fp.add(btnCinza);
        fp.add(btnSlate);
        panelThema.add(fp);
        panel.add(panelThema);



        return panel;
    }

    /**
     * Muda o thema do Portal
     * @param sender
     */
    public void mudarTemaAction(String thema) {
        ThemeManager tm = new ThemeManager();
        List<Theme> list = tm.getThemes();
        Theme blue = list.get(0);
        Theme gray = list.get(1);
        Theme slate = list.get(2);
        if ("Azul".equals(thema)) {
            GXT.switchTheme(blue);
        } else if ("Cinza".equals(thema)) {
            GXT.switchTheme(gray);
        } else if ("Slate".equals(thema)) {
            GXT.switchTheme(slate);
        }

    }

    /**
     * Define a area do Banner
     * @return
     */
    private ContentPanel getBanner() {
        ContentPanel panel = new ContentPanel();
        panel.setCollapsible(false);
        panel.setFrame(true);
        Image img = new Image(Constantes.URL + "/images/web10gwt.png");
        img.setSize("80", "30");
        panel.setHeaderVisible(false);
        panel.setLayout(new RowLayout(Orientation.HORIZONTAL));
        panel.setSize("100%", "50");
        panel.add(img);
        return panel;
    }

    /**
     * @return the nomeUsuario
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * @param nomeUsuario the nomeUsuario to set
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        panelBase.setHeading("EasyPortal 3.0 Bem vindo " + nomeUsuario);
    }
}
