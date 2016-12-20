package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public class Met_metodoDAO_Mysql extends ObjectDAO implements IMet_metodoDAO {

    public Met_metodoDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into met_metodo  ( ope_nr_id, met_tx_metodo, met_tx_status, sis_nr_id, met_tx_descricao) values ( ? , ? , ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, met_metodoT.getOpe_nr_id());
            pStmt.setObject(2, met_metodoT.getMet_tx_metodo());
            pStmt.setObject(3, met_metodoT.getMet_tx_status());
            pStmt.setObject(4, met_metodoT.getSis_nr_id());
            pStmt.setObject(5, met_metodoT.getMet_tx_descricao());
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

    public void update(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update met_metodo set  ope_nr_id=?, met_tx_metodo=?, met_tx_status=?, sis_nr_id=?, met_tx_descricao=?  where  met_nr_id=?";
            pStmt = con.prepareStatement(sql);
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

        }
    }

    public void delete(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from met_metodo where  met_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, met_metodoT.getMet_nr_id());
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

    public List<Met_metodoTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Met_metodoTGWT> objs = new Vector();
        while (rs.next()) {
            Met_metodoTGWT met_metodoT = new Met_metodoTGWT();
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

    public List<Met_metodoTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Met_metodoTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Met_metodoTGWT> getByPK(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo where  met_nr_id=?";
            pStmt = con.prepareStatement(sql);
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

        }
    }

    public List<Met_metodoTGWT> getByOpe_nr_id(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo where  ope_nr_id = ? order by met_tx_metodo";
            pStmt = con.prepareStatement(sql);
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

        }
    }

    public List<Met_metodoTGWT> getByOperacaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo met where met.ope_nr_id = ? and met.met_nr_id  in (select distinct met_nr_id from opm_met_ope_per where per_nr_id=?) order by met_tx_metodo ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, met_metodoT.getOpe_nr_id());
            pStmt.setObject(2, perT.getPer_nr_id());
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

    public List<Met_metodoTGWT> getByOperacaoNaoPerfil(Met_metodoTGWT met_metodoT, Per_perfilTGWT perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo met where met.ope_nr_id = ? and met.met_nr_id not in (select distinct met_nr_id from opm_met_ope_per where per_nr_id=?) order by met_tx_metodo ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, met_metodoT.getOpe_nr_id());
            pStmt.setObject(2, perT.getPer_nr_id());
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

    public List<Met_metodoTGWT> getByMet_nr_id(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo where  met_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
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

        }
    }

    public List<Met_metodoTGWT> getByMet_tx_metodo(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo where  Upper(met_tx_metodo) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
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

        }
    }

    public List<Met_metodoTGWT> getByMet_tx_status(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo where  Upper(met_tx_status) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
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

        }
    }

    public List<Met_metodoTGWT> getBySis_nr_id(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo where  sis_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
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

        }
    }

    public List<Met_metodoTGWT> getByMet_tx_descricao(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo where  Upper(met_tx_descricao) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + met_metodoT.getMet_tx_descricao() + '%');
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
    public List<Met_metodoTGWT> getByOpe_operacao(Met_metodoTGWT met_metodoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from met_metodo where ope_operacao.ope_nr_id=? and ope_operacao.sis_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, met_metodoT.getOpe_nr_id());
            pStmt.setObject(2, met_metodoT.getSis_nr_id());
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
