/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.gwt.jb;

import br.com.easynet.criptografia.MD5;
import br.com.easynet.easylog.jb.INotLog;
import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.jb.INotSecurity;
import br.com.easynet.easyportal.menu.dao.IMen_menuDAO;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO;
import br.com.easynet.easyportal.menu.transfer.Men_menuT;
import br.com.easynet.easyportal.transfer.Sis_sistemaT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

/**
 *
 * @author geoleite
 */
public class MenuJB extends EasySecurityJB implements INotSecurity, INotLog {

    private String sistema;
    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();
    private static final String MENU_MONTADO = "menu_usuario_logado_GWT";
    private IMen_menuDAO menuDao;
    private ItemMenu menu;
    private TreeMap<Integer, Men_menuT> menuPerfilMap = new TreeMap<Integer, Men_menuT>();

    private void menu(Men_menuT menT, ItemMenu itemMenu) throws Exception {

        List<Men_menuT> subMenu = menuDao.getSubMenu(menT);
        List<ItemMenu> itens = new Vector<ItemMenu>();
        for (int i = 0; i < subMenu.size(); i++) {
            Men_menuT men_menuT = subMenu.get(i);
            if (menuPerfilMap.containsKey(men_menuT.getMen_nr_id())) {
                String nomeSistema = getNomeSistema(men_menuT.getSis_nr_id());
                ItemMenu item = new ItemMenu(men_menuT.getMen_tx_nome(), men_menuT.getMen_tx_acao(), men_menuT.getMen_tx_icone(), nomeSistema, men_menuT.getMen_tx_url());
                itens.add(item);
                menu(men_menuT, item);
            }
        }
        itemMenu.setSubMenu(itens);
    }

    /**
     * Carregando todos os menus que o usuário possui acesso
     * @throws Exception
     */
    private void loadMenusPerfil() throws Exception {
        List<Men_menuT> list = null;
        //verifica se o sistema foi definido no parametro
        if (sistema != null && sistema.trim().length() > 0) {
            list = menuDao.getAllUsuario(getUsu_usuarioT(), sistema);
        } else {
            list = menuDao.getAllUsuario(getUsu_usuarioT());
        }
        
        for (int i = 0; i < list.size(); i++) {
            Men_menuT men_menuT = list.get(i);
            menuPerfilMap.put(men_menuT.getMen_nr_id(), men_menuT);
        }
    }

    private boolean userCheck() {
        try {
            usu_usuarioT.setUsu_tx_senha(MD5.criptografar(usu_usuarioT.getUsu_tx_senha()));
            List<Usu_usuarioT> list = getUsu_usuarioDAO().getByAutentication(usu_usuarioT);
            if (list.size() > 0) {
                usu_usuarioT = list.get(0);
                return true;
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return false;
    }
    
    public void pageLoad() throws Exception {
        super.pageLoad();
        try {            
            easyLogger.debug(getSession().getId());
            if (getUsuarioLogado() != null) {
                setUsu_usuarioT(getUsuarioLogado());
            } else if (!userCheck()){
                usu_usuarioT = null;
            }
            String strMenu = "";
            setMenu(new ItemMenu("root", "", "", "", ""));
            if (getUsu_usuarioT() != null) {
                if (getSession().getAttribute(MENU_MONTADO) != null) {
                    menu = (ItemMenu) getSession().getAttribute(MENU_MONTADO);
                } else {
                    menuDao = getMen_menuDAO();

                    //String sql = "select men.men_nr_id, men.supermenu_nr_id, men.men_tx_nome from portal.pu_per_usu pu, portal.mep_men_per mep, portal.men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and  men.supermenu_nr_id=0 order by men.men_nr_ordem";
//                    String sql = "select men.men_nr_id, men.supermenu_nr_id, men.men_tx_nome, men.men_tx_acao, sis.sis_tx_nome from portal.pu_per_usu pu, portal.mep_men_per mep, portal.men_menu men, portal.sis_sistema sis where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and  men.supermenu_nr_id=0 and men.sis_nr_id=sis.sis_nr_id order by men.men_nr_ordem";
                    //String sql1 = "select  men.men_tx_url,men.men_nr_id, men.supermenu_nr_id, men.men_tx_nome, men.men_tx_acao, sis.sis_tx_nome from portal.pu_per_usu pu, portal.mep_men_per mep, portal.men_menu men, portal.sis_sistema sis where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and men.sis_nr_id=sis.sis_nr_id order by men.men_nr_ordem";
                    //Object[] param = {usu_usuarioT.getUsu_nr_id()};


                    loadMenusPerfil();
                    List<Men_menuT> menuPrimario = null;
                    if (sistema != null && sistema.trim().length() > 0) {
                        menuPrimario = menuDao.getMenuPrimario(getUsu_usuarioT(), sistema);
                    } else {
                        menuPrimario = menuDao.getMenuPrimario(getUsu_usuarioT());
                    }
                    
                    // Perrcore cada menu trazendo seus itens e submenu
                    List<ItemMenu> menuRaiz = new Vector<ItemMenu>();
                    getMenu().setSubMenu(menuRaiz);
                    for (int i = 0; i < menuPrimario.size(); i++) {
                        Men_menuT men_menuT = menuPrimario.get(i);
                        String nomeSistema = getNomeSistema(men_menuT.getSis_nr_id());
                        ItemMenu itemMenu = new ItemMenu(men_menuT.getMen_tx_nome(), men_menuT.getMen_tx_acao(), men_menuT.getMen_tx_icone(), nomeSistema, men_menuT.getMen_tx_url());
                        menuRaiz.add(itemMenu);
                        menu(men_menuT, itemMenu);

                    }
                    getSession().setAttribute(MENU_MONTADO, menu);
                }

            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            try {
                close();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Obtem o nome do sistema
     * @param sisNrId
     * @return
     * @throws java.lang.Exception
     */
    private String getNomeSistema(int sisNrId) throws Exception {
        Sis_sistemaT sisT = new Sis_sistemaT();
        sisT.setSis_nr_id(sisNrId);
        List<Sis_sistemaT> list = getSis_sistemaDAO().getById(sisT);

        return list.size() > 0 ? list.get(0).getSis_tx_nome() : "";
    }

    /**
     * @return the menu
     */
    public ItemMenu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(ItemMenu menu) {
        this.menu = menu;
    }

    /**
     * @return the usu_usuarioT
     */
    public Usu_usuarioT getUsu_usuarioT() {
        return usu_usuarioT;
    }

    /**
     * @param usu_usuarioT the usu_usuarioT to set
     */
    public void setUsu_usuarioT(Usu_usuarioT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
    }

    /**
     * @return the sistema
     */
    public String getSistema() {
        return sistema;
    }

    /**
     * @param sistema the sistema to set
     */
    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
}
