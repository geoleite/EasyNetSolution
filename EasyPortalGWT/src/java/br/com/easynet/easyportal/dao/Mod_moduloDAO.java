package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Mod_moduloDAO extends ObjectDAOClusterEasyPortal {

    public Mod_moduloDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Mod_moduloT mod_moduloT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into portal.mod_modulo  ( mod_nr_id, mod_tx_nome, mod_tx_status, mod_nr_ordem, mod_tx_acao, mod_tx_icone) values (?, ?, ?, ? , ? , ? )";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mod_moduloT.getMod_nr_id());
            pStmt.setObject(2, mod_moduloT.getMod_tx_nome());
            pStmt.setObject(3, mod_moduloT.getMod_tx_status());
            pStmt.setObject(4, mod_moduloT.getMod_nr_ordem());
            pStmt.setObject(5, mod_moduloT.getMod_tx_acao());
            pStmt.setObject(6, mod_moduloT.getMod_tx_acao());
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

    public void update(Mod_moduloT mod_moduloT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update portal.mod_modulo set  mod_tx_nome=?, mod_tx_status=?, mod_nr_ordem=?, mod_tx_acao=?  where  mod_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mod_moduloT.getMod_tx_nome());
            pStmt.setObject(2, mod_moduloT.getMod_tx_status());
            pStmt.setObject(3, mod_moduloT.getMod_nr_ordem());
            pStmt.setObject(4, mod_moduloT.getMod_tx_acao());
            pStmt.setObject(5, mod_moduloT.getMod_nr_id());
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

    public void delete(Mod_moduloT mod_moduloT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from portal.mod_modulo where  mod_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mod_moduloT.getMod_nr_id());
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

    private List<Mod_moduloT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Mod_moduloT> objs = new Vector();
        while (rs.next()) {
            Mod_moduloT mod_moduloT = new Mod_moduloT();
            mod_moduloT.setMod_nr_id(rs.getInt("mod_nr_id"));
            mod_moduloT.setMod_tx_nome(rs.getString("mod_tx_nome"));
            mod_moduloT.setMod_tx_status(rs.getString("mod_tx_status"));
            mod_moduloT.setMod_nr_ordem(rs.getInt("mod_nr_ordem"));
            mod_moduloT.setMod_tx_acao(rs.getString("mod_tx_acao"));
            mod_moduloT.setMod_tx_icone(rs.getString("mod_tx_icone"));
            mod_moduloT.setMod_tx_autologin(rs.getString("mod_tx_autologin"));
            mod_moduloT.setMod_tx_urllogin(rs.getString("mod_tx_urllogin"));


            objs.add(mod_moduloT);
        }
        return objs;
    }

    public List<Mod_moduloT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mod_modulo";
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Mod_moduloT> list = resultSetToObjectTransfer(rs);
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

    public List<Mod_moduloT> getByPerfis(String paramPerfis) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            StringBuffer  sql = new StringBuffer();
            sql.append("select mod.* from portal.mod_modulo mod, portal.mop_mod_per mop where mop.per_nr_id in (")
                    .append(paramPerfis).append(") and mop.mod_nr_id=mod.mod_nr_id order by mod.mod_nr_ordem, mod.mod_tx_nome");
            pStmt = createPrepareStatment(sql.toString());
            rs = pStmt.executeQuery();
            List<Mod_moduloT> list = resultSetToObjectTransfer(rs);
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

    public List<Mod_moduloT> getByPK(Mod_moduloT mod_moduloT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mod_modulo where  mod_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mod_moduloT.getMod_nr_id());
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

    public List<Mod_moduloT> getByMod_nr_id(Mod_moduloT mod_moduloT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mod_modulo where  mod_nr_id = ? ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, mod_moduloT.getMod_nr_id());
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

    public List<Mod_moduloT> getByMod_tx_nome(Mod_moduloT mod_moduloT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mod_modulo where  Upper(mod_tx_nome) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + mod_moduloT.getMod_tx_nome() + '%');
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

    public List<Mod_moduloT> getByMod_tx_status(Mod_moduloT mod_moduloT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.mod_modulo where  Upper(mod_tx_status) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + mod_moduloT.getMod_tx_status() + '%');
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
