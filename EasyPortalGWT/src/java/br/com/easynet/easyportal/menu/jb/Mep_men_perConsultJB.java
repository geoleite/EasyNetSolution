package br.com.easynet.easyportal.menu.jb;

import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.easyportal.jb.SystemBase;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.menu.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;
import br.com.easynet.easyportal.transfer.Per_perfilT;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Mep_men_perConsultJB extends EasySecurityJB  {

    // Atributos e propriedades
    private Per_perfilT per_perfilT = new Per_perfilT();
    private List<Men_menuT> listMenu;
    private List<Men_menuT> listNoMenu;
    private Mep_men_perT mep_men_perAddT = new Mep_men_perT();
    private Mep_men_perT mep_men_perRemT = new Mep_men_perT();

    private List<Mep_men_perT> list;

    public List<Mep_men_perT> getList() {
        return list;
    }

    public void setList(List<Mep_men_perT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        setPer_perfilT(findPerfil(getPer_perfilT()));
        consult();
    }

    public void add() throws Exception {
        try {
            mep_men_perAddT.setPer_nr_id(per_perfilT.getPer_nr_id());
            getMep_men_perDAO().insert(mep_men_perAddT);
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void rem() throws Exception {
        try {
            mep_men_perRemT.setPer_nr_id(per_perfilT.getPer_nr_id());
            getMep_men_perDAO().delete(mep_men_perRemT);
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
    
    public void consult() throws Exception {
        try {
            IMen_menuDAO men_menuDAO = getMen_menuDAO();
            setListMenu(men_menuDAO.getMenuPerfil(getPer_perfilT()));
            setListNoMenu(men_menuDAO.getMenuNaoPerfil(getPer_perfilT()));
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            IMep_men_perDAO mep_men_perDAO = getMep_men_perDAO();
            mep_men_perDAO.delete(mep_men_perAddT);
            setMsg("Exclusao efetuada com sucesso!");
            mep_men_perAddT = new Mep_men_perT();
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar exclusao!");
        } finally {
            close();
        }
    }

    public void insert() throws Exception {
        // TODO Insert
        try {
            String page = "mep_men_perInsert.jsp";// defina aqui a pagina que deve ser chamada  

            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "";// defina aqui a pagina que deve ser chamada  

            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public Per_perfilT getPer_perfilT() {
        return per_perfilT;
    }

    public void setPer_perfilT(Per_perfilT per_perfilT) {
        this.per_perfilT = per_perfilT;
    }

    public List<Men_menuT> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<Men_menuT> listMenu) {
        this.listMenu = listMenu;
    }

    public List<Men_menuT> getListNoMenu() {
        return listNoMenu;
    }

    public void setListNoMenu(List<Men_menuT> listNoMenu) {
        this.listNoMenu = listNoMenu;
    }

    public Mep_men_perT getMep_men_perAddT() {
        return mep_men_perAddT;
    }

    public void setMep_men_perAddT(Mep_men_perT mep_men_perAddT) {
        this.mep_men_perAddT = mep_men_perAddT;
    }

    public Mep_men_perT getMep_men_perRemT() {
        return mep_men_perRemT;
    }

    public void setMep_men_perRemT(Mep_men_perT mep_men_perRemT) {
        this.mep_men_perRemT = mep_men_perRemT;
    }
}
