package br.com.easynet.portal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Por_portalConsultJB extends SystemBase {

    // Atributos e propriedades
    private Por_portalT por_portalT = new Por_portalT();

    public void setPor_portalT(Por_portalT por_portalT) {
        this.por_portalT = por_portalT;
    }

    public Por_portalT getPor_portalT() {
        return por_portalT;
    }
    private List<Por_portalT> list;

    public List<Por_portalT> getList() {
        return list;
    }

    public void setList(List<Por_portalT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        consult();
    }

    public void consult() throws Exception {
        try {
            IPor_portalDAO por_portalDAO = getPor_portalDAO();
            list = por_portalDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            IPor_portalDAO por_portalDAO = getPor_portalDAO();
            por_portalDAO.delete(por_portalT);
            setMsg("Exclusão efetuada com sucesso!");
            por_portalT = new Por_portalT();
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
            String page = "portal.jsp?urlCanalAtual=por_portalConsult.jsp&urlCanal=por_portalInsert.jsp";// defina aqui a pagina que deve ser chamada  

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
