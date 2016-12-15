package br.com.easynet.easyportal.menu.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.easynet.easyportal.dao.ObjectDAOClusterEasyPortal;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Mep_men_perDAO_Oracle extends ObjectDAOClusterEasyPortal implements IMep_men_perDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Mep_men_perDAO_Oracle(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Mep_men_perT mep_men_perT) throws Exception {
        try {
            String sql = "insert into usr_portal.mep_men_per  ( per_nr_id, men_nr_id) values ( ? , ? )";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
            pStmt.setObject(2, mep_men_perT.getMen_nr_id());
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

    public void update(Mep_men_perT mep_men_perT) throws Exception {
        try {
            String sql = "update usr_portal.mep_men_per set - where  per_nr_id=? and men_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
            pStmt.setObject(2, mep_men_perT.getMen_nr_id());
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

    public void delete(Mep_men_perT mep_men_perT) throws Exception {
        try {
            String sql = "delete from usr_portal.mep_men_per where  per_nr_id=? and men_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
            pStmt.setObject(2, mep_men_perT.getMen_nr_id());
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

    public List<Mep_men_perT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Mep_men_perT> objs = new Vector();
        while (rs.next()) {
            Mep_men_perT mep_men_perT = new Mep_men_perT();
            mep_men_perT.setPer_nr_id(rs.getInt("per_nr_id"));
            mep_men_perT.setMen_nr_id(rs.getInt("men_nr_id"));
            objs.add(mep_men_perT);
        }
        return objs;
    }

    public List<Mep_men_perT> getAll() throws Exception {
        try {
            String sql = "select * from usr_portal.mep_men_per";
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
        }

    }

    public List<Mep_men_perT> getById(Mep_men_perT mep_men_perT) throws Exception {
        try {
            String sql = "select * from usr_portal.mep_men_per where  per_nr_id=? and men_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
            pStmt.setObject(2, mep_men_perT.getMen_nr_id());
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

    public List<Mep_men_perT> getByPer_nr_id(Mep_men_perT mep_men_perT) throws Exception {
        try {
            String sql = "select * from usr_portal.mep_men_per where  per_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mep_men_perT.getPer_nr_id());
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

    public List<Mep_men_perT> getByMen_nr_id(Mep_men_perT mep_men_perT) throws Exception {
        try {
            String sql = "select * from usr_portal.mep_men_per where  men_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mep_men_perT.getMen_nr_id());
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
    public Men_menuT getByMen_menuT(Mep_men_perT mep_men_perT) throws Exception {

        IMen_menuDAO dao = new Men_menuDAO(getdAOFactoryCluster());
        Men_menuT men_menuT = new Men_menuT();
        men_menuT.setMen_nr_id(mep_men_perT.getMen_nr_id());
        List<Men_menuT> list = dao.getById(men_menuT);
        return list.size() > 0 ? list.get(0) : null;
    }

    /** Metodos FK */
    public List<Mep_men_perT> getByMen_menu(Mep_men_perT mep_men_perT) throws Exception {
        try {
            String sql = "select * from usr_portal.mep_men_per where men_menu.men_nr_id=?  ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mep_men_perT.getMen_nr_id());
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

    public List<Mep_men_perT> getByPerfis(List<Per_perfilT> listPer) throws Exception  {
        try {
            StringBuffer perfis = new StringBuffer();
            for (Per_perfilT per_perfilT : listPer) {
                perfis.append(per_perfilT.getPer_nr_id()).append(", ");
            }
            perfis.append("0");
            StringBuffer sql = new StringBuffer("select * from usr_portal.mep_men_per where per_nr_id in (");
            sql.append(perfis.toString()).append(")");
            //pStmt = con.prepareStatement(sql.toString());
            pStmt = createPrepareStatment(sql.toString());
            rs = pStmt.executeQuery();
            return resultSetToObjectTransfer(rs);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            throw e;
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
            }
            try {
                pStmt.close();
            } catch (Exception ex) {
            }
        }
    }
}
