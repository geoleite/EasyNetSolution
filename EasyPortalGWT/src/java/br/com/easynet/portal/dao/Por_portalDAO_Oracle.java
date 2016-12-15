package br.com.easynet.portal.dao;

import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public class Por_portalDAO_Oracle extends ObjectDAO implements IPor_portalDAO{

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Por_portalDAO_Oracle(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Por_portalT por_portalT) throws Exception {
        try {
            String sql = "insert into usr_portal.por_portal  ( por_tx_nome, por_tx_status, por_nr_colunas) values ( ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, por_portalT.getPor_tx_nome());
            pStmt.setObject(2, por_portalT.getPor_tx_status());
            pStmt.setObject(3, por_portalT.getPor_nr_colunas());
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

    public void update(Por_portalT por_portalT) throws Exception {
        try {
            String sql = "update usr_portal.por_portal set  por_tx_nome=?, por_tx_status=?, por_nr_colunas=?  where  por_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, por_portalT.getPor_tx_nome());
            pStmt.setObject(2, por_portalT.getPor_tx_status());
            pStmt.setObject(3, por_portalT.getPor_nr_colunas());
            pStmt.setObject(4, por_portalT.getPor_nr_id());
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

    public void delete(Por_portalT por_portalT) throws Exception {
        try {
            String sql = "delete from usr_portal.por_portal where  por_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, por_portalT.getPor_nr_id());
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

    public List<Por_portalT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Por_portalT> objs = new Vector();
        while (rs.next()) {
            Por_portalT por_portalT = new Por_portalT();
            por_portalT.setPor_nr_id(rs.getInt("por_nr_id"));
            por_portalT.setPor_tx_nome(rs.getString("por_tx_nome"));
            por_portalT.setPor_tx_status(rs.getString("por_tx_status"));
            por_portalT.setPor_nr_colunas(rs.getInt("por_nr_colunas"));
            objs.add(por_portalT);
        }
        return objs;
    }

    public List<Por_portalT> getAll() throws Exception {
        try {
            String sql = "select * from usr_portal.por_portal";
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

    public List<Por_portalT> getById(Por_portalT por_portalT) throws Exception {
        try {
            String sql = "select * from usr_portal.por_portal where  por_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, por_portalT.getPor_nr_id());
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

    public List<Por_portalT> getByIdUsuario(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from usr_portal.por_portal por, usr_portal.por_usu pu where pu.usu_nr_id=? and pu.por_nr_id=por.por_nr_id";
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

    public List<Por_portalT> getByPor_tx_nome(Por_portalT por_portalT) throws Exception {
        try {
            String sql = "select * from usr_portal.por_portal where  Upper(por_tx_nome) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + por_portalT.getPor_tx_nome() + '%');
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

    public List<Por_portalT> getByPor_tx_status(Por_portalT por_portalT) throws Exception {
        try {
            String sql = "select * from usr_portal.por_portal where  Upper(por_tx_status) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + por_portalT.getPor_tx_status() + '%');
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

    public List<Por_portalT> getByPor_nr_colunas(Por_portalT por_portalT) throws Exception {
        try {
            String sql = "select * from usr_portal.por_portal where  por_nr_colunas = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, por_portalT.getPor_nr_colunas());
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