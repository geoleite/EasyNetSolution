package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Sis_sistemaConsultJB extends EasySecurityJB {

    // Atributos e propriedades
    private Sis_sistemaT sis_sistemaT = new Sis_sistemaT();

    public void setSis_sistemaT(Sis_sistemaT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
    }

    public Sis_sistemaT getSis_sistemaT() {
        return sis_sistemaT;
    }
    private List<Sis_sistemaT> list;

    public List<Sis_sistemaT> getList() {
        return list;
    }

    public void setList(List<Sis_sistemaT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        //if (!isPostBack()) {
        consult();
        //}
    }

    public void consult() throws Exception {
        try {
            ISis_sistemaDAO sis_sistemaDAO = getSis_sistemaDAO();
            list = sis_sistemaDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void consultAtivos() throws Exception {
        try {
            ISis_sistemaDAO sis_sistemaDAO = getSis_sistemaDAO();
            list = sis_sistemaDAO.getAllAtivos();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            ISis_sistemaDAO sis_sistemaDAO = getSis_sistemaDAO();
            sis_sistemaDAO.delete(sis_sistemaT);
            setMsg("Exclusao efetuada com sucesso!");
            sis_sistemaT = new Sis_sistemaT();
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
            String page = "sis_sistemaInsert.jsp";// defina aqui a pagina que deve ser chamada  
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
