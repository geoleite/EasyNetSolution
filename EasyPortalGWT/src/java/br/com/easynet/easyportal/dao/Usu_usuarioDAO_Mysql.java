package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.easynet.portal.transfer.Por_portalT;

public class Usu_usuarioDAO_Mysql extends ObjectDAO implements IUsu_usuarioDAO {

    private static Object sinal = "sinal";
    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Usu_usuarioDAO_Mysql(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "insert into portal.usu_usuario  ( usu_tx_nome, usu_tx_login, usu_tx_senha, usu_tx_status, usu_tx_email, usu_tx_trocarsenha, usu_dt_cadastro) values ( ? , ? , ? , ?, ?, ?, ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_nome());
            pStmt.setObject(2, usu_usuarioT.getUsu_tx_login());
            pStmt.setObject(3, usu_usuarioT.getUsu_tx_senha());
            pStmt.setObject(4, usu_usuarioT.getUsu_tx_status());
            pStmt.setObject(5, usu_usuarioT.getUsu_tx_email());
            pStmt.setObject(6, usu_usuarioT.getUsu_tx_trocarsenha());
            pStmt.setObject(7, new Timestamp(System.currentTimeMillis()));
            synchronized (sinal) {
                pStmt.execute();
                usu_usuarioT.setUsu_nr_id(getCodigoAutoIncremento());
            }
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
     * Obtém o código autoincremento
     * @return
     * @throws java.lang.Exception
     */
    public int getCodigoAutoIncremento() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select max(usu_nr_id) from portal.usu_usuario";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
            rs.close();
            pStmt.close();
            } catch (Exception e) {}
        }
        return 0;
    }

    public Usu_usuarioT createUser(Usu_usuarioT usuT) throws Exception {
        synchronized (sinal) {
            insert(usuT);
            return getUltimoUsuarioInserido();
        }
        
    }

    public void update(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "update portal.usu_usuario set  usu_tx_nome=?, usu_tx_login=?, usu_tx_senha=?, usu_tx_status=?, usu_tx_email=?, usu_tx_trocarsenha=?  where  usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_nome());
            pStmt.setObject(2, usu_usuarioT.getUsu_tx_login());
            pStmt.setObject(3, usu_usuarioT.getUsu_tx_senha());
            pStmt.setObject(4, usu_usuarioT.getUsu_tx_status());
            pStmt.setObject(5, usu_usuarioT.getUsu_tx_email());
            pStmt.setObject(6, usu_usuarioT.getUsu_tx_trocarsenha());
            pStmt.setObject(7, usu_usuarioT.getUsu_nr_id());
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
     * Altera apenas a senha do usuário
     * @param usu_usuarioT
     * @throws java.lang.Exception
     */
    public void updateSenha(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "update portal.usu_usuario set  usu_tx_senha=?, usu_tx_trocarsenha='N' where  usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_senha());
            pStmt.setObject(2, usu_usuarioT.getUsu_nr_id());
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
     * Altera os dados do usuário sem a senha
     * @param usu_usuarioT
     * @throws java.lang.Exception
     */
    public void updateDados(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "update portal.usu_usuario set  usu_tx_nome=?, usu_tx_login=?, usu_tx_status=?, usu_tx_email=?   where  usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_nome());
            pStmt.setObject(2, usu_usuarioT.getUsu_tx_login());
            pStmt.setObject(3, usu_usuarioT.getUsu_tx_status());
            pStmt.setObject(4, usu_usuarioT.getUsu_tx_email());
            pStmt.setObject(5, usu_usuarioT.getUsu_nr_id());
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

    public void updateTrocarSenha(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "update portal.usu_usuario set  usu_tx_trocarsenha=? where  usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_trocarsenha());
            pStmt.setObject(2, usu_usuarioT.getUsu_nr_id());
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

    public void delete(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "delete from portal.usu_usuario where  usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_nr_id());
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

    public List<Usu_usuarioT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Usu_usuarioT> objs = new Vector();
        while (rs.next()) {
            Usu_usuarioT usu_usuarioT = new Usu_usuarioT();
            usu_usuarioT.setUsu_nr_id(rs.getInt("usu_nr_id"));
            usu_usuarioT.setUsu_tx_nome(rs.getString("usu_tx_nome"));
            usu_usuarioT.setUsu_tx_login(rs.getString("usu_tx_login"));
            usu_usuarioT.setUsu_tx_senha(rs.getString("usu_tx_senha"));
            usu_usuarioT.setUsu_tx_status(rs.getString("usu_tx_status"));
            usu_usuarioT.setUsu_tx_email(rs.getString("usu_tx_email"));
            usu_usuarioT.setUsu_tx_trocarsenha(rs.getString("usu_tx_trocarsenha"));
            usu_usuarioT.setUsu_dt_cadastro(rs.getTimestamp("usu_dt_cadastro"));
            objs.add(usu_usuarioT);
        }
        return objs;
    }

    public List<Usu_usuarioT> getAll() throws Exception {
        try {
            String sql = "select * from portal.usu_usuario";
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

    /**
     * Obtém o usuário com maior código inserido na tabela
     * @return
     * @throws java.lang.Exception
     */
    public Usu_usuarioT getUltimoUsuarioInserido() throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where usu_nr_id =(select max(usu_nr_id) from portal.usu_usuario)";
            pStmt = con.prepareStatement(sql);
            rs = pStmt.executeQuery();
            List<Usu_usuarioT> list = resultSetToObjectTransfer(rs);
            return list.size() > 0 ? list.get(0) : null;
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

    public List<Usu_usuarioT> getUsuariosPortal(Por_portalT porT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where usu_nr_id in (select usu_nr_id from canais.por_usu where por_nr_id=?) order by usu_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, porT.getPor_nr_id());
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

    public List<Usu_usuarioT> getUsuariosNaoPortal(Por_portalT porT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where usu_nr_id not in (select usu_nr_id from canais.por_usu where por_nr_id=?) order by usu_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setInt(1, porT.getPor_nr_id());
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

    public List<Usu_usuarioT> getUsuariosPerfil(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where usu_nr_id in (select usu_nr_id from portal.pu_per_usu pu where pu.per_nr_id=?) order by usu_tx_nome";
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

    public List<Usu_usuarioT> getUsuariosNaoPerfil(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where usu_nr_id not in (select usu_nr_id from portal.pu_per_usu pu where pu.per_nr_id=?) order by usu_tx_nome";
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

    public List<Usu_usuarioT> getById(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where  usu_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_nr_id());
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

    public List<Usu_usuarioT> getByUsu_nr_id(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where  usu_nr_id = ? ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_nr_id());
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

    public List<Usu_usuarioT> getByUsu_tx_nome(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_nome) like Upper(?) order by usu_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + usu_usuarioT.getUsu_tx_nome() + '%');
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

    /**
     * Pesquisar usuário pelo nome e/ou pelo Login
     * @param usu_usuarioT
     * @return
     * @throws Exception
     */
    public List<Usu_usuarioT> getByNomeLogin(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where  (Upper(usu_tx_nome) like Upper(?)) or (Upper(usu_tx_login) like Upper(?))order by usu_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + usu_usuarioT.getUsu_tx_nome() + '%');
            pStmt.setObject(2, '%' + usu_usuarioT.getUsu_tx_login() + '%');
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

    public List<Usu_usuarioT> getByLoginEmail(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_login) like Upper(?)  and usu_tx_email==? order by usu_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_login());
            pStmt.setObject(2, usu_usuarioT.getUsu_tx_email());
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

    public List<Usu_usuarioT> getByUsu_tx_login(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_login) like Upper(?)  order by usu_tx_nome";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_login());
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

    /**
     * Realiza a autenticação do usuário
     * @param usu_usuarioT
     * @return
     * @throws java.lang.Exception
     */
    public List<Usu_usuarioT> getByAutentication(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where  usu_tx_login = ? and usu_tx_senha= ? and usu_tx_status='A'";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_login());
            pStmt.setObject(2, usu_usuarioT.getUsu_tx_senha());
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

    public List<Usu_usuarioT> getByUsu_tx_senha(Usu_usuarioT usu_usuarioT) throws Exception {
        String sql = "select * from portal.usu_usuario where  Upper(usu_tx_senha) like Upper(?) ";
        PreparedStatement pStmt = con.prepareStatement(sql);
        pStmt.setObject(1, '%' + usu_usuarioT.getUsu_tx_senha() + '%');
        ResultSet rs = pStmt.executeQuery();
        return resultSetToObjectTransfer(rs);
    }

    public List<Usu_usuarioT> getByUsu_tx_status(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_status) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + usu_usuarioT.getUsu_tx_status() + '%');
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
    public List<Pu_per_usuT> getByPu_per_usuTs(Usu_usuarioT usu_usuarioT) throws Exception {
        IPu_per_usuDAO dao = new Pu_per_usuDAO_Mysql(getDAOFactory());
        Pu_per_usuT pu_per_usuT = new Pu_per_usuT();
        pu_per_usuT.setUsu_nr_id(usu_usuarioT.getUsu_nr_id());
        List<Pu_per_usuT> list = dao.getByUsu_usuario(pu_per_usuT);
        return list;
    }

    public DataSet checaPermissao(int idUsuario, String classMetodo) throws Exception {
        String sql = "select pu.usu_nr_id from portal.pu_per_usu pu, portal.opm_met_ope_per op , portal.ope_operacao ope where pu.usu_nr_id=? and pu.per_nr_id=op.per_nr_id and op.ope_nr_id=ope.ope_nr_id and ope.ope_tx_classe=? and ope.ope_tx_status='A'";
        Object[] param = {idUsuario, classMetodo};
        return executeQuery(sql, param);
    }

    public DataSet checaPermissao(int idUsuario, String op, String classMetodo) throws Exception {
        String sql = "select met.met_nr_id from portal.pu_per_usu pu, portal.opm_met_ope_per op, portal.met_metodo met, portal.ope_operacao ope where pu.usu_nr_id=? and pu.per_nr_id=op.per_nr_id and op.sis_nr_id=met.sis_nr_id and op.met_nr_id=met.met_nr_id and met.met_tx_metodo=? and ope.sis_nr_id=met.sis_nr_id and ope.ope_nr_id=met.ope_nr_id and ope.ope_tx_classe=? and ope.ope_tx_status='A' and met.met_tx_status='A'";
        Object[] param = {idUsuario, op, classMetodo};
        return executeQuery(sql, param);
    }

}
