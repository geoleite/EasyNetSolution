package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public class Opm_met_ope_perDAO_Mysql extends ObjectDAO implements IOpm_met_ope_perDAO {

    public Opm_met_ope_perDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into opm_met_ope_per  ( per_nr_id, ope_nr_id, sis_nr_id, met_nr_id) values ( ? , ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getPer_nr_id());
            pStmt.setObject(2, opm_met_ope_perT.getOpe_nr_id());
            pStmt.setObject(3, opm_met_ope_perT.getSis_nr_id());
            pStmt.setObject(4, opm_met_ope_perT.getMet_nr_id());
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

    public void update(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update opm_met_ope_per set - where  per_nr_id=? and ope_nr_id=? and sis_nr_id=? and met_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getPer_nr_id());
            pStmt.setObject(2, opm_met_ope_perT.getOpe_nr_id());
            pStmt.setObject(3, opm_met_ope_perT.getSis_nr_id());
            pStmt.setObject(4, opm_met_ope_perT.getMet_nr_id());
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

    public void delete(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from opm_met_ope_per where  per_nr_id=? and ope_nr_id=? and sis_nr_id=? and met_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getPer_nr_id());
            pStmt.setObject(2, opm_met_ope_perT.getOpe_nr_id());
            pStmt.setObject(3, opm_met_ope_perT.getSis_nr_id());
            pStmt.setObject(4, opm_met_ope_perT.getMet_nr_id());
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

    public List<Opm_met_ope_perTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Opm_met_ope_perTGWT> objs = new Vector();
        while (rs.next()) {
            Opm_met_ope_perTGWT opm_met_ope_perT = new Opm_met_ope_perTGWT();
            opm_met_ope_perT.setPer_nr_id(rs.getInt("per_nr_id"));
            opm_met_ope_perT.setOpe_nr_id(rs.getInt("ope_nr_id"));
            opm_met_ope_perT.setSis_nr_id(rs.getInt("sis_nr_id"));
            opm_met_ope_perT.setMet_nr_id(rs.getInt("met_nr_id"));
            objs.add(opm_met_ope_perT);
        }
        return objs;
    }

    public List<Opm_met_ope_perTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Opm_met_ope_perTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Opm_met_ope_perTGWT> getByPK(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per where  per_nr_id=? and ope_nr_id=? and sis_nr_id=? and met_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getPer_nr_id());
            pStmt.setObject(2, opm_met_ope_perT.getOpe_nr_id());
            pStmt.setObject(3, opm_met_ope_perT.getSis_nr_id());
            pStmt.setObject(4, opm_met_ope_perT.getMet_nr_id());
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

    public List<Opm_met_ope_perTGWT> getByPer_nr_id(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per where  per_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getPer_nr_id());
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

    public List<Opm_met_ope_perTGWT> getByOpe_nr_id(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per where  ope_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getOpe_nr_id());
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

    public List<Opm_met_ope_perTGWT> getBySis_nr_id(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per where  sis_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getSis_nr_id());
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

    public List<Opm_met_ope_perTGWT> getByMet_nr_id(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per where  met_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getMet_nr_id());
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

    /** Metodos FK */
    public List<Opm_met_ope_perTGWT> getByPer_perfil(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per where per_perfil.per_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getPer_nr_id());
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

    /** Metodos FK */
    public List<Opm_met_ope_perTGWT> getByOpe_operacao(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per where ope_operacao.ope_nr_id=? and ope_operacao.sis_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getOpe_nr_id());
            pStmt.setObject(2, opm_met_ope_perT.getSis_nr_id());
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

    /** Metodos FK */
    public List<Opm_met_ope_perTGWT> getByMet_metodo(Opm_met_ope_perTGWT opm_met_ope_perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from opm_met_ope_per where met_metodo.met_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, opm_met_ope_perT.getMet_nr_id());
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
