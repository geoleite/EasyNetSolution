package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.bl.*;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Mop_mod_perConsultJB extends SystemBase {

    // Atributos e propriedades
    private Mop_mod_perT mop_mod_perT = new Mop_mod_perT();

    public void setMop_mod_perT(Mop_mod_perT mop_mod_perT) {
        this.mop_mod_perT = mop_mod_perT;
    }

    public Mop_mod_perT getMop_mod_perT() {
        return mop_mod_perT;
    }
    private List<Mop_mod_perT> list;

    public List<Mop_mod_perT> getList() {
        return list;
    }

    public void setList(List<Mop_mod_perT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    public void consult() throws Exception {
        try {
            list = getMop_mod_perDAO().getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        }
    }

    public void delete() throws Exception {
        try {
            getMop_mod_perDAO().delete(mop_mod_perT);
            setMsg("Exclusao efetuada com sucesso!");
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar exclusao!");
        } finally {
            close();
        }
    }
}
