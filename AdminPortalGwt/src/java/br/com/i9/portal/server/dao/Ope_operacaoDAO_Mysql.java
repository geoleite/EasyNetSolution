package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;

public class Ope_operacaoDAO_Mysql extends ObjectDAO implements IOpe_operacaoDAO {

    public Ope_operacaoDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into ope_operacao  ( sis_nr_id, ope_tx_nome, ope_tx_status, ope_tx_url, ope_tx_descricao, ope_tx_classe) values ( ? , ? , ? , ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, ope_operacaoT.getSis_nr_id());
            pStmt.setObject(2, ope_operacaoT.getOpe_tx_nome());
            pStmt.setObject(3, ope_operacaoT.getOpe_tx_status());
            pStmt.setObject(4, ope_operacaoT.getOpe_tx_url());
            pStmt.setObject(5, ope_operacaoT.getOpe_tx_descricao());
            pStmt.setObject(6, ope_operacaoT.getOpe_tx_classe());
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

    public void update(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update ope_operacao set  ope_tx_nome=?, ope_tx_status=?, ope_tx_url=?, ope_tx_descricao=?, ope_tx_classe=?  where  ope_nr_id=? and sis_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, ope_operacaoT.getOpe_tx_nome());
            pStmt.setObject(2, ope_operacaoT.getOpe_tx_status());
            pStmt.setObject(3, ope_operacaoT.getOpe_tx_url());
            pStmt.setObject(4, ope_operacaoT.getOpe_tx_descricao());
            pStmt.setObject(5, ope_operacaoT.getOpe_tx_classe());
            pStmt.setObject(6, ope_operacaoT.getOpe_nr_id());
            pStmt.setObject(7, ope_operacaoT.getSis_nr_id());
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

    public void delete(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from ope_operacao where  ope_nr_id=? and sis_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, ope_operacaoT.getOpe_nr_id());
            pStmt.setObject(2, ope_operacaoT.getSis_nr_id());
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

    public List<Ope_operacaoTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Ope_operacaoTGWT> objs = new Vector();
        while (rs.next()) {
            Ope_operacaoTGWT ope_operacaoT = new Ope_operacaoTGWT();
            ope_operacaoT.setOpe_nr_id(rs.getInt("ope_nr_id"));
            ope_operacaoT.setSis_nr_id(rs.getInt("sis_nr_id"));
            ope_operacaoT.setOpe_tx_nome(rs.getString("ope_tx_nome"));
            ope_operacaoT.setOpe_tx_status(rs.getString("ope_tx_status"));
            ope_operacaoT.setOpe_tx_url(rs.getString("ope_tx_url"));
            ope_operacaoT.setOpe_tx_descricao(rs.getString("ope_tx_descricao"));
            ope_operacaoT.setOpe_tx_classe(rs.getString("ope_tx_classe"));
            objs.add(ope_operacaoT);
        }
        return objs;
    }

    public List<Ope_operacaoTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Ope_operacaoTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Ope_operacaoTGWT> getByPK(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where  ope_nr_id=? and sis_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, ope_operacaoT.getOpe_nr_id());
            pStmt.setObject(2, ope_operacaoT.getSis_nr_id());
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

    public List<Ope_operacaoTGWT> getByOpe_nr_id(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where  ope_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, ope_operacaoT.getOpe_nr_id());
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

    public List<Ope_operacaoTGWT> getBySis_nr_id(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where  sis_nr_id = ?  order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, ope_operacaoT.getSis_nr_id());
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


    public List<Ope_operacaoTGWT> getByNotPerfil(Per_perfilTGWT perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao ope where ope.ope_nr_id not in (select distinct ope_nr_id from opm_met_ope_per where per_nr_id=?)  order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, perT.getPer_nr_id());
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


    public List<Ope_operacaoTGWT> getByOpe_tx_nome(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_nome) like Upper(?)  order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + ope_operacaoT.getOpe_tx_nome() + '%');
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

    public List<Ope_operacaoTGWT> getByOpe_tx_status(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_status) like Upper(?)  order by ope_tx_nome ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + ope_operacaoT.getOpe_tx_status() + '%');
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

    public List<Ope_operacaoTGWT> getByOpe_tx_url(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_url) like Upper(?)  order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + ope_operacaoT.getOpe_tx_url() + '%');
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

    public List<Ope_operacaoTGWT> getByOpe_tx_descricao(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_descricao) like Upper(?)  order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + ope_operacaoT.getOpe_tx_descricao() + '%');
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

    public List<Ope_operacaoTGWT> getByOpe_tx_classe(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_classe) like Upper(?)  order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + ope_operacaoT.getOpe_tx_classe() + '%');
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
    public List<Ope_operacaoTGWT> getBySis_sistema(Ope_operacaoTGWT ope_operacaoT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao where sis_sistema.sis_nr_id=?  ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, ope_operacaoT.getSis_nr_id());
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

    public List<Ope_operacaoTGWT> getByPerfil(Per_perfilTGWT perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from ope_operacao ope where ope.ope_nr_id in (select distinct ope_nr_id from opm_met_ope_per where per_nr_id=?)  order by ope_tx_nome ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, perT.getPer_nr_id());
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