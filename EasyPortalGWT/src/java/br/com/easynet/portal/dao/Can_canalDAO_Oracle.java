package br.com.easynet.portal.dao;

import java.util.*;
import java.sql.*;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.transfer.*;

public class Can_canalDAO_Oracle extends ObjectDAO implements ICan_canalDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Can_canalDAO_Oracle(DAOFactory dao) throws Exception {
        setDAOFactory(dao);
        con = dao.create();
    }

    public void insert(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "insert into usr_portal.can_canal  ( can_tx_nome, can_tx_descricao, can_tx_url, can_tx_status, can_tx_border, can_tx_estado, can_tx_iframe) values ( ? , ? , ? , ? , ? , ? , ? )";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_canalT.getCan_tx_nome());
            pStmt.setObject(2, can_canalT.getCan_tx_descricao());
            pStmt.setObject(3, can_canalT.getCan_tx_url());
            pStmt.setObject(4, can_canalT.getCan_tx_status());
            pStmt.setObject(5, can_canalT.getCan_tx_border());
            pStmt.setObject(6, can_canalT.getCan_tx_estado());
            pStmt.setObject(7, can_canalT.getCan_tx_iframe());
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

    public void update(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "update usr_portal.can_canal set  can_tx_nome=?, can_tx_descricao=?, can_tx_url=?, can_tx_status=?, can_tx_border=?, can_tx_estado=?, can_tx_iframe=?  where  can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_canalT.getCan_tx_nome());
            pStmt.setObject(2, can_canalT.getCan_tx_descricao());
            pStmt.setObject(3, can_canalT.getCan_tx_url());
            pStmt.setObject(4, can_canalT.getCan_tx_status());
            pStmt.setObject(5, can_canalT.getCan_tx_border());
            pStmt.setObject(6, can_canalT.getCan_tx_estado());
            pStmt.setObject(7, can_canalT.getCan_tx_iframe());
            pStmt.setObject(8, can_canalT.getCan_nr_id());
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

    public void delete(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "delete from usr_portal.can_canal where  can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_canalT.getCan_nr_id());
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

    public List<Can_canalT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Can_canalT> objs = new Vector();
        while (rs.next()) {
            Can_canalT can_canalT = new Can_canalT();
            can_canalT.setCan_nr_id(rs.getInt("can_nr_id"));
            can_canalT.setCan_tx_nome(rs.getString("can_tx_nome"));
            can_canalT.setCan_tx_descricao(rs.getString("can_tx_descricao"));
            can_canalT.setCan_tx_url(rs.getString("can_tx_url"));
            can_canalT.setCan_tx_status(rs.getString("can_tx_status"));
            can_canalT.setCan_tx_border(rs.getString("can_tx_border"));
            can_canalT.setCan_tx_estado(rs.getString("can_tx_estado"));
            can_canalT.setCan_tx_iframe(rs.getString("can_tx_iframe"));
            objs.add(can_canalT);
        }
        return objs;
    }

    public List<Can_canalT> getAll() throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal";
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

    public List<Can_canalT> getAllPortal(Por_portalT porT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal can where can.can_nr_id in (select can_nr_id from usr_portal.can_por where por_nr_id=?) order by can.can_tx_nome";
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

    public List<Can_canalT> getAllNaoPortal(Por_portalT porT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal can where can.can_nr_id not in (select can_nr_id from usr_portal.can_por where por_nr_id=?) order by can.can_tx_nome";
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

    public List<Can_canalT> getById(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal where  can_nr_id=?";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, can_canalT.getCan_nr_id());
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

    public List<Can_canalT> getByPortal(Por_portalT portalT) throws Exception {
        try {
            String sql = "select can.* from usr_portal.can_canal can, usr_portal.can_por cp where  cp.por_nr_id=? and cp.can_nr_id=can.can_nr_id and can.can_tx_status='A' and can.can_tx_estado <>'CLO' order by cp.cp_nr_ordem";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, portalT.getPor_nr_id());
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

    public List<Can_canalT> getByCan_tx_nome(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal where  Upper(can_tx_nome) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + can_canalT.getCan_tx_nome() + '%');
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

    public List<Can_canalT> getByCan_tx_descricao(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal where  Upper(can_tx_descricao) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + can_canalT.getCan_tx_descricao() + '%');
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

    public List<Can_canalT> getByCan_tx_url(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal where  Upper(can_tx_url) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + can_canalT.getCan_tx_url() + '%');
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

    public List<Can_canalT> getByCan_tx_status(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal where  Upper(can_tx_status) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + can_canalT.getCan_tx_status() + '%');
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

    public List<Can_canalT> getByCan_tx_border(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal where  Upper(can_tx_border) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + can_canalT.getCan_tx_border() + '%');
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

    public List<Can_canalT> getByCan_tx_estado(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal where  Upper(can_tx_estado) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + can_canalT.getCan_tx_estado() + '%');
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

    public List<Can_canalT> getByCan_tx_iframe(Can_canalT can_canalT) throws Exception {
        try {
            String sql = "select * from usr_portal.can_canal where  Upper(can_tx_iframe) like Upper(?) ";
            pStmt = con.prepareStatement(sql);
            pStmt.setObject(1, '%' + can_canalT.getCan_tx_iframe() + '%');
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