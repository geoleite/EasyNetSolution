package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Usu_usuarioConsultJB extends EasySecurityJB {

    // Atributos e propriedades
    private Usu_usuarioT usu_usuarioT = new Usu_usuarioT();
    private String tipo, texto;

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

    public void consult() throws Exception {
        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
//      if ("N".equals(getTipo())) {
//        usu_usuarioT.setUsu_tx_nome(getTexto());
//        list = usu_usuarioDAO.getByUsu_tx_nome(usu_usuarioT);
//      } else {
//        usu_usuarioT.setUsu_tx_login(getTexto());
//        list = usu_usuarioDAO.getByUsu_tx_login(usu_usuarioT);
//      }
            usu_usuarioT.setUsu_tx_login(getTexto());
            list = usu_usuarioDAO.getByNomeLogin(usu_usuarioT);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void consultByNomeLogin() throws Exception {
        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            list = usu_usuarioDAO.getByNomeLogin(usu_usuarioT);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            IUsu_usuarioDAO usu_usuarioDAO = getUsu_usuarioDAO();
            usu_usuarioDAO.delete(usu_usuarioT);
            setMsg("Exclusao efetuada com sucesso!");
            usu_usuarioT = new Usu_usuarioT();
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
            String page = "usu_usuarioInsert.jsp";// defina aqui a pagina que deve ser chamada
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
