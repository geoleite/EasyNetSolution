package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public class Pi_per_intDAO_Oracle extends ObjectDAO implements IPi_per_intDAO {

    public Pi_per_intDAO_Oracle(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Pi_per_intT pi_per_intT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into usr_portal.pi_per_int  ( int_nr_id, per_nr_id) values ( ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pi_per_intT.getInt_nr_id());
            pStmt.setObject(2, pi_per_intT.getPer_nr_id());
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

    public void update(Pi_per_intT pi_per_intT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update usr_portal.pi_per_int set - where  int_nr_id=? and per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pi_per_intT.getInt_nr_id());
            pStmt.setObject(2, pi_per_intT.getPer_nr_id());
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

    public void delete(Pi_per_intT pi_per_intT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from usr_portal.pi_per_int where  int_nr_id=? and per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pi_per_intT.getInt_nr_id());
            pStmt.setObject(2, pi_per_intT.getPer_nr_id());
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

    public List<Pi_per_intT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Pi_per_intT> objs = new Vector();
        while (rs.next()) {
            Pi_per_intT pi_per_intT = new Pi_per_intT();
            pi_per_intT.setInt_nr_id(rs.getInt("int_nr_id"));
            pi_per_intT.setPer_nr_id(rs.getInt("per_nr_id"));
            objs.add(pi_per_intT);
        }
        return objs;
    }

    public List<Pi_per_intT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.pi_per_int";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Pi_per_intT> list = resultSetToObjectTransfer(rs);
            return list;
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

    public List<Pi_per_intT> getByPK(Pi_per_intT pi_per_intT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.pi_per_int where  int_nr_id=? and per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pi_per_intT.getInt_nr_id());
            pStmt.setObject(2, pi_per_intT.getPer_nr_id());
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

    public List<Pi_per_intT> getByInt_nr_id(Pi_per_intT pi_per_intT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.pi_per_int where  int_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pi_per_intT.getInt_nr_id());
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

    public List<Pi_per_intT> getByPer_nr_id(Pi_per_intT pi_per_intT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.pi_per_int where  per_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pi_per_intT.getPer_nr_id());
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

    /** M todos FK */
    public List<Pi_per_intT> getByInt_interface(Pi_per_intT pi_per_intT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.pi_per_int where int_interface.int_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pi_per_intT.getInt_nr_id());
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
