package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Sis_sistemaUpdateDeleteJB extends EasySecurityJB {

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
    }

    public void clear() throws Exception {

        sis_sistemaT = new Sis_sistemaT();
    }

    public void delete() throws Exception {
        try {
            if (exist()) {
                ISis_sistemaDAO sis_sistemaDAO = getSis_sistemaDAO();
                sis_sistemaDAO.delete(sis_sistemaT);
                setMsg("Exclusao efetuada com sucesso!");
                clear();
            } else {
                setMsg("Error: Registro inexistente!");
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar exclusao!");
        } finally {
            close();
        }
    }

    public boolean exist() throws Exception {
        try {
            ISis_sistemaDAO sis_sistemaDAO = getSis_sistemaDAO();
            List<Sis_sistemaT> listTemp = sis_sistemaDAO.getById(sis_sistemaT);

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
                ISis_sistemaDAO sis_sistemaDAO = getSis_sistemaDAO();
                sis_sistemaDAO.update(sis_sistemaT);
                setMsg("Alteracao efetuada com sucesso!");
                setRedirect("portal.jsp?urlCanalAtual=principalPage.jsp&urlCanal=sis_sistemaConsult.jsp");
            } else {
                setMsg("Error: Registro inexistente!");
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar alteracao!");
        } finally {
            close();
        }
    }

    //Method Download Image   montando se houver algum campo do tipo binario
    //|DOWNLOADIMAGE|
    public void findbyid() throws Exception {
        try {
            ISis_sistemaDAO sis_sistemaDAO = getSis_sistemaDAO();
            List<Sis_sistemaT> listTemp = sis_sistemaDAO.getById(sis_sistemaT);

            sis_sistemaT = listTemp.size() > 0 ? listTemp.get(0) : new Sis_sistemaT();

        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    /**
     * Volta para a pagina de consulta
     */
    public void consult() throws Exception {
        // TODO Consult
        try {
            String page = "sis_sistemaConsult.jsp";// defina aqui a pagina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "sis_sistemaConsult.jsp";// defina aqui a pagina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }
}
