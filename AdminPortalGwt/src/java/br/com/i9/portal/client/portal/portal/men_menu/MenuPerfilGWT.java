/*
 * EasyNet JDragon
 */
package br.com.i9.portal.client.portal.portal.men_menu;

import br.com.easynet.gwt.client.CPConsultarBaseGWT;
import br.com.i9.portal.client.Util;
import br.com.i9.portal.client.portal.portal.dao.Men_menuDAOGWT;
import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;


import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseTreeLoader;
import com.extjs.gxt.ui.client.data.TreeLoader;
import com.extjs.gxt.ui.client.data.TreeModelReader;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

import java.util.HashMap;

/**
 *
 * @author geoleite
 */
public class MenuPerfilGWT extends CPConsultarBaseGWT {

    private Men_menuDAOGWT men_menuDao = new Men_menuDAOGWT();
    private HashMap<Integer, Men_menuTGWT> menMap = new HashMap<Integer, Men_menuTGWT>();
    private TreePanel<MenuTree> treeMenu;
    private TreeStore<MenuTree> treeStore;
    private List<Men_menuTGWT> listMen;

    public MenuPerfilGWT() {
        setHeaderVisible(false);
        setBodyBorder(false);
        getToolBarMaster().setVisible(false);
        //getToolBarMaster().remove(getBtnNovoSuper());
        getToolBarPage().setVisible(false);

    }

    public void btnNovoAction(ButtonEvent be) {
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

    public void load(ListStore<Men_menuTGWT> list) {
        getCpMaster().removeAll();

        listMen = new ArrayList<Men_menuTGWT>();
        for (int i = 0; i < list.getCount(); i++) {
            Men_menuTGWT menT = list.getAt(i);
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
        //definirMenuSuspenso();        
        getCpMaster().add(treeMenu);
        getCpMaster().layout();
        treeMenu.expandAll();
    }
}
