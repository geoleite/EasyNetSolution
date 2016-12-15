package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Usu_usuarioUpdateDeleteJB extends EasySecurityJB {

    // Atributos e propriedades
    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();

    public void setUsu_usuarioT(Usu_usuarioT usu_usuarioT) {
        this.usu_usuarioT = usu_usuarioT;
    }

    public Usu_usuarioT getUsu_usuarioT() {
        return usu_usuarioT;
    }
    private List<Usu_usuarioT> list;

    public List<Usu_usuarioT> getList() {
        return list;
    }

    public void setList(List<Usu_usuarioT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    public void clear() throws Exception {

        usu_usuarioT = new Usu_usuarioT();
    }

    public void delete() throws Exception {
        try {
            if (exist()) {
                IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
                usu_usuarioDAO.delete(usu_usuarioT);
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
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            List<Usu_usuarioT> listTemp = usu_usuarioDAO.getById(usu_usuarioT);

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
                IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
                usu_usuarioDAO.updateDados(usu_usuarioT);
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
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            List<Usu_usuarioT> listTemp = usu_usuarioDAO.getById(usu_usuarioT);

            usu_usuarioT = listTemp.size() > 0 ? listTemp.get(0) : new Usu_usuarioT();

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
            String page = "usu_usuarioConsult.jsp";// defina aqui a pagina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "usu_usuarioConsult.jsp";// defina aqui a pagina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }
}
