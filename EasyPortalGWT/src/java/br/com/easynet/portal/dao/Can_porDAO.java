package br.com.easynet.portal.dao;

import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public class Can_porDAO extends ObjectDAO implements ICan_porDAO{

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Can_porDAO(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Can_porT can_porT) throws Exception {
        try {
            String sql = "insert into canais.can_por  ( por_nr_id, can_nr_id, cp_nr_ordem) values ( ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_porT.getPor_nr_id());
            pStmt.setObject(2, can_porT.getCan_nr_id());
            pStmt.setObject(3, can_porT.getCp_nr_ordem());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public void update(Can_porT can_porT) throws Exception {
        try {
            String sql = "update canais.can_por set  cp_nr_ordem=?  where  por_nr_id=? and can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_porT.getCp_nr_ordem());
            pStmt.setObject(2, can_porT.getPor_nr_id());
            pStmt.setObject(3, can_porT.getCan_nr_id());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public void delete(Can_porT can_porT) throws Exception {
        try {
            String sql = "delete from canais.can_por where  por_nr_id=? and can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_porT.getPor_nr_id());
            pStmt.setObject(2, can_porT.getCan_nr_id());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<Can_porT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Can_porT> objs = new Vector();
        while (rs.next()) {
            Can_porT can_porT = new Can_porT();
            can_porT.setPor_nr_id(rs.getInt("por_nr_id"));
            can_porT.setCan_nr_id(rs.getInt("can_nr_id"));
            can_porT.setCp_nr_ordem(rs.getInt("cp_nr_ordem"));
            objs.add(can_porT);
        }
        return objs;
    }

    /**
     * Lista todos os canais de um Portal
     * @param cpT
     * @return
     * @throws java.lang.Exception
     */
    public List<Can_porT> getAllPortal(Can_porT cpT) throws Exception {
        try {
            String sql = "select * from canais.can_por where por_nr_id=? order by can_nr_id";
            pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, cpT.getPor_nr_id());
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<Can_porT> getById(Can_porT can_porT) throws Exception {
        try {
            String sql = "select * from canais.can_por where  por_nr_id=? and can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_porT.getPor_nr_id());
            pStmt.setObject(2, can_porT.getCan_nr_id());
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }

    public List<Can_porT> getByCp_nr_ordem(Can_porT can_porT) throws Exception {
        try {
            String sql = "select * from canais.can_por where  cp_nr_ordem = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_porT.getCp_nr_ordem());
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
        }

    }
}