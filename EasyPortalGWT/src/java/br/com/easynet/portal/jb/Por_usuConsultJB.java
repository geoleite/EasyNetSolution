package br.com.easynet.portal.jb;

import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Por_usuConsultJB extends SystemBase {

    // Atributos e propriedades
    private Por_portalT por_portalT = new Por_portalT();
    private Por_usuT por_usuT = new Por_usuT();
    private List<Usu_usuarioT> listUsuario;
    private List<Usu_usuarioT> listNoUsuario;

    public void setPor_usuT(Por_usuT por_usuT) {
        this.por_usuT = por_usuT;
    }

    public Por_usuT getPor_usuT() {
        return por_usuT;
    }
    private List<Por_usuT> list;

    public List<Por_usuT> getList() {
        return list;
    }

    public void setList(List<Por_usuT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        por_portalT = findPor_portalT(por_portalT);
        consult();
    }

    public void add() throws Exception {
        try {
            por_usuT.setPor_nr_id(por_portalT.getPor_nr_id());
            getPor_usuDAO().insert(por_usuT);
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void rem() throws Exception {
        try {
            if (por_usuT.getUsu_nr_id() == 1) {
                setMsg("Este usuário não pode ser removido!");
                return;
            }            
            por_usuT.setPor_nr_id(por_portalT.getPor_nr_id());
            getPor_usuDAO().delete(por_usuT);
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void consult() throws Exception {
        try {
            listUsuario = getUsu_usuarioDAO().getUsuariosPortal(por_portalT);
            listNoUsuario = getUsu_usuarioDAO().getUsuariosNaoPortal(por_portalT);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            IPor_usuDAO por_usuDAO = getPor_usuDAO();
            por_usuDAO.delete(por_usuT);
            setMsg("Exclusão efetuada com sucesso!");
            por_usuT = new Por_usuT();
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
            String page = "por_usuInsert.jsp";// defina aqui a pagina que deve ser chamada  

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

    public Por_portalT getPor_portalT() {
        return por_portalT;
    }

    public void setPor_portalT(Por_portalT por_portalT) {
        this.por_portalT = por_portalT;
    }

    public List<Usu_usuarioT> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usu_usuarioT> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public List<Usu_usuarioT> getListNoUsuario() {
        return listNoUsuario;
    }

    public void setListNoUsuario(List<Usu_usuarioT> listNoUsuario) {
        this.listNoUsuario = listNoUsuario;
    }
}
