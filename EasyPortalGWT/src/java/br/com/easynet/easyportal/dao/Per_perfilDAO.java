package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Per_perfilDAO extends ObjectDAOClusterEasyPortal implements IPer_perfilDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Per_perfilDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "insert into portal.per_perfil  ( per_tx_nome, per_tx_status, per_tx_class, per_nr_id) values (?, ? , ?, ? )";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, per_perfilT.getPer_tx_nome());
            pStmt.setObject(2, per_perfilT.getPer_tx_status());
            pStmt.setObject(3, per_perfilT.getPer_tx_class());
            pStmt.setObject(4, per_perfilT.getPer_nr_id());
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

    public void update(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "update portal.per_perfil set  per_tx_nome=?, per_tx_status=?, per_tx_class=?  where  per_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, per_perfilT.getPer_tx_nome());
            pStmt.setObject(2, per_perfilT.getPer_tx_status());
            pStmt.setObject(3, per_perfilT.getPer_tx_class());
            pStmt.setObject(4, per_perfilT.getPer_nr_id());
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

    public void delete(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "delete from portal.per_perfil where  per_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, per_perfilT.getPer_nr_id());
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

    public List<Per_perfilT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Per_perfilT> objs = new Vector();
        while (rs.next()) {
            Per_perfilT per_perfilT = new Per_perfilT();
            per_perfilT.setPer_nr_id(rs.getInt("per_nr_id"));
            per_perfilT.setPer_tx_nome(rs.getString("per_tx_nome"));
            per_perfilT.setPer_tx_status(rs.getString("per_tx_status"));
            per_perfilT.setPer_tx_class(rs.getString("per_tx_class"));
            objs.add(per_perfilT);
        }
        return objs;
    }

    public List<Per_perfilT> getAll() throws Exception {
        try {
            String sql = "select * from portal.per_perfil";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
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

    public List<Per_perfilT> getById(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from portal.per_perfil where  per_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }

    }

    public List<Per_perfilT> getByPer_nr_id(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from portal.per_perfil where  per_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
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
            try {
                close();
            } catch (Exception e) {
            }

        }

    }

    public List<Per_perfilT> getByPer_tx_nome(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from portal.per_perfil where  Upper(per_tx_nome) like Upper(?) ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + per_perfilT.getPer_tx_nome() + '%');
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

    public List<Per_perfilT> getByPer_tx_status(Per_perfilT per_perfilT) throws Exception {
        try {
            String sql = "select * from portal.per_perfil where  Upper(per_tx_status) like Upper(?) ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + per_perfilT.getPer_tx_status() + '%');
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

    public List<Per_perfilT> getByUsuario(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select per.* from portal.per_perfil per, portal.pu_per_usu pu where pu.usu_nr_id=? and pu.per_nr_id=per.per_nr_id order by per.per_tx_nome";
            //pStmt = con.prepareStatement(sql);
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

    /**
     * One To Many
     */
    public List<Op_ope_perT> getByOp_ope_perTs(Per_perfilT per_perfilT) throws Exception {
        try {
            Op_ope_perDAO dao = new Op_ope_perDAO(getdAOFactoryCluster());
            Op_ope_perT op_ope_perT = new Op_ope_perT();
            op_ope_perT.setPer_nr_id(per_perfilT.getPer_nr_id());
            List<Op_ope_perT> list = dao.getByPer_perfil(op_ope_perT);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                close();
            } catch (Exception e) {
            }

        }

    }

    /**
     * One To Many
     */
    public List<Pu_per_usuT> getByPu_per_usuTs(Per_perfilT per_perfilT) throws Exception {
        try {
            Pu_per_usuDAO dao = new Pu_per_usuDAO(getdAOFactoryCluster());
            Pu_per_usuT pu_per_usuT = new Pu_per_usuT();
            pu_per_usuT.setPer_nr_id(per_perfilT.getPer_nr_id());
            List<Pu_per_usuT> list = dao.getByPer_perfil(pu_per_usuT);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                close();
            } catch (Exception e) {
            }

        }

    }
}
