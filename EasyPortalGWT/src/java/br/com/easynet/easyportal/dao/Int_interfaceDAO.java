package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Int_interfaceDAO extends ObjectDAOClusterEasyPortal implements IInt_interfaceDAO{

    public Int_interfaceDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Int_interfaceT int_interfaceT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into portal.int_interface  ( int_tx_nome) values ( ? )";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, int_interfaceT.getInt_tx_nome());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public void update(Int_interfaceT int_interfaceT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update portal.int_interface set  int_tx_nome=?  where  int_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, int_interfaceT.getInt_tx_nome());
            pStmt.setObject(2, int_interfaceT.getInt_nr_id());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public void delete(Int_interfaceT int_interfaceT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from portal.int_interface where  int_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, int_interfaceT.getInt_nr_id());
            pStmt.execute();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                pStmt.close();
            } catch (Exception e) {
            }
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Int_interfaceT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Int_interfaceT> objs = new Vector();
        while (rs.next()) {
            Int_interfaceT int_interfaceT = new Int_interfaceT();
            int_interfaceT.setInt_nr_id(rs.getInt("int_nr_id"));
            int_interfaceT.setInt_tx_nome(rs.getString("int_tx_nome"));
            objs.add(int_interfaceT);
        }
        return objs;
    }

    public List<Int_interfaceT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.int_interface order by int_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Int_interfaceT> list = resultSetToObjectTransfer(rs);
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                rs.close();
                pStmt.close();
            } catch (Exception e) {
            }
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Int_interfaceT> getByPK(Int_interfaceT int_interfaceT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.int_interface where  int_nr_id=? order by int_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, int_interfaceT.getInt_nr_id());
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Int_interfaceT> getByInt_nr_id(Int_interfaceT int_interfaceT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.int_interface where  int_nr_id = ? order by int_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, int_interfaceT.getInt_nr_id());
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Int_interfaceT> getByInt_tx_nome(Int_interfaceT int_interfaceT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.int_interface where  Upper(int_tx_nome) like Upper(?) order by int_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + int_interfaceT.getInt_tx_nome() + '%');
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }
}
