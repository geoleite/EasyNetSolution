package br.com.easynet.easyportal.menu.jb;

import br.com.easynet.easyportal.jb.SystemBase;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.menu.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;
import br.com.easynet.easyportal.transfer.Per_perfilT;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Men_menuConsultJB extends SystemBase {

    // Atributos e propriedades
    private Men_menuT men_menuT = new Men_menuT();
    

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
    }

    public void consult() throws Exception {
        try {
            IMen_menuDAO men_menuDAO = getMen_menuDAO();
            list = men_menuDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }
    

    public void delete() throws Exception {
        try {
            IMen_menuDAO men_menuDAO = getMen_menuDAO();
            men_menuDAO.delete(men_menuT);
            setMsg("Exclusão efetuada com sucesso!");
            men_menuT = new Men_menuT();
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar exclusão!");
        } finally {
            close();
        }
    }

    public void insert() throws Exception {
        // TODO Insert
        try {
            String page = "men_menuInsert.jsp";// defina aqui a pagina que deve ser chamada  

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

}
