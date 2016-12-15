package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Usu_usuarioDAO extends ObjectDAOClusterAdminPortal implements IUsu_usuarioDAO {

    public Usu_usuarioDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into portal.usu_usuario  ( usu_tx_nome, usu_tx_login, usu_tx_senha, usu_tx_status, usu_tx_email, usu_tx_trocarsenha, usu_nr_id) values (?,  ? , ? , ? , ? , ?, ? )";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }
        }
    }

    public void update(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update portal.usu_usuario set  usu_tx_nome=?, usu_tx_login=?, usu_tx_status=?, usu_tx_email=?, usu_tx_trocarsenha=?  where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_tx_nome());
            pStmt.setObject(2, usu_usuarioT.getUsu_tx_login());
            pStmt.setObject(3, usu_usuarioT.getUsu_tx_status());
            pStmt.setObject(4, usu_usuarioT.getUsu_tx_email());
            pStmt.setObject(5, usu_usuarioT.getUsu_tx_trocarsenha());
            pStmt.setObject(6, usu_usuarioT.getUsu_nr_id());
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

    public void delete(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from portal.usu_usuario where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, usu_usuarioT.getUsu_nr_id());
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

    public List<Usu_usuarioTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Usu_usuarioTGWT> objs = new Vector();
        while (rs.next()) {
            Usu_usuarioTGWT usu_usuarioT = new Usu_usuarioTGWT();
            usu_usuarioT.setUsu_nr_id(rs.getInt("usu_nr_id"));
            usu_usuarioT.setUsu_tx_nome(rs.getString("usu_tx_nome"));
            usu_usuarioT.setUsu_tx_login(rs.getString("usu_tx_login"));
            usu_usuarioT.setUsu_tx_status(rs.getString("usu_tx_status"));
            usu_usuarioT.setUsu_tx_email(rs.getString("usu_tx_email"));
            usu_usuarioT.setUsu_tx_trocarsenha(rs.getString("usu_tx_trocarsenha"));
            objs.add(usu_usuarioT);
        }
        return objs;
    }

    public List<Usu_usuarioTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario";
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Usu_usuarioTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Usu_usuarioTGWT> getByPK(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Usu_usuarioTGWT> getByUsu_nr_id(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario where  usu_nr_id = ? ";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Usu_usuarioTGWT> getByUsu_tx_nome(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_nome) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Usu_usuarioTGWT> getByUsu_tx_login(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_login) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + usu_usuarioT.getUsu_tx_login() + '%');
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

    public List<Usu_usuarioTGWT> getByUsu_tx_senha(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_senha) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + usu_usuarioT.getUsu_tx_senha() + '%');
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

    public List<Usu_usuarioTGWT> getByUsu_tx_status(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_status) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Usu_usuarioTGWT> getByUsu_tx_email(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario where  Upper(usu_tx_email) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + usu_usuarioT.getUsu_tx_email() + '%');
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

    public void updateSenha(Usu_usuarioTGWT usu_usuarioT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update portal.usu_usuario set usu_tx_trocarsenha='S', usu_tx_senha=?  where  usu_nr_id=?";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }
    }

    public List<Usu_usuarioTGWT> getByPerfil(Per_perfilTGWT perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario usu where usu.usu_nr_id in (select distinct usu_nr_id from portal.pu_per_usu where per_nr_id=?)  order by usu_tx_nome ";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }
            
        }
    }

    public List<Usu_usuarioTGWT> getByNotPerfil(Per_perfilTGWT perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario usu where usu.usu_nr_id not in (select distinct usu_nr_id from portal.pu_per_usu where per_nr_id=?)  order by usu_tx_nome ";
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }
            
        }
    }

    public List<Usu_usuarioTGWT> getByNomeLogin(String texto) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.usu_usuario usu where upper(usu_tx_nome) like upper(?) or upper(usu_tx_login) like upper(?) order by usu_tx_nome ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, "%" + texto + "%");
            pStmt.setObject(2, "%" + texto + "%");
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
