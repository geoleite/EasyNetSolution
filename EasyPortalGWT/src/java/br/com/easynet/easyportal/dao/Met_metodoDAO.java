package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Met_metodoDAO extends ObjectDAOClusterEasyPortal implements IMet_metodoDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Met_metodoDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "insert into portal.met_metodo  ( ope_nr_id, met_tx_metodo, met_tx_status, sis_nr_id, met_tx_descricao, met_nr_id) values (?, ? , ? , ? , ?, ?  )";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getOpe_nr_id());
            pStmt.setObject(2, met_metodoT.getMet_tx_metodo());
            pStmt.setObject(3, met_metodoT.getMet_tx_status());
            pStmt.setObject(4, met_metodoT.getSis_nr_id());
            pStmt.setObject(5, met_metodoT.getMet_tx_descricao());
            pStmt.setObject(6, met_metodoT.getMet_nr_id());
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

    public void update(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "update portal.met_metodo set  ope_nr_id=?, met_tx_metodo=?, met_tx_status=?, sis_nr_id=?, met_tx_descricao=?  where  met_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getOpe_nr_id());
            pStmt.setObject(2, met_metodoT.getMet_tx_metodo());
            pStmt.setObject(3, met_metodoT.getMet_tx_status());
            pStmt.setObject(4, met_metodoT.getSis_nr_id());
            pStmt.setObject(5, met_metodoT.getMet_tx_descricao());
            pStmt.setObject(6, met_metodoT.getMet_nr_id());
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

    public void delete(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "delete from portal.met_metodo where  met_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getMet_nr_id());
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

    public List<Met_metodoT> getAllMetodosPerfilSistema(Per_perfilT per_perfilT, Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "select * from portal.met_metodo met where  met.sis_nr_id = ? and met.met_nr_id in (select met_nr_id from portal.opm_met_ope_per opm where opm.sis_nr_id=? and opm.per_nr_id=?) order by met.met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getSis_nr_id());
            pStmt.setObject(2, met_metodoT.getSis_nr_id());
            pStmt.setObject(3, per_perfilT.getPer_nr_id());
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

    public List<Met_metodoT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Met_metodoT> objs = new Vector();
        while (rs.next()) {
            Met_metodoT met_metodoT = new Met_metodoT();
            met_metodoT.setOpe_nr_id(rs.getInt("ope_nr_id"));
            met_metodoT.setMet_nr_id(rs.getInt("met_nr_id"));
            met_metodoT.setMet_tx_metodo(rs.getString("met_tx_metodo"));
            met_metodoT.setMet_tx_status(rs.getString("met_tx_status"));
            met_metodoT.setSis_nr_id(rs.getInt("sis_nr_id"));
            met_metodoT.setMet_tx_descricao(rs.getString("met_tx_descricao"));
            objs.add(met_metodoT);
        }
        return objs;
    }

    public List<Met_metodoT> getAll() throws Exception {
        try {
            String sql = "select * from portal.met_metodo order by met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);

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

    public List<Met_metodoT> getById(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "select * from portal.met_metodo where  met_nr_id=? order by met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getMet_nr_id());
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

    public List<Met_metodoT> getByMetodosNaoPerfil(Per_perfilT per_perfilT, Met_metodoT met_metodoT) throws Exception {
        try {

            String sql = "select * from portal.met_metodo met where  met.sis_nr_id= ? and met.ope_nr_id=? and met.met_nr_id not in (select met_nr_id from portal.opm_met_ope_per opm where opm.sis_nr_id=? and opm.ope_nr_id=? and opm.per_nr_id=?) order by met.met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getSis_nr_id());
            pStmt.setObject(2, met_metodoT.getOpe_nr_id());
            pStmt.setObject(3, met_metodoT.getSis_nr_id());
            pStmt.setObject(4, met_metodoT.getOpe_nr_id());
            pStmt.setObject(5, per_perfilT.getPer_nr_id());
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

    public List<Met_metodoT> getByMetodosPerfil(Per_perfilT per_perfilT, Met_metodoT met_metodoT) throws Exception {
        try {

            String sql = "select * from portal.met_metodo met where  met.sis_nr_id= ? and met.ope_nr_id=? and met.met_nr_id in (select met_nr_id from portal.opm_met_ope_per opm where opm.sis_nr_id=? and opm.ope_nr_id=? and opm.per_nr_id=?) order by met.met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getSis_nr_id());
            pStmt.setObject(2, met_metodoT.getOpe_nr_id());
            pStmt.setObject(3, met_metodoT.getSis_nr_id());
            pStmt.setObject(4, met_metodoT.getOpe_nr_id());
            pStmt.setObject(5, per_perfilT.getPer_nr_id());
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

    public List<Met_metodoT> getByOpe_nr_id(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "select * from portal.met_metodo where  ope_nr_id = ? order by met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getOpe_nr_id());
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

    public List<Met_metodoT> getByMet_nr_id(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "select * from portal.met_metodo where  met_nr_id = ? order by met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getMet_nr_id());
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

    public List<Met_metodoT> getByMet_tx_metodo(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "select * from portal.met_metodo where  Upper(met_tx_metodo) like Upper(?) order by met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + met_metodoT.getMet_tx_metodo() + '%');
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

    public List<Met_metodoT> getByMet_tx_status(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "select * from portal.met_metodo where  Upper(met_tx_status) like Upper(?) order by met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + met_metodoT.getMet_tx_status() + '%');
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

    public List<Met_metodoT> getBySis_nr_id(Met_metodoT met_metodoT) throws Exception {
        try {
            String sql = "select * from portal.met_metodo where  sis_nr_id = ? order by met_tx_metodo";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, met_metodoT.getSis_nr_id());
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
