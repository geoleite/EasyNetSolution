package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.easynet.easyportal.transfer.Res_recall_senhaT;
import br.com.jdragon.dao.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Res_recall_senhaDAO_Oracle extends ObjectDAOClusterEasyPortal implements IRes_recall_senhaDAO {

    public Res_recall_senhaDAO_Oracle(DAOFactoryCluster dao) throws Exception {
        //setdAOFactoryCluster(dao);
    }

    public void insert(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into usr_portal.res_recall_senha  ( usu_nr_id, res_tx_pergunta, res_tx_resposta, res_dt_ultimoacesso, res_nr_tentativas) values ( ? , ? , ? , ? , ? )";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getUsu_nr_id());
            pStmt.setObject(2, res_recall_senhaT.getRes_tx_pergunta());
            pStmt.setObject(3, res_recall_senhaT.getRes_tx_resposta());
            pStmt.setObject(4, res_recall_senhaT.getRes_dt_ultimoacesso());
            pStmt.setObject(5, res_recall_senhaT.getRes_nr_tentativas());
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

    public void update(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update usr_portal.res_recall_senha set  res_tx_pergunta=?, res_tx_resposta=?, res_dt_ultimoacesso=?, res_nr_tentativas=?  where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getRes_tx_pergunta());
            pStmt.setObject(2, res_recall_senhaT.getRes_tx_resposta());
            pStmt.setObject(3, res_recall_senhaT.getRes_dt_ultimoacesso());
            pStmt.setObject(4, res_recall_senhaT.getRes_nr_tentativas());
            pStmt.setObject(5, res_recall_senhaT.getUsu_nr_id());
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

    public void delete(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from usr_portal.res_recall_senha where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getUsu_nr_id());
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

    public void zerarContadorTentativas(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update usr_portal.res_recall_senha set res_nr_tentativas=0 where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getUsu_nr_id());
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

    public void registrarTentativa(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update usr_portal.res_recall_senha set res_nr_tentativas=res_nr_tentativas+1 where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getUsu_nr_id());
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

    /**
     * Registrar o ultimo acesso do usuário
     * @param res_recall_senhaT
     * @throws Exception
     */
    public void registrarAcesso(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update usr_portal.res_recall_senha set res_dt_ultimoacesso=? where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, new Timestamp(System.currentTimeMillis()));
            pStmt.setObject(2, res_recall_senhaT.getUsu_nr_id());
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

    public List<Res_recall_senhaT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Res_recall_senhaT> objs = new Vector();
        while (rs.next()) {
            Res_recall_senhaT res_recall_senhaT = new Res_recall_senhaT();
            res_recall_senhaT.setUsu_nr_id(rs.getInt("usu_nr_id"));
            res_recall_senhaT.setRes_tx_pergunta(rs.getString("res_tx_pergunta"));
            res_recall_senhaT.setRes_tx_resposta(rs.getString("res_tx_resposta"));
            res_recall_senhaT.setRes_dt_ultimoacesso(rs.getTimestamp("res_dt_ultimoacesso"));
            res_recall_senhaT.setRes_nr_tentativas(rs.getInt("res_nr_tentativas"));
            objs.add(res_recall_senhaT);
        }
        return objs;
    }

    public List<Res_recall_senhaT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.res_recall_senha";
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Res_recall_senhaT> list = resultSetToObjectTransfer(rs);
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

    public List<Res_recall_senhaT> getByPK(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.res_recall_senha where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getUsu_nr_id());
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

    public List<Res_recall_senhaT> getByUsu_nr_id(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.res_recall_senha where  usu_nr_id = ? ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getUsu_nr_id());
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

    public List<Res_recall_senhaT> getByRes_tx_pergunta(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.res_recall_senha where  Upper(res_tx_pergunta) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + res_recall_senhaT.getRes_tx_pergunta() + '%');
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

    public List<Res_recall_senhaT> getByRes_tx_resposta(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.res_recall_senha where  Upper(res_tx_resposta) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + res_recall_senhaT.getRes_tx_resposta() + '%');
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

    public List<Res_recall_senhaT> getByRes_dt_ultimoacesso(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.res_recall_senha where  res_dt_ultimoacesso = ? ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getRes_dt_ultimoacesso());
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

    public List<Res_recall_senhaT> getByRes_nr_tentativas(Res_recall_senhaT res_recall_senhaT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from usr_portal.res_recall_senha where  res_nr_tentativas = ? ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, res_recall_senhaT.getRes_nr_tentativas());
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

    public List<Res_recall_senhaT> getByLoginEmail(String login, String email) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select res.* from usr_portal.res_recall_senha res, usu_portal.usu_usuario usu where  usu.usu_tx_login=? and usu.usu_tx_email=? and usu.nr_id=res.usu_nr_id";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, login);
            pStmt.setObject(2, email);
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
