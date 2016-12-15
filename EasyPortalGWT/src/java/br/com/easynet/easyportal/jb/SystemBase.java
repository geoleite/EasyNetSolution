package br.com.easynet.easyportal.jb;

import br.com.easynet.database.ConnectionRPL;
import br.com.easynet.easylog.dao.ILogDAO;
import br.com.easynet.jb.BeanBase;
import br.com.jdragon.dao.*;
import br.com.easynet.easylog.dao.LogDAO;
import br.com.easynet.easylog.dao.LogDAO_Mysql;
import br.com.easynet.easylog.dao.LogDAO_Oracle;
import br.com.easynet.easyportal.bl.EasyPortalBusinessBase;
import br.com.easynet.easyportal.cluster.ConfigI9Cluster;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.menu.dao.IMen_menuDAO;
import br.com.easynet.easyportal.menu.dao.IMep_men_perDAO;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO_Mysql;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO_Oracle;
import br.com.easynet.easyportal.menu.dao.Mep_men_perDAO;
import br.com.easynet.easyportal.menu.dao.Mep_men_perDAO_Mysql;
import br.com.easynet.easyportal.menu.dao.Mep_men_perDAO_Oracle;
import br.com.easynet.easyportal.transfer.Con_componente_negadoT;
import br.com.easynet.easyportal.transfer.Int_interfaceT;
import br.com.easynet.easyportal.transfer.Mod_moduloT;
import br.com.easynet.easyportal.transfer.Mop_mod_perT;
import br.com.easynet.easyportal.transfer.Par_parametroT;
import br.com.easynet.easyportal.transfer.Per_perfilT;
import br.com.easynet.easyportal.transfer.Pi_per_intT;
import br.com.easynet.easyportal.transfer.Sis_sistemaT;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.DefinitionConnectionCluster;
import br.com.jdragon.dao.cluster.I9Cluster;
import java.io.File;
import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class SystemBase extends BeanBase {

    public static String PATH_LOG_CLUSTER = "/Users/geoleite/pathLog";//"/home/geoleite/pathLog";
    public static String fileI9Cluster = "/easyportalcluster.xml";
    public static String realFileI9Cluster = null;
    private int typeDatabase = DAOFactory.POSTGRESQL;
    private DAOFactoryCluster dao;
    public final static String REDIRECT_SESSION = "redirect_sessao";
    private String redirect = "";
    private static I9Cluster i9Cluster = null;
    public final static String SISTEMA_EASYPORTAL = "EASYPORTAL";
    private static final Object SINALIZADOR_AUTOINCREMENTO = "sinalizacao autoincremento";
    public static final TreeMap<String, String> MAP_SINALIZADOR = new TreeMap<String, String>();
    //private String datasourceName = "java:comp/env/jdbc/easyportalds";

    /**
     * Limpa a o hastable com os parametros
     */
    public void clearParametros() {
//        parametroBDPortal.clear();
//        sistemas.clear();
    }

    private boolean valideLogSystem(String path) {
        if (path != null) {
            int posF = path.lastIndexOf("/");
            if (posF > 1) {
                String pathReal = path.substring(0, posF);
                File file = new File(pathReal);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            return true;
        }
        return false;
    }

    public void pageLoad() throws Exception {
        super.pageLoad();
        realFileI9Cluster = ConfigI9Cluster.REAL_PATH_BASE + fileI9Cluster;
        boolean debug = false;
        String logSystem = "./easyportal.log";
        try {
            Par_parametroT parT = getParametro(SISTEMA_EASYPORTAL, "DEBUG");
            String debugStr = "false";
            if (parT != null) {
                debugStr = parT.getPar_tx_valor();
            }
//            parT = getParametro(SISTEMA_EASYPORTAL, "LogSystem");
//
//            if (parT != null) {
//                String temp = parT.getPar_tx_valor();
//                File fileLog = new File(temp);
//                if (valideLogSystem(temp)) {
//                    logSystem = temp;
//                }
//            }
            debug = Boolean.parseBoolean(debugStr);
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        //DebugMessage.setDebug(debug, logSystem);
    }

    public DAOFactoryCluster getDAOEasyPortal() throws Exception {
/*
        if (dao == null) {
            try {
                if (getApplication() != null) {
                    String typeDatabaseParam = getApplication().getInitParameter("type_database");
                    if (typeDatabaseParam != null) {
                        if ("ORACLE".equalsIgnoreCase(typeDatabaseParam)) {
                            setTypeDatabase(DAOFactory.ORACLE);
                        } else if ("Postgresql".equalsIgnoreCase(typeDatabaseParam)) {
                            setTypeDatabase(DAOFactory.POSTGRESQL);
                        } else if ("SqlServer".equalsIgnoreCase(typeDatabaseParam)) {
                            setTypeDatabase(DAOFactory.SQLSERVER);
                        } else if ("SqlServer2005".equalsIgnoreCase(typeDatabaseParam)) {
                            setTypeDatabase(DAOFactory.SQLSERVER2005);
                        } else if ("MySql".equalsIgnoreCase(typeDatabaseParam)) {
                            setTypeDatabase(DAOFactory.MYSQL);
                        }
                    }
                }
            } catch (Exception e) {
            }
            if (i9Cluster == null) {
                if (realFileI9Cluster == null) {
                    String path = getPage().getServletContext().getRealPath(fileI9Cluster);
                    realFileI9Cluster = path;
                }
                i9Cluster = I9Cluster.getI9Cluster(realFileI9Cluster);
            }
            List<DefinitionConnectionCluster> list = i9Cluster.getListConnectionClusters();
            dao = new DAOFactoryCluster();
            for (int i = 0; i < list.size(); i++) {
                DefinitionConnectionCluster dcc = list.get(i);
                dao.addConnectionRPL(getConnectionRpl(dcc.getName(), dcc.getDatasourceName()));
            }
        }
        return dao;
*/
        return null;
    }

    /**
     * Obtem a conexao para o I9Cluster
     *
     * @param nameConnection
     * @param datasourceName
     * @return
     */
    public ConnectionRPL getConnectionRpl(String nameConnection, String datasourceName) {
        try {
            ConnectionRPL conRPL = new ConnectionRPL();
            conRPL.setPathLog(PATH_LOG_CLUSTER);
            conRPL.setNome(nameConnection);
            conRPL.setCon(getDAODatasource(datasourceName));
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
    public Connection getDAODatasource(String dataSourceName) throws Exception {

        DataSource ds = getDataSource(dataSourceName);
        return ds.getConnection();
    }
    /*
     public DAOFactory getDAODatasourceEasyPortal() throws Exception {
     if (dao != null && !dao.getConnection().isClosed()) {
     return dao;
     }

     try {
     if (getApplication() != null) {
     String typeDatabaseParam = getApplication().getInitParameter("type_database");
     if (typeDatabaseParam != null) {
     if ("ORACLE".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.ORACLE);
     } else if ("Postgresql".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.POSTGRESQL);
     } else if ("SqlServer".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.SQLSERVER);
     } else if ("SqlServer2005".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.SQLSERVER2005);
     } else if ("MySql".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.MYSQL);
     }
     }

     String dsName = getApplication().getInitParameter("datasource_name");
     if (dsName != null) {
     setDatasourceName(dsName);
     }
     }
     } catch (Exception e) {
     }

     DataSource ds = getDataSource(getDatasourceName());
     dao = DAOFactory.getDAOFactory(getTypeDatabase(), ds.getConnection());
     return dao;
     //        return getDAOEasyPortal();
     }

     public DAOFactory getDAOEasyPortal() throws Exception {
     return getDAODatasourceEasyPortal();
     }

     public DAOFactory getDAO() throws Exception {

     if (dao != null && !dao.getConnection().isClosed()) {
     return dao;
     }
     String typeDatabaseParam = getApplication().getInitParameter("type_database");
     if (typeDatabaseParam != null) {
     if ("ORACLE".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.ORACLE);
     } else if ("Postgresql".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.POSTGRESQL);
     } else if ("SqlServer".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.SQLSERVER);
     } else if ("SqlServer2005".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.SQLSERVER2005);
     } else if ("MySql".equalsIgnoreCase(typeDatabaseParam)) {
     setTypeDatabase(DAOFactory.MYSQL);
     }
     }

     dao = getDAODatasourceEasyPortal();
     return dao;
     }
     */

    public DAOFactory getDAOOld(int typeDatabase, String url, String user, String pass) throws Exception {
        return DAOFactory.getDAOFactory(typeDatabase, url, user, pass);
    }

    public void close() {
        closeDatasourceEasyPortal();
    }

    public void closeDatasourceEasyPortal() {
        try {
            if (dao != null) {
                dao.close();
            }
            dao = null;
            //i9Cluster = null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
    }

    /**
     * Obtém o proximo número auto incremento e grava na tabela de parâmetro
     * seguindo o padrão NomeSistema.NomeTabela.NomeCampo
     *
     * @param sistema
     * @param tabela
     * @param campo
     * @return
     */
    public int getAutoIncremento(String sistema, String tabela, String campo) throws Exception {
        int incremento = -1;
        StringBuffer paramName = new StringBuffer();
        paramName.append(sistema).append(".").append(tabela).append(".").append(campo);
        String key = paramName.toString().toUpperCase();
        if (!MAP_SINALIZADOR.containsKey(key)) {
            MAP_SINALIZADOR.put(key, key);
        }
        String sinalizador = MAP_SINALIZADOR.get(key);
        //Sincroniza utilizando o sistema+tabela+campo
        synchronized (sinalizador) {
            Par_parametroT parT = getParametro(sistema, paramName.toString());
            if (parT == null) {
                Sis_sistemaT sisT = new Sis_sistemaT();
                sisT.setSis_tx_nome(sistema);
                sisT = getSis_sistemaDAO().getBySis_tx_nome(sisT);
                parT = new Par_parametroT();
                parT.setSis_nr_id(sisT.getSis_nr_id());
                parT.setPar_tx_nome(key);
                parT.setPar_tx_valor("1");
                parT.setPar_nr_id(System.currentTimeMillis());
                incremento = 1;
                getPar_parametroDAO().insert(parT);
            } else {
                try {
                    incremento = Integer.parseInt(parT.getPar_tx_valor());
                    incremento++;
                    parT.setPar_tx_valor(String.valueOf(incremento));
                    getPar_parametroDAO().update(parT);
                } catch (Exception e) {
                    easyLogger.debug("Tipo auto incremento nao é numérico: " + key);
                    easyLogger.error(e.getMessage(), e);
                }
            }
        }
        return incremento;
    }

    public int getIncUsuario() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "USU_USUARIO", "USU_NR_ID");
    }

    public int getIncSistema() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "SIS_SISTEMA", "SIS_NR_ID");
    }

    public int getIncMenu() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "MEN_MENU", "MEN_NR_ID");
    }

    public int getIncPerfil() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "PER_PERFIL", "PER_NR_ID");
    }

    public int getIncMetodo() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "MET_METODO", "MET_NR_ID");
    }

    public int getIncOperacao() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "OPE_OPERACAO", "OPE_NR_ID");
    }

    public int getIncInterface() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "INT_INTERFACE", "INT_NR_ID");
    }

    public int getIncComponente() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "CON_COMPONENTE_NEGADO", "CON_NR_ID");
    }

    public int getIncCModulo() throws Exception {
        return getAutoIncremento(SISTEMA_EASYPORTAL, "MOD_MODULO", "MOD_NR_ID");
    }

    /**
     * Obtém o proximo número auto incremento e grava na tabela de parâmetro
     * seguindo o padrão NomeSistema.NomeTabela.NomeCampo
     *
     * @param sistema
     * @param tabela
     * @param campo
     * @return
     * @throws Exception
     */
    public long getAutoIncrementoLong(String sistema, String tabela, String campo) throws Exception {
        long incremento = -1;
        StringBuffer paramName = new StringBuffer();
        paramName.append(sistema).append(".").append(tabela).append(".").append(campo);
        String key = paramName.toString().toUpperCase();
        if (!MAP_SINALIZADOR.containsKey(key)) {
            MAP_SINALIZADOR.put(key, key);
        }
        String sinalizador = MAP_SINALIZADOR.get(key);
        //Sincroniza utilizando o sistema+tabela+campo
        synchronized (sinalizador) {
            Par_parametroT parT = getParametro(sistema, key);
            if (parT == null) {
                Sis_sistemaT sisT = new Sis_sistemaT();
                sisT.setSis_tx_nome(sistema);
                sisT = getSis_sistemaDAO().getBySis_tx_nome(sisT);
                parT = new Par_parametroT();
                parT.setSis_nr_id(sisT.getSis_nr_id());
                parT.setPar_tx_nome(key);
                parT.setPar_tx_valor("1");
                parT.setPar_nr_id(System.currentTimeMillis());
                incremento = 1;
                getPar_parametroDAO().insert(parT);
            } else {
                try {
                    incremento = Long.parseLong(parT.getPar_tx_valor());
                    incremento++;
                    parT.setPar_tx_valor(String.valueOf(incremento));
                    getPar_parametroDAO().update(parT);
                } catch (Exception e) {
                    //easyLogger.debug("Tipo auto incremento nao é numérico: " + key);
                    easyLogger.error(e.getMessage(), e);
                }
            }
        }

        return incremento;
    }

    public String getParametro(String nomeParam) throws Exception {
        Par_parametroT parT = getParametro(SISTEMA_EASYPORTAL, nomeParam);
        return parT == null ? null : parT.getPar_tx_valor();
    }

    public Par_parametroT getParametro(int sisNrId, String nome) throws Exception {
        //Primeiro verifica se já existe o objeto na memória
        return new EasyPortalBusinessBase().getParametro(sisNrId, nome);
//        if (parametroBDPortal.contains(nomeParam)) {
//            parT = parametroBDPortal.get(nomeParam);
//        } else {
//            //Nao existe este parametro no objeto em memoria
//            parT = new Par_parametroT();
//            parT.setSis_nr_id(sisNrId);
//            parT.setPar_tx_nome(nome);
//            parT = getPar_parametroDAO().getByPK(parT);
//            if (parT != null) {
//                parametroBDPortal.put(nomeParam, parT);
//            }
//        }
//        return parT;        
    }

    public Par_parametroT getParametro(String nomeSistema, String nomeParametro) throws Exception {
        return new EasyPortalBusinessBase().getParametro(nomeSistema, nomeParametro);
//        Sis_sistemaT sisT = null;
//        if (sistemas.contains(nomeSistema)) {
//            sisT = sistemas.get(nomeSistema);
//        } else {
//            sisT = new Sis_sistemaT();
//            sisT.setSis_tx_nome(nomeSistema);
//            sisT = getSis_sistemaDAO().getBySis_tx_nome(sisT);
//            sistemas.put(nomeSistema, sisT);
//        }
//        if (sisT == null) {
//            return null;
//        }
//        return getParametro(sisT.getSis_nr_id(), nomeParametro);
    }

    public IOp_ope_perDAO getOp_ope_perDAO() throws Exception {
        //dao = getDAOEasyPortal();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Op_ope_perDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Op_ope_perDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Op_ope_perDAO_Mysql(dao);
        }
        return null;
    }

    public IOpe_operacaoDAO getOpe_operacaoDAO() throws Exception {
        //dao = getDAODatasourceEasyPortal();
        //dao = getDAOEasyPortal();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Ope_operacaoDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Ope_operacaoDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Ope_operacaoDAO_Mysql(dao);
        }
        return null;
    }

    public IPer_perfilDAO getPer_perfilDAO() throws Exception {
        //dao = getDAOEasyPortal();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Per_perfilDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Per_perfilDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Per_perfilDAO_Mysql(dao);
        }
        return null;

    }

    public IPu_per_usuDAO getPu_per_usuDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Pu_per_usuDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Pu_per_usuDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Pu_per_usuDAO_Mysql(dao);
        }
        return null;
    }

    public ISis_sistemaDAO getSis_sistemaDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Sis_sistemaDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Sis_sistemaDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Sis_sistemaDAO_Mysql(dao);
        }
        return null;
    }

    public IUsu_usuarioDAO getUsu_usuarioDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Usu_usuarioDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Usu_usuarioDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Usu_usuarioDAO_Mysql(dao);
        }
        return null;
    }

    public IRes_recall_senhaDAO getRes_recall_senhaDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Res_recall_senhaDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Usu_usuarioDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Usu_usuarioDAO_Mysql(dao);
        }
        return null;
    }

    public IMet_metodoDAO getMet_metodoDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Met_metodoDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Met_metodoDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Met_metodoDAO_Mysql(dao);
        }
        return null;
    }

    public IMen_menuDAO getMen_menuDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Men_menuDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Men_menuDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Men_menuDAO_Mysql(dao);
        }
        return null;
    }

    public IMep_men_perDAO getMep_men_perDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Mep_men_perDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Mep_men_perDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Mep_men_perDAO_Mysql(dao);
        }
        return null;
    }

    public ILogDAO getLogDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new LogDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new LogDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new LogDAO_Mysql(dao);
        }
        return null;
    }

    public ICon_componente_negadoDAO getCon_componente_negadoDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            //return new Con_componente_negadoDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Con_componente_negadoDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Con_componente_negadoDAO_Mysql(dao);
        }
        return null;
    }

    public IInt_interfaceDAO getInt_interfaceDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Int_interfaceDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Int_interfaceDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Int_interfaceDAO_Mysql(dao);
        }
        return null;
    }

    public IPi_per_intDAO getPi_per_intDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Pi_per_intDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Pi_per_intDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Pi_per_intDAO_Mysql(dao);
        }
        return null;
    }

    public IPar_parametroDAO getPar_parametroDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Par_parametroDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            return new Par_parametroDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            return new Par_parametroDAO_Mysql(dao);
        }
        return null;
    }

    public Mod_moduloDAO getMod_moduloDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Mod_moduloDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new LogDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new LogDAO_Mysql(dao);
        }
        return null;

    }

    public Mop_mod_perDAO getMop_mod_perDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Mop_mod_perDAO(dao);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new LogDAO_Oracle(dao);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new LogDAO_Mysql(dao);
        }
        return null;
    }

    public Mod_moduloT findbyIdMod_modulo(Mod_moduloT mod_moduloT) throws Exception {
        try {
            Mod_moduloDAO mod_moduloDAO = getMod_moduloDAO();

            List<Mod_moduloT> listTemp = mod_moduloDAO.getByPK(mod_moduloT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }

    public Mop_mod_perT findbyIdMop_mod_per(Mop_mod_perT mop_mod_perT) throws Exception {
        try {
            Mop_mod_perDAO mop_mod_perDAO = getMop_mod_perDAO();

            List<Mop_mod_perT> listTemp = mop_mod_perDAO.getByPK(mop_mod_perT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }

    public Per_perfilT findPerfil(Per_perfilT perT) {
        try {
            List<Per_perfilT> list = getPer_perfilDAO().getById(perT);
            return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
        } finally {
            close();
        }
        return null;
    }

    public Con_componente_negadoT findbyIdCon_componente_negado(Con_componente_negadoT con_componente_negadoT) throws Exception {
        try {
            ICon_componente_negadoDAO con_componente_negadoDAO = getCon_componente_negadoDAO();

            List<Con_componente_negadoT> listTemp = con_componente_negadoDAO.getByPK(con_componente_negadoT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }

    public Int_interfaceT findbyIdInt_interface(Int_interfaceT int_interfaceT) throws Exception {
        try {
            IInt_interfaceDAO int_interfaceDAO = getInt_interfaceDAO();

            List<Int_interfaceT> listTemp = int_interfaceDAO.getByPK(int_interfaceT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }

    public Pi_per_intT findbyIdPi_per_int(Pi_per_intT pi_per_intT) throws Exception {
        try {
            IPi_per_intDAO pi_per_intDAO = getPi_per_intDAO();

            List<Pi_per_intT> listTemp = pi_per_intDAO.getByPK(pi_per_intT);

            return listTemp.size() > 0 ? listTemp.get(0) : null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }

    public String getRedirect() {

        return redirect;
    }

//    public void setMsg(String msg) {
//        super.setMsg(msg);
//            request.setAttribute("msg", msg);
//    }
//
//    public void setMsg(String tipoMsg, String msg) {
//        super.setMsg(tipoMsg, msg);
//            request.setAttribute("tipoMsg", tipoMsg);
//            request.setAttribute("msg", msg);
//    }
    public void setRedirect(String redirect) {

        this.redirect = redirect;
        request.setAttribute("redirect", redirect);
        if (redirect != null) {
            session.setAttribute(REDIRECT_SESSION, redirect);
        }
    }

    public Par_parametroT findbyIdPar_parametro(Par_parametroT par_parametroT) throws Exception {
        try {
            IPar_parametroDAO par_parametroDAO = getPar_parametroDAO();
            par_parametroT = par_parametroDAO.getByPK(par_parametroT);
            return par_parametroT;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            close();
        }
        return null;
    }

    public int getTypeDatabase() {
        return typeDatabase;
    }

    public void setTypeDatabase(int typeDatabase) {
        this.typeDatabase = typeDatabase;
    }
//
//    public String getDatasourceName() {
//        return datasourceName;
//    }
//
//    public void setDatasourceName(String datasourceName) {
//        this.datasourceName = datasourceName;
//    }
}
