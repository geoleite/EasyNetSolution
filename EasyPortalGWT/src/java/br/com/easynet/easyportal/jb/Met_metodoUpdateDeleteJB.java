package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Met_metodoUpdateDeleteJB extends EasySecurityJB {

    // Atributos e propriedades
    private Met_metodoT met_metodoT = new Met_metodoT();
    private Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
    private Sis_sistemaT sis_sistemaT = new Sis_sistemaT();

    public void setMet_metodoT(Met_metodoT met_metodoT) {
        this.met_metodoT = met_metodoT;
    }

    public Met_metodoT getMet_metodoT() {
        return met_metodoT;
    }
    private List<Met_metodoT> list;

    public List<Met_metodoT> getList() {
        return list;
    }

    public void setList(List<Met_metodoT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        findbyid();
    }

    public void clear() throws Exception {

        met_metodoT = new Met_metodoT();
    }

    public void delete() throws Exception {
        try {
            if (exist()) {
                IMet_metodoDAO met_metodoDAO = getMet_metodoDAO();
                met_metodoDAO.delete(met_metodoT);
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
            IMet_metodoDAO met_metodoDAO = getMet_metodoDAO();
            List<Met_metodoT> listTemp = met_metodoDAO.getById(met_metodoT);

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
                IMet_metodoDAO met_metodoDAO = getMet_metodoDAO();
                met_metodoDAO.update(met_metodoT);
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
            IMet_metodoDAO met_metodoDAO = getMet_metodoDAO();
            List<Met_metodoT> listTemp = met_metodoDAO.getById(met_metodoT);

            met_metodoT = listTemp.size() > 0 ? listTemp.get(0) : new Met_metodoT();
            ope_operacaoT.setOpe_nr_id(met_metodoT.getOpe_nr_id());
            ope_operacaoT.setSis_nr_id(met_metodoT.getSis_nr_id());
            
            getSis_sistemaT().setSis_nr_id(getOpe_operacaoT().getSis_nr_id());
            List<Ope_operacaoT> list = getOpe_operacaoDAO().getById(getOpe_operacaoT());
            setOpe_operacaoT(list.size() > 0 ? list.get(0) : new Ope_operacaoT());
            List<Sis_sistemaT> listSis = getSis_sistemaDAO().getById(getSis_sistemaT());
            setSis_sistemaT(listSis.size() > 0 ? listSis.get(0) : new Sis_sistemaT());
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
            findbyid();
            String page = "met_metodoConsult.jsp?ope_operacaoT.sis_nr_id=" + ope_operacaoT.getSis_nr_id() + 
                    "&ope_operacaoT.ope_nr_id="+ ope_operacaoT.getOpe_nr_id();// defina aqui a pagina que deve ser chamada  
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "met_metodoConsult.jsp";// defina aqui a pagina que deve ser chamada  
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public Ope_operacaoT getOpe_operacaoT() {
        return ope_operacaoT;
    }

    public void setOpe_operacaoT(Ope_operacaoT ope_operacaoT) {
        this.ope_operacaoT = ope_operacaoT;
    }

    public Sis_sistemaT getSis_sistemaT() {
        return sis_sistemaT;
    }

    public void setSis_sistemaT(Sis_sistemaT sis_sistemaT) {
        this.sis_sistemaT = sis_sistemaT;
    }
}
