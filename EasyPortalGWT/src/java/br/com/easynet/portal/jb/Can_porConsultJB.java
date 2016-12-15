package br.com.easynet.portal.jb;

import br.com.easynet.database.DataSet;
import java.util.List;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.DAOFactory;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.*;

/** Classe Criada Automaticamente pelo "EasyNet Generate JDragon" */
public class Can_porConsultJB extends SystemBase {

    // Atributos e propriedades
    private Por_portalT por_portalT = new Por_portalT();
    private Can_porT can_porT = new Can_porT();
    private List<Can_canalT> listCanal;
    private List<Can_canalT> listNoCanal;

    public void setCan_porT(Can_porT can_porT) {
        this.can_porT = can_porT;
    }

    public Can_porT getCan_porT() {
        return can_porT;
    }
    private List<Can_porT> list;

    public List<Can_porT> getList() {
        return list;
    }

    public void setList(List<Can_porT> list) {
        this.list = list;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        por_portalT = findPor_portalT(por_portalT);
        consult();
    }

    private int getMaiorOrdemCanal() throws Exception {
        String sql  = "select max(cp_nr_ordem) as cp_nr_ordem from canais.can_por where por_nr_id=?";
        Object[] param = {por_portalT.getPor_nr_id()};
        DataSet ds = getCan_porDAO().executeQuery(sql, param);
        if ( ds.size() > 0 && ds.getList().get(0).getColumn(0) != null)
            return (Integer)ds.getList().get(0).getColumn(0);
        return 0;
    }
    public void add() throws Exception {
        try {
            can_porT.setPor_nr_id(por_portalT.getPor_nr_id());
            can_porT.setCp_nr_ordem(getMaiorOrdemCanal() + 1);
            getCan_porDAO().insert(can_porT);
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
    
    public void rem() throws Exception {
        try {
            if (can_porT.getPor_nr_id() == 1 && can_porT.getCan_nr_id() == 1) {
                setMsg("Este canal não pode ser removido!");
                return;
            }
            
            can_porT.setPor_nr_id(por_portalT.getPor_nr_id());
            getCan_porDAO().delete(can_porT);  
            consult();
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
    }
    
    public void consult() throws Exception {
        try {
            setListCanal(getCan_canalDAO().getAllPortal(por_portalT));
            setListNoCanal(getCan_canalDAO().getAllNaoPortal(por_portalT));
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
    }

    public void delete() throws Exception {
        try {
            ICan_porDAO can_porDAO = getCan_porDAO();
            can_porDAO.delete(can_porT);
            setMsg("Exclusao efetuada com sucesso!");
            can_porT = new Can_porT();
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
            String page = "can_porInsert.jsp";// defina aqui a pagina que deve ser chamada  

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

    public Por_portalT getPor_portalT() {
        return por_portalT;
    }

    public void setPor_portalT(Por_portalT por_portalT) {
        this.por_portalT = por_portalT;
    }

    public List<Can_canalT> getListCanal() {
        return listCanal;
    }

    public void setListCanal(List<Can_canalT> listCanal) {
        this.listCanal = listCanal;
    }

    public List<Can_canalT> getListNoCanal() {
        return listNoCanal;
    }

    public void setListNoCanal(List<Can_canalT> listNoCanal) {
        this.listNoCanal = listNoCanal;
    }
}
