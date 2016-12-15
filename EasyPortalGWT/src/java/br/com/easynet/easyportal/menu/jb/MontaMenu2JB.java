/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.menu.jb;

import br.com.easynet.easylog.jb.INotLog;
import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.jb.INotSecurity;
import br.com.easynet.easyportal.menu.dao.IMen_menuDAO;
import br.com.easynet.easyportal.menu.dao.IMep_men_perDAO;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO;
import br.com.easynet.easyportal.menu.dao.Mep_men_perDAO;
import br.com.easynet.easyportal.menu.transfer.Men_menuT;
import br.com.easynet.easyportal.menu.transfer.Mep_men_perT;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author geoleite
 */
public class MontaMenu2JB extends EasySecurityJB implements INotSecurity, INotLog {

    private static final String MENU_MONTADO = "menu_montado";
    private StringBuffer sbMenu = new StringBuffer();
    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();
    private IMen_menuDAO menuDao;
    private TreeMap<Integer, Mep_men_perT> treeMenuPerfil = new TreeMap<Integer, Mep_men_perT>();

    public void subMenu(Men_menuT men_menuT, String barra) throws Exception {
        //["|Features","testlink.html", "", "", "", "", "", "", "", "", "", ],
        List<Men_menuT> subMenu = menuDao.getSubMenu(men_menuT);
        if (subMenu.size() > 0) {
            for (Men_menuT menT : subMenu) {
                if (treeMenuPerfil.containsKey(menT.getMen_nr_id())) {
                    sbMenu.append("['").append(barra).append(menT.getMen_tx_nome()).append("','").append(menT.getMen_tx_url()).append("', '', '', '', '', '', '', '', '', '' , ],");
                    subMenu(menT, barra + "|");
                }
            }
        }
    }

    public void subMenuAntigo(Men_menuT men_menuT, String barra) throws Exception {
        //["|Features","testlink.html", "", "", "", "", "", "", "", "", "", ],
        List<Men_menuT> subMenu = menuDao.getSubMenu(men_menuT);
        if (subMenu.size() > 0) {
            for (Men_menuT menT : subMenu) {
                sbMenu.append("['").append(barra).append(menT.getMen_tx_nome()).append("','").append(menT.getMen_tx_url()).append("', '', '', '', '', '', '', '', '', '' , ],");
                subMenu(menT, barra + "|");
            }

        }
    }

    private void loadMenuPerfil() throws Exception {
        List<Per_perfilT> listPer = getPerfilUser();
        IMep_men_perDAO mepDao = getMep_men_perDAO();
        List<Mep_men_perT> listMep = mepDao.getByPerfis(listPer);
        for (Mep_men_perT mep_men_perT : listMep) {
            treeMenuPerfil.put(mep_men_perT.getMen_nr_id(), mep_men_perT);
        }
    }

    public void pageLoad() {
        // ["Home","testlink.html", "", "", "", "", "0", "0", "", "", "", ]
        try {
            usu_usuarioT = getUsuarioLogado();
            // Verifica se o usuario existe
            if (usu_usuarioT != null) {
                String menu = "";
                if (getSession().getAttribute(MENU_MONTADO) != null) {
                    menu = (String) getSession().getAttribute(MENU_MONTADO);
                } else {
                    //String sql = "select men.men_nr_id, men.supermenu_nr_id, men.men_tx_nome from portal.pu_per_usu pu, portal.mep_men_per mep, portal.men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' order by men.men_nr_ordem";
                    //Object[] param = {usu_usuarioT.getUsu_nr_id()};
                    loadMenuPerfil();
                    menuDao = getMen_menuDAO();
                    List<Men_menuT> menuPrimario = menuDao.getMenuPrimario(usu_usuarioT);
                    for (Men_menuT men_menuT : menuPrimario) {
                        sbMenu.append("['").append(men_menuT.getMen_tx_nome()).append("','").append(men_menuT.getMen_tx_url()).append("',").append("'', '', '', '', '0', '0', '', '', '', ],");
                        subMenu(men_menuT, "|");
                    }

                    menu = sbMenu.toString().replaceAll("'", "\"");
                    getSession().setAttribute(MENU_MONTADO, menu);
                }
                out.print(menu);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
}
