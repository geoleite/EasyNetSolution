package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public class Mep_men_perDAO_Mysql extends ObjectDAO implements IMep_men_perDAO {

    public Mep_men_perDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Mep_men_perTGWT mep_men_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into mep_men_per  ( per_nr_id, men_nr_id) values ( ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
            pStmt.setObject(2, mep_men_perT.getMen_nr_id());
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

    public void update(Mep_men_perTGWT mep_men_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update mep_men_per set - where  per_nr_id=? and men_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
            pStmt.setObject(2, mep_men_perT.getMen_nr_id());
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

    public void delete(Mep_men_perTGWT mep_men_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from mep_men_per where  per_nr_id=? and men_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
            pStmt.setObject(2, mep_men_perT.getMen_nr_id());
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

    public List<Mep_men_perTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Mep_men_perTGWT> objs = new Vector();
        while (rs.next()) {
            Mep_men_perTGWT mep_men_perT = new Mep_men_perTGWT();
            mep_men_perT.setPer_nr_id(rs.getInt("per_nr_id"));
            mep_men_perT.setMen_nr_id(rs.getInt("men_nr_id"));
            objs.add(mep_men_perT);
        }
        return objs;
    }

    public List<Mep_men_perTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from mep_men_per";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Mep_men_perTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Mep_men_perTGWT> getByPK(Mep_men_perTGWT mep_men_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from mep_men_per where  per_nr_id=? and men_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
            pStmt.setObject(2, mep_men_perT.getMen_nr_id());
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

    public List<Mep_men_perTGWT> getByPer_nr_id(Mep_men_perTGWT mep_men_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from mep_men_per where  per_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
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

    public List<Mep_men_perTGWT> getByMen_nr_id(Mep_men_perTGWT mep_men_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from mep_men_per where  men_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, mep_men_perT.getMen_nr_id());
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

    /** Metodos FK */
    public List<Mep_men_perTGWT> getByPer_perfil(Mep_men_perTGWT mep_men_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from mep_men_per where per_perfil.per_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
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

    /** Metodos FK */
    public List<Mep_men_perTGWT> getByMen_menu(Mep_men_perTGWT mep_men_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from mep_men_per where men_menu.men_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, mep_men_perT.getMen_nr_id());
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
