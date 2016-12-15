/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.easynet.easyportal.ws;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.*;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO;
import br.com.easynet.easyportal.menu.dao.Mep_men_perDAO;
/**
 *
 * @author geoleite
 */
public class SystemBaseWS {
    
    private int typeDatabase = DAOFactory.POSTGRESQL;
    private String url1 = "jdbc:postgresql://pgsql.uni5.net:5432/geoleite";
    private String url2 = "jdbc:postgresql://127.0.0.1:5432/smtt";
    private String user = "postgres";
    private String pass = "postgres";
    private DAOFactory dao;
    private String datasourceName = "";

    public DAOFactory getDAO() throws Exception {
        String ip = java.net.InetAddress.getLocalHost().toString();
        String url = url2;
        if (ip.indexOf("zante") > -1) {
            url = url1;
        }
        if (dao != null && !dao.getConnection().isClosed()) {
            return dao;
        }
        return getDAO(typeDatabase, url, user, pass);
    }

    public DAOFactory getDAO(int typeDatabase, String url, String user, String pass) throws Exception {
        return DAOFactory.getDAOFactory(typeDatabase, url, user, pass);
    }

    public void close() {
        try {
            dao.close();
            dao = null;
        } catch (Exception e) {

        }
    }
//
//    public Op_ope_perDAO getOp_ope_perDAO() throws Exception {
//        dao = getDAO();
//        return new Op_ope_perDAO(dao);
//    }
//
//    public Ope_operacaoDAO getOpe_operacaoDAO() throws Exception {
//        dao = getDAO();
//        return new Ope_operacaoDAO(dao);
//    }
//
//    public Per_perfilDAO getPer_perfilDAO() throws Exception {
//        dao = getDAO();
//        return new Per_perfilDAO(dao);
//    }
//
//    public Pu_per_usuDAO getPu_per_usuDAO() throws Exception {
//        dao = getDAO();
//        return new Pu_per_usuDAO(dao);
//    }
//
//    public Sis_sistemaDAO getSis_sistemaDAO() throws Exception {
//        dao = getDAO();
//        return new Sis_sistemaDAO(dao);
//    }
//
//    public Usu_usuarioDAO getUsu_usuarioDAO() throws Exception {
//        dao = getDAO();
//        return new Usu_usuarioDAO(dao);
//    }
//
//    public Met_metodoDAO getMet_metodoDAO() throws Exception {
//        dao = getDAO();
//        return new Met_metodoDAO(dao);
//    }
//
//
//    public Men_menuDAO getMen_menuDAO() throws Exception {
//        dao = getDAO();
//        return new Men_menuDAO(dao);
//    }
//
//    public Mep_men_perDAO getMep_men_perDAO() throws Exception {
//        dao = getDAO();
//        return new Mep_men_perDAO(dao);
//    }

}
