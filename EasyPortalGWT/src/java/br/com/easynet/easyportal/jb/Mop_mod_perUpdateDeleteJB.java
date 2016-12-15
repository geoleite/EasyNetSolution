package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.bl.*;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Mop_mod_perUpdateDeleteJB extends SystemBase {

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

    public void clear() throws Exception {

        mop_mod_perT = new Mop_mod_perT();
    }

    public void delete() throws Exception {
        try {
            getMop_mod_perDAO().delete(mop_mod_perT);
            setMsg("Exclusao efetuada com sucesso!");
        } catch (Exception e) {
            setMsg(ERROR, "Falha: ".concat(e.getMessage()));
        } finally {
            close();
        }
    }

    public void update() throws Exception {
        try {
            getMop_mod_perDAO().update(mop_mod_perT);
            setMsg("Alteracao efetuada com sucesso!");
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar alteracao!");
        } finally {
            close();
        }
    }

    //Method Download Image e montando se houver algum campo do tipo binario
    //|DOWNLOADIMAGE|
    public void findbyid() throws Exception {
        try {
            List<Mop_mod_perT> list = getMop_mod_perDAO().getByPK(mop_mod_perT);
            mop_mod_perT = list.size() > 0?list.get(0):null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        } finally {
            close();
        }
    }
}
