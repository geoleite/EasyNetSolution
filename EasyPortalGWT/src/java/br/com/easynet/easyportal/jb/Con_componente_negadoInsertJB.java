package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Con_componente_negadoInsertJB extends SystemBase {

    // Atributos e propriedades
    private Con_componente_negadoT con_componente_negadoT = new Con_componente_negadoT();

    public void setCon_componente_negadoT(Con_componente_negadoT con_componente_negadoT) {
        this.con_componente_negadoT = con_componente_negadoT;
    }

    public Con_componente_negadoT getCon_componente_negadoT() {
        return con_componente_negadoT;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        //
        consultPi_per_int();

    }

    // M?todos de Eventos
    public void insert() throws Exception {

        try {
            ICon_componente_negadoDAO con_componente_negadoDAO = getCon_componente_negadoDAO();
            con_componente_negadoT.setCon_nr_id(getIncComponente());
            con_componente_negadoDAO.insert(con_componente_negadoT);
            setMsg(INFO, "Cadastro efetuado com sucesso!");
            clear();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar cadastro!");
        } finally {
            close();
        }
    }
// Method de lookup
// 
    private List<Pi_per_intT> listpi_per_int;

    public List<Pi_per_intT> getListpi_per_int() {
        return listpi_per_int;
    }

    public void setListpi_per_int(List<Pi_per_intT> list) {
        this.listpi_per_int = list;
    }

    public void consultPi_per_int() throws Exception {
        try {
            IPi_per_intDAO pi_per_intDAO = getPi_per_intDAO();
            listpi_per_int = pi_per_intDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void clear() throws Exception {

        con_componente_negadoT = new Con_componente_negadoT();
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "con_componente_negadoConsult.jsp";// defina aqui a p?gina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }
}
