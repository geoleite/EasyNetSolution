package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public class Par_parametroDAO_Oracle extends ObjectDAO implements IPar_parametroDAO {

    public Par_parametroDAO_Oracle(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Par_parametroTGWT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into usr_portal.par_parametro  ( sis_nr_id, par_tx_nome, par_tx_valor, par_tx_secreto) values ( ? , ? , ? ,?)";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, par_parametroT.getSis_nr_id());
            pStmt.setObject(2, par_parametroT.getPar_tx_nome());
            pStmt.setObject(3, par_parametroT.getPar_tx_valor());
            pStmt.setObject(4, par_parametroT.getPar_tx_secreto());
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

    public void update(Par_parametroTGWT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update usr_portal.par_parametro set par_tx_valor=?, par_tx_nome=?, sis_nr_id=?, par_tx_secreto=?  where  par_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, par_parametroT.getPar_tx_valor());
            pStmt.setObject(2, par_parametroT.getPar_tx_nome());
            pStmt.setObject(3, par_parametroT.getSis_nr_id());
            pStmt.setObject(4, par_parametroT.getPar_tx_secreto());
            pStmt.setObject(5, par_parametroT.getPar_nr_id());
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

    public void delete(Par_parametroTGWT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from usr_portal.par_parametro where  par_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, par_parametroT.getSis_nr_id());
            pStmt.setObject(2, par_parametroT.getPar_nr_id());
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

    public List<Par_parametroTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Par_parametroTGWT> objs = new Vector();
        while (rs.next()) {
            Par_parametroTGWT par_parametroT = new Par_parametroTGWT();
            par_parametroT.setSis_nr_id(rs.getInt("sis_nr_id"));
            par_parametroT.setPar_nr_id(rs.getInt("par_nr_id"));
            par_parametroT.setPar_tx_nome(rs.getString("par_tx_nome"));
            par_parametroT.setPar_tx_valor(rs.getString("par_tx_valor"));
            par_parametroT.setPar_tx_secreto(rs.getString("par_tx_secreto"));
            objs.add(par_parametroT);
        }
        return objs;
    }

    public List<Par_parametroTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.par_parametro order by par_tx_nome";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Par_parametroTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Par_parametroTGWT> getByPK(Par_parametroTGWT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.par_parametro where  par_nr_id=?";
            pStmt = con.prepareStatement(sql);
            //pStmt.setObject(1, par_parametroT.getSis_nr_id());
            pStmt.setObject(1, par_parametroT.getPar_nr_id());
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

    public List<Par_parametroTGWT> getBySis_nr_id(Par_parametroTGWT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.par_parametro where  sis_nr_id = ? order by par_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, par_parametroT.getSis_nr_id());
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

    public List<Par_parametroTGWT> getByPar_tx_nome(Par_parametroTGWT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.par_parametro where  Upper(par_tx_nome) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + par_parametroT.getPar_tx_nome() + '%');
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

    public List<Par_parametroTGWT> getByPar_tx_valor(Par_parametroTGWT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.par_parametro where  Upper(par_tx_valor) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + par_parametroT.getPar_tx_valor() + '%');
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
