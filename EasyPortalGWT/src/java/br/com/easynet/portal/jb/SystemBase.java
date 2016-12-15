package br.com.easynet.portal.jb;

import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.*;
import br.com.easynet.portal.dao.*;
import br.com.easynet.portal.transfer.Por_portalT;
import java.util.List;
import javax.sql.DataSource;

public class SystemBase extends EasySecurityJB {

    private int typeDatabase = DAOFactory.POSTGRESQL;
//
//  private String url  = "jdbc:postgresql://pgsql.uni5.net:5432/geoleite";
//  private String user = "geoleite";
//  private String pass = "geoleite2007";
    private String url1 = "jdbc:postgresql://pgsql.geoleite.com.br:5432/geoleite";
    private String url2 = "jdbc:postgresql://127.0.0.1:5432/geoleite";
//    private String url2 = "jdbc:postgresql://lab2a-prof:5432/geoleite";
    private String user = "geoleite";
    private String pass = "geoleite2007";
    private DAOFactory dao;
    private String datasourceName = "java:comp/env/jdbc/easyportalds";//datasource

    public DAOFactory getDAODatasourceEasyPortal() throws Exception {
/*
        if (dao != null && !dao.getConnection().isClosed()) {
            return dao;
        }
        String typeDatabaseParam = getApplication().getInitParameter("type_database");
        if (typeDatabaseParam != null) {
            if ("ORACLE".equalsIgnoreCase(typeDatabaseParam)) {
                typeDatabase = DAOFactory.ORACLE;
            } else if ("Postgresql".equalsIgnoreCase(typeDatabaseParam)) {
                typeDatabase = DAOFactory.POSTGRESQL;
            } else if ("SqlServer".equalsIgnoreCase(typeDatabaseParam)) {
                typeDatabase = DAOFactory.SQLSERVER;
            } else if ("SqlServer2005".equalsIgnoreCase(typeDatabaseParam)) {
                typeDatabase = DAOFactory.SQLSERVER2005;
            } else if ("MySql".equalsIgnoreCase(typeDatabaseParam)) {
                typeDatabase = DAOFactory.MYSQL;
            }
        }

        String dsName = getApplication().getInitParameter("datasource_name");
        if (dsName != null) {
            datasourceName = dsName;
        }
        DataSource ds = getDataSource(datasourceName);
        dao = DAOFactory.getDAOFactory(typeDatabase, ds.getConnection());
        return dao;
//        return getDAOEasyPortal();
        */
        return null;
    }

    public DAOFactory getDAOEasyPortal1() throws Exception {
/*
        if (dao != null && !dao.getConnection().isClosed()) {
            return dao;
        }

        String urlWebConfig = getApplication().getInitParameter("urljdbc");
        String userWebConfig = getApplication().getInitParameter("userjdbc");
        String passwordWebConfig = getApplication().getInitParameter("passwordjdbc");
        if (urlWebConfig != null) {
            url1 = urlWebConfig;
            user = userWebConfig;
            pass = passwordWebConfig;
        } else {
            return getDAODatasourceEasyPortal();
        }

        return getDAO(typeDatabase, url1, user, pass);
*/
        return null;
    }

    public DAOFactory getDAO() throws Exception {
        /*
        String ip = java.net.InetAddress.getLocalHost().toString();
        String url = url2;
        if (ip.indexOf("zante") > -1) {
            url = url1;
        }

        String urlWebConfig = getApplication().getInitParameter("urljdbc");
        String userWebConfig = getApplication().getInitParameter("userjdbc");
        String passwordWebConfig = getApplication().getInitParameter("passwordjdbc");
        if (urlWebConfig != null) {
            url = urlWebConfig;
            user = userWebConfig;
            pass = passwordWebConfig;
        }

        if (dao != null && dao.getConnection() != null && !dao.getConnection().isClosed()) {
            return dao;
        }
        return getDAO(typeDatabase, url, user, pass);
                */
        return null;
    }

    public DAOFactory getDAO(int typeDatabase, String url, String user, String pass) throws Exception {
        return DAOFactory.getDAOFactory(typeDatabase, url, user, pass);
    }

    public void close() {
        try {
            if (dao != null) {
                dao.close();
                dao = null;
            }
        } catch (Exception e) {
        }
    }

    public ICan_canalDAO getCan_canalDAO() throws Exception {
        //dao = getDAODatasourceEasyPortal();
        if (typeDatabase == DAOFactory.POSTGRESQL) {
            return new Can_canalDAO(dao);
        } else if (typeDatabase == DAOFactory.ORACLE) {
            return new Can_canalDAO_Oracle(dao);
        }
        return null;
    }

    public ICan_porDAO getCan_porDAO() throws Exception {
        //dao = getDAODatasourceEasyPortal();
        
        if (typeDatabase == DAOFactory.POSTGRESQL) {
            return new Can_porDAO(dao);
        } else if (typeDatabase == DAOFactory.ORACLE) {
            return new Can_porDAO_Oracle(dao);
        }
        return null;
    }

    public IPor_portalDAO getPor_portalDAO() throws Exception {
        //dao = getDAODatasourceEasyPortal();
        
        if (typeDatabase == DAOFactory.POSTGRESQL) {
            return new Por_portalDAO(dao);
        } else if (typeDatabase == DAOFactory.ORACLE) {
            return new Por_portalDAO_Oracle(dao);
        }
        return null;
    }

    public IPor_usuDAO getPor_usuDAO() throws Exception {
        //dao = getDAODatasourceEasyPortal();
        
        if (typeDatabase == DAOFactory.POSTGRESQL) {
            return new Por_usuDAO(dao);
        } else if (typeDatabase == DAOFactory.ORACLE) {
            return new Por_usuDAO_Oracle(dao);
        }
        return null;
    }

    public IUsu_por_canDAO getUsu_por_canDAO() throws Exception {
        dao = getDAO();
        
        if (typeDatabase == DAOFactory.POSTGRESQL) {
            return new Usu_por_canDAO(dao);
        } else if (typeDatabase == DAOFactory.ORACLE) {
            return new Usu_por_canDAO_Oracle(dao);
        }
        return null;
    }

    public Por_portalT findPor_portalT(Por_portalT porT) {
        try {
            List<Por_portalT> list = getPor_portalDAO().getById(porT);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
        } finally {
            close();
        }
        return null;
    }
    
}
