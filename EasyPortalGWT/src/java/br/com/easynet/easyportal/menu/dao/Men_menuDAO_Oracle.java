package br.com.easynet.easyportal.menu.dao;

import java.util.*;
import java.sql.*;
import br.com.easynet.database.DataSet;
import br.com.easynet.easyportal.dao.ObjectDAOClusterEasyPortal;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.menu.transfer.*;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Usu_usuarioT;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;

public class Men_menuDAO_Oracle extends ObjectDAOClusterEasyPortal implements IMen_menuDAO {

    private ResultSet rs = null;
    private PreparedStatement pStmt = null;

    public Men_menuDAO_Oracle(DAOFactoryCluster dao) throws Exception {
        //setDAOFactory(dao);
        //con = dao.create();
        //setdAOFactoryCluster(dao);
    }

    public void insert(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "insert into usr_portal.men_menu  ( supermenu_nr_id, men_tx_status, men_tx_nome, men_nr_ordem, men_tx_url, sis_nr_id, men_tx_acao, men_tx_icone, men_tx_tipo) values ( ? , ? , ? , ? , ?, ?, ?, ?, ? )";
            //PreparedStatement pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getSupermenu_nr_id());
            pStmt.setObject(2, men_menuT.getMen_tx_status());
            pStmt.setObject(3, men_menuT.getMen_tx_nome());
            pStmt.setObject(4, men_menuT.getMen_nr_ordem());
            pStmt.setObject(5, men_menuT.getMen_tx_url());
            pStmt.setObject(6, men_menuT.getSis_nr_id());
            pStmt.setObject(7, men_menuT.getMen_tx_acao());
            pStmt.setObject(8, men_menuT.getMen_tx_icone());
            pStmt.setObject(9, men_menuT.getMen_tx_tipo());
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

    public void update(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "update usr_portal.men_menu set  supermenu_nr_id=?, men_tx_status=?, men_tx_nome=?, men_nr_ordem=?, men_tx_url=?, sis_nr_id=?, men_tx_acao=?, men_tx_icone=?, men_tx_tipo=?  where  men_nr_id=?";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, men_menuT.getSupermenu_nr_id());
            pStmt.setObject(2, men_menuT.getMen_tx_status());
            pStmt.setObject(3, men_menuT.getMen_tx_nome());
            pStmt.setObject(4, men_menuT.getMen_nr_ordem());
            pStmt.setObject(5, men_menuT.getMen_tx_url());
            pStmt.setObject(6, men_menuT.getSis_nr_id());
            pStmt.setObject(7, men_menuT.getMen_tx_acao());
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
        }

    }

    public void delete(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "delete from usr_portal.men_menu where  men_nr_id=?";
            //pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Men_menuT> resultSetToObjectTransfer(ResultSet rs) throws Exception {
        List<Men_menuT> objs = new Vector();
        while (rs.next()) {
            Men_menuT men_menuT = new Men_menuT();
            men_menuT.setMen_nr_id(rs.getInt("men_nr_id"));
            men_menuT.setSupermenu_nr_id(rs.getInt("supermenu_nr_id"));
            men_menuT.setMen_tx_status(rs.getString("men_tx_status"));
            men_menuT.setMen_tx_nome(rs.getString("men_tx_nome"));
            men_menuT.setMen_nr_ordem(rs.getInt("men_nr_ordem"));
            men_menuT.setMen_tx_url(rs.getString("men_tx_url"));
            men_menuT.setSis_nr_id(rs.getInt("sis_nr_id"));
            men_menuT.setMen_tx_acao(rs.getString("men_tx_acao"));
            men_menuT.setMen_tx_icone(rs.getString("men_tx_icone"));
            try {
                men_menuT.setMen_tx_tipo(rs.getString("men_tx_tipo"));
            } catch (Exception e) {
            }
            objs.add(men_menuT);
        }
        return objs;
    }

    /**
     * Obtém os submenu menu de um menu
     *
     * @param usu_usuarioT
     * @return
     * @throws java.lang.Exception
     */
    public List<Men_menuT> getSubMenu(Men_menuT menT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu men where men.supermenu_nr_id=? order by men_nr_ordem, men_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setInt(1, menT.getMen_nr_id());
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

    public List<Men_menuT> getMenuPrimario(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select men.* from usr_portal.pu_per_usu pu, usr_portal.mep_men_per mep, usr_portal.men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and men.men_tx_tipo='M' and  men.supermenu_nr_id=0 order by men.men_nr_ordem, men_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setInt(1, usu_usuarioT.getUsu_nr_id());
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
    public List<Men_menuT> getMenuPrimarioDash(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            //String sql = "select men.* from portal.pu_per_usu pu, portal.mep_men_per mep, portal.men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and  men.supermenu_nr_id=0 order by men.men_nr_ordem, men_tx_nome";
            String sql = "select men.* from usr_portal.pu_per_usu pu, usr_portal.mep_men_per mep, usr_portal.men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and men.men_tx_tipo='A' and  men.supermenu_nr_id=0 order by men.men_nr_ordem, men_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setInt(1, usu_usuarioT.getUsu_nr_id());
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

    public List<Men_menuT> getAllUsuario(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select men.* from usr_portal.pu_per_usu pu, usr_portal.mep_men_per mep, usr_portal.men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' order by men.men_nr_ordem, men_tx_nome";
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
        }
    }

    public List<Men_menuT> getAll() throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu order by men_tx_nome, men_nr_id";
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
        }
    }

    public List<Men_menuT> getAllExceto(Men_menuT menT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu  where men_nr_id <> ? order by men_tx_nome, men_nr_id";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setInt(1, menT.getMen_nr_id());
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

    public List<Men_menuT> getMenuPerfil(Per_perfilT perT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where men_nr_id in (select men_nr_id from usr_portal.mep_men_per where per_nr_id=?) order by men_nr_ordem, men_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setInt(1, perT.getPer_nr_id());
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

    public List<Men_menuT> getMenuNaoPerfil(Per_perfilT perT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where men_nr_id not in (select men_nr_id from usr_portal.mep_men_per where per_nr_id=?) order by men_nr_ordem, men_tx_nome";
            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setInt(1, perT.getPer_nr_id());
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

    public List<Men_menuT> getById(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where  men_nr_id=?";
            //pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Men_menuT> getBySistema(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where  sis_nr_id=?";
            //pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Men_menuT> getByMen_nr_id(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where  men_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Men_menuT> getBySupermenu_nr_id(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where  supermenu_nr_id = ? ";
            //pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Men_menuT> getByMen_tx_status(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where  Upper(men_tx_status) like Upper(?) ";
            //pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Men_menuT> getByMen_tx_nome(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where  Upper(men_tx_nome) like Upper(?) ";
            //pStmt = con.prepareStatement(sql);
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
        }

    }

    public List<Men_menuT> getByMen_nr_ordem(Men_menuT men_menuT) throws Exception {
        try {
            String sql = "select * from usr_portal.men_menu where  men_nr_ordem = ? ";
            //pStmt = con.prepareStatement(sql);
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
        }

    }

    /**
     * One To Many
     */
    public List<Mep_men_perT> getByMep_men_perTs(Men_menuT men_menuT) throws Exception {
        IMep_men_perDAO dao = new Mep_men_perDAO(getdAOFactoryCluster());
        Mep_men_perT mep_men_perT = new Mep_men_perT();
        mep_men_perT.setMen_nr_id(men_menuT.getMen_nr_id());
        List<Mep_men_perT> list = dao.getByMen_menu(mep_men_perT);
        return list;
    }

    public List<Men_menuT> getAllUsuario(Usu_usuarioT usu_usuarioT, String sistema) throws Exception {
        try {
            String sql = "select distinct men.* from portal.pu_per_usu pu, portal.mep_men_per mep, portal.men_menu men, portal.sis_sistema sis where sis.sis_tx_nome=? and pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and sis.sis_nr_id=men.sis_nr_id order by men.men_nr_ordem, men_tx_nome";

            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, sistema);
            pStmt.setObject(2, usu_usuarioT.getUsu_nr_id());
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

    public List<Men_menuT> getMenuPrimario(Usu_usuarioT usu_usuarioT, String sistema) throws Exception {
        try {
            String sql = "select distinct men.* from usr_portal.pu_per_usu pu, usr_portal.mep_men_per mep, usr_portal.men_menu men, usr_portal.sis_sistema sis where sis.sis_tx_nome='?' and pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and  men.supermenu_nr_id=0 and men.sis_nr_id=sis.sis_nr_id order by men.men_nr_ordem, men_tx_nome";

            //pStmt = con.prepareStatement(sql);
            pStmt = createPrepareStatment(sql);
            pStmt.setObject(1, sistema);
            pStmt.setInt(2, usu_usuarioT.getUsu_nr_id());
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

    @Override
    public List<Men_menuT> getAllUsuarioDash(Usu_usuarioT usu_usuarioT) throws Exception {
        try {
            String sql = "select distinct men.* from pu_per_usu pu, mep_men_per mep, men_menu men where pu.usu_nr_id=? and pu.per_nr_id=mep.per_nr_id and  mep.men_nr_id=men.men_nr_id and men.men_tx_status='A' and men.men_tx_tipo='A' order by men.men_nr_ordem, men_tx_nome";
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
        }
    }
}