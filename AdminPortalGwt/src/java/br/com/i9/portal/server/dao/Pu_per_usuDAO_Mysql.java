package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public class Pu_per_usuDAO_Mysql extends ObjectDAO implements IPu_per_usuDAO {

    public Pu_per_usuDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into pu_per_usu  ( per_nr_id, usu_nr_id) values ( ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getPer_nr_id());
            pStmt.setObject(2, pu_per_usuT.getUsu_nr_id());
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

    public void update(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update pu_per_usu set - where  per_nr_id=? and usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getPer_nr_id());
            pStmt.setObject(2, pu_per_usuT.getUsu_nr_id());
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

    public void delete(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from pu_per_usu where  per_nr_id=? and usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getPer_nr_id());
            pStmt.setObject(2, pu_per_usuT.getUsu_nr_id());
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

    public List<Pu_per_usuTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Pu_per_usuTGWT> objs = new Vector();
        while (rs.next()) {
            Pu_per_usuTGWT pu_per_usuT = new Pu_per_usuTGWT();
            pu_per_usuT.setPer_nr_id(rs.getInt("per_nr_id"));
            pu_per_usuT.setUsu_nr_id(rs.getInt("usu_nr_id"));
            objs.add(pu_per_usuT);
        }
        return objs;
    }

    public List<Pu_per_usuTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from pu_per_usu";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Pu_per_usuTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Pu_per_usuTGWT> getByPK(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from pu_per_usu where  per_nr_id=? and usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getPer_nr_id());
            pStmt.setObject(2, pu_per_usuT.getUsu_nr_id());
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

    public List<Pu_per_usuTGWT> getByPer_nr_id(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from pu_per_usu where  per_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getPer_nr_id());
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

    public List<Pu_per_usuTGWT> getByUsu_nr_id(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from pu_per_usu where  usu_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getUsu_nr_id());
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
    public List<Pu_per_usuTGWT> getByPer_perfil(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from pu_per_usu where per_perfil.per_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getPer_nr_id());
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
    public List<Pu_per_usuTGWT> getByUsu_usuario(Pu_per_usuTGWT pu_per_usuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from pu_per_usu where usu_usuario.usu_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getUsu_nr_id());
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
