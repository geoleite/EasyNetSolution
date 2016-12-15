package br.com.easynet.portal.dao;

import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public class Usu_por_canDAO extends ObjectDAO implements IUsu_por_canDAO{

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Usu_por_canDAO(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Usu_por_canT usu_por_canT) throws Exception {
        try {
            String sql = "insert into canais.usu_por_can  ( usu_nr_id, por_nr_id, can_nr_id, upc_tx_status, upc_nr_ordem) values ( ? , ? , ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_por_canT.getUsu_nr_id());
            pStmt.setObject(2, usu_por_canT.getPor_nr_id());
            pStmt.setObject(3, usu_por_canT.getCan_nr_id());
            pStmt.setObject(4, usu_por_canT.getUpc_tx_status());
            pStmt.setObject(5, usu_por_canT.getUpc_nr_ordem());
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

    public void update(Usu_por_canT usu_por_canT) throws Exception {
        try {
            String sql = "update canais.usu_por_can set  upc_tx_status=?, upc_nr_ordem=?  where  usu_nr_id=? and por_nr_id=? and can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_por_canT.getUpc_tx_status());
            pStmt.setObject(2, usu_por_canT.getUpc_nr_ordem());
            pStmt.setObject(3, usu_por_canT.getUsu_nr_id());
            pStmt.setObject(4, usu_por_canT.getPor_nr_id());
            pStmt.setObject(5, usu_por_canT.getCan_nr_id());
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

    public void delete(Usu_por_canT usu_por_canT) throws Exception {
        try {
            String sql = "delete from canais.usu_por_can where  usu_nr_id=? and por_nr_id=? and can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_por_canT.getUsu_nr_id());
            pStmt.setObject(2, usu_por_canT.getPor_nr_id());
            pStmt.setObject(3, usu_por_canT.getCan_nr_id());
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

    public List<Usu_por_canT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Usu_por_canT> objs = new Vector();
        while (rs.next()) {
            Usu_por_canT usu_por_canT = new Usu_por_canT();
            usu_por_canT.setUsu_nr_id(rs.getInt("usu_nr_id"));
            usu_por_canT.setPor_nr_id(rs.getInt("por_nr_id"));
            usu_por_canT.setCan_nr_id(rs.getInt("can_nr_id"));
            usu_por_canT.setUpc_tx_status(rs.getString("upc_tx_status"));
            usu_por_canT.setUpc_nr_ordem(rs.getInt("upc_nr_ordem"));
            objs.add(usu_por_canT);
        }
        return objs;
    }

    public List<Usu_por_canT> getAll() throws Exception {
        try {
            String sql = "select * from canais.usu_por_can";
            pStmt = con.prepareStatement(sql);
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

    public List<Usu_por_canT> getById(Usu_por_canT usu_por_canT) throws Exception {
        try {
            String sql = "select * from canais.usu_por_can where  usu_nr_id=? and por_nr_id=? and can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_por_canT.getUsu_nr_id());
            pStmt.setObject(2, usu_por_canT.getPor_nr_id());
            pStmt.setObject(3, usu_por_canT.getCan_nr_id());
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

    public List<Usu_por_canT> getByUpc_tx_status(Usu_por_canT usu_por_canT) throws Exception {
        try {
            String sql = "select * from canais.usu_por_can where  Upper(upc_tx_status) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + usu_por_canT.getUpc_tx_status() + '%');
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

    public List<Usu_por_canT> getByUpc_nr_ordem(Usu_por_canT usu_por_canT) throws Exception {
        try {
            String sql = "select * from canais.usu_por_can where  upc_nr_ordem = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_por_canT.getUpc_nr_ordem());
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