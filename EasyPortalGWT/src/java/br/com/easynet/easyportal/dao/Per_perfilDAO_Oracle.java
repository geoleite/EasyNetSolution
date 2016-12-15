package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public class Per_perfilDAO_Oracle extends ObjectDAO implements IPer_perfilDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Per_perfilDAO_Oracle(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "insert into usr_portal.per_perfil  ( per_tx_nome, per_tx_status, per_tx_class) values ( ? , ?, ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, per_perfilT.getPer_tx_nome());
            pStmt.setObject(2, per_perfilT.getPer_tx_status());
            pStmt.setObject(3, per_perfilT.getPer_tx_class());
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

    public void update(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "update usr_portal.per_perfil set  per_tx_nome=?, per_tx_status=?, per_tx_class=?  where  per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, per_perfilT.getPer_tx_nome());
            pStmt.setObject(2, per_perfilT.getPer_tx_status());
            pStmt.setObject(3, per_perfilT.getPer_tx_class());
            pStmt.setObject(4, per_perfilT.getPer_nr_id());
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

    public void delete(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "delete from usr_portal.per_perfil where  per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, per_perfilT.getPer_nr_id());
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

    public List<Per_perfilT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Per_perfilT> objs = new Vector();
        while (rs.next()) {
            Per_perfilT per_perfilT = new Per_perfilT();
            per_perfilT.setPer_nr_id(rs.getInt("per_nr_id"));
            per_perfilT.setPer_tx_nome(rs.getString("per_tx_nome"));
            per_perfilT.setPer_tx_status(rs.getString("per_tx_status"));
            per_perfilT.setPer_tx_class(rs.getString("per_tx_class"));
            objs.add(per_perfilT);
        }
        return objs;
    }

    public List<Per_perfilT> getAll() throws Exception {
        try {
            String sql = "select * from usr_portal.per_perfil";
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

    public List<Per_perfilT> getById(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from usr_portal.per_perfil where  per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, per_perfilT.getPer_nr_id());
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

    public List<Per_perfilT> getByPer_nr_id(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from usr_portal.per_perfil where  per_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, per_perfilT.getPer_nr_id());
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

    public List<Per_perfilT> getByPer_tx_nome(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from usr_portal.per_perfil where  Upper(per_tx_nome) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + per_perfilT.getPer_tx_nome() + '%');
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

    public List<Per_perfilT> getByPer_tx_status(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from usr_portal.per_perfil where  Upper(per_tx_status) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + per_perfilT.getPer_tx_status() + '%');
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

    public List<Per_perfilT> getByUsuario(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select per.* from usr_portal.per_perfil per, usr_portal.pu_per_usu pu where pu.usu_nr_id=? and pu.per_nr_id=per.per_nr_id order by per.per_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_nr_id());
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

    /** One To Many */
    public List<Op_ope_perT> getByOp_ope_perTs(Per_perfilT per_perfilT) throws Exception {
        IOp_ope_perDAO dao = new Op_ope_perDAO_Oracle(getDAOFactory());
        Op_ope_perT op_ope_perT = new Op_ope_perT();
        op_ope_perT.setPer_nr_id(per_perfilT.getPer_nr_id());
        List<Op_ope_perT> list = dao.getByPer_perfil(op_ope_perT);
        return list;
    }

    /** One To Many */
    public List<Pu_per_usuT> getByPu_per_usuTs(Per_perfilT per_perfilT) throws Exception {
        IPu_per_usuDAO dao = new Pu_per_usuDAO_Oracle(getDAOFactory());
        Pu_per_usuT pu_per_usuT = new Pu_per_usuT();
        pu_per_usuT.setPer_nr_id(per_perfilT.getPer_nr_id());
        List<Pu_per_usuT> list = dao.getByPer_perfil(pu_per_usuT);
        return list;
    }
}