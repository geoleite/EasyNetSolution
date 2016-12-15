package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public class Sis_sistemaDAO_Mysql extends ObjectDAO implements ISis_sistemaDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Sis_sistemaDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Sis_sistemaT sis_sistemaT) throws Exception {
        try {
            String sql = "insert into portal.sis_sistema  ( sis_tx_nome, sis_tx_descricao, sis_tx_status) values ( ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, sis_sistemaT.getSis_tx_nome());
            pStmt.setObject(2, sis_sistemaT.getSis_tx_descricao());
            pStmt.setObject(3, sis_sistemaT.getSis_tx_status());
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

    public void update(Sis_sistemaT sis_sistemaT) throws Exception {
        try {
            String sql = "update portal.sis_sistema set  sis_tx_nome=?, sis_tx_descricao=?, sis_tx_status=?  where  sis_nr_id=?";
            pStmt = con.prepareStatement(sql);
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
        }

    }

    public void delete(Sis_sistemaT sis_sistemaT) throws Exception {
        try {
            String sql = "delete from portal.sis_sistema where  sis_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, sis_sistemaT.getSis_nr_id());
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

    public List<Sis_sistemaT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Sis_sistemaT> objs = new Vector();
        while (rs.next()) {
            Sis_sistemaT sis_sistemaT = new Sis_sistemaT();
            sis_sistemaT.setSis_nr_id(rs.getInt("sis_nr_id"));
            sis_sistemaT.setSis_tx_nome(rs.getString("sis_tx_nome"));
            sis_sistemaT.setSis_tx_descricao(rs.getString("sis_tx_descricao"));
            sis_sistemaT.setSis_tx_status(rs.getString("sis_tx_status"));
            objs.add(sis_sistemaT);
        }
        return objs;
    }

    public List<Sis_sistemaT> getAll() throws Exception {
        try {
            String sql = "select * from portal.sis_sistema order by sis_tx_nome";
            pStmt = con.prepareStatement(sql);
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

    public List<Sis_sistemaT> getAllAtivos() throws Exception {
        try {
            String sql = "select * from portal.sis_sistema where sis_tx_status='A' order by sis_tx_nome";
            pStmt = con.prepareStatement(sql);
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

    public List<Sis_sistemaT> getById(Sis_sistemaT sis_sistemaT) throws Exception {
        try {
            String sql = "select * from portal.sis_sistema where  sis_nr_id=?";
            pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Sis_sistemaT> getBySis_nr_id(Sis_sistemaT sis_sistemaT) throws Exception {
        try {
            String sql = "select * from portal.sis_sistema where  sis_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
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
        }

    }

    public Sis_sistemaT getBySis_tx_nome(Sis_sistemaT sis_sistemaT) throws Exception {
        try {
            //String sql = "select * from portal.sis_sistema where  Upper(sis_tx_nome) like Upper(?) ";
            String sql = "select * from portal.sis_sistema where upper(sis_tx_nome) = upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, sis_sistemaT.getSis_tx_nome());
            rs = pStmt.executeQuery();
            List<Sis_sistemaT> list = resultSetToObjectTransfer(rs);
            return list.size()>0?list.get(0):null;
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

    public List<Sis_sistemaT> getBySis_tx_descricao(Sis_sistemaT sis_sistemaT) throws Exception {
        try {
            String sql = "select * from portal.sis_sistema where  Upper(sis_tx_descricao) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Sis_sistemaT> getBySis_tx_status(Sis_sistemaT sis_sistemaT) throws Exception {
        try {
            String sql = "select * from portal.sis_sistema where  Upper(sis_tx_status) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
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
        }

    }

    /** One To Many */
    public List<Ope_operacaoT> getByOpe_operacaoTs(Sis_sistemaT sis_sistemaT) throws Exception {
        IOpe_operacaoDAO dao = new Ope_operacaoDAO_Mysql(getDAOFactory());
        Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
        ope_operacaoT.setSis_nr_id(sis_sistemaT.getSis_nr_id());
        List<Ope_operacaoT> list = dao.getBySis_sistema(ope_operacaoT);
        return list;
    }

    public DataSet getSistemaByOperacao(String classe) throws Exception {
        String sql = "select sis.sis_tx_nome from portal.ope_operacao ope, portal.sis_sistema sis where ope.ope_tx_classe=? and ope.sis_nr_id=sis.sis_nr_id";
        Object[] param = {classe};
        return executeQuery(sql, param);
    }
}
