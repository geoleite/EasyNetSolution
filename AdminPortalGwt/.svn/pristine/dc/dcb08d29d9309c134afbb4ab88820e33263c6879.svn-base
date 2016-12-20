/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9.portal.server.dao;

import br.com.easynet.database.ConnectionRPL;
import br.com.easynet.datasource.DataSource;
import static br.com.easynet.easyportal.bl.EasyPortalBusinessBase.PATH_LOG_CLUSTER;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.ObjectDAOCluster;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author georgejunior
 */
public class ObjectDAOClusterAdminPortal extends ObjectDAOCluster{
    protected Logger easyLogger = Logger.getLogger(this.getClass());
    protected final static String DATASOURCE_PROPERTIES_FILE_EASYPORTAL="datasource_easyportal.properties";
    protected static Properties propsConnection;
    private static DataSource datasource = null;    
    private static String DB_MODULE = "EasyPortal";
    private static String sinal = "sinalizacao";
    
    public ObjectDAOClusterAdminPortal() {
        super();
        synchronized (sinal) {
            if (propsConnection == null) {
                try {
                    InputStream is = DataSource.class.getResourceAsStream(DATASOURCE_PROPERTIES_FILE_EASYPORTAL);
                    propsConnection = DataSource.getProperties(is);
                    datasource = new DataSource(propsConnection);
                } catch (Exception e) {
                    easyLogger.error(e.getMessage(), e);
                }
            }

            DAOFactoryCluster dao = new DAOFactoryCluster();
            dao.addConnectionRPL(getConnectionRplPortal(DB_MODULE));
            setdAOFactoryCluster(dao);
        }
    }
    
    public synchronized ConnectionRPL getConnectionRplPortal(String nameConnection) {
        try {
            ConnectionRPL conRPL = new ConnectionRPL();
            conRPL.setPathLog(PATH_LOG_CLUSTER);
            conRPL.setNome(nameConnection);
            conRPL.setDatasource(datasource);
            conRPL.recoverLog();
            return conRPL;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void close() {
        super.close(); //To change body of generated methods, choose Tools | Templates.
        try {
            StringBuffer sb =  new StringBuffer();
            sb.append(DB_MODULE).append(" Max Con:").append(datasource.getCpds().getMaxPoolSize())
                    .append(" Min Con:").append(datasource.getCpds().getMinPoolSize())
                    .append(" Num Con Abertas:").append(datasource.getCpds().getNumConnectionsDefaultUser())
                    .append(" - Num Ocupadas :").append(datasource.getCpds().getNumBusyConnectionsDefaultUser());
            
            easyLogger.info(sb.toString());
        } catch (SQLException ex) {
            easyLogger.error(ex.getMessage(), ex);
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        close();
    }
}
