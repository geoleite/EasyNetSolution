package br.com.easynet.easyportal.jb;

import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Con_componente_negadoConsultJB extends EasySecurityJB implements INotSecurity {

    // Atributos e propriedades
    private Int_interfaceT int_interfaceT = new Int_interfaceT();
    private Con_componente_negadoT con_componente_negadoT = new Con_componente_negadoT();

    public void setCon_componente_negadoT(Con_componente_negadoT con_componente_negadoT) {
        this.con_componente_negadoT = con_componente_negadoT;
    }

    public Con_componente_negadoT getCon_componente_negadoT() {
        return con_componente_negadoT;
    }
    private List<Con_componente_negadoT> list;

    public List<Con_componente_negadoT> getList() {
        return list;
    }

    public void setList(List<Con_componente_negadoT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        //consult();
    }

    public void consultComponentesTela() throws Exception {
        try {
            List<Per_perfilT> listPer = getPerfilUser();
            StringBuffer sbPer = new StringBuffer();
            for (Per_perfilT per_perfilT : listPer) {
                sbPer.append(per_perfilT.getPer_nr_id()).append(", ");
            }
            sbPer.append("-");
            String perfisId = sbPer.toString().replaceAll(",-", "");
            //con_componente_negadoT.setPer_nr_id(per_perfilT.getPer_nr_id());
            list = getCon_componente_negadoDAO().getByInterfacePerfis(con_componente_negadoT, perfisId);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void consult() throws Exception {
        try {
            ICon_componente_negadoDAO con_componente_negadoDAO = getCon_componente_negadoDAO();
            list = con_componente_negadoDAO.getAll();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void consultarInterfacePerfil() {
        try {
            List<Int_interfaceT> listInt = getInt_interfaceDAO().getByInt_tx_nome(int_interfaceT);
            if (listInt.size() > 0) {
                con_componente_negadoT.setInt_nr_id(listInt.get(0).getInt_nr_id());
                List<Per_perfilT> listPer = getPerfilUser();
                StringBuffer param = new StringBuffer();
                if (listPer != null) {
                    for (Per_perfilT per_perfilT : listPer) {
                        param.append(per_perfilT.getPer_nr_id()).append(",");
                    }
                    param.append("-");
                } else {
                    param.append("66");
                }
                list = getCon_componente_negadoDAO().getByInterfacePerfis(con_componente_negadoT, param.toString().replaceAll(",-", ""));
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            ICon_componente_negadoDAO con_componente_negadoDAO = getCon_componente_negadoDAO();
            con_componente_negadoDAO.delete(con_componente_negadoT);
            setMsg("Exclusao efetuada com sucesso!");
            con_componente_negadoT = new Con_componente_negadoT();
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg(ERROR, "Falha ao realizar exclusao!");
        } finally {
            close();
        }
    }

    public void insert() throws Exception {
        // TODO Insert
        try {
            String page = "con_componente_negadoInsert.jsp";// defina aqui a p?gina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    public void cancel() throws Exception {
        // TODO Cancel
        try {
            String page = "";// defina aqui a p?gina que deve ser chamada
            getResponse().sendRedirect(page);
        } catch (Exception e) {
        }
    }

    /**
     * @return the int_interfaceT
     */
    public Int_interfaceT getInt_interfaceT() {
        return int_interfaceT;
    }

    /**
     * @param int_interfaceT the int_interfaceT to set
     */
    public void setInt_interfaceT(Int_interfaceT int_interfaceT) {
        this.int_interfaceT = int_interfaceT;
    }
}
