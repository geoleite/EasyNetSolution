/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.client;

import com.extjs.gxt.ui.client.data.Model;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author marcos
 */
public class MontarTreeGWT {

    private TreeMap<String, List<Model>> treeChildren;
    private List<Model> listSuper;
    private List<Model> listModel;
    private List list;
    private String id_super;
    private String id;
    private String display;

    public MontarTreeGWT() {
    }
    
    public MontarTreeGWT(List lista, String tx_id_super, String tx_id, String display){
        this.setList(lista);
        this.setDisplay(display);
        this.setId(tx_id);
        this.setId_super(tx_id_super);
    }

/**
 *
 * @param list: lista de Objetos a ser montado o tree;
 * @param id_super :campo que referencia o id do nível acima;
 * @param id: chave primarai do objeto;
 * @param display: nome do atributo a ser visualizado;
 * @return
 */
    public BaseTreeGWT getListModelData() {
        listModel = new Vector();
        listModel.addAll((Vector) list);
        povoaSuper(listModel, id_super, id, display);
        BaseTreeGWT[] base = getArrayModelData(listSuper);
        BaseTreeGWT root = new BaseTreeGWT("root");
        for (int i = 0; i < base.length; i++) {
            root.add((BaseTreeGWT) base[i]);
        }
        return root;
    }

    private void povoaSuper(List<Model> list, String tx_id_super, String id, String display) {
        try {
            treeChildren = new TreeMap<String, List<Model>>();
            listSuper = new Vector<Model>();
            for (Model model : list) {
                String idmaster = model.get(tx_id_super).toString();
                if (idmaster == null || idmaster.trim().length() == 0 || Integer.parseInt(idmaster) == 0) {
                    listSuper.add(model);
                } else if (Integer.parseInt(idmaster) > 0) {
                    if (treeChildren.get(idmaster) == null) {
                        List<Model> ls = new Vector<Model>();
                        treeChildren.put(idmaster, ls);
                    } else {
                        treeChildren.get(idmaster).add(model);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //easyLogger.error("Erro", e);
        }
    }

    private BaseTreeGWT[] getArrayModelData(List<Model> lisObjeto) {
        BaseTreeGWT[] baseArray = new BaseTreeGWT[lisObjeto.size()];
        List<Model> lisbase = new Vector<Model>();
        int pos = 0;
        for (Model model : lisObjeto) {
            if (treeChildren.get(model.get(id).toString()) != null) {
                BaseTreeGWT baseTreeGWT = new BaseTreeGWT(model.get(display).toString(), getArrayModelData(treeChildren.get(model.get(id).toString())));
                baseArray[pos] = baseTreeGWT;
            } else {
                baseArray[pos] = new BaseTreeGWT(model.get(display).toString());
            }
            pos++;
        }
        return baseArray;
    }

    /**
     * @return the list
     */
    public List<Object> getList() {
        return list;
    }

    /**
     * @param list : lista dos objetos a serem povoados no tree.
     */
    public void setList(List<Object> list) {
        this.list = list;
    }

    /**
     * @return the id_super
     */
    public String getId_super() {
        return id_super;
    }

    /**
     * @param id_super : nome do atributo referencia o id do nível superior
     */
    public void setId_super(String id_super) {
        this.id_super = id_super;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id: nome do atributo que referencia a chave primaria
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the display
     */
    public String getDisplay() {
        return display;
    }

    /**
     * @param display :nome do atributo a ser visualizado
     */
    public void setDisplay(String display) {
        this.display = display;
    }
}
