package br.com.easynet.easyportal.jb;

import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import java.sql.Timestamp;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Res_recall_senhaInsertJB extends EasySecurityJB implements INotSecurity {

    private Res_recall_senhaT res_recall_senhaT = new Res_recall_senhaT();

    public void setRes_recall_senhaT(Res_recall_senhaT res_recall_senhaT) {
        this.res_recall_senhaT = res_recall_senhaT;
    }

    public Res_recall_senhaT getRes_recall_senhaT() {
        return res_recall_senhaT;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    // Metodos de Eventos
    public void insert() throws Exception {
        try {
            res_recall_senhaT.setUsu_nr_id(getUsuarioLogado().getUsu_nr_id());
            res_recall_senhaT.setRes_dt_ultimoacesso(new Timestamp(System.currentTimeMillis()));
            res_recall_senhaT.setRes_nr_tentativas(0);
            getRes_recall_senhaDAO().insert(res_recall_senhaT);
            setMsg(INFO, "Cadastro efetuado com sucesso!");
        } catch (Exception e) {
            setMsg(ERROR, "Falha: ".concat(e.getMessage()));
        } finally {
            close();
        }
    }
}
