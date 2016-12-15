package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;

public class Ope_operacaoDAO_Mysql extends ObjectDAO implements IOpe_operacaoDAO{

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Ope_operacaoDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "insert into ope_operacao  ( sis_nr_id, ope_tx_nome, ope_tx_status, ope_tx_url, ope_tx_descricao, ope_tx_classe) values ( ? , ? , ? , ? , ? , ?)";
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

    public void update(Ope_operacaoT ope_operacaoT) throws Exception {
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

    public void delete(Ope_operacaoT ope_operacaoT) throws Exception {
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

    public List<Ope_operacaoT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Ope_operacaoT> objs = new Vector();
        while (rs.next()) {
            Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
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

    public List<Ope_operacaoT> getAll() throws Exception {
        try {
            String sql = "select * from ope_operacao order by ope_tx_nome";
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

    public List<Ope_operacaoT> getById(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "select * from ope_operacao where  ope_nr_id=? and sis_nr_id=? order by ope_tx_nome";
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

    public List<Ope_operacaoT> getOperacoesPerfil(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from ope_operacao where ope_nr_id in (select ope_nr_id from opm_met_ope_per op where op.per_nr_id=?) order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Ope_operacaoT> getOperacoesNaoPerfil(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from ope_operacao where ope_nr_id not in (select ope_nr_id from opm_met_ope_per where per_nr_id=?) order by ope_tx_nome";
            pStmt = con.prepareStatement(sql);
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
        }
    }

    public List<Ope_operacaoT> getByOpe_nr_id(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "select * from ope_operacao where  ope_nr_id = ? order by ope_tx_nome";
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

    public List<Ope_operacaoT> getBySis_nr_id(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "select * from ope_operacao where  sis_nr_id = ? order by ope_tx_nome, ope_tx_descricao ";
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

    public List<Ope_operacaoT> getByOpe_tx_nome(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_nome) like Upper(?) order by ope_tx_nome ";
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

    public List<Ope_operacaoT> getByOpe_tx_status(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_status) like Upper(?) order by ope_tx_nome";
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

    public List<Ope_operacaoT> getByOpe_tx_url(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_url) like Upper(?) order by ope_tx_nome";
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

    public List<Ope_operacaoT> getByOpe_tx_descricao(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "select * from ope_operacao where  Upper(ope_tx_descricao) like Upper(?) order by ope_tx_nome";
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

    /** Many to One */
    public Sis_sistemaT getBySis_sistemaT(Ope_operacaoT ope_operacaoT) throws Exception {
        ISis_sistemaDAO dao = new Sis_sistemaDAO_Mysql(getDAOFactory());
        Sis_sistemaT sis_sistemaT = new Sis_sistemaT();
        sis_sistemaT.setSis_nr_id(ope_operacaoT.getSis_nr_id());
        List<Sis_sistemaT> list = dao.getById(sis_sistemaT);
        return list.size() > 0 ? list.get(0) : null;
    }

    /** One To Many */
    public List<Op_ope_perT> getByOp_ope_perTs(Ope_operacaoT ope_operacaoT) throws Exception {
        IOp_ope_perDAO dao = new Op_ope_perDAO_Mysql(getDAOFactory());
        Op_ope_perT op_ope_perT = new Op_ope_perT();
        op_ope_perT.setOpe_nr_id(ope_operacaoT.getOpe_nr_id());
        op_ope_perT.setSis_nr_id(ope_operacaoT.getSis_nr_id());
        List<Op_ope_perT> list = dao.getByOpe_operacao(op_ope_perT);
        return list;
    }

    /** Metodos FK */
    public List<Ope_operacaoT> getBySis_sistema(Ope_operacaoT ope_operacaoT) throws Exception {
        try {
            String sql = "select * from ope_operacao where sis_nr_id=?  order by ope_tx_nome";
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
}
