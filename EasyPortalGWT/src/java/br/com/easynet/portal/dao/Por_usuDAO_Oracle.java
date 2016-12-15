package br.com.easynet.portal.dao;

import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public class Por_usuDAO_Oracle extends ObjectDAO implements IPor_usuDAO{

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Por_usuDAO_Oracle(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Por_usuT por_usuT) throws Exception {
        try {
            String sql = "insert into usr_portal.por_usu  ( usu_nr_id, por_nr_id) values ( ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, por_usuT.getUsu_nr_id());
            pStmt.setObject(2, por_usuT.getPor_nr_id());
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

    public void update(Por_usuT por_usuT) throws Exception {
        try {
            String sql = "update usr_portal.por_usu set - where  usu_nr_id=? and por_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, por_usuT.getUsu_nr_id());
            pStmt.setObject(2, por_usuT.getPor_nr_id());
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

    public void delete(Por_usuT por_usuT) throws Exception {
        String sql = "delete from usr_portal.por_usu where  usu_nr_id=? and por_nr_id=?";
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setObject(1, por_usuT.getUsu_nr_id());
        pStmt.setObject(2, por_usuT.getPor_nr_id());
        pStmt.execute();
    }

    public List<Por_usuT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Por_usuT> objs = new Vector();
        while (rs.next()) {
            Por_usuT por_usuT = new Por_usuT();
            por_usuT.setUsu_nr_id(rs.getInt("usu_nr_id"));
            por_usuT.setPor_nr_id(rs.getInt("por_nr_id"));
            objs.add(por_usuT);
        }
        return objs;
    }

    public List<Por_usuT> getAll() throws Exception {
        try {
            String sql = "select * from usr_portal.por_usu";
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

    public List<Por_usuT> getById(Por_usuT por_usuT) throws Exception {
        try {
            String sql = "select * from usr_portal.por_usu where  usu_nr_id=? and por_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, por_usuT.getUsu_nr_id());
            pStmt.setObject(2, por_usuT.getPor_nr_id());
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