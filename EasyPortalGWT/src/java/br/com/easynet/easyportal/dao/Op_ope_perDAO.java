package br.com.easynet.easyportal.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Op_ope_perDAO extends ObjectDAOClusterEasyPortal implements IOp_ope_perDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Op_ope_perDAO(DAOFactoryCluster dao) throws Exception {
        //setdAOFactoryCluster(dao);
//        setDAOFactory(dao);
//        con = dao.create();
    }

    public void insert(Op_ope_perT op_ope_perT) throws Exception {
        try {
            String sql = "insert into portal.opm_met_ope_per ( per_nr_id, ope_nr_id, sis_nr_id, met_nr_id) values ( ? , ? , ?, ? )";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, op_ope_perT.getPer_nr_id());
            pStmt.setObject(2, op_ope_perT.getOpe_nr_id());
            pStmt.setObject(3, op_ope_perT.getSis_nr_id());
            pStmt.setObject(4, op_ope_perT.getMet_nr_id());
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

    public void delete(Op_ope_perT op_ope_perT) throws Exception {
        try {
            String sql = "delete from portal.opm_met_ope_per where  per_nr_id=? and ope_nr_id=? and sis_nr_id=? and met_nr_id=? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, op_ope_perT.getPer_nr_id());
            pStmt.setObject(2, op_ope_perT.getOpe_nr_id());
            pStmt.setObject(3, op_ope_perT.getSis_nr_id());
            pStmt.setObject(4, op_ope_perT.getMet_nr_id());
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

    public List<Op_ope_perT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Op_ope_perT> objs = new Vector();
        while (rs.next()) {
            Op_ope_perT op_ope_perT = new Op_ope_perT();
            op_ope_perT.setPer_nr_id(rs.getInt("per_nr_id"));
            op_ope_perT.setOpe_nr_id(rs.getInt("ope_nr_id"));
            op_ope_perT.setSis_nr_id(rs.getInt("sis_nr_id"));
            op_ope_perT.setMet_nr_id(rs.getInt("met_nr_id"));
            objs.add(op_ope_perT);
        }
        return objs;
    }

    public List<Op_ope_perT> getAll() throws Exception {
        try {
            String sql = "select * from portal.opm_met_ope_per";
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

    public List<Op_ope_perT> getById(Op_ope_perT op_ope_perT) throws Exception {
        try {
            String sql = "select * from portal.opm_met_ope_per where  per_nr_id=? and ope_nr_id=? and sis_nr_id=? and met_nr_id=? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, op_ope_perT.getPer_nr_id());
            pStmt.setObject(2, op_ope_perT.getOpe_nr_id());
            pStmt.setObject(3, op_ope_perT.getSis_nr_id());
            pStmt.setObject(4, op_ope_perT.getMet_nr_id());
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

    public List<Op_ope_perT> getByPer_nr_id(Op_ope_perT op_ope_perT) throws Exception {
        try {
            String sql = "select * from portal.opm_met_ope_per where  per_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, op_ope_perT.getPer_nr_id());
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

    public List<Op_ope_perT> getByOpe_nr_id(Op_ope_perT op_ope_perT) throws Exception {
        try {
            String sql = "select * from portal.opm_met_ope_per where  ope_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, op_ope_perT.getOpe_nr_id());
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

    public List<Op_ope_perT> getBySis_nr_id(Op_ope_perT op_ope_perT) throws Exception {
        try {
            String sql = "select * from portal.opm_met_ope_per where  sis_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, op_ope_perT.getSis_nr_id());
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
     * Many to One
     */
    public Per_perfilT getByPer_perfilT(Op_ope_perT op_ope_perT) throws Exception {
        try {
            Per_perfilDAO dao = new Per_perfilDAO(getdAOFactoryCluster());
            Per_perfilT per_perfilT = new Per_perfilT();
            per_perfilT.setPer_nr_id(op_ope_perT.getPer_nr_id());
            List<Per_perfilT> list = dao.getById(per_perfilT);
            return list.size() > 0 ? list.get(0) : null;
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
     * Many to One
     */
    public Ope_operacaoT getByOpe_operacaoT(Op_ope_perT op_ope_perT) throws Exception {
        try {
            Ope_operacaoDAO dao = new Ope_operacaoDAO(getdAOFactoryCluster());
            Ope_operacaoT ope_operacaoT = new Ope_operacaoT();
            ope_operacaoT.setOpe_nr_id(op_ope_perT.getOpe_nr_id());
            ope_operacaoT.setSis_nr_id(op_ope_perT.getSis_nr_id());
            List<Ope_operacaoT> list = dao.getById(ope_operacaoT);
            return list.size() > 0 ? list.get(0) : null;
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

    public List<Ope_operacaoT> getOperacoes() throws Exception {
        return null;
    }

    /**
     * Metodos FK
     */
    public List<Op_ope_perT> getByPer_perfil(Op_ope_perT op_ope_perT) throws Exception {
        try {
            String sql = "select * from portal.op_ope_per where per_perfil.per_nr_id=?  ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, op_ope_perT.getPer_nr_id());
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
     * Metodos FK
     */
    public List<Op_ope_perT> getByOpe_operacao(Op_ope_perT op_ope_perT) throws Exception {
        try {
            String sql = "select * from portal.op_ope_per where ope_operacao.ope_nr_id=? and ope_operacao.sis_nr_id=?  ";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, op_ope_perT.getOpe_nr_id());
            pStmt.setObject(2, op_ope_perT.getSis_nr_id());
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
