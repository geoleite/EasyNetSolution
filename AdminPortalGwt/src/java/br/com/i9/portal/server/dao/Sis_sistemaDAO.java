package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Sis_sistemaDAO extends ObjectDAOClusterAdminPortal implements ISis_sistemaDAO {

    public Sis_sistemaDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into portal.sis_sistema  ( sis_tx_nome, sis_tx_descricao, sis_tx_status, sis_nr_id) values (?, ? , ? , ? )";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, sis_sistemaT.getSis_tx_nome());
            pStmt.setObject(2, sis_sistemaT.getSis_tx_descricao());
            pStmt.setObject(3, sis_sistemaT.getSis_tx_status());
            pStmt.setObject(4, sis_sistemaT.getSis_nr_id());
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

    public void update(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update portal.sis_sistema set  sis_tx_nome=?, sis_tx_descricao=?, sis_tx_status=?  where  sis_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, sis_sistemaT.getSis_tx_nome());
            pStmt.setObject(2, sis_sistemaT.getSis_tx_descricao());
            pStmt.setObject(3, sis_sistemaT.getSis_tx_status());
            pStmt.setObject(4, sis_sistemaT.getSis_nr_id());
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

    public void delete(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from portal.sis_sistema where  sis_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, sis_sistemaT.getSis_nr_id());
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

    public List<Sis_sistemaTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Sis_sistemaTGWT> objs = new Vector();
        while (rs.next()) {
            Sis_sistemaTGWT sis_sistemaT = new Sis_sistemaTGWT();
            sis_sistemaT.setSis_nr_id(rs.getInt("sis_nr_id"));
            sis_sistemaT.setSis_tx_nome(rs.getString("sis_tx_nome"));
            sis_sistemaT.setSis_tx_descricao(rs.getString("sis_tx_descricao"));
            sis_sistemaT.setSis_tx_status(rs.getString("sis_tx_status"));
            objs.add(sis_sistemaT);
        }
        return objs;
    }

    public List<Sis_sistemaTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.sis_sistema order by sis_tx_nome";
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Sis_sistemaTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Sis_sistemaTGWT> getByPK(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.sis_sistema where  sis_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, sis_sistemaT.getSis_nr_id());
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

    public List<Sis_sistemaTGWT> getBySis_nr_id(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.sis_sistema where  sis_nr_id = ? ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, sis_sistemaT.getSis_nr_id());
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

    public List<Sis_sistemaTGWT> getBySis_tx_nome(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.sis_sistema where  Upper(sis_tx_nome) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + sis_sistemaT.getSis_tx_nome() + '%');
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

    public List<Sis_sistemaTGWT> getBySis_tx_descricao(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.sis_sistema where  Upper(sis_tx_descricao) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + sis_sistemaT.getSis_tx_descricao() + '%');
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

    public List<Sis_sistemaTGWT> getBySis_tx_status(Sis_sistemaTGWT sis_sistemaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.sis_sistema where  Upper(sis_tx_status) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + sis_sistemaT.getSis_tx_status() + '%');
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
