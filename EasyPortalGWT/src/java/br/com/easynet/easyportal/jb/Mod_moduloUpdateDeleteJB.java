package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.easynet.jb.EasyDownloadJB;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.bl.*;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Mod_moduloUpdateDeleteJB extends SystemBase {

    // Atributos e propriedades
    private Mod_moduloT mod_moduloT = new Mod_moduloT();

    public void setMod_moduloT(Mod_moduloT mod_moduloT) {
        this.mod_moduloT = mod_moduloT;
    }

    public Mod_moduloT getMod_moduloT() {
        return mod_moduloT;
    }
    private List<Mod_moduloT> list;

    public List<Mod_moduloT> getList() {
        return list;
    }

    public void setList(List<Mod_moduloT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
    }

    public void clear() throws Exception {

        mod_moduloT = new Mod_moduloT();
    }

    public void delete() throws Exception {
        try {
            getMod_moduloDAO().delete(mod_moduloT);
            setMsg("Exclusao efetuada com sucesso!");
        } catch (Exception e) {
            setMsg(ERROR, "Falha: ".concat(e.getMessage()));
        } finally {
            close();
        }
    }

    public void update() throws Exception {
        try {
            getMod_moduloDAO().update(mod_moduloT);
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
            List<Mod_moduloT> list = getMod_moduloDAO().getByPK(mod_moduloT);
            mod_moduloT = list.size()>0?list.get(0):null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        } finally {
            close();
        }
    }
}
