/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easyportal.gwt.client;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.IListenetResponse;
import com.extjs.gxt.desktop.client.Desktop;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;

/**
 *
 * @author geoleite
 */
public class MenuGWT extends ContentPanel implements IListenetResponse {

    private MessageBox mbProgress;
    //private ContentPanel panel = new ContentPanel();
    private MenuBar menuBar = new MenuBar(false);
    private ContentPanel cpExecucao;

    public MenuGWT() {
        setCollapsible(false);
        setFrame(false);
        setHeading("Menu");
        setHeaderVisible(false);
        setLayout(new RowLayout(Orientation.HORIZONTAL));
        setSize("100%", "30");


        menuBar.setAnimationEnabled(true);
        menuBar.setWidth("100%");
        menuBar.setHeight("40");
        Command cmd = new Command() {

            public void execute() {
                Window.alert("Saindo");
            }
        };


        add(menuBar);
    }

    public void loadMenu() {
        try {
            String url = Constantes.URL + "portalgwt/menu.jsp";
            EasyAccessURL eaurl = new EasyAccessURL(this);
            mbProgress = MessageBox.wait("Requisicao", "Solicitando ao Servidor.", "Processo...");
            eaurl.accessJSon(url);
        } catch (Exception ex) {
            Window.alert("Error:" + ex.getMessage());
        }
    }

    /**
     * Constroi o menu padrao
     * @return
     */
    public MenuItem getMenuPadrao() {
        MenuEventoPortal mep = new MenuEventoPortal();
        mep.setActionDisplay("Arquivo");
        MenuItem miArq = new MenuItem("Arquivo", mep);
        MenuBar mbArq = new MenuBar(true);
        mep = new MenuEventoPortal();
        mep.setActionDisplay("Alterar Senha");
        MenuItem miAltSenha = new MenuItem("Alterar Senha", mep);
        mep = new MenuEventoPortal();
        mep.setActionDisplay("Limpar Execucoes");
        MenuItem miLimparExecucoes = new MenuItem("Limpar Execucoes", mep);
        mep = new MenuEventoPortal();
        mep.setActionDisplay("Sair");
        MenuItem miSair = new MenuItem("Sair", mep);
        mbArq.addItem(miLimparExecucoes);
        mbArq.addItem(miAltSenha);
        mbArq.addSeparator();
        mbArq.addItem(miSair);
        miArq.setSubMenu(mbArq);
        return miArq;
    }

    public void read(JSONValue jsonValue) {
        // Monta o menu baseado nas informacoes do servidor

        JSONObject jsonObject;
        mbProgress.close();
        if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
            JSONObject resultado = jsonObject.get("resultado").isObject();
            Window.alert(resultado.toString());
            JSONArray menuContent = resultado.get("menu").isArray();
            Window.alert(menuContent.isArray().toString());
            menuBar.addItem(getMenuPadrao());
            for (int i = 1; i < menuContent.size(); i++) {
                //Window.alert("111");
                JSONValue menu = menuContent.get(i);
                String nome = menu.isObject().get("nome").toString();
                nome = nome.replace('"', ' ').trim();
                String acao = menu.isObject().get("acao").toString();
                acao = acao.replace('"', ' ').trim();
                String sistema = menu.isObject().get("sistema").toString();
                sistema = sistema.replace('"', ' ').trim();
                MenuEvento menuEvento = getMenuEvento(sistema);
                //String status = menu.isObject().get("status").toString();
                //status = status.replace('"', ' ').trim();

                //MenuEvento menuEvento = new MenuEvento();

                menuEvento.setActionDisplay(acao);
                //menuEvento.setCpExecucao(cpExecucao);
                MenuItem item = new MenuItem(nome, menuEvento);
                menuBar.addItem(item);
                JSONArray submenu = menu.isObject().get("submenu").isArray();

                montaSubMenu(item, submenu);
            }

        }

    }

    /**
     * Monta recursivamente os submenus
     * @param item
     * @param submenu
     */
    private void montaSubMenu(MenuItem item, JSONArray submenu) {

        if (submenu != null && submenu.size() > 1) {
            MenuBar subMenu1 = new MenuBar(true);
            for (int j = 1; j < submenu.size(); j++) {
                JSONValue sm1 = submenu.get(j);
                String subnome = sm1.isObject().get("nome").toString();
                subnome = subnome.replace('"', ' ').trim();
                String subacao = sm1.isObject().get("acao").toString();
                String sistema = sm1.isObject().get("sistema").toString();
                sistema = sistema.replace('"', ' ').trim();

                subacao = subacao.replace('"', ' ').trim();
                MenuEvento subMenuEvento = new MenuEvento();
                subMenuEvento.setActionDisplay(subacao);
                MenuItem subitem = new MenuItem(subnome, subMenuEvento);
                subMenu1.addItem(subitem);
                item.setSubMenu(subMenu1);
                JSONArray submenu1 = sm1.isObject().get("submenu").isArray();
                montaSubMenu(subitem, submenu1);
            }

        }
    }

    /**
     * Obtem o tratamento de Eventos do menu pelo nome do Sistema
     * @param sistema
     * @return
     */
    public MenuEvento getMenuEvento(String sistema) {
        MenuEvento menuEvento = null;
        MessageBox mb = new MessageBox();

        if ("EasyCondominium".equals(sistema)) {
            menuEvento = new MenuEvento();
        } else if ("EasyCartorio".equals(sistema)) {
            menuEvento = new MenuEvento();
        } else {
            menuEvento = new MenuEvento();
        }
        return menuEvento;

    }

    /**
     * @return the cpExecucao
     */
    public ContentPanel getCpExecucao() {
        return cpExecucao;
    }

    /**
     * @param cpExecucao the cpExecucao to set
     */
    public void setCpExecucao(ContentPanel cpExecucao) {
        this.cpExecucao = cpExecucao;
    }
}
