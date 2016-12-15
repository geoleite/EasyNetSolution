package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Par_parametroDAO_Mysql extends ObjectDAOClusterEasyPortal implements IPar_parametroDAO{

    public Par_parametroDAO_Mysql(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Par_parametroT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into par_parametro  ( sis_nr_id, par_tx_nome, par_tx_valor) values ( ? , ? , ? )";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, par_parametroT.getSis_nr_id());
            pStmt.setObject(2, par_parametroT.getPar_tx_nome());
            pStmt.setObject(3, par_parametroT.getPar_tx_valor());
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

    public void update(Par_parametroT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update par_parametro set  par_tx_valor=?, sis_nr_id=?, par_tx_nome=?  where  par_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, par_parametroT.getPar_tx_valor());
            pStmt.setObject(2, par_parametroT.getSis_nr_id());
            pStmt.setObject(3, par_parametroT.getPar_tx_nome());
            pStmt.setObject(4, par_parametroT.getPar_nr_id());
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

    public void delete(Par_parametroT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from par_parametro where  sis_nr_id=? and par_tx_nome=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, par_parametroT.getSis_nr_id());
            pStmt.setObject(2, par_parametroT.getPar_tx_nome());
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

    public List<Par_parametroT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Par_parametroT> objs = new Vector();
        while (rs.next()) {
            Par_parametroT par_parametroT = new Par_parametroT();
            par_parametroT.setSis_nr_id(rs.getInt("sis_nr_id"));
            par_parametroT.setPar_nr_id(rs.getInt("par_nr_id"));
            par_parametroT.setPar_tx_nome(rs.getString("par_tx_nome"));
            par_parametroT.setPar_tx_valor(rs.getString("par_tx_valor"));

            objs.add(par_parametroT);
        }
        return objs;
    }

    public List<Par_parametroT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from par_parametro order by sis_nr_id, par_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Par_parametroT> list = resultSetToObjectTransfer(rs);
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

    public Par_parametroT getByPK(Par_parametroT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from par_parametro where  sis_nr_id=? and par_tx_nome=? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, par_parametroT.getSis_nr_id());
            pStmt.setObject(2, par_parametroT.getPar_tx_nome());
            rs = pStmt.executeQuery();
            List<Par_parametroT> list = resultSetToObjectTransfer(rs);
            return list.size() > 0?list.get(0):null;
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

    public List<Par_parametroT> getBySis_nr_id(Par_parametroT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from par_parametro where  sis_nr_id = ? order by par_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
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

    public List<Par_parametroT> getByPar_tx_nome(Par_parametroT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from par_parametro where  Upper(par_tx_nome) like Upper(?) order by par_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
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

    public List<Par_parametroT> getBySistemaPar_tx_nome(Par_parametroT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.par_parametro where sis_nr_id=? and Upper(par_tx_nome) like Upper(?) order by par_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);

            pStmt.setObject(1, par_parametroT.getSis_nr_id());
            pStmt.setObject(2, '%' + par_parametroT.getPar_tx_nome() + '%');
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

    public List<Par_parametroT> getByPar_tx_valor(Par_parametroT par_parametroT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from par_parametro where  Upper(par_tx_valor) like Upper(?)  order by par_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
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
