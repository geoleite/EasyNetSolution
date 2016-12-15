/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.bl;

import br.com.easynet.easyportal.gwt.jb.ItemMenu;
import br.com.easynet.easyportal.menu.transfer.Men_menuT;
import br.com.easynet.easyportal.transfer.Sis_sistemaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author georgejunior
 */
public class MenMenuBL extends EasyPortalBusinessBase {

    public Men_menuT createMenu(String sistema, Usu_usuarioT usuT) {
        try {
            List<Men_menuT> menuPrimario = null;
            if (sistema != null && sistema.trim().length() > 0) {
                menuPrimario = getMen_menuDAO().getMenuPrimario(usuT, sistema);
            } else {
                menuPrimario = getMen_menuDAO().getMenuPrimario(usuT);
            }
            easyLogger.debug("Menu primario " + menuPrimario);
            TreeMap<Integer, Men_menuT> menuPerfilMap = loadMenusPerfil(sistema, usuT);
            // Perrcore cada menu trazendo seus itens e submenu
            Men_menuT menRoot = new Men_menuT();
            menRoot.setMen_nr_id(-1);
            menRoot.setMen_tx_nome("root");
            menRoot.setSubMenu(menuPrimario);
            //Consult todos os Sistemas pra aumentar performance
            TreeMap<Integer, Sis_sistemaT> mapSistema = getMapSistema();
            for (int i = 0; i < menuPrimario.size(); i++) {
                Men_menuT men_menuT = menuPrimario.get(i);

                menu(men_menuT, menuPerfilMap, mapSistema);
            }
            return menRoot;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }

    private void menu(Men_menuT menT, TreeMap<Integer, Men_menuT> menuPerfilMap, TreeMap<Integer, Sis_sistemaT> mapSistema) throws Exception {
        try {
            List<Men_menuT> subMenu = getMen_menuDAO().getSubMenu(menT);
            for (int i = 0; i < subMenu.size(); i++) {
                Men_menuT men_menuT = subMenu.get(i);
                //String nomeSistema = getNomeSistema(men_menuT.getSis_nr_id());
                Sis_sistemaT sisT = mapSistema.get(men_menuT.getSis_nr_id());
                String nomeSistema = "NAO_DEFINIDO";
                if (sisT != null) {
                    nomeSistema =  sisT.getSis_tx_nome();
                }
                 
                men_menuT.setSistema(nomeSistema);
                if (menuPerfilMap.containsKey(men_menuT.getMen_nr_id())) {
                    menu(men_menuT, menuPerfilMap, mapSistema);
                }
            }
            menT.setSubMenu(subMenu);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public TreeMap<Integer, Men_menuT> loadMenusPerfil(String sistema, Usu_usuarioT usuT) throws Exception {
        try {

            List<Men_menuT> list = null;
            //verifica se o sistema foi definido no parametro
            if (sistema != null && sistema.trim().length() > 0) {
                list = getMen_menuDAO().getAllUsuario(usuT, sistema);
            } else {
                list = getMen_menuDAO().getAllUsuario(usuT);
            }

            TreeMap<Integer, Men_menuT> menuPerfilMap = new TreeMap<Integer, Men_menuT>();
            for (int i = 0; i < list.size(); i++) {
                Men_menuT men_menuT = list.get(i);
                menuPerfilMap.put(men_menuT.getMen_nr_id(), men_menuT);
            }
            return menuPerfilMap;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }

    private String getNomeSistema(int sisNrId) {
        try {
            Sis_sistemaT sisT = new Sis_sistemaT();
            sisT.setSis_nr_id(sisNrId);
            List<Sis_sistemaT> list = getSis_sistemaDAO().getById(sisT);
            return list.size() > 0 ? list.get(0).getSis_tx_nome() : "";
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return "";
    }

    private TreeMap<Integer, Sis_sistemaT> getMapSistema() {
        TreeMap<Integer, Sis_sistemaT> mapSis = new TreeMap<Integer, Sis_sistemaT>();
        try {
            List<Sis_sistemaT> listSis = getSis_sistemaDAO().getAll();
            for (int i = 0; i < listSis.size(); i++) {
                Sis_sistemaT sisT = listSis.get(i);
                mapSis.put(sisT.getSis_nr_id(), sisT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return mapSis;
    }
}
