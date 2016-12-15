package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Pi_per_intInsertJB extends SystemBase {

    // Atributos e propriedades
    private Pi_per_intT pi_per_intT = new Pi_per_intT();

    public void setPi_per_intT(Pi_per_intT pi_per_intT) {
        this.pi_per_intT = pi_per_intT;
    }

    public Pi_per_intT getPi_per_intT() {
        return pi_per_intT;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        //
        consultInt_interface();

        consultPer_perfil();

    }

    // M?todos de Eventos
    public void insert() throws Exception {

        try {
            IPi_per_intDAO pi_per_intDAO = getPi_per_intDAO();
            pi_per_intDAO.insert(pi_per_intT);
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
    private List<Int_interfaceT> listint_interface;

    public List<Int_interfaceT> getListint_interface() {
        return listint_interface;
    }

    public void setListint_interface(List<Int_interfaceT> list) {
        this.listint_interface = list;
    }

    public void consultInt_interface() throws Exception {
        try {
            IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();
            listint_interface = int_interfaceDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
    private List<Per_perfilT> listper_perfil;

    public List<Per_perfilT> getListper_perfil() {
        return listper_perfil;
    }

    public void setListper_perfil(List<Per_perfilT> list) {
        this.listper_perfil = list;
    }

    public void consultPer_perfil() throws Exception {
        try {
            IPer_perfilDAO per_perfilDAO = getPer_perfilDAO();
            listper_perfil = per_perfilDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }

    public void clear() throws Exception {

        pi_per_intT = new Pi_per_intT();
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "pi_per_intConsult.jsp";// defina aqui a p?gina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }
}
