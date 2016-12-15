/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.sql;

import br.com.jdragon.dao.DAOFactory;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;
import java.util.TreeMap;
import org.apache.log4j.Logger;

/**
 *
 * @author geoleite
 */
public class SqlEasyDatabase {

    private int databaseType = DAOFactory.POSTGRESQL;
    private String className;
    private static Hashtable<Integer, Properties> SQL_CACHE = null;
    protected Logger easyLogger = Logger.getLogger(this.getClass());
    public SqlEasyDatabase() {
        className = "";
        String name = getClass().getName();
        String[] parts = name.split("\\.");
        if (parts.length > 0) {
            className = parts[parts.length - 1];
        } else {
            className = name;
        }
    }

    /**
     * Ler o arquivo de propriedades e caso nao encontre lê o arquivo padrao
     * @param database
     * @return
     * @throws Exception
     */
    private Properties getFile(String database) throws Exception {
        Properties proper = new Properties();
        InputStream is = null;
        try {
            //Lendo arquivo padrao
            StringBuffer sbName = new StringBuffer();
            sbName.append(className).append(".properties");
            is = getClass().getResourceAsStream(sbName.toString());
            proper.load(is);
            
            //Tentando ler arquivo específico
            sbName = new StringBuffer();
            sbName.append(className).append("_").append(database).append(".properties");
            is = getClass().getResourceAsStream(sbName.toString());
            proper.load(is);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
        return proper;
    }

    /**
     * Obtem o comando sql do arquivo de propriedades de acordoc com o banco definido
     * @param sqlId
     * @return
     * @throws Exception
     */
    public String getSql(String sqlId) throws Exception {
        Properties properties = null;
        if (SQL_CACHE == null) {
            SQL_CACHE = new Hashtable<Integer, Properties>();
        }
        switch (databaseType) {
            case DAOFactory.POSTGRESQL:
                if (!SQL_CACHE.contains(DAOFactory.POSTGRESQL)) {
                    properties = getFile("postgresql");
                    SQL_CACHE.put(DAOFactory.POSTGRESQL, properties);
                } else {
                    properties = SQL_CACHE.get(DAOFactory.POSTGRESQL);
                }
                break;
            case DAOFactory.FIREBIRD:

                if (!SQL_CACHE.contains(DAOFactory.FIREBIRD)) {
                    properties = getFile("firebird");
                    SQL_CACHE.put(DAOFactory.FIREBIRD, properties);
                } else {
                    properties = SQL_CACHE.get(DAOFactory.FIREBIRD);
                }

                break;
            case DAOFactory.MYSQL:
                if (!SQL_CACHE.contains(DAOFactory.MYSQL)) {
                    properties = getFile("MYSQL");
                    SQL_CACHE.put(DAOFactory.MYSQL, properties);
                } else {
                    properties = SQL_CACHE.get(DAOFactory.MYSQL);
                }
                break;
            case DAOFactory.SQLSERVER:
                if (!SQL_CACHE.contains(DAOFactory.SQLSERVER)) {
                    properties = getFile("sqlserver");
                    SQL_CACHE.put(DAOFactory.SQLSERVER, properties);
                } else {
                    properties = SQL_CACHE.get(DAOFactory.SQLSERVER);
                }

                break;
            case DAOFactory.SQLSERVER2005:
                if (!SQL_CACHE.contains(DAOFactory.SQLSERVER2005)) {
                    properties = getFile("sqlserver2005");
                    SQL_CACHE.put(DAOFactory.SQLSERVER2005, properties);
                } else {
                    properties = SQL_CACHE.get(DAOFactory.SQLSERVER2005);
                }
                break;
            case DAOFactory.ORACLE:
                if (!SQL_CACHE.contains(DAOFactory.ORACLE)) {
                    properties = getFile("oracle");
                    SQL_CACHE.put(DAOFactory.ORACLE, properties);
                } else {
                    properties = SQL_CACHE.get(DAOFactory.ORACLE);
                }
                break;
        }
        String sql = "";
        if (properties != null) {
            sql = properties.getProperty(sqlId);
        }
        return sql;
    }

    /**
     * @return the databaseType
     */
    public int getDatabaseType() {
        return databaseType;
    }

    /**
     * @param databaseType the databaseType to set
     */
    public void setDatabaseType(int databaseType) {
        this.databaseType = databaseType;
    }
}
