/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.men_menu;

import br.com.easynet.gwt.client.CPConsultarBaseGWT;
import br.com.i9.portal.client.Util;
import br.com.i9.portal.client.portal.portal.dao.Men_menuDAOGWT;
import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;
import br.com.i9.portal.client.rpc.EasyAdmPortalRPCFactory;
import br.com.i9.portal.client.rpc.Men_menuService;
import br.com.i9.portal.client.rpc.Men_menuServiceAsync;

import com.extjs.gxt.ui.client.event.Listener;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.MenuEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.menu.Menu;
import com.extjs.gxt.ui.client.widget.menu.MenuItem;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;
import com.google.gwt.core.client.GWT;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.HashMap;

/**
 *
 * @author geoleite
 */
public class Men_menuConsultGWT extends CPConsultarBaseGWT {

    private Men_menuDAOGWT men_menuDao = new Men_menuDAOGWT();
    private HashMap<Integer, Men_menuTGWT> menMap = new HashMap<Integer, Men_menuTGWT>();
    private TreePanel<MenuTree> treeMenu;
    private TreeStore<MenuTree> treeStore;
    private List<Men_menuTGWT> listMen;
    private Button btnRefresh = new Button("Atualizar");

    public Men_menuConsultGWT() {
        setHeaderVisible(false);
        //getToolBarMaster().remove(getBtnNovoSuper());
        getToolBarPage().setVisible(false);
        load();
        btnRefresh.addListener(Events.OnClick, new Listener<ButtonEvent>() {

            public void handleEvent(ButtonEvent be) {
                load();
            }
        });
    }

    public void btnNovoAction(ButtonEvent be) {
        abrirJanelaInsert();
    }

    private void montaTree(MenuTree menu) {
        List<Men_menuTGWT> filhos = getFilhos(menu.getId());
        for (Men_menuTGWT menT : filhos) {
            MenuTree newMenu = new MenuTree(menT.getMen_nr_id(), menT.getMen_tx_nome(), "");
            menu.add(newMenu);
            montaTree(newMenu);
        }
    }

    private List<Men_menuTGWT> getFilhos(int setNrId) {
        List<Men_menuTGWT> filhos = new ArrayList<Men_menuTGWT>();
        for (Men_menuTGWT men_menuTGWT : listMen) {
            if (men_menuTGWT.getSupermenu_nr_id() == setNrId) {
                filhos.add(men_menuTGWT);
            }
        }
        return filhos;
    }

    public void load() {

        AsyncCallback<List<Men_menuTGWT>> callback = new AsyncCallback<List<Men_menuTGWT>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.info("IMPORTANTE", "Falha ao realizar a consulta", null);
            }

            @Override
            public void onSuccess(List<Men_menuTGWT> result) {
                listMen = new ArrayList<Men_menuTGWT>();
                for (int i = 0; i < result.size(); i++) {
                    Men_menuTGWT menT = result.get(i);
                    listMen.add(menT);
                    menMap.put(menT.getMen_nr_id(), menT);
                }
                MenuTree root = new MenuTree(Util.getCodigoSetorRaiz(listMen), "", "");
                montaTree(root);
                TreeLoader<MenuTree> loader = new BaseTreeLoader<MenuTree>(new TreeModelReader<List<MenuTree>>());
                treeStore = new TreeStore<MenuTree>(loader);
                treeMenu = new TreePanel<MenuTree>(treeStore);
                treeMenu.setStateful(true);
                treeMenu.setAutoLoad(true);
                treeMenu.setDisplayProperty("name");
                loader.load(root);
                definirMenuSuspenso();
                getCpMaster().add(treeMenu);
                getCpMaster().layout();
                treeMenu.expandAll();
            }
        };
        Men_menuServiceAsync async = EasyAdmPortalRPCFactory.getMen_MenuService();
        async.consult(callback);

//        men_menuDao.consultarTodos();
//        Timer timer = new Timer() {
//
//            @Override
//            public void run() {
//                getCpMaster().removeAll();
//                ListStore<Men_menuTGWT> list = men_menuDao.getList();
//                if (list == null) {
//                    schedule(500);
//                } else {
//
//                    listMen = new ArrayList<Men_menuTGWT>();
//                    for (int i = 0; i < list.getCount(); i++) {
//                        Men_menuTGWT menT = list.getAt(i);
//                        listMen.add(menT);
//                        menMap.put(menT.getMen_nr_id(), menT);
//                    }
//                    MenuTree root = new MenuTree(Util.getCodigoSetorRaiz(listMen), "", "");
//                    montaTree(root);
//                    TreeLoader<MenuTree> loader = new BaseTreeLoader<MenuTree>(new TreeModelReader<List<MenuTree>>());
//                    treeStore = new TreeStore<MenuTree>(loader);
//                    treeMenu = new TreePanel<MenuTree>(treeStore);
//                    treeMenu.setStateful(true);
//                    treeMenu.setAutoLoad(true);
//                    treeMenu.setDisplayProperty("name");
//                    loader.load(root);
//                    definirMenuSuspenso();
//                    getCpMaster().add(treeMenu);
//                    getCpMaster().layout();
//                    treeMenu.expandAll();
//                }
//            }
//        };
//
//        timer.schedule(500);
    }

    private void abrirJanelaInsert() {
        MenuTree setor = treeMenu.getSelectionModel().getSelectedItem();
        Men_menuInsertGWT menInsert = new Men_menuInsertGWT();
        if (setor != null) {
            Men_menuTGWT menT = menMap.get(setor.getId());
            menInsert.setMenTPai(menT);
        } else {
        }
        menInsert.setMenuConsult(Men_menuConsultGWT.this);
        menInsert.setVisible(true);
    }

    private void definirMenuSuspenso() {
        Menu contextMenu = new Menu();

        MenuItem insert = new MenuItem();
        insert.setText("Cadastrar Menu");
        insert.setIcon(ICONS.add());
        insert.addSelectionListener(new SelectionListener<MenuEvent>() {

            public void componentSelected(MenuEvent ce) {
                abrirJanelaInsert();
            }
        });
        contextMenu.add(insert);

        MenuItem remove = new MenuItem();
        remove.setText("Editar Menu");
        remove.setIcon(ICONS.edit());
        remove.addSelectionListener(new SelectionListener<MenuEvent>() {

            public void componentSelected(MenuEvent ce) {
                MenuTree selected = treeMenu.getSelectionModel().getSelectedItem();
                Men_menuUpdateDeleteGWT men_menuUpdateDeleteGWT = new Men_menuUpdateDeleteGWT();
                men_menuUpdateDeleteGWT.setMenuConsult(Men_menuConsultGWT.this);
                Men_menuTGWT menT = menMap.get(selected.getId());
                men_menuUpdateDeleteGWT.setMenTPai(menT);
                men_menuUpdateDeleteGWT.load(menT);
                men_menuUpdateDeleteGWT.setVisible(true);
            }
        });
        contextMenu.add(remove);

        treeMenu.setContextMenu(contextMenu);
    }

    public void setSet_setorTGWT(Men_menuTGWT men_menuTGWT) {
    }
}
