package br.com.easynet.easylog.jb;

import br.com.easynet.database.ConnectionRPL;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.*;
import br.com.easynet.easylog.dao.*;
import br.com.easynet.easylog.transfer.*;
import java.util.List;
import br.com.easynet.easyportal.jb.EasySecurityJB;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.DefinitionConnectionCluster;
import br.com.jdragon.dao.cluster.I9Cluster;
import java.sql.Connection;
import javax.sql.DataSource;

public class SystemBase extends EasySecurityJB {

    private int typeDatabase = DAOFactory.POSTGRESQL;
    private String url = "jdbc:postgresql://127.0.0.1:5432/geoleite";
    private String user = "postgres";
    private String pass = "postgres";
    private DAOFactoryCluster dao;
    private I9Cluster i9Cluster = null;
    private String datasourceName = "";

    public void close() {
        try {
            dao.close();
            dao = null;
        } catch (Exception e) {
        }
    }

    public DAOFactoryCluster getDAO() throws Exception {

        if (dao == null) {
            if (i9Cluster == null) {
                String path = getPage().getServletContext().getRealPath(br.com.easynet.easyportal.jb.SystemBase.fileI9Cluster);
                i9Cluster = I9Cluster.getI9Cluster(path);
                List<DefinitionConnectionCluster> list = i9Cluster.getListConnectionClusters();
                dao = new DAOFactoryCluster();
                for (int i = 0; i < list.size(); i++) {
                    DefinitionConnectionCluster dcc = list.get(i);
                    dao.addConnectionRPL(getConnectionRpl(dcc.getName(), dcc.getDatasourceName()));
                }
            }
        }
        return dao;
    }

    /**
     * Obtem a conexao para o I9Cluster
     * @param nameConnection
     * @param datasourceName
     * @return
     */
    public ConnectionRPL getConnectionRpl(String nameConnection, String datasourceName) {
        try {
            ConnectionRPL conRPL = new ConnectionRPL();
            conRPL.setPathLog(br.com.easynet.easyportal.jb.SystemBase.PATH_LOG_CLUSTER);
            conRPL.setNome(nameConnection);
            conRPL.setCon(getDAODatasourceEasyPortalCluster(datasourceName));
            conRPL.recoverLog();
            return conRPL;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     *
     * @param dataSourceName
     * @return
     * @throws Exception
     */
    public Connection getDAODatasourceEasyPortalCluster(String dataSourceName) throws Exception {

        DataSource ds = getDataSource(dataSourceName);
        return ds.getConnection();
    }

    public ILogDAO getLogDAO() throws Exception {
        dao = getDAO();

        if (typeDatabase == DAOFactory.POSTGRESQL) {
            return new LogDAO(dao);
        } else if (typeDatabase == DAOFactory.ORACLE) {
            return new LogDAO_Oracle(dao);
        }
        return null;
    }

    public LogT findbyIdLog(LogT logT) throws Exception {
        try {
            
            List<LogT> listTemp = getLogDAO().getById(logT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
            setMsg("Falha ao realizar consulta!");
        } finally {
            close();
        }
        return null;
    }
}
