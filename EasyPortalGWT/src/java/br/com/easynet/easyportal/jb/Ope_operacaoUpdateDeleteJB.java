package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Ope_operacaoUpdateDeleteJB extends EasySecurityJB {

    // Atributos e propriedades
    private Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
    private Sis_sistemaT sis_sistemaT = new Sis_sistemaT();

    public void setOpe_operacaoT(Ope_operacaoT ope_operacaoT) {
        this.ope_operacaoT = ope_operacaoT;
    }

    public Ope_operacaoT getOpe_operacaoT() {
        return ope_operacaoT;
    }
    private List<Ope_operacaoT> list;

    public List<Ope_operacaoT> getList() {
        return list;
    }

    public void setList(List<Ope_operacaoT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        findbyidSistema();
    }

    public void clear() throws Exception {

        ope_operacaoT = new Ope_operacaoT();
    }

    public void findbyidSistema() throws Exception {
        try {
            ISis_sistemaDAO sisDao = getSis_sistemaDAO();
            getSis_sistemaT().setSis_nr_id(ope_operacaoT.getSis_nr_id());
            List<Sis_sistemaT> list = sisDao.getById(getSis_sistemaT());
            if (list.size() > 0) {
                setSis_sistemaT(list.get(0));
            }

        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            if (exist()) {
                IOpe_operacaoDAO ope_operacaoDAO = getOpe_operacaoDAO();
                ope_operacaoDAO.delete(ope_operacaoT);
                setMsg("Exclusão efetuada com sucesso!");
                consult();
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
            IOpe_operacaoDAO ope_operacaoDAO = getOpe_operacaoDAO();
            List<Ope_operacaoT> listTemp = ope_operacaoDAO.getById(ope_operacaoT);

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
                IOpe_operacaoDAO ope_operacaoDAO = getOpe_operacaoDAO();
                ope_operacaoDAO.update(ope_operacaoT);
                setMsg("Alteração efetuada com sucesso!");
            } else {
                setMsg("Error: Registro inexistente!");
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar alteração!");
        } finally {
            close();
        }
    }

    //Method Download Image   montando se houver algum campo do tipo binario
    //|DOWNLOADIMAGE| 
    public void findbyid() throws Exception {
        try {
            IOpe_operacaoDAO ope_operacaoDAO = getOpe_operacaoDAO();
            List<Ope_operacaoT> listTemp = ope_operacaoDAO.getById(ope_operacaoT);

            ope_operacaoT = listTemp.size() > 0 ? listTemp.get(0) : new Ope_operacaoT();

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
            String page = "ope_operacaoConsult.jsp?ope_operacaoT.sis_nr_id=" + 
                    ope_operacaoT.getSis_nr_id();// defina aqui a pagina que deve ser chamada  
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "ope_operacaoConsult.jsp";// defina aqui a pagina que deve ser chamada  
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public Sis_sistemaT getSis_sistemaT() {
        return sis_sistemaT;
    }

    public void setSis_sistemaT(Sis_sistemaT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
    }
}
