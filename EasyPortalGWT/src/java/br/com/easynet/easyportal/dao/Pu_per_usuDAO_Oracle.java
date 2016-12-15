package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public class Pu_per_usuDAO_Oracle extends ObjectDAO implements IPu_per_usuDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Pu_per_usuDAO_Oracle(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "insert into usr_portal.pu_per_usu  ( per_nr_id, usu_nr_id) values ( ? , ? )";
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

    public void update(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "update usr_portal.pu_per_usu set - where  per_nr_id=? and usu_nr_id=?";
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

    public void deleteByUsuario(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "delete from usr_portal.pu_per_usu where  usu_nr_id=?";
            PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, pu_per_usuT.getUsu_nr_id());
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
    public void delete(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "delete from usr_portal.pu_per_usu where  per_nr_id=? and usu_nr_id=?";
            PreparedStatement pStmt = con.prepareStatement(sql);
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

    public List<Pu_per_usuT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Pu_per_usuT> objs = new Vector();
        while (rs.next()) {
            Pu_per_usuT pu_per_usuT = new Pu_per_usuT();
            pu_per_usuT.setPer_nr_id(rs.getInt("per_nr_id"));
            pu_per_usuT.setUsu_nr_id(rs.getInt("usu_nr_id"));
            objs.add(pu_per_usuT);
        }
        return objs;
    }

    public List<Pu_per_usuT> getAll() throws Exception {
        try {
            String sql = "select * from usr_portal.pu_per_usu";
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

    public List<Pu_per_usuT> getById(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "select * from usr_portal.pu_per_usu where  per_nr_id=? and usu_nr_id=?";
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

    public List<Pu_per_usuT> getByPer_nr_id(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "select * from usr_portal.pu_per_usu where  per_nr_id = ? ";
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

    public List<Pu_per_usuT> getByUsu_nr_id(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "select * from usr_portal.pu_per_usu where  usu_nr_id = ? ";
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

    /** Many to One */
    public Per_perfilT getByPer_perfilT(Pu_per_usuT pu_per_usuT) throws Exception {
        IPer_perfilDAO dao = new Per_perfilDAO_Oracle(getDAOFactory());
        Per_perfilT per_perfilT = new Per_perfilT();
        per_perfilT.setPer_nr_id(pu_per_usuT.getPer_nr_id());
        List<Per_perfilT> list = dao.getById(per_perfilT);
        return list.size() > 0 ? list.get(0) : null;
    }

    /** Many to One */
    public Usu_usuarioT getByUsu_usuarioT(Pu_per_usuT pu_per_usuT) throws Exception {
        IUsu_usuarioDAO dao = new Usu_usuarioDAO_Oracle(getDAOFactory());
        Usu_usuarioT usu_usuarioT = new Usu_usuarioT();
        usu_usuarioT.setUsu_nr_id(pu_per_usuT.getUsu_nr_id());
        List<Usu_usuarioT> list = dao.getById(usu_usuarioT);
        return list.size() > 0 ? list.get(0) : null;
    }

    /** Metodos FK */
    public List<Pu_per_usuT> getByPer_perfil(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "select * from usr_portal.pu_per_usu where per_perfil.per_nr_id=?  ";
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
    public List<Pu_per_usuT> getByUsu_usuario(Pu_per_usuT pu_per_usuT) throws Exception {
        try {
            String sql = "select * from usr_portal.pu_per_usu where usu_usuario.usu_nr_id=?  ";
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