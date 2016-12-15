package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Per_perfilConsultJB extends EasySecurityJB {

    // Atributos e propriedades
    private Per_perfilT per_perfilT = new Per_perfilT();
    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();

    public void setPer_perfilT(Per_perfilT per_perfilT) {
        this.per_perfilT = per_perfilT;
    }

    public Per_perfilT getPer_perfilT() {
        return per_perfilT;
    }
    private List<Per_perfilT> list;

    public List<Per_perfilT> getList() {
        return list;
    }

    public void setList(List<Per_perfilT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    public void consult() throws Exception {
        try {
            IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
            list = per_perfilDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void consultPerfisUsuario() throws Exception {
        try {
            IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
            list = per_perfilDAO.getByUsuario(usu_usuarioT);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
            per_perfilDAO.delete(per_perfilT);
            setMsg("Exclusao efetuada com sucesso!");
            per_perfilT = new Per_perfilT();
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
            String page = "per_perfilInsert.jsp";// defina aqui a pagina que deve ser chamada
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
     * @return the usu_usuarioT
     */
    public Usu_usuarioT getUsu_usuarioT() {
        return usu_usuarioT;
    }

    /**
     * @param usu_usuarioT the usu_usuarioT to set
     */
    public void setUsu_usuarioT(Usu_usuarioT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
    }
}
