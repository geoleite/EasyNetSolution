package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public class Con_componente_negadoDAO_Oracle extends ObjectDAO implements ICon_componente_negadoDAO {

    public Con_componente_negadoDAO_Oracle(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into usr_portal.con_componente_negado  ( int_nr_id, per_nr_id, con_tx_visivel, con_tx_ativo, con_tx_nome) values ( ? , ? , ? , ?, ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getInt_nr_id());
            pStmt.setObject(2, con_componente_negadoT.getPer_nr_id());
            pStmt.setObject(3, con_componente_negadoT.getCon_tx_visivel());
            pStmt.setObject(4, con_componente_negadoT.getCon_tx_ativo());
            pStmt.setObject(5, con_componente_negadoT.getCon_tx_nome());
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

    public void update(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update usr_portal.con_componente_negado set  con_tx_visivel=?, con_tx_ativo=? , con_tx_nome=? where  con_nr_id=? and int_nr_id=? and per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getCon_tx_visivel());
            pStmt.setObject(2, con_componente_negadoT.getCon_tx_ativo());
            pStmt.setObject(3, con_componente_negadoT.getCon_tx_nome());
            pStmt.setObject(4, con_componente_negadoT.getCon_nr_id());
            pStmt.setObject(5, con_componente_negadoT.getInt_nr_id());
            pStmt.setObject(6, con_componente_negadoT.getPer_nr_id());
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

    public void delete(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from usr_portal.con_componente_negado where  con_nr_id=? and int_nr_id=? and per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getCon_nr_id());
            pStmt.setObject(2, con_componente_negadoT.getInt_nr_id());
            pStmt.setObject(3, con_componente_negadoT.getPer_nr_id());
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

    public List<Con_componente_negadoT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Con_componente_negadoT> objs = new Vector();
        while (rs.next()) {
            Con_componente_negadoT con_componente_negadoT = new Con_componente_negadoT();
            con_componente_negadoT.setCon_nr_id(rs.getInt("con_nr_id"));
            con_componente_negadoT.setInt_nr_id(rs.getInt("int_nr_id"));
            con_componente_negadoT.setPer_nr_id(rs.getInt("per_nr_id"));
            con_componente_negadoT.setCon_tx_visivel(rs.getString("con_tx_visivel"));
            con_componente_negadoT.setCon_tx_nome(rs.getString("con_tx_nome"));
            con_componente_negadoT.setCon_tx_ativo(rs.getString("con_tx_ativo"));
            objs.add(con_componente_negadoT);
        }
        return objs;
    }

    public List<Con_componente_negadoT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Con_componente_negadoT> list = resultSetToObjectTransfer(rs);
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

    public List<Con_componente_negadoT> getByPK(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado where  con_nr_id=? and int_nr_id=? and per_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getCon_nr_id());
            pStmt.setObject(2, con_componente_negadoT.getInt_nr_id());
            pStmt.setObject(3, con_componente_negadoT.getPer_nr_id());
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

    public List<Con_componente_negadoT> getByCon_nr_id(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado where  con_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getCon_nr_id());
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

    public List<Con_componente_negadoT> getByInt_nr_id(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado where  int_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getInt_nr_id());
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

    public List<Con_componente_negadoT> getByInterfacePerfis(Con_componente_negadoT con_componente_negadoT, String perfis) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            StringBuffer sql = new StringBuffer("select distinct * from usr_portal.con_componente_negado where  int_nr_id = ? and per_nr_id in (").append(perfis).append(") ");
            pStmt = con.prepareStatement(sql.toString());
            pStmt.setObject(1, con_componente_negadoT.getInt_nr_id());
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

    public List<Con_componente_negadoT> getByInterfacePerfil(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado where  int_nr_id = ? and per_nr_id=? order by";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getInt_nr_id());
            pStmt.setObject(2, con_componente_negadoT.getPer_nr_id());
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

    public List<Con_componente_negadoT> getByPer_nr_id(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado where  per_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getPer_nr_id());
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

    public List<Con_componente_negadoT> getByCon_tx_visivel(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado where  Upper(con_tx_visivel) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + con_componente_negadoT.getCon_tx_visivel() + '%');
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

    public List<Con_componente_negadoT> getByCon_tx_ativo(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado where  Upper(con_tx_ativo) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + con_componente_negadoT.getCon_tx_ativo() + '%');
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

    /** M todos FK */
    public List<Con_componente_negadoT> getByPi_per_int(Con_componente_negadoT con_componente_negadoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.con_componente_negado where pi_per_int.int_nr_id=? and pi_per_int.per_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, con_componente_negadoT.getInt_nr_id());
            pStmt.setObject(2, con_componente_negadoT.getPer_nr_id());
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
