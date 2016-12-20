/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client.portal.portal.men_menu;

import br.com.easynet.gwt.client.EasyAccessURL;
import br.com.easynet.gwt.client.EasyContainer;
import br.com.easynet.gwt.client.IListenetResponse;
import br.com.i9.portal.client.BaseTreeGWT;
import br.com.i9.portal.client.Constantes;
import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;
import br.com.i9.portal.client.portal.portal.transfer.Per_perfilTGWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author I9
 */
public class ListaMenuGWT implements IListenetResponse {

    public static final String PAGE = "i9factory/factory/men_menu/men_menuConsultGWT.jsp";
    private List<Men_menuTGWT> list;
    private List<Men_menuTGWT> listChildren;
    private List<Men_menuTGWT> listSuper;
    private TreeMap<Integer, Men_menuTGWT> tree;
    private TreeMap<Integer, List<Men_menuTGWT>> treeListMenu;
    public boolean preechido;

    public void povoaMenuVinculado(Per_perfilTGWT per_perfilTGWT) {

        try {
            preechido = false;
            EasyAccessURL eaurl = new EasyAccessURL(this);
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("op", "consultMenuPerfil");
            param.put("per_perfilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
            //eaurl.accessJSonMapNoProgress(Constantes.URL + PAGE, param);
            eaurl.accessJSonMap(Constantes.URL + PAGE, param);
        } catch (Exception ex) {
        }


    }

    public void povoaMenuNaoVinculado(Per_perfilTGWT per_perfilTGWT) {
        try {
            preechido = false;
            EasyAccessURL eaurl = new EasyAccessURL(this);
            HashMap<String, String> param = new HashMap<String, String>();

            param.put("op", "consultMenuNaoPerfil");
            param.put("per_perfilT.per_nr_id", per_perfilTGWT.getPer_nr_id() + "");
            //eaurl.accessJSonMapNoProgress(Constantes.URL + PAGE, param);
            eaurl.accessJSonMap(Constantes.URL + PAGE, param);
        } catch (Exception ex) {
        }
    }

    public void read(JSONValue jsonValue) {
        try {
            setList((List<Men_menuTGWT>) new Vector());
            listSuper = new Vector();
            tree = new TreeMap<Integer, Men_menuTGWT>();
            treeListMenu = new TreeMap<Integer, List<Men_menuTGWT>>();
            JSONObject jsonObject;
            if (jsonValue != null && (jsonObject = jsonValue.isObject()) != null) {
                //Set<String> keys = jsonObject.keySet();
                //Window.alert(usuario);
                JSONArray resultado = jsonObject.get("resultado").isArray();
                for (int i = 1; i < resultado.size(); i++) {
                    Men_menuTGWT men_menuTGWT = new Men_menuTGWT();
                    JSONObject registro = resultado.get(i).isObject();
                    Integer men_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("men_nr_id").toString()));
                    men_menuTGWT.setMen_nr_id(men_nr_id);

                    Integer supermenu_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("supermenu_nr_id").toString()));
                    men_menuTGWT.setSupermenu_nr_id(supermenu_nr_id);

                    String men_tx_status = EasyContainer.clearAspas(registro.get("men_tx_status").toString());
                    men_menuTGWT.setMen_tx_status(men_tx_status);

                    String men_tx_nome = EasyContainer.clearAspas(registro.get("men_tx_nome").toString());
                    men_menuTGWT.setMen_tx_nome(men_tx_nome);

                    Integer men_nr_ordem = Integer.parseInt(EasyContainer.clearAspas(registro.get("men_nr_ordem").toString()));
                    men_menuTGWT.setMen_nr_ordem(men_nr_ordem);

                    String men_tx_url = EasyContainer.clearAspas(registro.get("men_tx_url").toString());
                    men_menuTGWT.setMen_tx_url(men_tx_url);

                    Integer sis_nr_id = Integer.parseInt(EasyContainer.clearAspas(registro.get("sis_nr_id").toString()));
                    men_menuTGWT.setSis_nr_id(sis_nr_id);

                    String men_tx_acao = EasyContainer.clearAspas(registro.get("men_tx_acao").toString());
                    men_menuTGWT.setMen_tx_acao(men_tx_acao);

                    String men_tx_icone = EasyContainer.clearAspas(registro.get("men_tx_icone").toString());
                    men_menuTGWT.setMen_tx_icone(men_tx_icone);
                    tree.put(men_nr_id, men_menuTGWT);

                    getList().add(men_menuTGWT);
                    if (supermenu_nr_id > 0) { 
                        if (tree.get(men_menuTGWT.getSupermenu_nr_id()) != null) {
                            men_menuTGWT.setMen_tx_super(tree.get(men_menuTGWT.getSupermenu_nr_id()).getMen_tx_nome());
                        }
                        if (treeListMenu.get(supermenu_nr_id) == null) {
                            List<Men_menuTGWT> listTemp = new Vector<Men_menuTGWT>();
                            listTemp.add(men_menuTGWT);
                            treeListMenu.put(supermenu_nr_id, listTemp);
                        } else {
                            treeListMenu.get(supermenu_nr_id).add(men_menuTGWT);
                        }
                    }else{
                        listSuper.add(men_menuTGWT);
                    }

                }
            }
        } catch (Exception e) {
        } finally {
            preechido = true;
        }
    }


    public BaseTreeGWT[] getArrayModelData(List<Men_menuTGWT> lisObjeto) {
        BaseTreeGWT[] baseArray = new BaseTreeGWT[lisObjeto.size()];
        List<BaseTreeGWT> lisbase = new Vector<BaseTreeGWT>();
        int pos = 0;
        for (Men_menuTGWT men_menuTGWT : lisObjeto) {
            Window.alert(men_menuTGWT.getMen_tx_nome());
            if (getTreeListMenu().get(men_menuTGWT.getMen_nr_id()) != null) {
                BaseTreeGWT baseTreeGWT = new BaseTreeGWT(men_menuTGWT.getMen_tx_nome(), getArrayModelData(getTreeListMenu().get(men_menuTGWT.getMen_nr_id())));
                baseArray[pos] = baseTreeGWT;
            } else {
                baseArray[pos] = new BaseTreeGWT(men_menuTGWT.getMen_tx_nome());
            }
            pos++;
        }
        return baseArray;
    }

    public BaseTreeGWT getListModelData() {
        BaseTreeGWT[] base = getArrayModelData(listSuper);
        Window.alert(base.toString());
        BaseTreeGWT root = new BaseTreeGWT("root");
        for (int i = 0; i < base.length; i++) {
            root.add((BaseTreeGWT) base[i]);
        }
        return root;
    }

    /**
     * @return the list
     */
    public List<Men_menuTGWT> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Men_menuTGWT> list) {
        this.list = list;
    }

    /**
     * @return the listChildren
     */
    public List<Men_menuTGWT> getListChildren() {
        return listChildren;
    }

    /**
     * @param listChildren the listChildren to set
     */
    public void setListChildren(List<Men_menuTGWT> listChildren) {
        this.listChildren = listChildren;
    }

    /**
     * @return the treeListMenu
     */
    public TreeMap<Integer, List<Men_menuTGWT>> getTreeListMenu() {
        return treeListMenu;
    }

    /**
     * @param treeListMenu the treeListMenu to set
     */
    public void setTreeListMenu(TreeMap<Integer, List<Men_menuTGWT>> treeListMenu) {
        this.treeListMenu = treeListMenu;
    }
}
