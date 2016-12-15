package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Mop_mod_perDAO extends ObjectDAOClusterEasyPortal {

    public Mop_mod_perDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Mop_mod_perT mop_mod_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into portal.mop_mod_per  ( mod_nr_id, per_nr_id) values ( ? , ? )";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mop_mod_perT.getMod_nr_id());
            pStmt.setObject(2, mop_mod_perT.getPer_nr_id());
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

    public void update(Mop_mod_perT mop_mod_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update portal.mop_mod_per set - where  mod_nr_id=? and per_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mop_mod_perT.getMod_nr_id());
            pStmt.setObject(2, mop_mod_perT.getPer_nr_id());
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

    public void delete(Mop_mod_perT mop_mod_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from portal.mop_mod_per where  mod_nr_id=? and per_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mop_mod_perT.getMod_nr_id());
            pStmt.setObject(2, mop_mod_perT.getPer_nr_id());
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

    private List<Mop_mod_perT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Mop_mod_perT> objs = new Vector();
        while (rs.next()) {
            Mop_mod_perT mop_mod_perT = new Mop_mod_perT();
            mop_mod_perT.setMod_nr_id(rs.getInt("mod_nr_id"));
            mop_mod_perT.setPer_nr_id(rs.getInt("per_nr_id"));
            objs.add(mop_mod_perT);
        }
        return objs;
    }

    public List<Mop_mod_perT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mop_mod_per";
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Mop_mod_perT> list = resultSetToObjectTransfer(rs);
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

    public List<Mop_mod_perT> getByPK(Mop_mod_perT mop_mod_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mop_mod_per where  mod_nr_id=? and per_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mop_mod_perT.getMod_nr_id());
            pStmt.setObject(2, mop_mod_perT.getPer_nr_id());
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

    public List<Mop_mod_perT> getByMod_nr_id(Mop_mod_perT mop_mod_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mop_mod_per where  mod_nr_id = ? ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mop_mod_perT.getMod_nr_id());
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

    public List<Mop_mod_perT> getByPer_nr_id(Mop_mod_perT mop_mod_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mop_mod_per where  per_nr_id = ? ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mop_mod_perT.getPer_nr_id());
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

    /** Metodos FK */
    public List<Mop_mod_perT> getByMod_modulo(Mop_mod_perT mop_mod_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mop_mod_per where mod_modulo.mod_nr_id=?  ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mop_mod_perT.getMod_nr_id());
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
