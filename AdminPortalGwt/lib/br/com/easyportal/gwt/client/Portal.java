/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.EasyContainer;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

/**
 *
 * @author geoleite
 */
public class Portal extends EasyContainer {

    private String nomeUsuario;
    private ContentPanel panelBase = new ContentPanel();
    private ContentPanel panelExecucao;
    private MenuItem miSair;

    public Portal() {

        panelBase.setCollapsible(false);
        //panelBase.setFrame(true);
        //panelBase.setHeaderVisible(false);
        panelBase.setHeading("EasyPortal 3.0");
        panelBase.setLayout(new RowLayout(Orientation.VERTICAL));
        panelBase.setSize("100%", "100%");
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
        panel.add(getMenu());
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
        panel.setHeading("Execucao");
        panel.setLayout(new RowLayout(Orientation.HORIZONTAL));
        panel.setSize("80%", "100%");
        panelExecucao = panel;
        return panel;
    }

    /**
     * Constroi o menu do Usuario
     * @return
     */
    private ContentPanel getMenu() {
        ContentPanel panel = new ContentPanel();
        panel.setCollapsible(false);
        panel.setFrame(true);
        panel.setHeading("Menu");
        panel.setLayout(new RowLayout(Orientation.HORIZONTAL));
        panel.setSize("20%", "100%");

        MenuBar menu = new MenuBar(true);
        menu.setAnimationEnabled(true);
        //menu.setWidth("100%");
        menu.setHeight("100%");
        Command cmd = new Command() {

            public void execute() {
                //Window.alert("Saindo");
            }
        };
        
        MenuItem menuArquivo = new MenuItem("Arquivo", cmd);
        MenuItem menuEditar = new MenuItem("Editar", cmd);
        MenuItem menuSair = new MenuItem("Sair", cmd);        
        menu.addItem(menuArquivo);
        menu.addItem(menuEditar);

        MenuBar subMenuArquivo =  new MenuBar(true);
        subMenuArquivo.addItem(menuSair);

        menuArquivo.setSubMenu(subMenuArquivo);
        panel.add(menu);
        return panel;
    }


    /**
     * Define a area do Banner
     * @return
     */
    private ContentPanel getBanner() {
        ContentPanel panel = new ContentPanel();
        panel.setCollapsible(false);
        panel.setFrame(true);
        Image img = new Image(Constantes.URL + "/images/web10.png");
        img.setSize("80", "30");
        //panel.setHeading("Banner");
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
