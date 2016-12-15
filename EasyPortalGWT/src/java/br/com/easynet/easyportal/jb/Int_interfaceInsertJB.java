package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Int_interfaceInsertJB extends SystemBase {

    // Atributos e propriedades
    private Int_interfaceT int_interfaceT = new Int_interfaceT();

    public void setInt_interfaceT(Int_interfaceT int_interfaceT) {
        this.int_interfaceT = int_interfaceT;
    }

    public Int_interfaceT getInt_interfaceT() {
        return int_interfaceT;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        //
    }

    // M?todos de Eventos
    public void insert() throws Exception {

        try {
            IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();
            int_interfaceT.setInt_nr_id(getIncInterface());
            int_interfaceDAO.insert(int_interfaceT);
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
    public void clear() throws Exception {

        int_interfaceT = new Int_interfaceT();
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "int_interfaceConsult.jsp";// defina aqui a p?gina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }
}
