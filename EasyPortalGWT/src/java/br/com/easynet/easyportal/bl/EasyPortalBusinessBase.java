/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.easyportal.bl;

import br.com.easynet.bl.BusinessBase;
import br.com.easynet.database.ConnectionRPL;
import br.com.easynet.easylog.dao.ILogDAO;
import br.com.easynet.easylog.dao.LogDAO;
import br.com.easynet.easylog.dao.LogDAO_Mysql;
import br.com.easynet.easylog.dao.LogDAO_Oracle;
import br.com.easynet.easylog.transfer.LogT;
import br.com.easynet.easyportal.cluster.ConfigI9Cluster;
import br.com.easynet.easyportal.dao.*;
import br.com.easynet.easyportal.jb.SystemBase;
import static br.com.easynet.easyportal.jb.SystemBase.MAP_SINALIZADOR;
import br.com.easynet.easyportal.menu.dao.IMen_menuDAO;
import br.com.easynet.easyportal.menu.dao.IMep_men_perDAO;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO_Mysql;
import br.com.easynet.easyportal.menu.dao.Men_menuDAO_Oracle;
import br.com.easynet.easyportal.menu.dao.Mep_men_perDAO;
import br.com.easynet.easyportal.menu.dao.Mep_men_perDAO_Mysql;
import br.com.easynet.easyportal.menu.dao.Mep_men_perDAO_Oracle;
import br.com.easynet.easyportal.transfer.*;
import br.com.jdragon.dao.DAOFactory;
import br.com.jdragon.dao.cluster.DAOFactoryCluster;
import br.com.jdragon.dao.cluster.DefinitionConnectionCluster;
import br.com.jdragon.dao.cluster.I9Cluster;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author geoleite
 */
public class EasyPortalBusinessBase extends BusinessBase {

    public static String PATH_LOG_CLUSTER = "/Users/geoleite/pathLog";//"/home/geoleite/pathLog";
    private static I9Cluster i9Cluster = null;
    private int typeDatabase = DAOFactory.POSTGRESQL;
    private DAOFactoryCluster dao;
    private String datasourceName = "java:comp/env/jdbc/easyportalds";
    public static Hashtable<String, String> MAP_IMEI_IP = new Hashtable<String, String>();
    private static Map<String, Par_parametroT> parametroBDPortal = new HashMap<String, Par_parametroT>();
    private static Map<String, Sis_sistemaT> sistemas = new HashMap<String, Sis_sistemaT>();
    private static String sinal_sistemas = "sinal_sistemas";
    private static String sinal_parametros = "sinal_parametros";
    public static String EASYPORTAL_SYSTEM = "EASYPORTAL";

    /**
     * Limpa a o hastable com os parametros
     */
    public void clearParametros() {
        //parametroBDPortal.clear();
        //sistemas.clear();
    }

    protected void registroLog(String sistema,
            String classe, String method, String usuario, String status) {
        try {
            Par_parametroT parT = getParametro(EASYPORTAL_SYSTEM, "REGISTRAR_LOG");
            String registrarLog = parT != null ? parT.getPar_tx_valor() : "true";
            boolean habilitarLog = true;
            if (registrarLog != null) {
                habilitarLog = Boolean.parseBoolean(registrarLog);
            }
            if (habilitarLog) {
                //EasySecurityJB esjb = new EasySecurityJB();
                //esjb.setApplication(application);
                LogT logT = new LogT();
                logT.setLog_tx_classe(classe);
                logT.setLog_tx_metodo("");
                logT.setLog_tx_usuario(usuario);
                logT.setLog_tx_sistema(sistema);
                logT.setLog_tx_ip("");
                logT.setLog_tx_status(status);
                //logT.setLog_tx_parametro(readRequest());
                logT.setLog_nr_id(getAutoIncrementoLong("EasyPortal", "Log", "log_nr_id"));
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                logT.setLog_dt_datahora(ts);
                getLogDAO().insert(logT);
            }
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        } finally {
            try {
                close();
            } catch (Exception e) {
            }
        }
    }

    public DAOFactoryCluster getDAOEasyPortal() throws Exception {
        /*
         if (dao == null) {
         if (i9Cluster == null) {
         String path = ConfigI9Cluster.REAL_PATH_BASE + SystemBase.fileI9Cluster;
         i9Cluster = I9Cluster.getI9Cluster(path);
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
     *
     * public ConnectionRPL getConnectionRpl(String nameConnection, String
     * datasourceName) { try { ConnectionRPL conRPL = new ConnectionRPL();
     * conRPL.setPathLog(PATH_LOG_CLUSTER); conRPL.setNome(nameConnection);
     * conRPL.setCon(getDAODatasource(datasourceName)); conRPL.recoverLog();
     * return conRPL; } catch (Exception e) { easyLogger.error(e.getMessage(),
     * e); } return null; }
     *
     * /**
     *
     * @param dataSourceName
     * @return
     * @throws Exception
     *
     * public Connection getDAODatasource(String dataSourceName) throws
     * Exception {
     *
     * DataSource ds = getDataSource(dataSourceName); return ds.getConnection();
     * }
     */
//
//    public DAOFactory getDAODatasourceEasyPortal() throws Exception {
//        if (dao != null && !dao.getConnection().isClosed()) {
//            return dao;
//        }
//
//        try {
//            String typeDatabaseParam = super.getType_database();
//            if (typeDatabaseParam != null) {
//                if ("ORACLE".equalsIgnoreCase(typeDatabaseParam)) {
//                    setTypeDatabase(DAOFactory.ORACLE);
//                } else if ("Postgresql".equalsIgnoreCase(typeDatabaseParam)) {
//                    setTypeDatabase(DAOFactory.POSTGRESQL);
//                } else if ("SqlServer".equalsIgnoreCase(typeDatabaseParam)) {
//                    setTypeDatabase(DAOFactory.SQLSERVER);
//                } else if ("SqlServer2005".equalsIgnoreCase(typeDatabaseParam)) {
//                    setTypeDatabase(DAOFactory.SQLSERVER2005);
//                } else if ("MySql".equalsIgnoreCase(typeDatabaseParam)) {
//                    setTypeDatabase(DAOFactory.MYSQL);
//                }
//            }
//        } catch (Exception e) {
//        }
//
//        DataSource ds = getDataSource(getDatasourceName());
//        dao = DAOFactory.getDAOFactory(getTypeDatabase(), ds.getConnection());
//        return dao;
////        return getDAOEasyPortal();
//    }
//
//    public DAOFactory getDAOEasyPortal() throws Exception {
//        return getDAODatasourceEasyPortal();
//    }
//
//    public DAOFactory getDAO() throws Exception {
//
//        if (dao != null && !dao.getConnection().isClosed()) {
//            return dao;
//        }
//
//        return getDAODatasourceEasyPortal();
//    }
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
        if (!br.com.easynet.portal.jb.SystemBase.MAP_SINALIZADOR.containsKey(key)) {
            br.com.easynet.portal.jb.SystemBase.MAP_SINALIZADOR.put(key, key);
        }
        String sinalizador = br.com.easynet.portal.jb.SystemBase.MAP_SINALIZADOR.get(key);
        //Sincroniza utilizando o sistema+tabela+campo
        synchronized (sinalizador) {
            Par_parametroT parT = getParametroBD(sistema, key);
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
        return getAutoIncremento(br.com.easynet.portal.jb.SystemBase.SISTEMA_EASYPORTAL, "USU_USUARIO", "USU_NR_ID");
    }

    public int getIncSistema() throws Exception {
        return getAutoIncremento(br.com.easynet.portal.jb.SystemBase.SISTEMA_EASYPORTAL, "SIS_SISTEMA", "SIS_NR_ID");
    }

    public int getIncMenu() throws Exception {
        return getAutoIncremento(br.com.easynet.portal.jb.SystemBase.SISTEMA_EASYPORTAL, "MEN_MENU", "MEN_NR_ID");
    }

    public int getIncPerfil() throws Exception {
        return getAutoIncremento(br.com.easynet.portal.jb.SystemBase.SISTEMA_EASYPORTAL, "PER_PERFIL", "PER_NR_ID");
    }

    public int getIncMetodo() throws Exception {
        return getAutoIncremento(br.com.easynet.portal.jb.SystemBase.SISTEMA_EASYPORTAL, "MET_METODO", "MET_NR_ID");
    }

    public int getIncOperacao() throws Exception {
        return getAutoIncremento(br.com.easynet.portal.jb.SystemBase.SISTEMA_EASYPORTAL, "OPE_OPERACAO", "OPE_NR_ID");
    }

    public int getIncInterface() throws Exception {
        return getAutoIncremento(br.com.easynet.portal.jb.SystemBase.SISTEMA_EASYPORTAL, "INT_INTERFACE", "INT_NR_ID");
    }

    public int getIncComponente() throws Exception {
        return getAutoIncremento(br.com.easynet.portal.jb.SystemBase.SISTEMA_EASYPORTAL, "CON_COMPONENTE_NEGADO", "CON_NR_ID");
    }

    public void closeDatasourceEasyPortal() {
        super.close();
        try {
            if (dao != null) {
                dao.close();
            }
            //i9Cluster = null;
            dao = null;
        } catch (Exception e) {
            easyLogger.error(e.getMessage(), e);
        }
    }

    public void close() {
        closeDatasourceEasyPortal();
    }

    /**
     * Obtem o parametro pela id do sistema e nome do parametro
     *
     * @param sisNrId
     * @param nome
     * @return
     */
    public Par_parametroT getParametro(int sisNrId, String nome) throws Exception {
//        Par_parametroT parT = new Par_parametroT();
//        parT.setSis_nr_id(sisNrId);
//        parT.setPar_tx_nome(nome);
//        return getPar_parametroDAO().getByPK(parT);              
        Par_parametroT parT = null;
        //Primeiro verifica se já existe o objeto na memória
        String nomeParam = sisNrId + "_" + nome;
        if (parametroBDPortal.containsKey(nomeParam)) {
            parT = parametroBDPortal.get(nomeParam);
        } else {
            //Carrega todos os parametros da base
            synchronized (sinal_parametros) {
                List<Par_parametroT> listPar = getPar_parametroDAO().getAll();
                for (Par_parametroT par_parametroT : listPar) {
                    String keyParam = par_parametroT.getSis_nr_id() + "_" + par_parametroT.getPar_tx_nome();
                    parametroBDPortal.put(keyParam, par_parametroT);
                }
            }
            parT = parametroBDPortal.get(nomeParam);
//            //Nao existe este parametro no objeto em memoria
//            parT = new Par_parametroT();
//            parT.setSis_nr_id(sisNrId);
//            parT.setPar_tx_nome(nome);
//            parT = getPar_parametroDAO().getByPK(parT);
//            if (parT != null) {
//                parametroBDPortal.put(nomeParam, parT);
//            }
        }
        //Par_parametroT parT = super.getParametro(NOME_SISTEMA, nomeParam);
        return parT;

    }

    /**
     * Busca o parametro no banco
     * @param sisNrId
     * @param nome
     * @return
     * @throws Exception 
     */
    public Par_parametroT getParametroBD(int sisNrId, String nome) throws Exception {
        Par_parametroT parT = new Par_parametroT();
        parT.setSis_nr_id(sisNrId);
        parT.setPar_tx_nome(nome);
        //Primeiro verifica se já existe o objeto na memória
        //Carrega todos os parametros da base
        synchronized (sinal_parametros) {
            parT = getPar_parametroDAO().getByPK(parT);
        }

        //Par_parametroT parT = super.getParametro(NOME_SISTEMA, nomeParam);
        return parT;
    }

    /**
     * Pesquisa um parametroo pelo nome do sistema e nome do parametro
     *
     * @param nomeSistema
     * @param nomeParametro
     * @return
     * @throws Exception
     */
    public Par_parametroT getParametro(String nomeSistema, String nomeParametro) throws Exception {
        Sis_sistemaT sisT = null;
        //easyLogger.info("Sistemas map: " + sistemas);
        if (sistemas.containsKey(nomeSistema)) {
            sisT = sistemas.get(nomeSistema);
//            easyLogger.info("Achou sistema no map: " + sisT);

        } else {
            //Carrega todos os sistemas
            synchronized (sinal_sistemas) {
                List<Sis_sistemaT> listSis = getSis_sistemaDAO().getAll();
                for (Sis_sistemaT sis_sistemaT : listSis) {
                    sistemas.put(sis_sistemaT.getSis_tx_nome(), sis_sistemaT);
                }
            }
            sisT = sistemas.get(nomeSistema);
//            sisT = new Sis_sistemaT();
//            sisT.setSis_tx_nome(nomeSistema);
//            sisT = getSis_sistemaDAO().getBySis_tx_nome(sisT);
//            sistemas.put(nomeSistema, sisT);
        }

        if (sisT == null) {
            return null;
        }
        return getParametro(sisT.getSis_nr_id(), nomeParametro);
    }

    public Par_parametroT getParametroBD(String nomeSistema, String nomeParametro) throws Exception {
        Sis_sistemaT sisT = null;
        //easyLogger.info("Sistemas map: " + sistemas);
        if (sistemas.containsKey(nomeSistema)) {
            sisT = sistemas.get(nomeSistema);
        } else {
            //Carrega todos os sistemas
            synchronized (sinal_sistemas) {
                List<Sis_sistemaT> listSis = getSis_sistemaDAO().getAll();
                for (Sis_sistemaT sis_sistemaT : listSis) {
                    sistemas.put(sis_sistemaT.getSis_tx_nome(), sis_sistemaT);
                }
            }
            sisT = sistemas.get(nomeSistema);
//            sisT = new Sis_sistemaT();
//            sisT.setSis_tx_nome(nomeSistema);
//            sisT = getSis_sistemaDAO().getBySis_tx_nome(sisT);
//            sistemas.put(nomeSistema, sisT);
        }

        if (sisT == null) {
            return null;
        }
        return getParametroBD(sisT.getSis_nr_id(), nomeParametro);
    }

    public IOp_ope_perDAO getOp_ope_perDAO() throws Exception {
        ////dao = getDAOEasyPortal();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Op_ope_perDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Op_ope_perDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Op_ope_perDAO_Mysql(null);
        }
        return null;
    }

    public IOpe_operacaoDAO getOpe_operacaoDAO() throws Exception {
        ////dao = getDAOEasyPortal();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Ope_operacaoDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Ope_operacaoDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Ope_operacaoDAO_Mysql(null);
        }
        return null;
    }

    public IPer_perfilDAO getPer_perfilDAO() throws Exception {
        ////dao = getDAOEasyPortal();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Per_perfilDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Per_perfilDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Per_perfilDAO_Mysql(null);
        }
        return null;
    }

    public IPu_per_usuDAO getPu_per_usuDAO() throws Exception {
        ////dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Pu_per_usuDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Pu_per_usuDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Pu_per_usuDAO_Mysql(null);
        }
        return null;
    }

    public ISis_sistemaDAO getSis_sistemaDAO() throws Exception {
        ////dao = getDAOEasyPortal();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Sis_sistemaDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Sis_sistemaDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Sis_sistemaDAO_Mysql(null);
        }
        return null;
    }

    public IUsu_usuarioDAO getUsu_usuarioDAO() throws Exception {
        ////dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Usu_usuarioDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Usu_usuarioDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Usu_usuarioDAO_Mysql(null);
        }
        return null;
    }

    public IMet_metodoDAO getMet_metodoDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Met_metodoDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Met_metodoDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Met_metodoDAO_Mysql(null);
        }
        return null;
    }

    public IMen_menuDAO getMen_menuDAO() throws Exception {
        //dao = getDAOEasyPortal();
        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Men_menuDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Men_menuDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Men_menuDAO_Mysql(null);
        }
        return null;
    }

    public IMep_men_perDAO getMep_men_perDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Mep_men_perDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Mep_men_perDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Mep_men_perDAO_Mysql(null);
        }
        return null;
    }

    public ILogDAO getLogDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new LogDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new LogDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new LogDAO_Mysql(null);
        }
        return null;
    }

    public IRes_recall_senhaDAO getRes_recall_senhaDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Res_recall_senhaDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Usu_usuarioDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Usu_usuarioDAO_Mysql(null);
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

    public ICon_componente_negadoDAO getCon_componente_negadoDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Con_componente_negadoDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Con_componente_negadoDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Con_componente_negadoDAO_Mysql(null);
        }
        return null;
    }

    public IInt_interfaceDAO getInt_interfaceDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Int_interfaceDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Int_interfaceDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Int_interfaceDAO_Mysql(null);
        }
        return null;
    }

    public IPi_per_intDAO getPi_per_intDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Pi_per_intDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Pi_per_intDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Pi_per_intDAO_Mysql(null);
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

    public IPar_parametroDAO getPar_parametroDAO() throws Exception {
        //dao = getDAOEasyPortal();

        if (getTypeDatabase() == DAOFactory.POSTGRESQL) {
            return new Par_parametroDAO(null);
        } else if (getTypeDatabase() == DAOFactory.ORACLE) {
            //return new Par_parametroDAO_Oracle(null);
        } else if (getTypeDatabase() == DAOFactory.MYSQL) {
            //return new Par_parametroDAO_Mysql(null);
        }
        return null;
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

    /**
     * @return the datasourceName
     */
    public String getDatasourceName() {
        return datasourceName;
    }

    /**
     * @param datasourceName the datasourceName to set
     */
    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }

    /**
     * @return the typeDatabase
     */
    public int getTypeDatabase() {
        return typeDatabase;
    }

    /**
     * @param typeDatabase the typeDatabase to set
     */
    public void setTypeDatabase(int typeDatabase) {
        this.typeDatabase = typeDatabase;
    }
}
