package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Per_perfilDAO extends ObjectDAOClusterAdminPortal implements IPer_perfilDAO {

    public Per_perfilDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Per_perfilTGWT per_perfilT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into portal.per_perfil  ( per_tx_nome, per_tx_status, per_tx_class, per_nr_id) values (?,  ? , ? , ? )";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public void update(Per_perfilTGWT per_perfilT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update portal.per_perfil set  per_tx_nome=?, per_tx_status=?, per_tx_class=?  where  per_nr_id=?";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public void delete(Per_perfilTGWT per_perfilT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from portal.per_perfil where  per_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, per_perfilT.getPer_nr_id());
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

    public List<Per_perfilTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Per_perfilTGWT> objs = new Vector();
        while (rs.next()) {
            Per_perfilTGWT per_perfilT = new Per_perfilTGWT();
            per_perfilT.setPer_nr_id(rs.getInt("per_nr_id"));
            per_perfilT.setPer_tx_nome(rs.getString("per_tx_nome"));
            per_perfilT.setPer_tx_status(rs.getString("per_tx_status"));
            per_perfilT.setPer_tx_class(rs.getString("per_tx_class"));
            objs.add(per_perfilT);
        }
        return objs;
    }

    public List<Per_perfilTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.per_perfil";
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Per_perfilTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Per_perfilTGWT> getByPK(Per_perfilTGWT per_perfilT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.per_perfil where  per_nr_id=?";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Per_perfilTGWT> getByPer_nr_id(Per_perfilTGWT per_perfilT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.per_perfil where  per_nr_id = ? ";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Per_perfilTGWT> getByPer_tx_nome(Per_perfilTGWT per_perfilT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.per_perfil where  Upper(per_tx_nome) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Per_perfilTGWT> getByPer_tx_status(Per_perfilTGWT per_perfilT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.per_perfil where  Upper(per_tx_status) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Per_perfilTGWT> getByPer_tx_class(Per_perfilTGWT per_perfilT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.per_perfil where  Upper(per_tx_class) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + per_perfilT.getPer_tx_class() + '%');
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
