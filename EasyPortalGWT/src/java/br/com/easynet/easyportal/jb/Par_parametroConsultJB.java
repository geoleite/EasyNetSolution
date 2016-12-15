package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Par_parametroConsultJB extends SystemBase {

    // Atributos e propriedades
    private Par_parametroT par_parametroT = new Par_parametroT();
    private String nomeSistema;
    public void setPar_parametroT(Par_parametroT par_parametroT) {
        this.par_parametroT = par_parametroT;
    }

    public Par_parametroT getPar_parametroT() {
        return par_parametroT;
    }
    private List<Par_parametroT> list;

    public List<Par_parametroT> getList() {
        return list;
    }

    public void setList(List<Par_parametroT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    public void consult() throws Exception {
        try {
            IPar_parametroDAO par_parametroDAO = getPar_parametroDAO();
            list = par_parametroDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    private Sis_sistemaT getSistema() throws Exception {
        int sisNrId = 0;
        Sis_sistemaT sisT = new Sis_sistemaT();
        sisT.setSis_tx_nome(nomeSistema);
        return getSis_sistemaDAO().getBySis_tx_nome(sisT);
    }

    public void consultBySistema() throws Exception {
        try {
            Sis_sistemaT sisT = getSistema();
            if (sisT != null) {
                par_parametroT.setSis_nr_id(sisT.getSis_nr_id());
                list = getPar_parametroDAO().getBySis_nr_id(par_parametroT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void consultBySistemaParametro() throws Exception {
        try {
            Sis_sistemaT sisT = getSistema();
            if (sisT != null) {
                par_parametroT.setSis_nr_id(sisT.getSis_nr_id());
                list = getPar_parametroDAO().getBySistemaPar_tx_nome(par_parametroT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            IPar_parametroDAO par_parametroDAO = getPar_parametroDAO();
            par_parametroDAO.delete(par_parametroT);
            setMsg("Exclusao efetuada com sucesso!");
            par_parametroT = new Par_parametroT();
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar exclusao!");
        } finally {
            close();
        }
    }

    public void insert() throws Exception {
        // TODO Insert
        try {
            String page = "par_parametroInsert.jsp";// defina aqui a pagina que deve ser chamada
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

    /**
     * @return the nomeSistema
     */
    public String getNomeSistema() {
        return nomeSistema;
    }

    /**
     * @param nomeSistema the nomeSistema to set
     */
    public void setNomeSistema(String nomeSistema) {
        this.nomeSistema = nomeSistema;
    }
}
