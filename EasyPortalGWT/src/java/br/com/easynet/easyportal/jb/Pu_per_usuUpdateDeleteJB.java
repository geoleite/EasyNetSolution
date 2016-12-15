package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Pu_per_usuUpdateDeleteJB extends SystemBase implements INotSecurity {

    // Atributos e propriedades
    private Pu_per_usuT pu_per_usuT = new Pu_per_usuT();

    public void setPu_per_usuT(Pu_per_usuT pu_per_usuT) {
        this.pu_per_usuT = pu_per_usuT;
    }

    public Pu_per_usuT getPu_per_usuT() {
        return pu_per_usuT;
    }
    private List<Pu_per_usuT> list;

    public List<Pu_per_usuT> getList() {
        return list;
    }

    public void setList(List<Pu_per_usuT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    public void clear() throws Exception {

        pu_per_usuT = new Pu_per_usuT();
    }

    public void deleteByUsuario() {
        try {
            IPu_per_usuDAO pu_per_usuDAO = getPu_per_usuDAO();
            pu_per_usuDAO.deleteByUsuario(pu_per_usuT);
            setMsg("Exclusao efetuada com sucesso!");
            clear();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar exclusao!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            if (exist()) {
                IPu_per_usuDAO pu_per_usuDAO = getPu_per_usuDAO();
                pu_per_usuDAO.delete(pu_per_usuT);
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
            IPu_per_usuDAO pu_per_usuDAO = getPu_per_usuDAO();
            List<Pu_per_usuT> listTemp = pu_per_usuDAO.getById(pu_per_usuT);

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
                IPu_per_usuDAO pu_per_usuDAO = getPu_per_usuDAO();
                pu_per_usuDAO.update(pu_per_usuT);
                setMsg("Alteracao efetuada com sucesso!");
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
            IPu_per_usuDAO pu_per_usuDAO = getPu_per_usuDAO();
            List<Pu_per_usuT> listTemp = pu_per_usuDAO.getById(pu_per_usuT);

            pu_per_usuT = listTemp.size() > 0 ? listTemp.get(0) : new Pu_per_usuT();

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
            String page = "pu_per_usuConsult.jsp";// defina aqui a pagina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "pu_per_usuConsult.jsp";// defina aqui a pagina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }
}
