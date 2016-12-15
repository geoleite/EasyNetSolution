/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.i9.portal.client;

import br.com.i9.portal.client.portal.portal.transfer.Men_menuTGWT;
import java.util.List;

/**
 *
 * @author geoleite
 */
public class Util {
    public static int getCodigoSetorRaiz(List<Men_menuTGWT> listSet) {
        int codRaiz = Integer.MAX_VALUE;
        for (int i = 0; i < listSet.size(); i++) {
            Men_menuTGWT menT = listSet.get(i);
            if (codRaiz > menT.getSupermenu_nr_id()) {
                codRaiz = menT.getSupermenu_nr_id();
            }
        }
        return codRaiz;
    }
}
