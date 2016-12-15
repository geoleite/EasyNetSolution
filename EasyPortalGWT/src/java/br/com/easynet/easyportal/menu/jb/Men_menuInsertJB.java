package br.com.easynet.easyportal.menu.jb;

import br.com.easynet.easyportal.jb.SystemBase;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.menu.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Sis_sistemaT;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Men_menuInsertJB extends SystemBase {

    // Atributos e propriedades
    private Men_menuT men_menuT = new Men_menuT();
    private List<Men_menuT> listMenus;
    private Sis_sistemaT sis_sistemaT = new Sis_sistemaT();
    private List<Sis_sistemaT> listSistemas;
    private Sis_sistemaT sis_sistemaTFiltro = new Sis_sistemaT();
    private List<Sis_sistemaT> listSistemasFiltro;

    public void setMen_menuT(Men_menuT men_menuT) {
        this.men_menuT = men_menuT;
    }

    public Men_menuT getMen_menuT() {
        return men_menuT;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        consult();
        consultSistemas();
    }
        

    public void consultSistemas() {
        try {
            setListSistemas(getSis_sistemaDAO().getAll());
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void consult() {
        try {
            men_menuT.setSis_nr_id(sis_sistemaTFiltro.getSis_nr_id());
            listMenus = getMen_menuDAO().getBySistema(men_menuT);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
    // Metodos de Eventos
    public void insert() throws Exception {

        try {
            IMen_menuDAO men_menuDAO = getMen_menuDAO();
            men_menuT.setSis_nr_id(sis_sistemaT.getSis_nr_id() );
            men_menuT.setMen_nr_id(getAutoIncremento(SISTEMA_EASYPORTAL, "MEN_MENU", "MEN_NR_ID"));
            men_menuDAO.insert(men_menuT);
            setMsg("Cadastro efetuado com sucesso!");
            clear();
             consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar cadastro!");
        } finally {
            close();
        }
    }

    public void clear() throws Exception {

        men_menuT = new Men_menuT();
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "men_menuConsult.jsp";// defina aqui a pagina que deve ser chamada  

            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public List<Men_menuT> getListMenus() {
        return listMenus;
    }

    public void setListMenus(List<Men_menuT> listMenus) {
        this.listMenus = listMenus;
    }

    public Sis_sistemaT getSis_sistemaT() {
        return sis_sistemaT;
    }

    public void setSis_sistemaT(Sis_sistemaT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
    }

    public List<Sis_sistemaT> getListSistemas() {
        return listSistemas;
    }

    public void setListSistemas(List<Sis_sistemaT> listSistemas) {
        this.listSistemas = listSistemas;
    }

    public Sis_sistemaT getSis_sistemaTFiltro() {
        return sis_sistemaTFiltro;
    }

    public void setSis_sistemaTFiltro(Sis_sistemaT sis_sistemaTFiltro) {
        this.sis_sistemaTFiltro = sis_sistemaTFiltro;
    }

    public List<Sis_sistemaT> getListSistemasFiltro() {
        return listSistemasFiltro;
    }

    public void setListSistemasFiltro(List<Sis_sistemaT> listSistemasFiltro) {
        this.listSistemasFiltro = listSistemasFiltro;
    }
}
