package br.com.easynet.easyportal.menu.jb;

import br.com.easynet.easyportal.jb.SystemBase;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.menu.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;
import br.com.easynet.easyportal.transfer.Sis_sistemaT;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Men_menuUpdateDeleteJB extends SystemBase {

    // Atributos e propriedades
    private List<Men_menuT> listMenus;
    private Men_menuT men_menuT = new Men_menuT();
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
    private List<Men_menuT> list;

    public List<Men_menuT> getList() {
        return list;
    }

    public void setList(List<Men_menuT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        consult();
        consultSistemas();
        men_menuT.setSis_nr_id(sis_sistemaT.getSis_nr_id());
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

//            setListMenus(getMen_menuDAO().getAllExceto(men_menuT));
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void clear() throws Exception {

        setMen_menuT(new Men_menuT());
    }

    public void delete() throws Exception {
        try {
            if (exist()) {
                IMen_menuDAO men_menuDAO = getMen_menuDAO();
                men_menuDAO.delete(getMen_menuT());
                setMsg("Exclusão efetuada com sucesso!");
                clear();
            } else {
                setMsg("Error: Registro inexistente!");
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar exclusão!");
        } finally {
            close();
        }
    }

    public boolean exist() throws Exception {
        try {
            IMen_menuDAO men_menuDAO = getMen_menuDAO();
            List<Men_menuT> listTemp = men_menuDAO.getById(getMen_menuT());

            return listTemp.size() > 0;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
        return false;

    }

    public void update() throws Exception {
        try {
            if (exist()) {
                IMen_menuDAO men_menuDAO = getMen_menuDAO();
                men_menuDAO.update(getMen_menuT());
                setMsg("Alteracão efetuada com sucesso!");
            } else {
                setMsg("Error: Registro inexistente!");
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar alteracão!");
        } finally {
            close();
        }
    }

    //Method Download Image   montando se houver algum campo do tipo binario
    //|DOWNLOADIMAGE| 
    public void findbyid() throws Exception {
        try {
            IMen_menuDAO men_menuDAO = getMen_menuDAO();
            List<Men_menuT> listTemp = men_menuDAO.getById(getMen_menuT());

            setMen_menuT(listTemp.size() > 0 ? listTemp.get(0) : new Men_menuT());

        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
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
