/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.util;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import java.util.HashMap;

/**
 *
 * @author geoleite
 */
public class DefineFilterValues {

    /**
     * Define a lista de filtros com os valores da lista de dados, sendo que armazena
     * no filtro apenas os valores que não se repete.
     * @param columnName
     * @param list
     * @return
     */
    public static ListStore<ModelData> createFilter(String columnName, ListStore list) {
        ListStore<ModelData> listFilter = new ListStore<ModelData>();
        if (list != null && list.getCount() > 0) {
            HashMap<Object, Object> map = new HashMap<Object, Object>();
            for (int i = 0; i < list.getCount(); i++) {
                ModelData md = list.getAt(i);
                Object obj = md.get(columnName);
                if (!map.containsKey(obj)) {
                    map.put(obj, obj);
                    listFilter.add(createFilter(columnName, obj));
                }
            }
            
        }
        return listFilter;
    }

    private static ModelData createFilter(String nameColumn, Object valueColumn) {
        ModelData model = new BaseModelData();
        model.set(nameColumn, valueColumn);
        return model;
    }
}
