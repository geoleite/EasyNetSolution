package br.com.i9.portal.server.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.jdragon.dao.*;
import br.com.i9.portal.client.portal.portal.transfer.*;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Men_menuDAO extends ObjectDAOClusterAdminPortal implements IMen_menuDAO {

    public Men_menuDAO(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "insert into portal.men_menu  ( supermenu_nr_id, men_tx_status, men_tx_nome, men_nr_ordem, men_tx_url, men_tx_acao, sis_nr_id, men_tx_icone, men_nr_id, men_tx_tipo) values (?, ?, ? , ? , ? , ? , ? , ? , ? , ? )";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getSupermenu_nr_id());
            pStmt.setObject(2, men_menuT.getMen_tx_status());
            pStmt.setObject(3, men_menuT.getMen_tx_nome());
            pStmt.setObject(4, men_menuT.getMen_nr_ordem());
            pStmt.setObject(5, men_menuT.getMen_tx_url() != null ? men_menuT.getMen_tx_url() : "");
            pStmt.setObject(6, men_menuT.getMen_tx_acao() != null ? men_menuT.getMen_tx_acao() : "");
            pStmt.setObject(7, men_menuT.getSis_nr_id());
            pStmt.setObject(8, men_menuT.getMen_tx_icone() != null ? men_menuT.getMen_tx_icone() : "");
            pStmt.setObject(9, men_menuT.getMen_tx_tipo());
            pStmt.setObject(10, men_menuT.getMen_nr_id());
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

    public void update(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "update portal.men_menu set  supermenu_nr_id=?, men_tx_status=?, men_tx_nome=?, men_nr_ordem=?, men_tx_url=?, men_tx_acao=?, sis_nr_id=?, men_tx_icone=?, men_tx_tipo=?  where  men_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getSupermenu_nr_id());
            pStmt.setObject(2, men_menuT.getMen_tx_status());
            pStmt.setObject(3, men_menuT.getMen_tx_nome());
            pStmt.setObject(4, men_menuT.getMen_nr_ordem());
            pStmt.setObject(5, men_menuT.getMen_tx_url());
            pStmt.setObject(6, men_menuT.getMen_tx_acao());
            pStmt.setObject(7, men_menuT.getSis_nr_id());
            pStmt.setObject(8, men_menuT.getMen_tx_icone());
            pStmt.setObject(9, men_menuT.getMen_tx_tipo());
            pStmt.setObject(10, men_menuT.getMen_nr_id());
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

    public void delete(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        try {
            String sql = "delete from portal.men_menu where  men_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getMen_nr_id());
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

    public List<Men_menuTGWT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Men_menuTGWT> objs = new Vector();
        while (rs.next()) {
            Men_menuTGWT men_menuT = new Men_menuTGWT();
            men_menuT.setMen_nr_id(rs.getInt("men_nr_id"));
            men_menuT.setSupermenu_nr_id(rs.getInt("supermenu_nr_id"));
            men_menuT.setMen_tx_status(rs.getString("men_tx_status"));
            men_menuT.setMen_tx_nome(rs.getString("men_tx_nome"));
            men_menuT.setMen_nr_ordem(rs.getInt("men_nr_ordem"));
            men_menuT.setMen_tx_url(rs.getString("men_tx_url"));
            men_menuT.setMen_tx_acao(rs.getString("men_tx_acao"));
            men_menuT.setSis_nr_id(rs.getInt("sis_nr_id"));
            men_menuT.setMen_tx_icone(rs.getString("men_tx_icone"));
            try {
                men_menuT.setMen_tx_tipo(rs.getString("men_tx_tipo"));
            } catch (Exception e) {
            }
            objs.add(men_menuT);
        }
        return objs;
    }

    public List<Men_menuTGWT> getAll() throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu order by men_nr_ordem, men_tx_nome";
            pStmt = createPrepareStatment(sql);
            rs = pStmt.executeQuery();
            List<Men_menuTGWT> list = resultSetToObjectTransfer(rs);
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

    public List<Men_menuTGWT> getByPK(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  men_nr_id=?";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getMen_nr_id());
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

    public List<Men_menuTGWT> getByMen_nr_id(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  men_nr_id = ? ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getMen_nr_id());
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

    public List<Men_menuTGWT> getByPerfil(Per_perfilTGWT perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu men where men.men_nr_id  in (select distinct men_nr_id from portal.mep_men_per where per_nr_id=?)  order by men_nr_ordem, men_tx_nome";
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

    public List<Men_menuTGWT> getByNotPerfil(Per_perfilTGWT perT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu men where men.men_nr_id not in (select distinct men_nr_id from portal.mep_men_per where per_nr_id=?)  order by men_nr_ordem, men_tx_nome";
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

    public List<Men_menuTGWT> getBySupermenu_nr_id(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  supermenu_nr_id = ? order by men_nr_ordem, men_tx_nome";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getSupermenu_nr_id());
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

    public List<Men_menuTGWT> getByMen_tx_status(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  Upper(men_tx_status) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + men_menuT.getMen_tx_status() + '%');
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

    public List<Men_menuTGWT> getByMen_tx_nome(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  Upper(men_tx_nome) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + men_menuT.getMen_tx_nome() + '%');
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

    public List<Men_menuTGWT> getByMen_nr_ordem(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  men_nr_ordem = ? order by men_nr_ordem";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getMen_nr_ordem());
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

    public List<Men_menuTGWT> getByMen_tx_url(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  Upper(men_tx_url) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + men_menuT.getMen_tx_url() + '%');
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

    public List<Men_menuTGWT> getByMen_tx_acao(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  Upper(men_tx_acao) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + men_menuT.getMen_tx_acao() + '%');
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

    public List<Men_menuTGWT> getBySis_nr_id(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  sis_nr_id = ? order by men_nr_ordem, men_tx_nome";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getSis_nr_id());
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

    public List<Men_menuTGWT> getByMen_tx_icone(Men_menuTGWT men_menuT) throws Exception {
        PreparedStatement pStmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from portal.men_menu where  Upper(men_tx_icone) like Upper(?) ";
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, '%' + men_menuT.getMen_tx_icone() + '%');
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
